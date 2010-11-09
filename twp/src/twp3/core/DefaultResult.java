package twp3.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultResult implements Result {
	protected List<Object> response;
	
	public DefaultResult(List<Object> response) {
		this.response = response;
	}
	
	public List<Object> getResponse() {
		return response;
	}
	
	public Object get(int i) {
		return response.get(i);
	}
	
	public Iterator iterator() {
		return response.iterator();
	}
}
