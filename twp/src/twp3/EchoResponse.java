package twp3;

public class EchoResponse extends DefaultResponse implements Response {
	public String getText() {
		return (String) parameters.get(0);
	}
	
	public void setText(String text) {
		parameters.set(0, text);
	}
		
	public int getNumberOfLetters() {
		return (Integer) parameters.get(1);
	}
	
	public void setNumberOfLetters(int number_of_letters) {
		parameters.set(1, number_of_letters);
	}	
}
