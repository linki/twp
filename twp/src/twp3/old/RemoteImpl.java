package twp3.old;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RemoteImpl implements Specification  {
	@Override
	public EchoResult echo(String text) {
		EchoResult result = new EchoResult();
		result.text = text;
		result.number_of_letters = text.length();
		return result;
	}

	@Override
	public SimpleEchoResult simpleEcho(String text) {
		SimpleEchoResult result = new SimpleEchoResult();
		result.text = text;
		return result;
	}
}
