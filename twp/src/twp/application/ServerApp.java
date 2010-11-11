package twp.application;

import java.io.IOException;

import twp.generated.EchoRequest;
import twp.generated.EchoReply;
import twp.generated.Protocol;
import twp.generated.EchoHandler;

public class ServerApp implements EchoHandler {
	private Protocol protocol;
	
	public ServerApp(int port) {
		try {
			protocol = Protocol.startServer(port, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Starting Server...");
		new ServerApp(12347);
		
	}

	
	@Override
	public void onEchoReply(EchoReply reply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEchoRequest(EchoRequest request) {
		System.out.println("Received echo request: " + request.getText() + " - " + request.getText().length());
		try {
			protocol.echoReply(request.getText(), request.getText().length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
