package twp.generator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Protocol {
	public List<Message> messages;
	public List<Struct> structs;
	public List<Sequence> sequences;
	public List<Union> unions;
	public List<Forward> forwards;
	public int id;
	public String name;
	
	public Protocol() {
		messages = new ArrayList<Message>();
		structs = new ArrayList<Struct>();
		sequences = new ArrayList<Sequence>();
		unions = new ArrayList<Union>();
		forwards = new ArrayList<Forward>();
	}
}
