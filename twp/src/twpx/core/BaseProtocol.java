package twpx.core;

import java.util.HashMap;
import java.util.Map;

import twpx.interfaces.Message;
import twpx.interfaces.Protocol;

public class BaseProtocol implements Protocol {
	
	public int id;
	
	public Map<Integer, Message> messages = new HashMap<Integer, Message>();
	
	@Override
	public void addMessage(int id, Message message) {
		messages.put(new Integer(id), message);
	}

	@Override
	public Map<Integer, Message> getMessages() {
		return messages;
	}

	@Override
	public int getId() {
		return id;
	}
}
