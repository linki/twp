package twp.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import twp.generated.EchoHandler;

public abstract class TWPProtocol {
	protected TWPConnection connection;
	protected EchoHandler handler;
	
	public TWPProtocol(String host, int port, EchoHandler rh) throws UnknownHostException, IOException {
		handler = rh;
		connection = new TWPConnection(host, port, this);
	}
	
	public TWPProtocol(Socket s, TWPHandler handler) throws IOException {
		this.handler = (EchoHandler) handler;
		connection = new TWPConnection(s, this);
	}
	
	public void stop() throws IOException {
		connection.disconnect();
	}
	
	public abstract void onMessage(Message message) throws IOException;
	
	public abstract int getVersion();
}
