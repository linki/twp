package twp.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class TWPProtocol {
	protected TWPConnection connection;
	private SignatureHandler signatureHandler;
	protected boolean doSign = false;
	protected boolean isServer = false;
	
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
				GenericUnion un = new GenericUnion(((TWPContainer)param.getValue()).getId(), compose(((TWPContainer)param.getValue()).getParameters().get(0)));
				result = un;
				break;
			case APPLICATION_TYPE:
				GenericApplicationType at = new GenericApplicationType(((TWPContainer)param.getValue()).getId(), ((TWPContainer) param.getValue()).getParameters().get(0).getValue());
				result = at;
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
	
	public static Parameter decompose(Object o) {
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
	
	protected void addExtensions(Message message, List<Container> extensions) {
		if (extensions != null) {
			for (Container container:extensions) {
				if (container.getParameterType() == ParameterType.REGISTERED_EXTENSION)
					message.addParameter(decompose(container));
			}
		}
	}
	
	protected void addExtensions(AbstractRequest request, Iterator<Parameter> iter) {
		while (iter.hasNext()) {
			request.addExtension((GenericRegisteredExtension) compose(iter.next()));
		}
	}
	
	public TWPProtocol(String host, int port) throws UnknownHostException, IOException {
		connection = new TWPConnection(host, port, this);
	}
	
	public TWPProtocol(Socket s) throws IOException {
		connection = new TWPConnection(s, this);
		isServer = true;
	}
	
	public void stop() throws IOException {
		connection.disconnect();
	}
	
	public byte[] getLocalAddress() {
		return connection.getLocalAddress();
	}
	
	public int getLocalPort() {
		return connection.getLocalPort();
	}
	
	public abstract void onMessage(Message message) throws Exception;
	
	public abstract int getVersion();
	
	public void setSignatureHandler(SignatureHandler handler) throws IOException {
		signatureHandler = handler;
		doSign = true;
		if (!isServer) {
			connection.writeMessage(signatureHandler.getCertificateMessage());
		}
	}
	
	protected boolean checkSecurity(Message message) throws Exception {
		if (message.getType() == SignatureHandler.CERTIFICATE) {
			signatureHandler.storeCertificate(message);
			if (isServer) {
				connection.writeMessage(signatureHandler.getCertificateMessage());
			}	
			return false;
		} else if (message.getType() == SignatureHandler.ERROR) {
			signatureHandler.handleErrorMessage(message);
			return false;
		} else {
			if (doSign)
				return signatureHandler.checkSignature(message);
		}
		return true;
	}
	
	protected Message sign(Message message) {
		if (doSign) {
			return signatureHandler.sign(message);
		}
		return message;
	}
}
