package twp3;

import java.io.IOException;
import java.util.Collection;

public interface Specification {
	Response echo(String text) throws IOException;
}
