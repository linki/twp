package twp.generated;

import java.util.ArrayList;
import java.util.List;

import twp.core.ParameterType;
import twp.core.Sequence;

public class Path implements Sequence {
	
	private List<String> elements = new ArrayList<String>();
	
	public Path() {}
	
	public Path(List<Object> content) {
		for (Object elem:content) {
			elements.add((String) elem);
		}
	}
	
	public ParameterType getType() {
		return ParameterType.LONG_STRING;
	}
	
	public void add(String part) {
		elements.add(part);
	}
	
	public List<String> getParts() {
		return elements;
	}
	
	public List<Object> getElements() {
		List<Object> result = new ArrayList<Object>();
		for (String elem:elements)
			result.add(elem);
		return result;
	}
}
