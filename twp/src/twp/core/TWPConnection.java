package twp.core;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class TWPConnection {
	
	private class Listener extends Thread {

		private boolean isActive;
		
		public Listener() {
			this.isActive = true;
		}
		
		@Override
		public void run() {
			try {
				while (isActive) {
					Message message = readMessage();
					protocol.onMessage(message);
				}
			} catch (EOFException e) {}
			catch (IOException e) {}
		}
		
		public void disconnect() {
			isActive = false;
		}	
	}
	
	public static final String MAGIC_BYTES = "TWP3\n";
	
	private Socket socket;
	private int protocolVersion;
	private TWPOutputStream writer;
	private TWPInputStream reader;
	private TWPProtocol protocol;
	private Listener listener;
	
	public TWPConnection(String host, int port, TWPProtocol p) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		protocol = p;
		protocolVersion = p.getVersion();
		reader = new TWPInputStream(new DataInputStream(socket.getInputStream()));
		writer = new TWPOutputStream(new DataOutputStream(socket.getOutputStream()));
		startClient();
	}
	
	public TWPConnection(Socket s, TWPProtocol p) throws IOException {
		socket = s;
		reader = new TWPInputStream(new DataInputStream(s.getInputStream()));
		writer = new TWPOutputStream(new DataOutputStream(s.getOutputStream()));
		protocol = p;
		startServer();
		listen();
	}
	
	public byte[] getLocalAddress() {
		return socket.getLocalAddress().getAddress();
	}
	
	public int getLocalPort() {
		return socket.getLocalPort();
	}
	
	private void startClient() throws IOException {
		writer.writeMagicBytes();
		writer.writeProtocol(this.protocolVersion);
	}
	
	private void startServer() throws IOException {
		reader.readMagicBytes();
		this.protocolVersion = reader.readProtocol();
	}
	
	public void listen() throws IOException {
		listener = new Listener();
		listener.start();
	}
	
	public Message readMessage() throws IOException {
		return reader.readMessage();
	}
	
	public void writeMessage(Message message) throws IOException {
		// TODO: reconnect if protocol is different
		writer.writeMessage(message);
	}
	
	public void disconnect() throws IOException {
		if (listener != null && listener.isAlive())
			listener.disconnect();
		socket.close();
	}

	
	public static ParameterType getParameterType(int id) {
		ParameterType type = null;
		if (id == 0)
			type = ParameterType.END_OF_CONTENT;
		else if (id == 1)
			type = ParameterType.NO_VALUE;
		else if (id == 2)
			type = ParameterType.STRUCT;
		else if (id == 3)
			type = ParameterType.SEQUENCE;
		else if (4 <= id && id <= 11)
			// TODO: message alternative
			type = ParameterType.UNION;
		else if (id == 12)
			type = ParameterType.REGISTERED_EXTENSION;
		else if (id == 13)
			type = ParameterType.SHORT_INTEGER;
		else if (id == 14)
			type = ParameterType.LONG_INTEGER;
		else if (id == 15)
			type = ParameterType.SHORT_BINARY;
		else if (id == 16)
			type = ParameterType.LONG_BINARY;
		else if (17 <= id && id <= 126)
			type = ParameterType.SHORT_STRING;
		else if (id == 127)
			type = ParameterType.LONG_STRING;
		else if (128 <= id && id <= 159)
			type = ParameterType.RESERVED;
		else if (160 <= id && id <= 255)
			type = ParameterType.APPLICATION_TYPE;
		return type;
	}
}
