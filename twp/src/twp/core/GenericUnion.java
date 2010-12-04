package twp.core;

public class GenericUnion {
	private int id;
	private Object value;
	
	public GenericUnion(int number, Object content) {
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
