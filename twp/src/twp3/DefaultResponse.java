package twp3;

import java.util.ArrayList;
import java.util.List;

public class DefaultResponse implements Response {
	List parameters;

	public DefaultResponse() {
		this.parameters = new ArrayList(2);
	}
	
	@Override
	public List getParameters() {
		return parameters;
	}
	
	@Override
	public void addParameter(Object o) {
		parameters.add(o);
	}

	@Override
	public Object getParameter(int i) {
		return parameters.get(i);
	}
}
