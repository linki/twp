package twp.generated;

import java.util.ArrayList;
import java.util.List;

import twp.core.Container;
import twp.core.GenericSequence;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class Filelist implements Container {
	
	private List<String > elements = new ArrayList<String>();
	
	public Filelist() {}
	
	public Filelist(List<Object> content) {
		for (Object elem:content) {
			elements.add((String) elem
);
		}
	}
	
	public Filelist(GenericSequence seq) {
		for (Object elem:seq.getElements()) {
			elements.add((String) elem
);
		}
	}
	
	public ParameterType getParameterType() {
		return ParameterType.SEQUENCE;
	}
	
	public void add(String  part) {
		elements.add(part);
	}
	
	public List<String > getElements() {
		return elements;
	}
	
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.SEQUENCE);
		for (String  elem:elements) {
container.add(new Parameter(ParameterType.LONG_STRING, elem));

		}
		return container;
	} 
 }