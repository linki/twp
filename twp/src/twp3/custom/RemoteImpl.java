package twp3.custom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RemoteImpl implements Specification  {
	@Override
	public List<Object> echo(String text) {
		List<Object> result = new ArrayList<Object>();
		result.add(text);
		result.add(text.length());
		return result;
	}

	@Override
	public String simpleEcho(String text) {
		return text;
	}
}
