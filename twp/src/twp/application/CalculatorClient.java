package twp.application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import twp.core.Container;
import twp.generated.CalculatorProtocol;
import twp.generated.CalculatorReply;
import twp.generated.Double;
import twp.generated.Expression;
import twp.generated.LoggingProtocol;
import twp.generated.Parameters;
import twp.generated.Term;
import twp.generated.ThreadID;

public class CalculatorClient {
	CalculatorProtocol protocol = null;
	LoggingProtocol logger = null;
	
	private static int regId = 0;
	
	public static synchronized int getRegId() {
		return regId++;
	}
	
	public CalculatorClient() {
		
	}
	
	public void run() {
		System.out.println("Starting calculator...");
		/*if (protocol != null) {
			Parameters params = buildDemo();
			try {
				protocol.sendRequest(getRegId(), params, createThreadExtension());
				CalculatorReply response = protocol.receiveReply();
				System.out.println(response.getResult().getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		
	}
	
	private List<Container> createThreadExtension() {
		ArrayList<Container> exts = new ArrayList<Container>();
		exts.add(new ThreadID(protocol.getLocalAddress(), protocol.getLocalPort()));
		return exts;
	}
	
	private Parameters buildDemo() {
		// (6!+3)*4
		Parameters paramsMulti = new Parameters();
		Parameters paramsAdd = new Parameters();
		Parameters paramsFac = new Parameters();

		paramsFac.add(new Term(new Double(6)));
		try {
			InetAddress addr = InetAddress.getByName("localhost");
			paramsAdd.add(new Term(new Expression(addr.getAddress(), 12350, paramsFac)));
			paramsAdd.add(new Term(new Double(3)));
			paramsMulti.add(new Term(new Expression(addr.getAddress(), 12347, paramsAdd)));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}	
		paramsMulti.add(new Term(new Double(4)));
		
		return paramsMulti;
	}
	
	public static void main(String[] args) {
		CalculatorClient client = new CalculatorClient();
		client.run();
	}
}
