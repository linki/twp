package twp.generated;
import java.io.IOException;
import java.net.UnknownHostException;

import twp.core.TWPConnection;


public class Protocol {
	public static final int ID = 2;
	private TWPConnection connection;
	private RequestHandler handler;
	
	
	private Protocol(String host, int port) throws UnknownHostException, IOException {
		connection = new TWPConnection(host, port, ID);
	}
	
	private Protocol(int port, RequestHandler rh) throws IOException {
		handler = rh;
		connection = new TWPConnection(port, this);
		connection.startServer();
	}
	
	public static Protocol startClient(String host, int port) throws UnknownHostException, IOException {
		return new Protocol(host, port);
	}
	
	public static Protocol startServer(int port, RequestHandler rh) throws IOException {
		return new Protocol(port, rh);
	}
	
	public EchoResponse echo(String text) throws IOException {
		connection.writeMessage(0);
		connection.writeString(text);
		connection.writeEndOfMessage();
		
		connection.readMessage();
		EchoResponse resp = new EchoResponse(connection.readString(), connection.readInteger());
		connection.readEndOfMessage();
	
		return resp;
	}
	
	public void onMessage() throws IOException {
		int messageType = connection.readMessage();
		System.out.println("new Message " + messageType);
		if (messageType == 0) {
			System.out.println("Received Message");
			EchoRequest req = new EchoRequest(connection.readString());
			connection.readEndOfMessage();
			EchoResponse resp = handler.onEcho(req);
			respondEcho(resp);
		}
		// TODO: respond with error
	}
	
	private void respondEcho(EchoResponse resp) throws IOException {
		connection.writeMessage(1);
		connection.writeString(resp.getText());
		connection.writeInteger(resp.getNumberOfLetters());
		connection.writeEndOfMessage();
	}
}
