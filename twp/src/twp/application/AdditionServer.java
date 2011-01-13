package twp.application;

import java.io.IOException;

import twp.generated.CalculatorServer;

public class AdditionServer {
	public static void main(String[] args) {
		try {
			CalculatorServer server = new CalculatorServer(12347);
			server.listen(new AdditionServerHandler());
			System.out.println("CalculationServer started...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
