package twp.generated;
import java.io.IOException;
import java.net.UnknownHostException;

import twp.core.TWPConnection;


public class Protocol {
	public static final int ID = 2;
	private TWPConnection connection;
	
	
	private Protocol(String host, int port) throws UnknownHostException, IOException {
		connection = new TWPConnection(host, port, ID);
	}
	
	private Protocol(int port) throws IOException {
		connection = new TWPConnection(port);
	}
	
	public static Protocol startClient(String host, int port) throws UnknownHostException, IOException {
		return new Protocol(host, port);
	}
	
	public static Protocol startServer(int port) throws IOException {
		return new Protocol(port);
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
}
