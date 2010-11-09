package twp3.custom;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface Specification {
	List<Object> echo(String text) throws IOException;
	String simpleEcho(String text) throws IOException;
}
