package twp.core;

import java.util.List;

public interface Container {
	//public List<Object> getElements();
	public TWPContainer toContainer();
	public ParameterType getParameterType();
}
