package twp.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TWPInputStream extends DataInputStream {

	private int protocolVersion;
	
	public TWPInputStream(InputStream in) {
		super(in);
	}
	public Message readMessage() throws IOException {
		int tag = readMessageId();
		return readMessage(tag);
	}
	
	public Parameter createParameter(int tag) throws IOException {
		ParameterType type = TWPConnection.getParameterType(tag);
		Parameter p = null;
		switch(type) { 
			case SHORT_INTEGER: 
			case LONG_INTEGER:
				int value0 = readInteger(tag);
				p = new Parameter(type, value0);
				break;
			case SHORT_STRING: 
			case LONG_STRING:
				String value1 = readString(tag);
				p = new Parameter(type, value1);
				break;
			case SHORT_BINARY:
			case LONG_BINARY:
				byte[] value2 = readTWPByte(tag);
				p = new Parameter(type, value2);
				break;
			case STRUCT:
			case SEQUENCE:
			case UNION:
			case REGISTERED_EXTENSION:
				TWPContainer value3 = readContainer(tag);
				p = new Parameter(type, value3);
				break;
			case NO_VALUE:
				p = new Parameter(type, null);
				break;
			case RESERVED:
				break;
			case APPLICATION_TYPE:
				TWPContainer value4 = readApplicationType(tag);
				p = new Parameter(type, value4);
				break;
		}
		return p;
	}
	
	public TWPContainer readContainer(int tag) throws IOException {
		ParameterType type = TWPConnection.getParameterType(tag);
		TWPContainer container = new TWPContainer(type);
		if (type == ParameterType.REGISTERED_EXTENSION) {
			container.setId(readInt());
		} else if (type == ParameterType.UNION) {
			container.setId(tag - 4);
		}
		tag = readUnsignedByte();
		type = TWPConnection.getParameterType(tag);
		while (type != ParameterType.END_OF_CONTENT) {
			Parameter p = createParameter(tag);
			if (p != null) {
				container.add(p);
			}
			if (container.getType() == ParameterType.UNION)
				tag = 0;
			else
				tag = readUnsignedByte();
			type = TWPConnection.getParameterType(tag);
		}
		return container;
	}
	
	public TWPContainer readApplicationType(int tag) throws IOException {
		TWPContainer container = new TWPContainer();
		container.setId(tag);
		int length = readInt();
		byte[] content = new byte[length];
		readFully(content);
		container.add(new Parameter(ParameterType.APPLICATION_TYPE, content));
		return container;
	}
	
	public Message readMessage(int messageId) throws IOException {
		Message message = new Message(messageId, protocolVersion);
		int tag = readUnsignedByte();
		ParameterType type = TWPConnection.getParameterType(tag);
		while (type != ParameterType.END_OF_CONTENT) {
			Parameter p = createParameter(tag);
			if (p != null) {
				message.addParameter(p);
			}
			tag = readUnsignedByte();
			type = TWPConnection.getParameterType(tag);
		}
		return message;
	}
	
	public boolean readMagicBytes() throws IOException {
		byte[] bytes = new byte[5];
		readFully(bytes);
		return TWPConnection.MAGIC_BYTES.equals(new String(bytes));
	}
	
	public int readProtocol() throws IOException {
		protocolVersion = readInteger();
		return protocolVersion;
	}
	
	// TODO: registered extensions
	public int readMessageId() throws IOException {
		int message = readUnsignedByte();
		return message - 4;
	}
	
	public byte[] readTWPByte() throws IOException {
		int tag = readUnsignedByte();
		return readTWPByte(tag);
	}
	
	public byte[] readTWPByte(int tag) throws IOException {
		int length = 0;
		if (tag == 15)
			length = readUnsignedByte();
		else if (tag == 16)
			length = readInt();
		byte[] value = new byte[length];
		readFully(value);
		return value;
	}
	
	public int readInteger() throws IOException {
		int type = readUnsignedByte();
		return readInteger(type);
	}
	
	// TODO: throw error if type != SHORT OR LONGINT
	public int readInteger(int type) throws IOException {
		int value = 0;
		if (type == 13)
			value = super.readByte();
		else if (type == 14)
			value = readInt();
		return value;
	}
	
	public String readString() throws IOException {
		int type = readUnsignedByte();
		return readString(type);
	}
	
	public String readString(int type) throws IOException {
		int length = type == 127 ? readInt() : type - 17;
		byte[] value = new byte[length];
		read(value);
		return new String(value, "UTF-8");
	}
	
	public boolean readEndOfMessage() throws IOException {
		int tag = readUnsignedByte();
		return tag == 0;
	}
}
