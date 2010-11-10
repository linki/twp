package twp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

import twp3.core.Message;
import twp3.core.Parameter;

public class TWPClient {
	private static final String MAGIC_BYTES = "TWP3\n";
	private static final int SHORT_INTEGER = 13;
	
	private String host;
	private int port;
	
	private Socket socket;
	private DataOutputStream writer;
	private DataInputStream reader;
	
	public TWPClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void connect() throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);
		this.writer = new DataOutputStream(socket.getOutputStream());
		this.reader = new DataInputStream(socket.getInputStream());
	}

	public TWPClient(Socket socket) throws IOException {
		this.socket = socket;
		this.writer = new DataOutputStream(socket.getOutputStream());
		this.reader = new DataInputStream(socket.getInputStream());
	}
	
	public void disconnect() throws IOException {
		socket.close();
	}

	public String readMagicBytes() throws IOException {
		byte[] magicBytes = new byte[5];
		reader.readFully(magicBytes);
		return new String(magicBytes);
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
	
	// TODO: length / other types
	public int readInteger() throws IOException {
		int type = reader.readByte();
		int value = reader.readByte();
		return value;
	}
	
	// TODO: length / other types
	public void writeInteger(int value) throws IOException {
		writer.write(SHORT_INTEGER);
		writer.write(value);
	}

	// TODO: longer strings than 109
	public String readString() throws IOException {
		int type = reader.readByte();
		byte[] value = new byte[type - 17];
		reader.readFully(value);
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

	public void writeMessage(Message message) throws IOException {
		writeMagicBytes();
		writeProtocol(message.protocol);
		writeMessageId(message.id);
		
		Iterator<Parameter> iterator = message.parameters.iterator();
		while (iterator.hasNext()) {
			Parameter param = iterator.next();
			if (param.type.equals("string")) {
				writeString((String) param.value); // support other types
			}
			if (param.type.equals("int")) {
				writeInteger((Integer) param.value); // support other types
			}
		}
		writeEndOfMessage();
	}

	// example
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		if (args.length < 1) {
			System.err.println("Please provide a text to echo.");
			return;
		}
		
		TWPClient twp = new TWPClient("www.dcl.hpi.uni-potsdam.de", 80);
		twp.connect();
		
		int requestProtocol  = 2;
		int requestMessageId = 0;
		String requestText   = args[0];
		
		twp.writeMagicBytes();
		twp.writeProtocol(requestProtocol);
		twp.writeMessageId(requestMessageId);
		twp.writeString(requestText);
		twp.writeEndOfMessage();

		System.out.println();
		System.out.println("Request:");
		System.out.println("- Protocol: " + requestProtocol);
		System.out.println("- Message ID: " + requestMessageId);
		System.out.println("- Parameters:");
		System.out.println("  - Text: " + requestText);
		
		int responseMessageId = twp.readMessageId();
		String responseText = twp.readString();
		int responseLength = twp.readInteger();
		twp.readEndOfMessage();
		
		System.out.println();
		System.out.println("Response:");
		System.out.println("- Message ID: " + responseMessageId);
		System.out.println("- Parameters:");
		System.out.println("  - Text: " + responseText);
		System.out.println("  - Number of Letters: " + responseLength);
		System.out.println();

		twp.disconnect();
	}
}