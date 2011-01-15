package twp.generated;

import twp.core.Container;
import twp.core.Extension;
import twp.core.GenericRegisteredExtension;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class ThreadID5 implements Container, Extension {
	
	public static final int ID = 33;
	
	private String tid;
	private int recursion_depth;

	public ThreadID5(GenericRegisteredExtension regex) {
		tid = (String) regex.getElements().get(0);
		recursion_depth = (Integer) regex.getElements().get(1);
	}
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public int getRecursion_depth() {
		return recursion_depth;
	}

	public void setRecursion_depth(int recursionDepth) {
		recursion_depth = recursionDepth;
	}
	
	public void incDepth() {
		recursion_depth++;
	}

	@Override
	public ParameterType getParameterType() {
		return ParameterType.REGISTERED_EXTENSION;
	}

	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.REGISTERED_EXTENSION);
		container.setId(ID);
		container.add(new Parameter(ParameterType.LONG_STRING, tid));
		container.add(new Parameter(ParameterType.LONG_INTEGER, recursion_depth));
		return container;
	}

	@Override
	public int getId() {
		return ID;
	}

	public String toString() {
		return tid;
	}
}
