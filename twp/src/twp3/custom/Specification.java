package twp3.custom;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import twp3.generated.EchoResult;
import twp3.generated.SimpleEchoResult;

public interface Specification {
	EchoResult echo(String text) throws IOException;
	SimpleEchoResult simpleEcho(String text) throws IOException;
}
