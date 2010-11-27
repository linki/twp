package twp.core;

import java.util.ArrayList;
import java.util.List;

public class TWPContainer {
	
	private ParameterType type; 
	private int extensionId;
	private List<Parameter> parameters = new ArrayList<Parameter>();
	
	public TWPContainer(ParameterType containerType) {
		type = containerType;
	}
	
	public TWPContainer() {
		
	}
	
	public List<Parameter> getParameters() {
		return parameters;
	}
	
	public void add(Parameter parameter) {
		parameters.add(parameter);
	}
	
	public ParameterType getType() {
		return type;
	}
	
	public void setType(ParameterType containerType) {
		type = containerType;
	}
	
	public int getId() {
		return extensionId;
	}
	
	public void setId(int containerId) {
		extensionId = containerId;
	}
}
