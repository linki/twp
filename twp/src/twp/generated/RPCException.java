package twp.generated;

import java.util.List;

import twp.core.Container;
import twp.core.GenericStruct;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class RPCException implements Container {
	private String  text ;

	
	public RPCException(List<Object> params) {
		if (params.size() == 1) {
			text = (String) params.get(0);


		}
	}
	
	public RPCException(GenericStruct struct) {
		text = (String) struct.getElements().get(0);

	
	}
	

	public String  getText() {
			return text;
		}
		
		public void setText(String  text) {
			this.text = text;
		}

	
	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.STRUCT);
		container.add(RPCProtocol.decompose(text));

		return container;
	}

	@Override
	public ParameterType getParameterType() {
		return ParameterType.STRUCT;
	}
}
