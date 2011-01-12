package twp.generated;

import java.util.List;

import twp.core.Container;
import twp.core.GenericSequence;
import twp.core.GenericStruct;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class Expression implements Container {
	private byte[]  host ;
	private int  port ;
	private Parameters  arguments ;

	
	public Expression(List<Object> params) {
		if (params.size() == 3) {
			host = (byte[]) params.get(0);

			port = (Integer) params.get(1);

			arguments = new Parameters((GenericSequence ) params.get(2));

		}
	}
	
	public Expression(GenericStruct struct) {
		host = (byte[]) struct.getElements().get(0);

		port = (Integer) struct.getElements().get(1);

		arguments = new Parameters((GenericSequence ) struct.getElements().get(2));
	
	}
	

	public byte[]  getHost() {
			return host;
		}
		
		public void setHost(byte[]  host) {
			this.host = host;
		}

	public int  getPort() {
			return port;
		}
		
		public void setPort(int  port) {
			this.port = port;
		}

	public Parameters  getArguments() {
			return arguments;
		}
		
		public void setArguments(Parameters  arguments) {
			this.arguments = arguments;
		}

	
	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.STRUCT);
		container.add(CalculatorProtocol.decompose(host));
		container.add(CalculatorProtocol.decompose(port));
		container.add(CalculatorProtocol.decompose(arguments));

		return container;
	}

	@Override
	public ParameterType getParameterType() {
		return ParameterType.STRUCT;
	}
}
