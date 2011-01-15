package twp.core;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRequest {
	
	private List<GenericRegisteredExtension> extensions = new ArrayList<GenericRegisteredExtension>();

	public List<GenericRegisteredExtension> getExtensions() {
		return extensions;
	}
	
	public void addExtension(GenericRegisteredExtension container) {
		extensions.add(container);
	}
}
