package twp3.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import twp.TWPClientOld;
import twp3.custom.RemoteImpl;
import twp3.custom.Specification;
import twp3.generated.EchoResult;
import twp3.generated.SimpleEchoResult;

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
			
			TWPClientOld twp = new TWPClientOld(client);
			
			String magicBytes = twp.readMagicBytes();
			int protocol = twp.readProtocol();
			
			Specification remote = new RemoteImpl();
			
			if (protocol == 22) {
				int messageId = twp.readMessageId();
				String text = twp.readString();
				
				EchoResult result = remote.echo(text);
				twp.writeMessageId(2);
				twp.writeString(result.text);
				twp.writeInteger(result.number_of_letters);
				twp.writeEndOfMessage();
			}
					
			if (protocol == 23) {
				int messageId = twp.readMessageId();
				String text = twp.readString();
				
				SimpleEchoResult result = remote.simpleEcho(text);
				twp.writeMessageId(4);
				twp.writeString(result.text);
				twp.writeEndOfMessage();
			}

			client.close();
		}
	}
	

	

}
