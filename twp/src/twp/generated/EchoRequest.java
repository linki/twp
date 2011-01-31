package twp.generated;

import twp.core.AbstractRequest;

public class EchoRequest extends AbstractRequest {
	
	private EchoProtocol protocol;
	private String  text ;

	public EchoRequest(EchoProtocol p, String  text ) {
		this.protocol = p;
		this.text = text;

	}

	public EchoProtocol getProtocol() {
		return protocol;
	}
	

	public String  getText() {
			return text;
		}
	
}
 