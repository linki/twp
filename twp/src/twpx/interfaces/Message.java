package twpx.interfaces;

import java.io.IOException;
import java.util.List;

import twpx.core.TWPConnection;
import twpx.core.TWPParameter;


public interface Message {

	void setConnection(TWPConnection con);
	
	Message send(boolean withHeader) throws IOException;

	int getId();
	
	String getName();
	
	int getProtocolId();

	void addParameter(String name, String type);
	
	List<TWPParameter> getParameters();
	
	Object get(String parameter);
	
	void set(String parameter, Object value);
}
