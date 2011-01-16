package twp.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import twp.generated.CalculatorServer;

public class CalculatorServerManager {
	
	public static void main(String[] args) {
		List<CalculatorServer> servers = new ArrayList<CalculatorServer>();
		try {
			CalculatorServer server = new CalculatorServer(12347);
			server.listen(new AdditionServer());
			servers.add(server);
			server = new CalculatorServer(12348);
			server.listen(new MultiplyServer());
			servers.add(server);
			server = new CalculatorServer(12349);
			server.listen(new FacultyServer());
			servers.add(server);
			server = new CalculatorServer(12350);
			server.listen(new SinusServer());
			servers.add(server);
			server = new CalculatorServer(12351);
			server.listen(new PIServer());
			servers.add(server);
			server = new CalculatorServer(12352);
			server.listen(new DivisionServer());
			servers.add(server);
			server = new CalculatorServer(12353);
			server.listen(new SubtractionServer());
			servers.add(server);
			System.out.println("Started servers");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (CalculatorServer server:servers) {
			server.stop();
		}
		System.out.println("Stopped servers");
	}
}
