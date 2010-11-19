package twp.application;

import java.io.IOException;

import twp.generated.EchoHandler;
import twp.generated.EchoProtocol;
import twp.generated.EchoServer;
import twpx.core.Message;
import twpx.protocol.echo.EchoReply;
import twpx.protocol.echo.EchoRequest;

public class ServerApp implements EchoHandler {
		
	private EchoServer server;
	
	public ServerApp(int port) {
		try {
			server = new EchoServer(port);
			server.listen(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Starting Server...");
		new ServerApp(12347);
		System.out.println("Server is running...");
	}

	
	@Override
	public void onEchoReply(EchoReply reply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEchoRequest(EchoRequest request) {
//		EchoProtocol prot = request.getProtocol();
//		try {
//			prot.echoReply(request.getText(), request.getText().length());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public Message handle(Message message) {
		// TODO Auto-generated method stub
		return null;
	}
}
