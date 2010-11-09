package twp.core;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import twp.generated.Protocol;


public class TWPConnection {
	
	private static final String MAGIC_BYTES = "TWP3\n";
	
	private Socket socket;
	private ServerSocket serverSocket;
	private int protocolVersion;
	private DataOutputStream writer;
	private DataInputStream reader;
	private Protocol protocol;
	public TWPConnection(String host, int port, int protocol) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		this.protocolVersion = protocol;
		reader = new DataInputStream(socket.getInputStream());
		writer = new DataOutputStream(socket.getOutputStream());
		startClient();
	}
	
	private void startClient() {
		try {
			writeMagicBytes();
			writeProtocol(this.protocolVersion);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TWPConnection(int port, Protocol protocol) throws IOException {
		serverSocket = new ServerSocket(port);
		this.protocol = protocol;
		//startServer();
	}
	
	public void startServer() throws IOException {
		socket = serverSocket.accept();
		reader = new DataInputStream(socket.getInputStream());
		writer = new DataOutputStream(socket.getOutputStream());
		reader.skipBytes(7);
		protocol.onMessage();
		socket.close();
	}
	
	public void disconnect() throws IOException {
		socket.close();
	}

	public void writeMagicBytes() throws IOException {
		writer.writeBytes(MAGIC_BYTES);
	}
	
	public void writeProtocol(int protocol) throws IOException {
		writeInteger(protocol);
	}
	
	// TODO: registered extensions
	public int readMessage() throws IOException {
		byte message = reader.readByte();
		return message - 4;
	}

	// TODO: registered extensions
	public void writeMessage(int i) throws IOException {
		writer.write(i + 4);
	}
	
	// TODO: length / other types
	public int readInteger() throws IOException {
		int type = reader.readByte();
		int value = reader.readByte();
		return value;
	}
	
	// TODO: length / other types
	public void writeInteger(int value) throws IOException {
		writer.write(13);
		writer.write(value);
	}

	// TODO: longer strings than 109
	public String readString() throws IOException {
		int type = reader.readByte();
		byte[] value = new byte[type - 17];
		reader.read(value);
		return new String(value);
	}

	// TODO: longer strings than 109
	public void writeString(String string) throws IOException {
		int type = string.length() + 17;
		writer.write(type);
		writer.writeBytes(string);
	}

	public void readEndOfMessage() throws IOException {
		reader.readByte();
	}

	public void writeEndOfMessage() throws IOException {
		writer.write(0);
	}

	
	public TWPParameter getParameterType(int id) {
		TWPParameter type = null;
		if (id == 0)
			type = TWPParameter.END_OF_CONTENT;
		else if (id == 1)
			type = TWPParameter.NO_VALUE;
		else if (id == 2)
			type = TWPParameter.STRUCT;
		else if (id == 3)
			type = TWPParameter.SEQUENCE;
		else if (4 <= id && id <= 11)
			// TODO: message alternative
			type = TWPParameter.UNION;
		else if (id == 12)
			type = TWPParameter.REGISTERED_EXTENSION;
		else if (id == 13)
			type = TWPParameter.SHORT_INTEGER;
		else if (id == 14)
			type = TWPParameter.LONG_INTEGER;
		else if (id == 15)
			type = TWPParameter.SHORT_BINARY;
		else if (id == 16)
			type = TWPParameter.LONG_BINARY;
		else if (17 <= id && id <= 126)
			type = TWPParameter.SHORT_STRING;
		else if (id == 127)
			type = TWPParameter.LONG_STRING;
		else if (128 <= id && id <= 159)
			type = TWPParameter.RESERVED;
		else if (160 <= id && id <= 255)
			type = TWPParameter.APPLICATION_TYPE;
		return type;
	}
}
