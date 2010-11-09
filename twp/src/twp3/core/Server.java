package twp3.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import twp.TWPClient;
import twp3.custom.RemoteImpl;
import twp3.custom.Specification;

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
			
			Specification remote = new RemoteImpl();
			
			if (protocol == 22) {
				int messageId = twp.readMessage();
				String text = twp.readString();
				
				List<Object> result = remote.echo(text);
				twp.writeMessage(2);
				twp.writeString((String) result.get(0));
				twp.writeInteger((Integer) result.get(1));
				twp.writeEndOfMessage();
			}
					
			if (protocol == 23) {
				int messageId = twp.readMessage();
				String text = twp.readString();
				
				String result = remote.simpleEcho(text);
				twp.writeMessage(4);
				twp.writeString((String) result);
				twp.writeEndOfMessage();
			}

			client.close();
		}
	}
	

	

}
