package twp.generated;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import twp.core.*;

public class RPCProtocol extends TWPProtocol {
	public static final int ID = 1;
	
	private RPCHandler handler;
	
	public RPCProtocol(String host, int port, RPCHandler rh) throws UnknownHostException, IOException {
		super(host, port);
		handler = rh;
		connection.listen();
	}
	
	public RPCProtocol(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
	}
		
	public RPCProtocol(Socket s, RPCHandler rh) throws IOException {
		super(s);
		handler = rh;
	}
	
	public int getVersion() {
		return ID;
	}
	
	public void sendRequest(int  requestId , int  responseExpected , String  operation , List<Object>  parameters ) throws IOException {
		Message message = new Message(0, ID);
		message.addParameter(decompose(requestId));
		message.addParameter(decompose(responseExpected));
		message.addParameter(decompose(operation));
		message.addParameter(setAnyDefinedBy(parameters));

		connection.writeMessage(message);
	}

	public RPCRequest receiveRequest() throws IOException {
		Message message = connection.readMessage();
		if (message == null || message.getType() != 0) {
			// throw exception
		}
		Iterator<Parameter> iter = message.getParameters().iterator();
		RPCRequest req = new RPCRequest(this, (Integer) iter.next().getValue() , (Integer) iter.next().getValue() , (String) iter.next().getValue() , getAnyDefinedBy(iter.next()) );
		return req;
	}
		public void sendReply(int  requestId , List<Object>  result ) throws IOException {
		Message message = new Message(1, ID);
		message.addParameter(decompose(requestId));
		message.addParameter(setAnyDefinedBy(result));

		connection.writeMessage(message);
	}

	public RPCReply receiveReply() throws IOException {
		Message message = connection.readMessage();
		if (message == null || message.getType() != 1) {
			// throw exception
		}
		Iterator<Parameter> iter = message.getParameters().iterator();
		RPCReply req = new RPCReply(this, (Integer) iter.next().getValue() , getAnyDefinedBy(iter.next()) );
		return req;
	}
		public void sendCancelRequest(int  requestId ) throws IOException {
		Message message = new Message(2, ID);
		message.addParameter(decompose(requestId));

		connection.writeMessage(message);
	}

	public RPCCancelRequest receiveCancelRequest() throws IOException {
		Message message = connection.readMessage();
		if (message == null || message.getType() != 2) {
			// throw exception
		}
		Iterator<Parameter> iter = message.getParameters().iterator();
		RPCCancelRequest req = new RPCCancelRequest(this, (Integer) iter.next().getValue() );
		return req;
	}
		public void sendCloseConnection() throws IOException {
		Message message = new Message(4, ID);
		connection.writeMessage(message);
	}

	public RPCCloseConnection receiveCloseConnection() throws IOException {
		Message message = connection.readMessage();
		if (message == null || message.getType() != 4) {
			// throw exception
		}
		Iterator<Parameter> iter = message.getParameters().iterator();
		RPCCloseConnection req = new RPCCloseConnection(this);
		return req;
	}
		
	
	public void onMessage(Message message) throws IOException {
		Iterator<Parameter> iter = message.getParameters().iterator();
		switch (message.getType()) {
		case 0:
				RPCRequest req0 = new RPCRequest(this, (Integer) iter.next().getValue() , (Integer) iter.next().getValue() , (String) iter.next().getValue() , getAnyDefinedBy(iter.next()) );
				handler.onRPCRequest(req0);
				break;
		case 1:
				RPCReply req1 = new RPCReply(this, (Integer) iter.next().getValue() , getAnyDefinedBy(iter.next()) );
				handler.onRPCReply(req1);
				break;
		case 2:
				RPCCancelRequest req2 = new RPCCancelRequest(this, (Integer) iter.next().getValue() );
				handler.onRPCCancelRequest(req2);
				break;
		case 4:
				RPCCloseConnection req4 = new RPCCloseConnection(this);
				handler.onRPCCloseConnection(req4);
				break;

		}
	}
}
