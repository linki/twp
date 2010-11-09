package twp3.generated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import twp.TWPClient;
import twp3.custom.Specification;

public class LocalStub {
	Specification impl;
	
	public LocalStub() {
		impl = new LocalImpl();
	}
	
	public EchoResult echo(String text) throws IOException {
		return null;
		//return new EchoResult(impl.echo(text));
	}

	public SimpleEchoResult simpleEcho(String text) throws IOException {
		return null;
		//return new SimpleEchoResult(impl.simpleEcho(text));
	}
}
