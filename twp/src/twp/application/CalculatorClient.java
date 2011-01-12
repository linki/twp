package twp.application;

import java.io.IOException;
import java.net.UnknownHostException;

import twp.generated.CalculatorProtocol;
import twp.generated.CalculatorReply;
import twp.generated.Double;
import twp.generated.Parameters;
import twp.generated.Term;

public class CalculatorClient {
	CalculatorProtocol protocol = null;
	
	private static int regId = 0;
	
	public static synchronized int getRegId() {
		return regId++;
	}
	
	public CalculatorClient() {
		try {
			protocol = new CalculatorProtocol("www.dcl.hpi.uni-potsdam.de", 80);
			System.out.println("Connection established");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("Starting calculator...");
		if (protocol != null) {
			Parameters params = new Parameters();
			params.add(new Term(new Double(1)));
			//params.add(new Term(new Double(2)));
			try {
				protocol.sendRequest(getRegId(), params);
				CalculatorReply response = protocol.receiveReply();
				System.out.println(response.getResult().getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		CalculatorClient client = new CalculatorClient();
		client.run();
	}
}
