package twp.core;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

public class TWPOutputStream extends DataOutputStream {

	public TWPOutputStream(OutputStream out) {
		super(out);
	}
	
	public void writeParameter(Parameter p) throws IOException {
		switch(p.getType()) {
		case SHORT_INTEGER:
		case LONG_INTEGER: 
			writeInteger((Integer) p.getValue());
			break;
		case SHORT_STRING:
		case LONG_STRING:
			writeString((String) p.getValue());
			break;
		case SHORT_BINARY:
		case LONG_BINARY:
			writeTWPByte((byte[]) p.getValue());
			break;
		case STRUCT:
		case SEQUENCE:
		case UNION:
		case REGISTERED_EXTENSION:
			writeContainer((TWPContainer) p.getValue());
			break;
		case NO_VALUE:
			writeByte(1);
			break;
		case RESERVED:
			break;
		case APPLICATION_TYPE:
			writeApplicationType((TWPContainer) p.getValue());
			break;
		}
	}
	
	public void writeContainer(TWPContainer container) throws IOException {
		switch (container.getType()) {
		case STRUCT:
			write(2);
			break;
		case SEQUENCE:
			write(3);
			break;
		case UNION:
			write(container.getId() + 4);
			break;
		case REGISTERED_EXTENSION:
			write(12);
			writeInt(container.getId());
			break;
		}
		Iterator<Parameter> iterator = container.getParameters().iterator();
		while (iterator.hasNext()) {
			Parameter param = iterator.next();
			writeParameter(param);	
		}
		if (container.getType() != ParameterType.UNION)
			write(0);
	}
	
	public void writeApplicationType(TWPContainer container) throws IOException {
		writeByte(container.getId());
		if (container.getParameters().size() == 1) {
			writeInt(((byte[]) container.getParameters().get(0).getValue()).length);
			write((byte[]) container.getParameters().get(0).getValue());
		} else {
			write(0);
		}
	}

	public void writeMessage(Message message) throws IOException {
		// TODO: reconnect if protocol is different
		writeMessageId(message.getType());
		for (Parameter p:message.getParameters()) {
			writeParameter(p);
		}
		writeEndOfMessage();
	}
	
	public void writeMagicBytes() throws IOException {
		writeBytes(TWPConnection.MAGIC_BYTES);
	}
	
	public void writeProtocol(int protocol) throws IOException {
		writeInteger(protocol);
	}
	
	public void writeMessageId(int i) throws IOException {
		if (i > 7) {
			write(12);
			writeInt(i);
		} else
			write(i + 4);
	}
	
	public void writeTWPByte(byte[] value) throws IOException {
		if (value.length <= 255) {
			write(15);
			writeByte(value.length);
		} else {
			write(16);
			writeInt(value.length);
		}
		write(value);
	}
	
	public void writeInteger(int value) throws IOException {
		if (-128 <= value && value <= 127) {
			write(13);
			write(value);
		} else {
			write(14);
			writeInt(value);
		}
	}
	
	public void writeString(String string) throws IOException {
		byte[] bytes = string.getBytes("UTF-8");
		int type = bytes.length > 109 ? 127 : bytes.length + 17;
		write(type);
		if (type == 127)
			writeInt(bytes.length);
		write(bytes);
	}
	
	public void writeEndOfMessage() throws IOException {
		write(0);
	}
}
