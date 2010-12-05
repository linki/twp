package twp.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public abstract class TWPProtocol {
	protected TWPConnection connection;
	
	protected List<Object> getAnyDefinedBy(Parameter param) {
		List<Object> list = new ArrayList<Object>();
		if (param.getType() == ParameterType.STRUCT) {
			for (Parameter p:((TWPContainer) param.getValue()).getParameters())
				list.add(compose(p));
		} else if (param.getType() != ParameterType.NO_VALUE) {
			list.add(compose(param));
		}
		return list;
	}
	
	protected Object compose(Parameter param) {
		Object result = null;
		switch (param.getType()) {	
			case STRUCT:
				GenericStruct struct = new GenericStruct();
				for (Parameter p:((TWPContainer) param.getValue()).getParameters())
					struct.addElement(compose(p));
				result = struct;
				break;
			case SEQUENCE:
				GenericSequence seq = new GenericSequence();
				for (Parameter p:((TWPContainer) param.getValue()).getParameters())
					seq.addElement(compose(p));
				result = seq;
				break;
			case REGISTERED_EXTENSION:
				GenericRegisteredExtension gre = new GenericRegisteredExtension(((TWPContainer)param.getValue()).getId());
				for (Parameter p:((TWPContainer) param.getValue()).getParameters())
					gre.addElement(compose(p));
				result = gre;
				break;
			case UNION:
				GenericUnion un = new GenericUnion(((TWPContainer)param.getValue()).getId(), compose(param));
				result = un;
				break;
			case NO_VALUE:
				result = null;
				break;
			default:
				result = param.getValue();
				break;
		}
		return result;
	}
	
	protected static Parameter decompose(Object o) {
		Parameter param = null;
		if (o instanceof String) 
			param = new Parameter(ParameterType.LONG_STRING, o);
		else if (o instanceof Integer)
			param = new Parameter(ParameterType.LONG_INTEGER, o);
		else if (o instanceof byte[])
			param = new Parameter(ParameterType.LONG_BINARY, o);
		else if (o instanceof Container) 
			param = new Parameter(((Container) o).getParameterType(), ((Container) o).toContainer());	
		
		return param;
	}
	
	public static Parameter setAnyDefinedBy(List<Object> list) {
		Parameter param = null;
		if (list.size() == 0) 
			param = new Parameter(ParameterType.NO_VALUE, null);
		else if (list.size() == 1)
			param = decompose(list.get(0));
		else {
			TWPContainer container = new TWPContainer(ParameterType.STRUCT);
			for (Object o:list)
				container.add(decompose(o));
			param = new Parameter(ParameterType.STRUCT, container);
		}
		return param;
	}
	
	public TWPProtocol(String host, int port) throws UnknownHostException, IOException {
		connection = new TWPConnection(host, port, this);
	}
	
	public TWPProtocol(Socket s) throws IOException {
		connection = new TWPConnection(s, this);
	}
	
	public void stop() throws IOException {
		connection.disconnect();
	}
	
	public abstract void onMessage(Message message) throws IOException;
	
	public abstract int getVersion();
}
