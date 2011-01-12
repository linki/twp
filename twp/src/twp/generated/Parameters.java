package twp.generated;

import java.util.ArrayList;
import java.util.List;

import twp.core.Container;
import twp.core.GenericSequence;
import twp.core.GenericUnion;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class Parameters implements Container {
	
	private List<Term > elements = new ArrayList<Term>();
	
	public Parameters() {}
	
	public Parameters(List<Object> content) {
		for (Object elem:content) {
			elements.add(new Term((GenericUnion ) elem));
		}
	}
	
	public Parameters(GenericSequence seq) {
		for (Object elem:seq.getElements()) {
			elements.add(new Term((GenericUnion ) elem));
		}
	}
	
	public ParameterType getParameterType() {
		return ParameterType.SEQUENCE;
	}
	
	public void add(Term  part) {
		elements.add(part);
	}
	
	public List<Term > getElements() {
		return elements;
	}
	
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.SEQUENCE);
		for (Term  elem:elements) {
container.add(new Parameter(elem.getParameterType(), elem.toContainer()));
		}
		return container;
	} 
 }