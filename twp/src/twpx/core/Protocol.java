package twpx.core;

import java.util.Map;

public interface Protocol {
	
	Map<Integer, Message> getMessages();
	
	void addMessage(int id, Message message);

	int getId();

	void configure();
}
