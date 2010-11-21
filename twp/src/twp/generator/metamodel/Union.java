package twp.generator.metamodel;

import java.util.List;
import java.util.ArrayList;

public class Union extends Element {
	public List<Case> cases;
	
	public Union() {
		cases = new ArrayList<Case>();
	}
}
