package twp.generated;
public class SimpleEchoRequest {
	
	private SimpleEchoProtocol protocol;
	private String  text ;

	public SimpleEchoRequest(SimpleEchoProtocol p, String  text ) {
		this.protocol = p;
		this.text = text;

	}

	public SimpleEchoProtocol getProtocol() {
		return protocol;
	}
	

	public String  getText() {
			return text;
		}
	
}
 