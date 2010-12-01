package twp.generated;

import java.util.Iterator;

import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class RPCException {
	
	private String text;
	
	public RPCException(TWPContainer container) {
		// optional: check id for registered extensions
		if (container.getType() != ParameterType.STRUCT)
			return;
		Iterator<Parameter> iter = container.getParameters().iterator();
		text = (String) iter.next().getValue();
	}
	
	public String getText() {
		return text;
	}
}
