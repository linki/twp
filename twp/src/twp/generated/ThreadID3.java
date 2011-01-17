package twp.generated;

import twp.core.Container;
import twp.core.Extension;
import twp.core.GenericRegisteredExtension;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class ThreadID3 implements Container, Extension {
	
	public static final int ID = 31;
	
	private String address; /* ip address */
	private int port; /* port */
	private int timestamp; /* time of request */
	private int depth; /* recursion depth */

	public ThreadID3(GenericRegisteredExtension regex) {
		address = (String) regex.getElements().get(0);
		port = (Integer) regex.getElements().get(1);
		timestamp = (Integer) regex.getElements().get(2);
		depth = (Integer) regex.getElements().get(3);
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
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
		container.add(new Parameter(ParameterType.LONG_STRING, address));
		container.add(new Parameter(ParameterType.LONG_INTEGER, port));
		container.add(new Parameter(ParameterType.LONG_INTEGER, timestamp));
		return container;
	}

	@Override
	public int getId() {
		return ID;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(address).append(":");
		sb.append(port).append(":");
		sb.append(timestamp);
		return sb.toString();
	}

}
