package twp.generated.manual;
public class EchoRequest {
	
	private EchoProtocol protocol;
	private String text;
	
	
	public EchoRequest(EchoProtocol p, String content) {
		this.protocol = p;
		this.text = content;
	}
	
	public EchoProtocol getProtocol() {
		return protocol;
	}
	
	public String getText() {
		return text;
	}
}
