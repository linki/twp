package twp3.core;

import java.util.Iterator;
import java.util.List;

public interface Result {
	List<Object> getResponse();

	Object get(int i);
	
	Iterator iterator();
}
