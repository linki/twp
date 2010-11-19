package twp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Message {
	
	String name;
	int id;
	
	
	Collection<Parameter> parameters;
	
	public Message(String name, int id) {
		this.name = name;
		this.id = id;
		this.parameters = new ArrayList<Parameter>();
	}

	public void addParameter(Parameter parameter) {
		parameters.add(parameter);
	}
	
	
}
