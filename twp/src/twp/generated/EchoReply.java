package twp.generated;
public class EchoReply {
	
	private EchoProtocol protocol;
	private String text;
	private int number_of_letters;
	
	public EchoReply(EchoProtocol p, String text, int length) {
		this.protocol = p;
		this.text = text;
		this.number_of_letters = length;
	}

	public EchoProtocol getProtocol() {
		return protocol;
	}
	
	public String getText() {
		return text;
	}
	
	public int getNumberOfLetters() {
		return number_of_letters;
	}
	
}
