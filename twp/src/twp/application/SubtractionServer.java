package twp.application;

import java.io.IOException;

import twp.generated.CalculatorServer;
import twp.generated.Double;

public class SubtractionServer extends CalculatorServerHandler {

	public SubtractionServer() {
		super("www.dcl.hpi.uni-potsdam.de", 80);
	}

	public static void main(String[] args) {
		int port = 12353;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		try {
			CalculatorServer server = new CalculatorServer(port);
			server.listen(new SubtractionServer());
			System.out.println("SubtractionServer started...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected String getOperationForLog(Double... values) {
		return "Calculating " + String.valueOf(values[0].getValue()) + " - " + String.valueOf(values[1].getValue());
	}

	@Override
	protected String getOperationName() {
		return "WLink - Subtraction";
	}

	@Override
	protected int getParamCount() {
		return 2;
	}

	@Override
	protected Double runCalculation(Double... values) throws Exception {
		double result = values[0].getValue() - values[1].getValue();
		return new Double(result);
	}

}
