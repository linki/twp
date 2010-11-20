package twp.generated;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;

import twp.core.Message;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPConnection;


public class Protocol {
	public static final int ID = 2;
	private TWPConnection connection;
	private EchoHandler handler;
	
	
	private Protocol(String host, int port, EchoHandler rh) throws UnknownHostException, IOException {
		handler = rh;
		connection = new TWPConnection(host, port, ID);
	}
	
	private Protocol(int port, EchoHandler rh) throws IOException {
		handler = rh;
		connection = new TWPConnection(port, this);
		connection.startServer();
	}
	
	public void stop() throws IOException {
		connection.disconnect();
	}
	
	public static Protocol startClient(String host, int port, EchoHandler rh) throws UnknownHostException, IOException {
		return new Protocol(host, port, rh);
	}
	
	public static Protocol startServer(int port, EchoHandler rh) throws IOException {
		return new Protocol(port, rh);
	}
	
	public void echoRequest(String text) throws IOException {
		Message message = new Message(0, ID);
		message.addParameter(new Parameter(ParameterType.LONG_STRING, text));
		connection.writeMessage(message);
	}
	
	public void echoReply(String text, int length) throws IOException {
		Message message = new Message(1, ID);
		message.addParameter(new Parameter(ParameterType.LONG_STRING, text));
		message.addParameter(new Parameter(ParameterType.LONG_INTEGER, length));
		connection.writeMessage(message);
	}
	
	public void onMessage(Message message) throws IOException {
		System.out.println("Received Message: " + message.getType());
		Iterator<Parameter> iter = message.getParameters().iterator();
		switch (message.getType()) {
			case 0:
				System.out.println("It's a Request.");
				EchoRequest req = new EchoRequest((String) iter.next().getValue());
				handler.onEchoRequest(req);
				break;
			case 1:
				System.out.println("It's a Reply.");
				EchoReply rep = new EchoReply((String) iter.next().getValue(), (Integer) iter.next().getValue());
				handler.onEchoReply(rep);
				break;
		}
	}
	
}
