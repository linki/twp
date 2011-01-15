package twp.generated;

import java.util.UUID;

import twp.core.Container;
import twp.core.Extension;
import twp.core.GenericRegisteredExtension;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class ThreadID implements Container, Extension {
	public static final int ID = 35;
	
	private String thread_id; /* unique id */
	private byte[] host; /* client address */
	private int port; /* client port */
	private int time; /* Unix timestamp in seconds */
	private int depth; /* recursion depth */
	
	public ThreadID(GenericRegisteredExtension regex) {
		thread_id = (String) regex.getElements().get(0);
		host = (byte[]) regex.getElements().get(1);
		port = (Integer) regex.getElements().get(2);
		time = (Integer) regex.getElements().get(3);
		depth = (Integer) regex.getElements().get(4);
	}
	
	public ThreadID(byte[] host, int port) {
		this.thread_id = UUID.randomUUID().toString();
		this.host = host;
		this.port = port;
		this.time = (int) (System.currentTimeMillis() / 1000);
		this.depth = 0;
	}
	
	public int getId() {
		return ID;
	}
	
	public String getThreadId() {
		return thread_id;
	}
	
	public void setThreadId(String id) {
		thread_id = id;
	}
	
	public byte[] getHost() {
		return host;
	}
	
	public void setHost(byte[] h) {
		host = h;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int p) {
		port = p;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int t) {
		time = t;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int d) {
		depth = d;
	}
	
	public void incDepth() {
		depth++;
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
		container.add(new Parameter(ParameterType.LONG_BINARY, host));
		container.add(new Parameter(ParameterType.LONG_INTEGER, port));
		container.add(new Parameter(ParameterType.LONG_INTEGER, time));
		container.add(new Parameter(ParameterType.LONG_INTEGER, depth));
		return container;
	}

}
