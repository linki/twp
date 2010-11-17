package twp.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class TWPProtocol {
	protected TWPConnection connection;
	
	public TWPProtocol(String host, int port) throws UnknownHostException, IOException {
		connection = new TWPConnection(host, port, this);
	}
	
	public TWPProtocol(Socket s) throws IOException {
		connection = new TWPConnection(s, this);
	}
	
	public void stop() throws IOException {
		connection.disconnect();
	}
	
	public abstract void onMessage(Message message) throws IOException;
	
	public abstract int getVersion();
}
