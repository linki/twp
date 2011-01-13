package twp.application;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import twp.generated.CalculatorProtocol;
import twp.generated.CalculatorReply;
import twp.generated.Double;
import twp.generated.Expression;
import twp.generated.LoggingProtocol;
import twp.generated.Parameters;
import twp.generated.Term;

public class CalculatorClient {
	CalculatorProtocol protocol = null;
	LoggingProtocol logger = null;
	
	private static int regId = 0;
	
	public static synchronized int getRegId() {
		return regId++;
	}
	
	public CalculatorClient() {
		try {
			//protocol = new CalculatorProtocol("www.dcl.hpi.uni-potsdam.de", 80);
			protocol = new CalculatorProtocol("localhost", 12347);
			//logger = new LoggingProtocol("www.dcl.hpi.uni-potsdam.de", 80);
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
			Parameters params = buildDemo();// new Parameters();
			//params.add(new Term(new Double(1)));
			//params.add(new Term(new Double(2)));
			try {
				protocol.sendRequest(getRegId(), params);
				CalculatorReply response = protocol.receiveReply();
			//	Date date = new Date();
			//	logger.sendLogEntry(((int) date.getTime() / 1000), ((int) date.getTime() * 1000), "Calculator", String.valueOf(response.getRequestId()), "This is a test!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Parameters buildDemo() {
		// 1 + sin(1)
		Parameters params = new Parameters();
		params.add(new Term(new Double(1)));
		Parameters params2 = new Parameters();
		params2.add(new Term(new Double(1)));
		byte[] ip = new byte[]{(byte) 141, (byte) 89, (byte) 224, (byte) 164};
		params.add(new Term(new Expression(Arrays.asList(ip, 80, params2))));
		return params;
	}
	
	public static void main(String[] args) {
		CalculatorClient client = new CalculatorClient();
		client.run();
	}
}
