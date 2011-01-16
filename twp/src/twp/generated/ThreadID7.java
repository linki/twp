package twp.generated;

import twp.core.Container;
import twp.core.Extension;
import twp.core.GenericRegisteredExtension;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class ThreadID7 implements Container, Extension {

	public static final int ID = 36; 
	
	private String initiator;     /* the one that started the request - unique in the whole distributed system */
	private String request_id;    /* identifier for the request - unique to the initiator */
	private String thread_path;   /* path in the tree of thread numbers (see below) */
	private int current = 0;
	
	public ThreadID7(GenericRegisteredExtension regex) {
		initiator = (String) regex.getElements().get(0);
		request_id = (String) regex.getElements().get(1);
		thread_path = (String) regex.getElements().get(2);
	}
	
	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String requestId) {
		request_id = requestId;
	}

	public String getThread_path() {
		return thread_path + "," + current;
	}

	public void setThread_path(String threadPath) {
		thread_path = threadPath;
	}

	public void incPath() {
		current++;
	}
	
	@Override
	public ParameterType getParameterType() {
		return ParameterType.REGISTERED_EXTENSION;
	}

	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.REGISTERED_EXTENSION);
		container.setId(ID);
		container.add(new Parameter(ParameterType.LONG_STRING, initiator));
		container.add(new Parameter(ParameterType.LONG_STRING, request_id));
		container.add(new Parameter(ParameterType.LONG_STRING, getThread_path()));
		return container;
	}

	@Override
	public int getId() {
		return ID;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(initiator).append(" ");
		sb.append(request_id).append(" ");
		sb.append(thread_path);
		return sb.toString();
	}

}
