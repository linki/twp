package twp3.generated;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import twp3.core.DefaultResult;
import twp3.core.Result;

public class EchoResult extends DefaultResult implements Result {
	public EchoResult(List<Object> response) {
		super(response);
	}

	public String getText() {
		return (String) response.get(0);
	}
	
	public void setText(String text) {
		response.set(0, text);
	}
		
	public int getNumberOfLetters() {
		return (Integer) response.get(1);
	}
	
	public void setNumberOfLetters(int number_of_letters) {
		response.set(1, number_of_letters);
	}
}
