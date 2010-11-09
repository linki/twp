package twp3;

import java.util.List;

public interface Response {
	List getParameters();

	void addParameter(Object o);

	Object getParameter(int i);
}
