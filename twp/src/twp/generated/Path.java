package twp.generated;

import java.util.ArrayList;
import java.util.List;

import twp.core.Container;
import twp.core.GenericSequence;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class Path implements Container {
	
	private List<String> elements = new ArrayList<String>();
	
	public Path() {}
	
	public Path(List<Object> content) {
		for (Object elem:content) {
			elements.add((String) elem);
		}
	}
	
	public Path(GenericSequence seq) {
		for (Object o:seq.getElements()) {
			elements.add((String) o);
		}
	}
	
	public ParameterType getParameterType() {
		return ParameterType.SEQUENCE;
	}
	
	public void add(String part) {
		elements.add(part);
	}
	
	public List<String> getElements() {
		return elements;
	}
	
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.SEQUENCE);
		for (String s:elements) {
			container.add(new Parameter(ParameterType.LONG_STRING, s));
		}
		return container;
	}
	
	public String toString() {
		if (elements.isEmpty())
			return "/";
		StringBuilder sb = new StringBuilder();
		for (String elem:elements) 
			sb.append("/").append(elem);
		return sb.toString();	
	}
	
	public Path clone() {
		Path clone = new Path();
		for (String elem:elements)
			clone.add(elem);
		return clone;
	}
}
