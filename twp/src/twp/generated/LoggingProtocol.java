package twp.generated;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import twp.core.*;

public class LoggingProtocol extends TWPProtocol {
	public static final int ID = 6;
	
	private LoggingHandler handler;
	
	public LoggingProtocol(String host, int port, LoggingHandler rh) throws UnknownHostException, IOException {
		super(host, port);
		handler = rh;
		connection.listen();
	}
	
	public LoggingProtocol(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
	}
		
	public LoggingProtocol(Socket s, LoggingHandler rh) throws IOException {
		super(s);
		handler = rh;
	}
	
	public int getVersion() {
		return ID;
	}
	
	public void sendLogEntry(int  seconds , int  useconds , String  source , String  threadId , String  text ) throws IOException {
		Message message = new Message(0, ID);
		message.addParameter(decompose(seconds));
		message.addParameter(decompose(useconds));
		message.addParameter(decompose(source));
		message.addParameter(decompose(threadId));
		message.addParameter(decompose(text));

		connection.writeMessage(message);
	}

	public LoggingLogEntry receiveLogEntry() throws IOException {
		Message message = connection.readMessage();
		if (message == null || message.getType() != 0) {
			// throw exception
		}
		Iterator<Parameter> iter = message.getParameters().iterator();
		LoggingLogEntry req = new LoggingLogEntry(this, (Integer) iter.next().getValue() , (Integer) iter.next().getValue() , (String) iter.next().getValue() , (String) iter.next().getValue() , (String) iter.next().getValue() );
		return req;
	}
		
	
	public void onMessage(Message message) throws IOException {
		Iterator<Parameter> iter = message.getParameters().iterator();
		switch (message.getType()) {
		case 0:
				LoggingLogEntry req0 = new LoggingLogEntry(this, (Integer) iter.next().getValue() , (Integer) iter.next().getValue() , (String) iter.next().getValue() , (String) iter.next().getValue() , (String) iter.next().getValue() );
				handler.onLoggingLogEntry(req0);
				break;

		}
	}
}
