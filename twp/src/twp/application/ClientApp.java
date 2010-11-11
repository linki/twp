package twp.application;
import java.io.IOException;
import java.net.UnknownHostException;

import twp.generated.EchoHandler;
import twp.generated.EchoReply;
import twp.generated.EchoRequest;
import twp.generated.Protocol;


public class ClientApp implements EchoHandler {

	private Protocol protocol;
	
	public ClientApp(String host, int port) {
		try {
			protocol = Protocol.startClient(host, port, this);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientApp client = new ClientApp("localhost", 12347);
		client.start();
	}

	public void start() {
		try {
			for (int i=0;i<10;i++) {
				if (protocol != null)
					protocol.echoRequest("fŸŸbar");
			}
		
			//protocol.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onEchoReply(EchoReply reply) {
		System.out.println("Response from Server:");
		System.out.println(reply.getText() + " has a length of " + reply.getNumberOfLetters());
	}

	@Override
	public void onEchoRequest(EchoRequest request) {
		
		
	}

}
