package twp.generator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Struct extends Element {
	
	public List<Field> fields;
	
	public Struct() {
		fields = new ArrayList<Field>();
	}
}
