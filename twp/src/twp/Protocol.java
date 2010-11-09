package twp;

import java.util.ArrayList;
import java.util.Collection;

public class Protocol {
	String name;
	int id;
	
	Collection<Message> messages;
	
	public Protocol(String name, int id) {
		this.name = name;
		this.id = id;
		this.messages = new ArrayList<Message>();
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

	public String getName() {
		return name;
	}

	public Collection<Message> getMessages() {
		return messages;
	}
}
