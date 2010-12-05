package twp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	String host;
	int port;
	
	Socket socket;
	DataOutputStream writer;
	DataInputStream reader;
	
	Specification specification;
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public Client(Specification specification) {
		this.specification = specification;
		this.host = "www.dcl.hpi.uni-potsdam.de";
		this.port = 80;		
	}	
	
	public void connect() throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		writer = new DataOutputStream(socket.getOutputStream());
		reader = new DataInputStream(socket.getInputStream());
	}

	public void disconnect() throws IOException {
		socket.close();
	}

	public String invoke(String protocol, String... parameters) throws UnknownHostException, IOException {
		Protocol protocolObject = specification.getProtocols().iterator().next();
		Message message = protocolObject.getMessages().iterator().next();
		
		TWPClient twp = new TWPClient(host, port);
		twp.connect();
		
		twp.writeMagicBytes();
		twp.writeProtocol(protocolObject.id);
		twp.writeMessageId(message.id);
		twp.writeString(parameters[0]);
		twp.writeEndOfMessage();
		
		// if message is something like exception or close connection,
		// we'll have to deal with that
		int message_id = twp.readMessageId();
		
		String returnedText = twp.readString();
		int length = twp.readInteger();
		twp.readEndOfMessage();
		
		twp.disconnect();
		
		return returnedText + ", " + length;
	}
}
