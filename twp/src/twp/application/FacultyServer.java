package twp.application;

import java.io.IOException;

import twp.generated.CalculatorServer;
import twp.generated.Double;

public class FacultyServer extends CalculatorServerHandler {

	public FacultyServer() {
		super("www.dcl.hpi.uni-potsdam.de", 80);
	}

	public static void main(String[] args) {
		int port = 12349;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		try {
			CalculatorServer server = new CalculatorServer(port);
			server.listen(new FacultyServer());
			System.out.println("FacultyServer started...");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	protected int getParamCount() {
		return 1;
	}

	@Override
	protected Double runCalculation(Double... values) {
		double value = values[0].getValue();
		int x = (int) Math.ceil(value);
		int result = 1;
		for (int i = 1; i <= x; i++) 
			result *= i;
		return new Double(result);
	}

	@Override
	protected String getOperationName() {
		return "WLink - Faculty";
	}

	@Override
	protected String getOperationForLog(Double... values) {
		return "Calculating " + String.valueOf(Math.ceil(values[0].getValue())) + "!";
	}

}
