package twp.application;

import java.io.IOException;
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

public class AdditionServerHandler implements CalculatorHandler {

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
	
	private HashMap<Integer, Job> jobs = new HashMap<Integer, Job>(); 
	
	protected int getParamCount() {
		return 2;
	}
	
	
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
		if (message.getArguments().getElements().size() != getParamCount())
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
	
	private String convertIP(byte[] host) {
		// I'm too lazy to change the underlying code
		// so the byte[] is converted to a readable IP String
		StringBuffer ip = new StringBuffer();
		if (host.length == 4) // IPv4
			for (int i = 0; i < 4; i++) {
				ip.append(new Byte(host[i]).intValue());
				if (i < 3) ip.append(".");
			}	
		else if (host.length == 16) // IPv6
			for (int i = 0; i < 8; i++) {
				byte[] temp = new byte[2];
				temp[0] = host[i];
				temp[1] = host[i+1];
				ip.append(Integer.toHexString(Integer.parseInt(new String(temp))));
				if (i < 7) ip.append(":");
			}
		return ip.toString();
	}

	private void sendRequest(byte[] host, int port, Parameters args, int reqId) {
		try {
			String ip = convertIP(host);
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
	
	private Double runCalculation(Double...values) {
		double result = 0.0;
		for (Double value:values) {
			result += value.getValue();
		}
		return new Double(result);
	}
	
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
