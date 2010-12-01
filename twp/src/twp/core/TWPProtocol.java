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
		if (param.getType() == ParameterType.STRUCT || 
			param.getType() == ParameterType.SEQUENCE) {
			for (Parameter p:((TWPContainer) param.getValue()).getParameters()) {
				switch (p.getType()) {
				case STRUCT:
				case SEQUENCE:
				case REGISTERED_EXTENSION:
				case UNION:
					list.add(getAnyDefinedBy(p));
					break;
				default:
					list.add(p.getValue());	
				}
			}
		} else if (param.getType() != ParameterType.NO_VALUE) {
			list.add(param.getValue());
		}
		return list;
	}
	
	protected TWPContainer decompose(Sequence s) {
		ParameterType type = s.getType();
		TWPContainer container = new TWPContainer(ParameterType.SEQUENCE);
		for (Object elem:s.getElements()) {	
			if (type == ParameterType.SEQUENCE) {
				container.add(new Parameter(type, decompose((Sequence) elem)));
			} else {
				container.add(new Parameter(type, elem));						
			}
		}
		return container;
	}
	
	protected Parameter setAnyDefinedBy(List<Object> list) {
		Parameter param = null;
		if (list.size() == 0) 
			param = new Parameter(ParameterType.NO_VALUE, null);
		else if (list.size() == 1) {
			Object o = list.get(0);
			if (o instanceof String) 
				param = new Parameter(ParameterType.LONG_STRING, o);
			else if (o instanceof Integer)
				param = new Parameter(ParameterType.LONG_INTEGER, o);
			else if (o instanceof byte[])
				param = new Parameter(ParameterType.LONG_BINARY, o);
			if (o instanceof Sequence) {
				ParameterType type = ((Sequence) o).getType();
				TWPContainer container = new TWPContainer(ParameterType.SEQUENCE);
				for (Object elem:((Sequence) o).getElements()) {	
					if (type == ParameterType.STRUCT ||
						type == ParameterType.SEQUENCE ||
						type == ParameterType.REGISTERED_EXTENSION ||
						type == ParameterType.UNION) {
						container.add(new Parameter(type, decompose((Sequence) elem))); 
					} else {
						container.add(new Parameter(type, elem));						
					}
				}
				param = new Parameter(ParameterType.SEQUENCE, container);	
			}
			
		} else {
			TWPContainer container = new TWPContainer(ParameterType.STRUCT);
			for (Object o:list) {
				if (o instanceof String) 
					container.add(new Parameter(ParameterType.LONG_STRING, o));
				else if (o instanceof Integer)
					container.add(new Parameter(ParameterType.LONG_INTEGER, o));
				else if (o instanceof byte[])
					container.add(new Parameter(ParameterType.LONG_BINARY, o));
				if (o instanceof Sequence) {
					ParameterType type = ((Sequence) o).getType();
					TWPContainer container2 = new TWPContainer(ParameterType.SEQUENCE);
					for (Object elem:((Sequence) o).getElements()) {	
						if (type == ParameterType.STRUCT ||
							type == ParameterType.SEQUENCE ||
							type == ParameterType.REGISTERED_EXTENSION ||
							type == ParameterType.UNION) {
							container2.add(new Parameter(type, decompose((Sequence) elem))); 
						} else {
							container2.add(new Parameter(type, elem));						
						}
					}
					container.add(new Parameter(ParameterType.SEQUENCE, container2));	
				}
			}
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
