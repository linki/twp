package twp3.core;

import java.util.ArrayList;
import java.util.List;

public class Message {
	public int id;
	public int protocol;
	public List<Parameter> parameters;
	
	public Message() {
		parameters = new ArrayList<Parameter>();
	}
}
