package twp.generated;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import twp.core.Container;
import twp.core.GenericApplicationType;
import twp.core.GenericSequence;
import twp.core.Message;
import twp.core.Parameter;
import twp.core.TWPProtocol;

public class CalculatorProtocol extends TWPProtocol {
	public static final int ID = 5;
	
	private CalculatorHandler handler;
	
	public CalculatorProtocol(String host, int port, CalculatorHandler rh) throws UnknownHostException, IOException {
		super(host, port);
		handler = rh;
		connection.listen();
	}
	
	public CalculatorProtocol(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
	}
		
	public CalculatorProtocol(Socket s, CalculatorHandler rh) throws IOException {
		super(s);
		handler = rh;
	}
	
	public int getVersion() {
		return ID;
	}
	
	public void sendRequest(int  requestId , Parameters  arguments ) throws IOException {
		sendRequest(requestId, arguments, null);
	}

	public void sendRequest(int  requestId , Parameters  arguments, List<Container> extensions) throws IOException {
		Message message = new Message(0, ID);
		message.addParameter(decompose(requestId));
		message.addParameter(decompose(arguments));
		addExtensions(message, extensions);
		message = sign(message);
		connection.writeMessage(message);
	}
	
	public CalculatorRequest receiveRequest() throws Exception {
		Message message = connection.readMessage();
		if (checkSecurity(message)) {
			if (message == null || message.getType() != 0) {
				// throw exception
			}
			Iterator<Parameter> iter = message.getParameters().iterator();
			CalculatorRequest req = new CalculatorRequest(this, (Integer) iter.next().getValue() , new Parameters((GenericSequence ) compose(iter.next())) );
			addExtensions(req, iter);
			return req;
		} else {
			return receiveRequest();
		}
	}

	public void sendReply(int  requestId , Double  result ) throws IOException {
		sendReply(requestId, result, null);
	}

	
	public void sendReply(int  requestId , Double  result, List<Container> extensions) throws IOException {
		Message message = new Message(1, ID);
		message.addParameter(decompose(requestId));
		message.addParameter(decompose(result));
		addExtensions(message, extensions);
		message = sign(message);
		connection.writeMessage(message);
	}

	public CalculatorReply receiveReply() throws Exception {
		Message message = connection.readMessage();
		if (checkSecurity(message)) {
			if (message == null || message.getType() != 1) {
				// throw exception
			}
			Iterator<Parameter> iter = message.getParameters().iterator();
			CalculatorReply req = new CalculatorReply(this, (Integer) iter.next().getValue() , new Double((GenericApplicationType ) compose(iter.next())) );
			addExtensions(req, iter);
			return req;
		} else {
			return receiveReply();
		}
	}
	
	public void sendError(String  text ) throws IOException {
		sendError(text, null);
	}
	
	
	public void sendError(String  text, List<Container> extensions) throws IOException {
		Message message = new Message(2, ID);
		message.addParameter(decompose(text));
		addExtensions(message, extensions);
		message = sign(message);
		connection.writeMessage(message);
	}

	public CalculatorError receiveError() throws Exception {
		Message message = connection.readMessage();
		if (checkSecurity(message)) {
			if (message == null || message.getType() != 2) {
				// throw exception
			}
			Iterator<Parameter> iter = message.getParameters().iterator();
			CalculatorError req = new CalculatorError(this, (String) iter.next().getValue() );
			addExtensions(req, iter);
			return req;
		} else {
			return receiveError();
		}
	}
		
	
	public void onMessage(Message message) throws Exception {
		Iterator<Parameter> iter = message.getParameters().iterator();
		if (checkSecurity(message)) {
			switch (message.getType()) {
				case 0:
					CalculatorRequest req0 = new CalculatorRequest(this, (Integer) iter.next().getValue() , new Parameters((GenericSequence ) compose(iter.next())) );
					addExtensions(req0, iter);
					handler.onCalculatorRequest(req0);
					break;
				case 1:
					CalculatorReply req1 = new CalculatorReply(this, (Integer) iter.next().getValue() , new Double((GenericApplicationType ) compose(iter.next())) );
					addExtensions(req1, iter);
					handler.onCalculatorReply(req1);
					break;
				case 2:
					CalculatorError req2 = new CalculatorError(this, (String) iter.next().getValue() );
					addExtensions(req2, iter);
					handler.onCalculatorError(req2);
					break;
			}
		}
	}
}
