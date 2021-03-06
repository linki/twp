package twp.application;

import java.io.IOException;

import twp.generated.CalculatorServer;
import twp.generated.Double;

public class MultiplyServer extends CalculatorServerHandler {

	public MultiplyServer() {
		super("www.dcl.hpi.uni-potsdam.de", 80);
	}

	public static void main(String[] args) {
		int port = 12348;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		try {
			CalculatorServer server = new CalculatorServer(port);
			server.listen(new MultiplyServer());
			System.out.println("MultiplyServer started...");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	protected int getParamCount() {
		return 2;
	}

	@Override
	protected Double runCalculation(Double... values) {
		double result = 1.0;
		for (Double value:values) 
			result *= value.getValue();
		return new Double(result);
	}

	@Override
	protected String getOperationName() {
		return "WLink - Multiply";
	}

	@Override
	protected String getOperationForLog(Double... values) {
		return "Calculating " + String.valueOf(values[0].getValue()) + " * " + String.valueOf(values[1].getValue());
	}

}
