package twp.core;

import java.util.ArrayList;
import java.util.List;

public class GenericRegisteredExtension {
	private int id;
	private List<Object> elements = new ArrayList<Object>();
	
	public GenericRegisteredExtension(int number) {
		id = number;
	}
	
	public List<Object> getElements() {
		return elements;
	}
	
	public void setElements(List<Object> content) {
		elements = content;
	}
	
	public void addElement(Object elem) {
		elements.add(elem);
	}
	
	public int getId() {
		return id;
	}
}
