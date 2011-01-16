package twp.application;

import java.io.IOException;

import twp.generated.CalculatorServer;
import twp.generated.Double;

public class SinusServer extends CalculatorServerHandler {

	public SinusServer() {
		super("www.dcl.hpi.uni-potsdam.de", 80);
	}

	public static void main(String[] args) {
		int port = 12350;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		try {
			CalculatorServer server = new CalculatorServer(port);
			server.listen(new SinusServer());
			System.out.println("SinusServer started...");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	protected String getOperationForLog(Double... values) {
		return "Calculating sin(" + values[0].getValue() + ")";
	}

	@Override
	protected String getOperationName() {
		return "WLink - Sinus";
	}

	@Override
	protected int getParamCount() {
		return 1;
	}

	@Override
	protected Double runCalculation(Double... values) {
		return new Double(Math.sin(values[0].getValue()));
	}

}
