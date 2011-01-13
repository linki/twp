package twp.application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import twp.generated.CalculatorError;
import twp.generated.CalculatorHandler;
import twp.generated.CalculatorProtocol;
import twp.generated.CalculatorReply;
import twp.generated.CalculatorRequest;
import twp.generated.Double;
import twp.generated.Expression;
import twp.generated.Parameters;
import twp.generated.Term;

abstract public class CalculatorServerHandler implements CalculatorHandler {

	private static int jobId = 0;
	
	private class Job {
		public CalculatorRequest request;
		public Double[] values;
		public int max;
		public Job(CalculatorRequest req, int max) {
			request = req; this.max = max;
			values = new Double[max];
		}
	}
	
	// our job center, requests are stored here until all intermediate results are resolved 
	private HashMap<Integer, Job> jobs = new HashMap<Integer, Job>(); 
	
	abstract protected int getParamCount();
	
	@Override
	public void onCalculatorError(CalculatorError message) {
		System.err.println(message.getText());
	}

	@Override
	public void onCalculatorReply(CalculatorReply message) {
		int[] id = decomposeId(message.getRequestId());
		Job job = updateJob(id[0], id[1], message.getResult());
		if (job != null) {
			// all intermediate results were returned
			// so finish the job
			Double result = runCalculation(job.values);
			try {
				job.request.getProtocol().sendReply(job.request.getRequestId(), result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void onCalculatorRequest(CalculatorRequest message) {
		if (getParamCount() == 0) {
			// in case we implement a constant
			Double result = runCalculation(new Double[0]);
			try {
				message.getProtocol().sendReply(message.getRequestId(), result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (message.getArguments().getElements().size() != getParamCount())
			try {
				message.getProtocol().sendError("Wrong number of parameters (" + getParamCount() + " were expected).");
			} catch (IOException e) {
				e.printStackTrace();
			}
		else {
			// correct number of parameters
			Job job = new Job(message, getParamCount());
			int i = 0;
			HashMap<Integer, Expression> resolve = new HashMap<Integer, Expression>();
			for (Term term:message.getArguments().getElements()) {
				// check if all given parameters are already Doubles or 
				// if we have to contact some other calculation service
				if (term.getContentClass() == Double.class)
					job.values[i] = term.getValue();
				else 
					resolve.put(i, term.getExpr());
				i++;
			}
			if (resolve.isEmpty()) {
				// nothing to resolve, just calculate the result
				Double result = runCalculation(job.values);
				try {
					message.getProtocol().sendReply(message.getRequestId(), result);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				// store the job and send the requests to the other services
				int id = addJob(job);
				for (int key:resolve.keySet()) {
					Expression expr = resolve.get(key);
					sendRequest(expr.getHost(), expr.getPort(), expr.getArguments(), composeId(id, key));
				}
			}
				
		}
	}

	private void sendRequest(byte[] host, int port, Parameters args, int reqId) {
		try {
			String ip = InetAddress.getByAddress(host).getHostAddress();
			CalculatorProtocol prot = new CalculatorProtocol(ip, port, this);
			prot.sendRequest(reqId, args);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int composeId(int reqId, int index) {
		return reqId * 10 + index;
	}
	
	private int[] decomposeId(int combined) {
		int[] result = new int[2];
		result[0] = combined / 10;
		result[1] = combined % 10;
		return result;
	}
	
	abstract protected Double runCalculation(Double...values);
	
	private synchronized Job updateJob(int reqId, int index, Double value) {
		if (jobs.containsKey(reqId)) {
			// update the job
			Job current = jobs.get(reqId);
			current.values[index] = value;
			for (int i=0; i < current.max; i++)
				if (current.values[i] == null)
					return null;
			// if all values are returned finish the job
			jobs.remove(reqId);
			return current;
		}
		return null;
	}
	
	private synchronized int addJob(Job job) {
		int id = jobId++;
		jobs.put(id, job);
		return id; 
	}
	
}
