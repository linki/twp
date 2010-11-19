package twp.generator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Protocol {
	public List<Message> messages;
	public int id;
	public String name;
	
	public Protocol() {
		messages = new ArrayList<Message>();
	}
}
