package twp.generated;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import twp.core.Container;
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
		connection.listen();
	}
	
	public EchoProtocol(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
	}
		
	public EchoProtocol(Socket s, EchoHandler rh) throws IOException {
		super(s);
		handler = rh;
	}
	
	public int getVersion() {
		return ID;
	}
	
	public void sendRequest(String  text ) throws IOException {
		sendRequest(text, null);
	}

	public void sendRequest(String text, List<Container> extensions) throws IOException {
		Message message = new Message(0, ID);
		message.addParameter(new Parameter(ParameterType.LONG_STRING, text));
		addExtensions(message, extensions);
		message = sign(message);
		connection.writeMessage(message);
	}
	
	public EchoRequest receiveRequest() throws Exception {
		Message message = connection.readMessage();
		if (checkSecurity(message)) {
			if (message == null || message.getType() != 0) {
				// throw exception
			}
			Iterator<Parameter> iter = message.getParameters().iterator();
			EchoRequest req = new EchoRequest(this, (String) iter.next().getValue());
			return req;
		} else {
			return receiveRequest();
		}
	}
	
	public void sendReply(String  text , int  numberOfLetters ) throws IOException {
		sendReply(text, numberOfLetters, null);
	}

	public void sendReply(String text, int numberOfLetters, List<Container> extensions) throws IOException {
		Message message = new Message(1, ID);
		message.addParameter(new Parameter(ParameterType.LONG_STRING, text));
		message.addParameter(new Parameter(ParameterType.LONG_INTEGER, numberOfLetters));
		addExtensions(message, extensions);
		message = sign(message);
		connection.writeMessage(message);
	}
	
	public EchoReply receiveReply() throws Exception {
		Message message = connection.readMessage();
		if (checkSecurity(message)) {
			if (message == null || message.getType() != 1) {
				// throw exception
			}
			Iterator<Parameter> iter = message.getParameters().iterator();
			EchoReply req = new EchoReply(this, (String) iter.next().getValue(), (Integer) iter.next().getValue());
			return req;
		} else {
			return receiveReply();
		}
	}
		
	
	public void onMessage(Message message) throws Exception {
		Iterator<Parameter> iter = message.getParameters().iterator();
		if (checkSecurity(message)) {
			switch (message.getType()) {
				case 0:
					EchoRequest req0 = new EchoRequest(this, (String) iter.next().getValue());
					handler.onEchoRequest(req0);
					break;
				case 1:
					EchoReply req1 = new EchoReply(this, (String) iter.next().getValue(), (Integer) iter.next().getValue());
					handler.onEchoReply(req1);
					break;
			}
		}
	}
}
