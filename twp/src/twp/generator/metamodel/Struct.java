package twp.generator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Struct {
	public String name;
	public int id;
	public List<Field> fields;
	
	public Struct() {
		fields = new ArrayList<Field>();
	}
}
