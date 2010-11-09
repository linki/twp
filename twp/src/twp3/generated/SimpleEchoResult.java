package twp3.generated;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import twp3.core.DefaultResult;
import twp3.core.Result;

public class SimpleEchoResult extends DefaultResult implements Result {
	public SimpleEchoResult(String result) {
		super(new ArrayList<Object>());
		response.add(result);
	}

	public String getText() {
		return (String) response.get(0);
	}
	
	public void setText(String text) {
		response.set(0, text);
	}
}
