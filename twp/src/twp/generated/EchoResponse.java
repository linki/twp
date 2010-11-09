package twp.generated;
public class EchoResponse {
	
	private String text;
	private int number_of_letters;
	
	public EchoResponse(String text, int length) {
		this.text = text;
		this.number_of_letters = length;
	}

	public String getText() {
		return text;
	}
	
	public int getNumberOfLetters() {
		return number_of_letters;
	}
	
}
