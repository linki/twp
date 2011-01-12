package twp.core;

public class GenericApplicationType {
	private int id;
	private Object value;
	
	public GenericApplicationType(int number, Object content) {
		id = number;
		value = content;
	}
	
	public int getId() {
		return id;
	}
	
	public Object getValue() {
		return value;
	}
}

