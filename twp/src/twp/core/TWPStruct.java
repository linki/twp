package twp.core;

import java.util.ArrayList;
import java.util.List;

public class TWPStruct {
	int id;
	List<Object> elements = new ArrayList<Object>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void add(Object elem) {
		elements.add(elem);
	}
	
	public List<Object> getElements() {
		return elements;
	}
}
