package twp.generator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Specification {
	public List<Protocol> protocols;
	public List<Message> messages;
	
	public Specification() {
		protocols = new ArrayList<Protocol>();
		messages = new ArrayList<Message>();
	}
}
