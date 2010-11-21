package twp3.old;

import java.io.IOException;
import java.util.Collection;
import java.util.List;


public interface Specification {
	EchoResult echo(String text) throws IOException;
	SimpleEchoResult simpleEcho(String text) throws IOException;
}
