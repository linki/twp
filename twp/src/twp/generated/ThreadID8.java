package twp.generated;

import twp.core.Container;
import twp.core.Extension;
import twp.core.GenericRegisteredExtension;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class ThreadID8 implements Container, Extension {

	public static final int ID = 37;
	
	private String thread_id; /* ID of the thread */
	private int max_recursion_depth; /* maximum of recusion depth of the arguemnt tree, begin > 0*/

	public ThreadID8(GenericRegisteredExtension regex) {
		thread_id = (String) regex.getElements().get(0);
		max_recursion_depth = (Integer) regex.getElements().get(1);
	}
	
	public String getThread_id() {
		return thread_id;
	}

	public void setThread_id(String threadId) {
		thread_id = threadId;
	}

	public int getMax_recursion_depth() {
		return max_recursion_depth;
	}

	public void setMax_recursion_depth(int maxRecursionDepth) {
		max_recursion_depth = maxRecursionDepth;
	}

	@Override
	public ParameterType getParameterType() {
		return ParameterType.REGISTERED_EXTENSION;
	}

	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.REGISTERED_EXTENSION);
		container.setId(ID);
		container.add(new Parameter(ParameterType.LONG_STRING, thread_id));
		container.add(new Parameter(ParameterType.LONG_INTEGER, max_recursion_depth));
		return container;
	}

	@Override
	public int getId() {
		return ID;
	}
	
	public String toString() {
		return thread_id;
	}

}
