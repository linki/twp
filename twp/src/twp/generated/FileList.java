package twp.generated;

import java.util.ArrayList;
import java.util.List;

import twp.core.Container;
import twp.core.GenericSequence;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class FileList implements Container {
	
	private List<String> elements = new ArrayList<String>();
	
	public FileList() {}
	
	public FileList(List<Object> content) {
		for (Object elem:content) {
			elements.add((String) elem);
		}
	}
	
	public FileList(GenericSequence seq) {
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
	/*
	public List<Object> getElements() {
		List<Object> result = new ArrayList<Object>();
		for (String elem:elements)
			result.add(elem);
		return result;
	}
	*/
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.SEQUENCE);
		for (String s:elements) {
			container.add(new Parameter(ParameterType.LONG_STRING, s));
		}
		return container;
	}
}
