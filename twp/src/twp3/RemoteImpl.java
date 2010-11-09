package twp3;

import java.util.ArrayList;
import java.util.Collection;

public class RemoteImpl implements Specification  {
	@Override	
	public Response echo(String text) {
		Response response = new DefaultResponse();
		response.addParameter(text);
		response.addParameter(text.length());
		return response;
	}
}
