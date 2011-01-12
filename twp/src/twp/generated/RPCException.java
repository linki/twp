package twp.generated;

import java.util.List;

import twp.core.Container;
import twp.core.GenericRegisteredExtension;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class RPCException implements Container {
	private int id = 0;
	private String  text ;

	
	public RPCException(List<Object> params) {
		if (params.size() == 1) {
			text = (String) params.get(0);
		}
	}
	
	public RPCException(GenericRegisteredExtension regex) {
		id = regex.getId();
		text = (String) regex.getElements().get(0);
	}
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String  getText() {
			return text;
		}
		
		public void setText(String  text) {
			this.text = text;
		}

	
	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.REGISTERED_EXTENSION);
		container.setId(id);
		container.add(RPCProtocol.decompose(text));

		return container;
	}

	@Override
	public ParameterType getParameterType() {
		return ParameterType.REGISTERED_EXTENSION;
	}
}
