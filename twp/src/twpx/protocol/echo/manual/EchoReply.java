package twpx.protocol.echo.manual;

import twpx.core.BaseMessage;
import twpx.interfaces.Message;

public class EchoReply extends BaseMessage implements Message {

	public EchoReply() {
		name = "Reply";
		protocolId = 2;
		id = 1;
		addParameter("text", "string");
		addParameter("number_of_letters", "int");
	}

	public EchoReply(String text, int number_of_letters) {
		this();
		set("text", text);
		set("number_of_letters", number_of_letters);
	}

	public String getText() {
		return (String) get("text");
	}
	
	public void setText(String text) {
		set("text", text);
	}

	public int getNumberOfLetters() {
		return (Integer) get("number_of_letters");
	}
	
	public void setNumberOfLetters(int number_of_letters) {
		set("number_of_letters", number_of_letters);
	}
}
