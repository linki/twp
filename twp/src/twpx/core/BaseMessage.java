package twpx.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseMessage implements Message {

	public TWPConnection con;
	
	public String name;
	public int protocolId;
	public int id;
	
	public List<TWPParameter> parameters = new ArrayList<TWPParameter>();
	public Map<String, Object> parameterValues = new HashMap<String, Object>();
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getProtocolId() {
		return protocolId;
	}
	
	@Override
	public Object get(String parameter) {
		return parameterValues.get(parameter);
	}
	
	@Override
	public void set(String parameter, Object value) {
		parameterValues.put(parameter, value);
	}
	
	@Override
	public Message send(boolean withHeader) throws IOException {
		MessageManager.send(con, this, withHeader);
		return this;
	}

	@Override
	public void addParameter(String name, String type) {
		parameters.add(new TWPParameter(name, type));
		parameterValues.put(name, null);
	}
	
	@Override
	public List<TWPParameter> getParameters() {
		return parameters;
	}	

	@Override
	public void setConnection(TWPConnection con) {
		this.con = con;
	}
}
