package twp.generated;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

import twp.core.Message;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.Sequence;
import twp.core.TWPProtocol;

public class FAMProtocol extends TWPProtocol {
	public static final int ID = 4;
	
	private FAMHandler handler;
	
	public FAMProtocol(String host, int port, FAMHandler rh) throws UnknownHostException, IOException {
		super(host, port);
		handler = rh;
		connection.listen();
	}
	
	public FAMProtocol(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
	}
		
	public FAMProtocol(Socket s, FAMHandler rh) throws IOException {
		super(s);
		handler = rh;
	}
	
	public int getVersion() {
		return ID;
	}
	
	public void sendChanged(Path  directory , String  filename ) throws IOException {
		Message message = new Message(0, ID);
		message.addParameter(new Parameter(ParameterType.SEQUENCE, decompose((Sequence) directory)));
		message.addParameter(new Parameter(ParameterType.LONG_STRING, filename));

		connection.writeMessage(message);
	}

	public FAMChanged receiveChanged() throws IOException {
		Message message = connection.readMessage();
		if (message == null || message.getType() != 0) {
			// throw exception
		}
		Iterator<Parameter> iter = message.getParameters().iterator();
		FAMChanged req = new FAMChanged(this, new Path(getAnyDefinedBy(iter.next())), (String) iter.next().getValue());
		return req;
	}
		public void sendDeleted(Path  directory , String  filename ) throws IOException {
		Message message = new Message(1, ID);
		message.addParameter(new Parameter(ParameterType.SEQUENCE, decompose((Sequence) directory)));
		message.addParameter(new Parameter(ParameterType.LONG_STRING, filename));

		connection.writeMessage(message);
	}

	public FAMDeleted receiveDeleted() throws IOException {
		Message message = connection.readMessage();
		if (message == null || message.getType() != 1) {
			// throw exception
		}
		Iterator<Parameter> iter = message.getParameters().iterator();
		FAMDeleted req = new FAMDeleted(this, new Path(getAnyDefinedBy(iter.next())), (String) iter.next().getValue());
		return req;
	}
		public void sendCreated(Path  directory , String  filename ) throws IOException {
		Message message = new Message(2, ID);
		message.addParameter(new Parameter(ParameterType.SEQUENCE, decompose((Sequence) directory)));
		message.addParameter(new Parameter(ParameterType.LONG_STRING, filename));

		connection.writeMessage(message);
	}

	public FAMCreated receiveCreated() throws IOException {
		Message message = connection.readMessage();
		if (message == null || message.getType() != 2) {
			// throw exception
		}
		Iterator<Parameter> iter = message.getParameters().iterator();
		FAMCreated req = new FAMCreated(this, new Path(getAnyDefinedBy(iter.next())), (String) iter.next().getValue());
		return req;
	}
		public void sendStartExecuting(Path  directory , String  filename ) throws IOException {
		Message message = new Message(3, ID);
		message.addParameter(new Parameter(ParameterType.SEQUENCE, decompose((Sequence) directory)));
		message.addParameter(new Parameter(ParameterType.LONG_STRING, filename));

		connection.writeMessage(message);
	}

	public FAMStartExecuting receiveStartExecuting() throws IOException {
		Message message = connection.readMessage();
		if (message == null || message.getType() != 3) {
			// throw exception
		}
		Iterator<Parameter> iter = message.getParameters().iterator();
		FAMStartExecuting req = new FAMStartExecuting(this, new Path(getAnyDefinedBy(iter.next())), (String) iter.next().getValue());
		return req;
	}
		public void sendStopExecuting(Path  directory , String  filename ) throws IOException {
		Message message = new Message(4, ID);
		message.addParameter(new Parameter(ParameterType.SEQUENCE, decompose((Sequence) directory)));
		message.addParameter(new Parameter(ParameterType.LONG_STRING, filename));

		connection.writeMessage(message);
	}

	public FAMStopExecuting receiveStopExecuting() throws IOException {
		Message message = connection.readMessage();
		if (message == null || message.getType() != 4) {
			// throw exception
		}
		Iterator<Parameter> iter = message.getParameters().iterator();
		FAMStopExecuting req = new FAMStopExecuting(this, new Path(getAnyDefinedBy(iter.next())), (String) iter.next().getValue());
		return req;
	}
		
	
	public void onMessage(Message message) throws IOException {
		Iterator<Parameter> iter = message.getParameters().iterator();
		switch (message.getType()) {
		case 0:
				FAMChanged req0 = new FAMChanged(this, new Path(getAnyDefinedBy(iter.next())), (String) iter.next().getValue());
				handler.onFAMChanged(req0);
				break;
		case 1:
				FAMDeleted req1 = new FAMDeleted(this, new Path(getAnyDefinedBy(iter.next())), (String) iter.next().getValue());
				handler.onFAMDeleted(req1);
				break;
		case 2:
				FAMCreated req2 = new FAMCreated(this, new Path(getAnyDefinedBy(iter.next())), (String) iter.next().getValue());
				handler.onFAMCreated(req2);
				break;
		case 3:
				FAMStartExecuting req3 = new FAMStartExecuting(this, new Path(getAnyDefinedBy(iter.next())), (String) iter.next().getValue());
				handler.onFAMStartExecuting(req3);
				break;
		case 4:
				FAMStopExecuting req4 = new FAMStopExecuting(this, new Path(getAnyDefinedBy(iter.next())), (String) iter.next().getValue());
				handler.onFAMStopExecuting(req4);
				break;

		}
	}
}
