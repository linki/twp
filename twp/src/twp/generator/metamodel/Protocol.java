package twp.generator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Protocol extends Element {
	public List<Message> messages;
	public List<Struct> structs;
	public List<Sequence> sequences;
	public List<Union> unions;
	public List<Forward> forwards;
	
	public Protocol() {
		messages = new ArrayList<Message>();
		structs = new ArrayList<Struct>();
		sequences = new ArrayList<Sequence>();
		unions = new ArrayList<Union>();
		forwards = new ArrayList<Forward>();
	}
}
