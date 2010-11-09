package twp.generated;
public class EchoRequest {
	private int messageId;
	private int requestId;
	private String text;
	
	
	public EchoRequest(int mId, int rId, String content) {
		this.messageId = mId;
		this.requestId = rId;
		this.text = content;
	}
}
