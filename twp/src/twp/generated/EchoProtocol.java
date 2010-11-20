package twp.generated;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

import twp.core.Message;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPProtocol;


public class EchoProtocol extends TWPProtocol {
	public static final int ID = 2;
	
	private EchoHandler handler;
	
	public EchoProtocol(String host, int port, EchoHandler rh) throws UnknownHostException, IOException {
		super(host, port);
		handler = rh;
	}
	
	public EchoProtocol(Socket s, EchoHandler rh) throws IOException {
		super(s);
		handler = rh;
	}
	
	public int getVersion() {
		return ID;
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
				EchoRequest req = new EchoRequest(this, (String) iter.next().getValue());
				handler.onEchoRequest(req);
				break;
			case 1:
				System.out.println("It's a Reply.");
				EchoReply rep = new EchoReply(this, (String) iter.next().getValue(), (Integer) iter.next().getValue());
				handler.onEchoReply(rep);
				break;
		}
	}
	
}
