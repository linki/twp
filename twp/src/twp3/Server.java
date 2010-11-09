package twp3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import twp.TWPClient;

public class Server {
	Specification spec;
	ServerSocket socket;
	DataOutputStream writer;
	DataInputStream reader;
	
	public Server(Specification spec) throws IOException {
		this.spec = spec;
		this.socket = new ServerSocket(6666);
	}
	
	public Socket accept() throws IOException {
		return socket.accept();
	}

	public void start() throws IOException {
		while (true) {
			Socket client = socket.accept();
			
			TWPClient twp = new TWPClient(client);
			
			String magicBytes = twp.readMagicBytes();
			int protocol = twp.readProtocol();
			
			int messageId = twp.readMessage();
			String text = twp.readString();
			
			Specification remote = new RemoteImpl();
			Response result = remote.echo(text);
			
			twp.writeMessage(2);
			twp.writeString((String) result.getParameter(0));
			twp.writeInteger((Integer) result.getParameter(1));
			twp.writeEndOfMessage();
			
			client.close();
		}
	}
	

	

}
