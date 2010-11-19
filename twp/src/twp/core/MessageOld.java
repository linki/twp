package twp.core;

import java.util.ArrayList;
import java.util.List;

import twp.core.Parameter;

public class MessageOld {

	private int id; 
	private int protocol;
	private List<Parameter> parameters;
	
	public MessageOld(int messageId, int protocolVersion) {
		id = messageId;
		protocol = protocolVersion;
		parameters = new ArrayList<Parameter>();
	}
	
	public List<Parameter> getParameters() {
		return parameters;
	}
	
	public void addParameter(Parameter parameter) {
		parameters.add(parameter);
	}
	
	public int getType() {
		return id;
	}
	
	public int getProtocol() {
		return protocol;
	}
}
