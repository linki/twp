package twp.generator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Specification {
	public List<Protocol> protocols;
	public List<Message> messages;
	public List<Struct> structs;
	
	public Specification() {
		protocols = new ArrayList<Protocol>();
		messages = new ArrayList<Message>();
		structs = new ArrayList<Struct>();
	}
}
