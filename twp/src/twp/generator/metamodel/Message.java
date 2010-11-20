package twp.generator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Message {
	
	public int id;
	public String name;
	public List<Field> fields;
	
	public Message() {
		fields = new ArrayList<Field>();
	}
}
