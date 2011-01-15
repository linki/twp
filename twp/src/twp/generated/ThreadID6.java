package twp.generated;

import twp.core.Container;
import twp.core.Extension;
import twp.core.GenericRegisteredExtension;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class ThreadID6 implements Container, Extension {

	public static final int ID = 34;
	
	private String initiatorID; /* e.g IPv4, IPv6 */
    private int messageID; /* unique for the TWP Connection */
    private int recursionDepth; /* initially 0, incremented with each delegation of the request */
	
    public ThreadID6(GenericRegisteredExtension regex) {
		initiatorID = (String) regex.getElements().get(0);
		messageID = (Integer) regex.getElements().get(1);
		recursionDepth = (Integer) regex.getElements().get(2);
	}
    
    public String getInitiatorID() {
		return initiatorID;
	}

	public void setInitiatorID(String initiatorID) {
		this.initiatorID = initiatorID;
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public int getRecursionDepth() {
		return recursionDepth;
	}

	public void setRecursionDepth(int recursionDepth) {
		this.recursionDepth = recursionDepth;
	}
	
	public void incDepth() {
		recursionDepth++;
	}

	@Override
	public ParameterType getParameterType() {
    	return ParameterType.REGISTERED_EXTENSION; 
	}
	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.REGISTERED_EXTENSION);
		container.setId(ID);
		container.add(new Parameter(ParameterType.LONG_STRING, initiatorID));
		container.add(new Parameter(ParameterType.LONG_INTEGER, messageID));
		container.add(new Parameter(ParameterType.LONG_INTEGER, recursionDepth));
		return container;
	}
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
    
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(initiatorID).append(":");
		sb.append(messageID);
		return sb.toString();
	}
	
}
