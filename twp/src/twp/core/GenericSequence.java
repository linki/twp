package twp.core;

import java.util.ArrayList;
import java.util.List;

public class GenericSequence {
	private List<Object> elements = new ArrayList<Object>();
	
	public List<Object> getElements() {
		return elements;
	}
	
	public void setElements(List<Object> content) {
		elements = content;
	}
	
	public void addElement(Object elem) {
		elements.add(elem);
	}
	
}
