package twpx.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MessageManager {
	
	public static Map<Integer, Protocol> protocols = new HashMap<Integer, Protocol>();
	
	public static void send(TWPConnection con, Message message, boolean withHeader) throws IOException {

		if (withHeader) {
			con.writeMagicBytes();
			con.writeProtocolId(message.getProtocol().getId());
		}

		con.writeMessageId(message.getId());
		
		// change to writeGeneric
		Iterator<TWPParameter> iterator = message.getParameters().iterator();
		while (iterator.hasNext()) {
			TWPParameter parameter = iterator.next();
			if (parameter.type == "string") {
				con.writeString((String) message.get(parameter.name));
			}
			if (parameter.type == "int") {
				con.writeInteger((Integer) message.get(parameter.name));
			}
		}
		
		con.writeEndOfMessage();
	}
	
	// if protocol is not given, then read it
	public static Message receive(TWPConnection con) throws IOException {
		con.readMagicBytes();
		int protocolId = con.readProtocolId();
		return receive(con, protocolId);
	}	

	// if protocol is given, skip the header
	public static Message receive(TWPConnection con, int protocolId) throws IOException {
		
		int id = con.readMessageId();
		
		Message message = MessageManager.protocols.get(protocolId).getMessages().get(id); // better to clone here! TODO
		
		// change to writeGeneric
		Iterator<TWPParameter> iterator = message.getParameters().iterator();
		while (iterator.hasNext()) {
			TWPParameter parameter = iterator.next();
			if (parameter.type == "string") {
				message.set(parameter.name, con.readString());
			}
			if (parameter.type == "int") {
				message.set(parameter.name, con.readInteger());
			}
		}
		
		con.readEndOfMessage();
		
		return message;
	}
	

	public static void register(Protocol protocol) {
		protocols.put(new Integer(protocol.getId()), protocol);
	}
}
