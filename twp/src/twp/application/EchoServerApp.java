package twp.application;

import java.io.IOException;

import twp.generated.EchoHandler;
import twp.generated.EchoProtocol;
import twp.generated.EchoReply;
import twp.generated.EchoRequest;
import twp.generated.EchoServer;

public class EchoServerApp implements EchoHandler {
		
	private EchoServer server;
	
	public EchoServerApp(int port) {
		try {
			server = new EchoServer(port);
			server.listen(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Starting Server...");
		new EchoServerApp(12347);
		System.out.println("Server is running...");
	}

	
	@Override
	public void onEchoReply(EchoReply reply) {
		// do nothing
	}

	@Override
	public void onEchoRequest(EchoRequest request) {
		EchoProtocol prot = request.getProtocol();
		String text = request.getText().replaceAll("\\s", "");
		try {
			prot.sendReply(request.getText(), text.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
