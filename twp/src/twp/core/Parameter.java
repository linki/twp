package twp.core;

public class Parameter {

	private ParameterType type;
	private Object value;
	
	public Parameter(ParameterType t, Object v) {
		type = t;
		value = v;
	}
	
	public ParameterType getType() {
		return type;
	}
	
	public Object getValue() {
		return value;
	}
}
