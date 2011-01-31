package twp.generated;

import twp.core.AbstractRequest;

public class EchoReply extends AbstractRequest {
	
	private EchoProtocol protocol;
	private String  text ;
	private int  numberOfLetters ;

	public EchoReply(EchoProtocol p, String  text , int  numberOfLetters ) {
		this.protocol = p;
		this.text = text;
		this.numberOfLetters = numberOfLetters;

	}

	public EchoProtocol getProtocol() {
		return protocol;
	}
	

	public String  getText() {
			return text;
		}
	public int  getNumberOfLetters() {
			return numberOfLetters;
		}
	
}
 