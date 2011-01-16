package twp.application;

import java.io.IOException;

import twp.generated.CalculatorServer;
import twp.generated.Double;

public class DivisionServer extends CalculatorServerHandler {

	public DivisionServer() {
		super("www.dcl.hpi.uni-potsdam.de", 80);
	}
	
	public static void main(String[] args) {
		int port = 12352;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		try {
			CalculatorServer server = new CalculatorServer(port);
			server.listen(new DivisionServer());
			System.out.println("DivisionServer started...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected String getOperationForLog(Double... values) {
		return "Calculating " + String.valueOf(values[0].getValue()) + " / " + String.valueOf(values[1].getValue());
	}

	@Override
	protected String getOperationName() {
		return "WLink - Division";
	}

	@Override
	protected int getParamCount() {
		return 2;
	}

	@Override
	protected Double runCalculation(Double... values) throws Exception {
		if (values[1].getValue() == 0.0) 
			throw new Exception("Division by 0 is not allowed.");
		double result = values[0].getValue() / values[1].getValue();
		return new Double(result);
	}

}
