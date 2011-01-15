package twp.generated;

import twp.core.Container;
import twp.core.Extension;
import twp.core.GenericRegisteredExtension;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class ThreadID4 implements Container, Extension {

	public static final int ID = 32;
	
	private int thread_id;
	private int recursion_depth;

	public ThreadID4(GenericRegisteredExtension regex) {
		thread_id = (Integer) regex.getElements().get(0);
		recursion_depth = (Integer) regex.getElements().get(1);
	}
	
	public int getThread_id() {
		return thread_id;
	}

	public void setThread_id(int threadId) {
		thread_id = threadId;
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
		container.add(new Parameter(ParameterType.LONG_INTEGER, thread_id));
		container.add(new Parameter(ParameterType.LONG_INTEGER, recursion_depth));
		return container;
	}

	@Override
	public int getId() {
		return ID;
	}
	
	public String toString() {
		return String.valueOf(thread_id);
	}

}
