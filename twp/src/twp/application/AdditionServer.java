package twp.application;

import java.io.IOException;

import twp.generated.CalculatorServer;
import twp.generated.Double;

public class AdditionServer extends CalculatorServerHandler {
	public static void main(String[] args) {
		int port = 80;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		try {
			CalculatorServer server = new CalculatorServer(port);
			server.listen(new AdditionServer());
			System.out.println("AdditionServer started...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected int getParamCount() {
		return 2;
	}

	@Override
	protected Double runCalculation(Double... values)  {
		double result = 0.0;
		for (Double value:values) {
			result += value.getValue();
		}
		return new Double(result);
	}
}
