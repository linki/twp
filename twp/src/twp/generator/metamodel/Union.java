package twp.generator.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Union extends Element {
	public List<Case> cases;
	
	public Union() {
		cases = new ArrayList<Case>();
	}
}
