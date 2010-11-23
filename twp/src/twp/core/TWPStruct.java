package twp.core;

import java.util.ArrayList;
import java.util.List;

public class TWPStruct {
	int id;
	List<Parameter> fields = new ArrayList<Parameter>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void add(Parameter parameter) {
		fields.add(parameter);
	}
	
	public List<Parameter> getFields() {
		return fields;
	}
}
