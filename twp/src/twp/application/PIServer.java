package twp.application;

import java.io.IOException;

import twp.generated.CalculatorServer;
import twp.generated.Double;

public class PIServer extends CalculatorServerHandler {

	public PIServer() {
		super("www.dcl.hpi.uni-potsdam.de", 80);
	}

	public static void main(String[] args) {
		int port = 12351;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		try {
			CalculatorServer server = new CalculatorServer(port);
			server.listen(new PIServer());
			System.out.println("PIServer started...");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	protected String getOperationForLog(Double... values) {
		return "Constant PI";
	}

	@Override
	protected String getOperationName() {
		return "WLink - PI";
	}

	@Override
	protected int getParamCount() {
		return 0;
	}

	@Override
	protected Double runCalculation(Double... values) {
		return new Double(Math.PI);
	}

}
