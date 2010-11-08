package twp;

import java.util.ArrayList;
import java.util.Collection;

public class Specification {
	Collection<Protocol> protocols;
	
	public Specification() {
		this.protocols = new ArrayList<Protocol>(); 
	}

	public void addProtocol(Protocol protocol) {
		protocols.add(protocol);
	}

	public Collection<Protocol> getProtocols() {
		return protocols;
	}
}
