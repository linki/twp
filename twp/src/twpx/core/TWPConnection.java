package twpx.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TWPConnection {
	
	private static final String MAGIC_BYTES = "TWP3\n";
	
	private String host;
	private int port;
	
	private Socket socket;
	private DataOutputStream writer;
	private DataInputStream reader;
	
	public TWPConnection(Socket socket) throws IOException {
		this.socket = socket;
		reader = new DataInputStream(socket.getInputStream());
		writer = new DataOutputStream(socket.getOutputStream());
	}
	
	public TWPConnection(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void connect() throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		reader = new DataInputStream(socket.getInputStream());
		writer = new DataOutputStream(socket.getOutputStream());
	}
	
	public void disconnect() throws IOException {
		socket.close();
	}

	public void reconnect() throws UnknownHostException, IOException {
		connect();
		disconnect();		
	}

	public boolean readMagicBytes() throws IOException {
		byte[] bytes = new byte[5];
		reader.readFully(bytes);
		return MAGIC_BYTES.equals(new String(bytes));
	}
	
	public void writeMagicBytes() throws IOException {
		writer.writeBytes(MAGIC_BYTES);
	}
	
	public int readProtocolId() throws IOException {
		return readInteger();
	}
	
	public void writeProtocolId(int protocol) throws IOException {
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
	
	public int readInteger(int type) throws IOException {
		switch (type) { 
		  case 13: 
			return reader.readByte();
		  case 14: 
			return reader.readInt();
		  default: 
		    throw new IllegalArgumentException("Wrong Type."); 
		}
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
		int length = type == 127 ? reader.readInt() : type - 17; // hossa, sweet :)
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
}
