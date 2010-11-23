package twp.application;
import java.io.IOException;
import java.net.UnknownHostException;

import twp.generated.EchoHandler;
import twp.generated.EchoReply;
import twp.generated.EchoRequest;
import twp.generated.EchoProtocol;


public class ClientApp implements EchoHandler {

	private EchoProtocol protocol;
	private boolean autoHandler;
	
	public ClientApp(String host, int port, boolean flag) {
		autoHandler = flag;
		try {
			if (flag)
				protocol = new EchoProtocol(host, port, this);
			else
				protocol = new EchoProtocol(host, port);
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
		ClientApp client = new ClientApp("localhost", 12347, false);
		//ClientApp client = new ClientApp("www.dcl.hpi.uni-potsdam.de", 80);
		client.start();
	}

	public void start() {
		try {
			for (int i=0;i<10;i++) {
				if (protocol != null) {
					protocol.sendRequest("fŸŸbar");
					if (!autoHandler) {
						EchoReply rep = protocol.receiveReply();
						System.out.println(rep.getText() + " has a length of " + rep.getNumberOfLetters());	
					}
				}
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

}
