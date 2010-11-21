package twp.generated;
public class SimpleEchoReply {
	
	private SimpleEchoProtocol protocol;
	private String  text ;

	public SimpleEchoReply(SimpleEchoProtocol p, String  text ) {
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
 