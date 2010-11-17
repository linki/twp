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
	
	private static final String MAGIC_BYTES = "TWP3\n";
	
	private Socket socket;
	private int protocolVersion;
	private DataOutputStream writer;
	private DataInputStream reader;
	private TWPProtocol protocol;
	private Listener listener;
	
	public TWPConnection(String host, int port, TWPProtocol p) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		protocol = p;
		protocolVersion = p.getVersion();
		reader = new DataInputStream(socket.getInputStream());
		writer = new DataOutputStream(socket.getOutputStream());
		startClient();
		listen();
	}
	
	public TWPConnection(Socket s, TWPProtocol p) throws IOException {
		socket = s;
		reader = new DataInputStream(s.getInputStream());
		writer = new DataOutputStream(s.getOutputStream());
		protocol = p;
		startServer();
		listen();
	}
	
	private void startClient() throws IOException {
		writeMagicBytes();
		writeProtocol(this.protocolVersion);
	}
	
	private void startServer() throws IOException {
		readMagicBytes();
		this.protocolVersion = readProtocol();
	}
	
	private void listen() throws IOException {
		listener = new Listener();
		listener.start();
	}
	
	public Message readMessage() throws IOException {
		int tag = readMessageId();
		return readMessage(tag);
	}
	public Message readMessage(int messageId) throws IOException {
		Message message = new Message(messageId, protocolVersion);
		int tag = reader.readByte();
		ParameterType type = getParameterType(tag);
		while (type != ParameterType.END_OF_CONTENT) {
			Parameter p = null;
			if (type == ParameterType.SHORT_INTEGER || type == ParameterType.LONG_INTEGER) {
				int value = readInteger(tag);
				p = new Parameter(type, value);
			} else if (type == ParameterType.SHORT_STRING || type == ParameterType.LONG_STRING) {
				String value = readString(tag);
				p = new Parameter(type, value);
			}
			if (p != null)
				message.addParameter(p);
			tag = reader.readByte();
			type = getParameterType(tag);
		}
		return message;
	}
	
	public void writeMessage(Message message) throws IOException {
		// TODO: reconnect if protocol is different
		writeMessageId(message.getType());
		for (Parameter p:message.getParameters()) {
			if (p.getType() == ParameterType.SHORT_INTEGER || p.getType() == ParameterType.LONG_INTEGER) 
				writeInteger((Integer) p.getValue());
			else if (p.getType() == ParameterType.SHORT_STRING || p.getType() == ParameterType.LONG_STRING)
				writeString((String) p.getValue());
		}
		writeEndOfMessage();
	}
	
	public void disconnect() throws IOException {
		if (listener != null && listener.isAlive())
			listener.disconnect();
		socket.close();
	}

	public boolean readMagicBytes() throws IOException {
		byte[] bytes = new byte[5];
		reader.readFully(bytes);
		System.out.println("Magic bytes: " + new String(bytes));
		return MAGIC_BYTES.equals(new String(bytes));
	}
	
	public void writeMagicBytes() throws IOException {
		writer.writeBytes(MAGIC_BYTES);
	}
	
	public int readProtocol() throws IOException {
		return readInteger();
	}
	
	public void writeProtocol(int protocol) throws IOException {
		writeInteger(protocol);
	}
	
	// TODO: registered extensions
	public int readMessageId() throws IOException {
		byte message = reader.readByte();
		return message - 4;
	}

	// TODO: registered extensions
	public void writeMessageId(int i) throws IOException {
		writer.write(i + 4);
	}
	
	public int readInteger() throws IOException {
		int type = reader.readByte();
		return readInteger(type);
	}
	
	// TODO: throw error if type != SHORT OR LONGINT
	public int readInteger(int type) throws IOException {
		int value = 0;
		if (type == 13)
			value = reader.readByte();
		else if (type == 14)
			value = reader.readInt();
		return value;
	}
	
	public void writeInteger(int value) throws IOException {
		if (-128 <= value && value <= 127) {
			writer.write(13);
			writer.write(value);
		} else {
			writer.write(14);
			writer.writeInt(value);
		}
	}

	public String readString() throws IOException {
		int type = reader.readByte();
		return readString(type);
	}
	
	public String readString(int type) throws IOException {
		int length = type == 127 ? reader.readInt() : type - 17;
		byte[] value = new byte[length];
		reader.read(value);
		return new String(value, "UTF-8");
	}

	public void writeString(String string) throws IOException {
		byte[] bytes = string.getBytes("UTF-8");
		int type = bytes.length > 109 ? 127 : bytes.length + 17;
		writer.write(type);
		if (type == 127)
			writer.writeInt(bytes.length);
		writer.write(bytes);
	}

	public boolean readEndOfMessage() throws IOException {
		int tag = reader.readByte();
		return tag == 0;
	}

	public void writeEndOfMessage() throws IOException {
		writer.write(0);
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
