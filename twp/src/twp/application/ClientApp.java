package twp.application;
import java.io.IOException;
import java.net.UnknownHostException;

import twp.generated.EchoHandler;
import twp.generated.EchoProtocol;
import twpx.core.Message;
import twpx.protocol.echo.EchoReply;
import twpx.protocol.echo.EchoRequest;


public class ClientApp implements EchoHandler {

	private EchoProtocol protocol;
	
	public ClientApp(String host, int port) {
		try {
			protocol = new EchoProtocol(host, port, this);
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
		//ClientApp client = new ClientApp("www.dcl.hpi.uni-potsdam.de", 80);
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

	@Override
	public Message handle(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

}
