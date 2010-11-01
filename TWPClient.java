import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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

	private void connect() throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		writer = new DataOutputStream(socket.getOutputStream());
		reader = new DataInputStream(socket.getInputStream());
	}

	private void disconnect() throws IOException {
		socket.close();
	}

	private void writeMagicBytes() throws IOException {
		writer.writeBytes(MAGIC_BYTES);
	}

	private void writeProtocol(int i) throws IOException {
		writer.write(SHORT_INTEGER);
		writer.write(i);
	}

	private void writeMessage(int i) throws IOException {
		writer.write(i + 4);
	}

	private void writeString(String string) throws IOException {
		int type = string.length() + 17;
		writer.write(type);
		writer.writeBytes(string);	
	}

	private void writeEndOfMessage() throws IOException {
		writer.write(0);
	}

	private void readMessage() throws IOException {
		byte message = reader.readByte();
		System.out.println("Message: "+ message);
	}

	private void readString() throws IOException {
		int type = reader.readByte();
		
		System.out.println("Type: " + type);
		
		byte[] value = new byte[type - 17];
		reader.read(value);
		
		System.out.println("Value: " + new String(value));
	}
	
	private void readInteger() throws IOException {
		int type = reader.readByte();
		
		System.out.println("Type: " + type);
		
		int value = reader.readByte();
		
		System.out.println("Value: " + value);
	}	

	private void readEndOfMessage() throws IOException {
		int eom = reader.readByte();
		
		System.out.println("End: " + eom);
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		TWPClient twp = new TWPClient("www.dcl.hpi.uni-potsdam.de", 80);
		twp.connect();
		
		System.out.println("Connected");

		twp.writeMagicBytes();
		twp.writeProtocol(2);
		twp.writeMessage(0);
		twp.writeString("martin");
		twp.writeEndOfMessage();
		
		twp.readMessage();
		twp.readString();
		twp.readInteger();
		twp.readEndOfMessage();
		
		twp.disconnect();
		
		System.out.println("Disconnected");
	}
}

/*
package org.threeriversinstitute.tyrant;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

class TyrantMap implements Iterable<byte[]> {
	private static final int RESET_ITERATOR_OPERATION = 0x50;
	private static final int GET_NEXT_KEY_OPERATION = 0x51;
	private static final int SIZE_OPERATION = 0x80;
	private static final int REMOVE_OPERATION = 0x20;
	private static final int VANISH_OPERATION = 0x72;
	private static final int GET_OPERATION = 0x30;
	private static final int PUT_OPERATION = 0x10;
	private static final int OPERATION_PREFIX = 0xC8;
	private Socket socket;
	private DataOutputStream writer;
	private DataInputStream reader;

	void put(byte[] key, byte[] value) throws IOException {
		writeOperation(PUT_OPERATION);
		writer.writeInt(key.length);
		writer.writeInt(value.length);
		writer.write(key);
		writer.write(value);
		int status = reader.read();
		if (status != 0)
			throw new RuntimeException();
	}

	public byte[] get(byte[] key) {
		try {
			writeOperation(GET_OPERATION);
			writer.writeInt(key.length);
			writer.write(key);
			return readBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void clear() throws IOException {
		writeOperation(VANISH_OPERATION);
		int status = reader.read();
		if (status != 0)
			throw new RuntimeException();
	}
	
	public void remove(byte[] key) throws IOException {
		if (key == null)
			throw new IllegalArgumentException();
		writeOperation(REMOVE_OPERATION);
		writer.writeInt(key.length);
		writer.write(key);
		int status = reader.read();
		if (status == 1)
			return;
		if (status != 0)
			throw new RuntimeException();
	}
	
	public long size() throws IOException {
		writeOperation(SIZE_OPERATION);
		int status = reader.read();
		if (status != 0)
			throw new RuntimeException();
		return reader.readLong();
	}
	
	@Override
	public Iterator<byte[]> iterator() {
		try {
			reset();
			final byte[] firstKey = getNextKey();
			
			return new Iterator<byte[]>() {
				byte[] nextKey = firstKey;
				private byte[] previousKey;
				
				@Override
				public boolean hasNext() {
					return nextKey != null;
				}
				
				@Override
				public byte[] next() {
					byte[] result = get(nextKey);
					previousKey = nextKey;
					nextKey = getNextKey();
					return result ;
				}
				
				@Override
				public void remove() {
					try {
						TyrantMap.this.remove(previousKey);
					} catch (IllegalArgumentException e) {
						throw new IllegalStateException(e);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			};
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	void open() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 1978);
		writer = new DataOutputStream(socket.getOutputStream());
		reader = new DataInputStream(socket.getInputStream());
	}

	public void close() throws IOException {
		socket.close();
	}

	private void reset() throws IOException {
		writeOperation(RESET_ITERATOR_OPERATION);
		int status = reader.read();
		if (status != 0)
			throw new RuntimeException();
	}

	private byte[] getNextKey() {
		try {
			writeOperation(GET_NEXT_KEY_OPERATION);
			return readBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeOperation(int operation) throws IOException {
		writer.write(OPERATION_PREFIX);
		writer.write(operation);
	}

	private byte[] readBytes() throws IOException {
		int status = reader.read();
		if (status == 1)
			return null;
		if (status != 0)
			throw new RuntimeException();
		int length = reader.readInt();
		byte[] results = new byte[length];
		reader.read(results); // TODO read longer values
		return results;
	}

}
*/