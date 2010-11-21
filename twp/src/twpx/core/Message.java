package twpx.core;

import java.io.IOException;
import java.util.List;



public interface Message {

	void setConnection(TWPConnection con);
	
	Message send(boolean withHeader) throws IOException;

	int getId();
	
	String getName();
	
	Protocol getProtocol();

	void addParameter(String name, String type);
	
	List<TWPParameter> getParameters();
	
	Object get(String parameter);
	
	void set(String parameter, Object value);
}
