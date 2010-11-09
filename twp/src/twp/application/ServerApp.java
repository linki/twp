package twp.application;

import java.io.IOException;

import twp.generated.EchoRequest;
import twp.generated.EchoResponse;
import twp.generated.Protocol;
import twp.generated.RequestHandler;

public class ServerApp implements RequestHandler {

	@Override
	public EchoResponse onEcho(EchoRequest request) {
		System.out.println("Received echo request: " + request.getText() + " - " + request.getText().length());
		return new EchoResponse(request.getText(), request.getText().length());
	}
	
	public static void main(String[] args) {
		ServerApp app = new ServerApp();
		try {
			System.out.println("Starting Server...");
			Protocol protocol = Protocol.startServer(12347, app);
			System.out.println("Server shutdown");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
