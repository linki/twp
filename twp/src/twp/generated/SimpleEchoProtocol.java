package twp.generated;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

import twp.core.Message;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPProtocol;

public class SimpleEchoProtocol extends TWPProtocol {
	public static final int ID = 23;
	
	private SimpleEchoHandler handler;
	
	public SimpleEchoProtocol(String host, int port, SimpleEchoHandler rh) throws UnknownHostException, IOException {
		super(host, port);
		handler = rh;
	}
	
	public SimpleEchoProtocol(Socket s, SimpleEchoHandler rh) throws IOException {
		super(s);
		handler = rh;
	}
	
	public int getVersion() {
		return ID;
	}
	
	public void simpleEchoRequest(String  text ) throws IOException {
		Message message = new Message(0, ID);
		message.addParameter(new Parameter(ParameterType.LONG_STRING, text));

		connection.writeMessage(message);
	}
		public void simpleEchoReply(String  text ) throws IOException {
		Message message = new Message(1, ID);
		message.addParameter(new Parameter(ParameterType.LONG_STRING, text));

		connection.writeMessage(message);
	}
		
	
	public void onMessage(Message message) throws IOException {
		Iterator<Parameter> iter = message.getParameters().iterator();
		switch (message.getType()) {
		case 0:
				SimpleEchoRequest req0 = new SimpleEchoRequest(this, (String) iter.next().getValue());
				handler.onSimpleEchoRequest(req0);
				break;
		case 1:
				SimpleEchoReply req1 = new SimpleEchoReply(this, (String) iter.next().getValue());
				handler.onSimpleEchoReply(req1);
				break;

		}
	}
}
