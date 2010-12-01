package twp.application;
import java.io.IOException;
import java.net.UnknownHostException;

import twp.generated.EchoHandler;
import twp.generated.EchoProtocol;
import twp.generated.EchoReply;
import twp.generated.EchoRequest;


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
		
		/*Path path = new Path();
		List<Object> list = new ArrayList<Object>();
		path.add("foo");
		path.add("bar");
		list.add((Object) path);
		list.add(1);
		for (Object o:list) {
			System.out.println(o.getClass().getName());
			if (o instanceof Sequence) 
				System.out.println("is path!");
			if (o instanceof Object)
				System.out.println("is object!");
		}*/
	}

	public void start() {
		try {
			for (int i=0;i<10;i++) {
				if (protocol != null) {
					protocol.sendRequest("f��bar");
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
