package twpx.protocol.echo;

import twpx.core.BaseMessage;
import twpx.interfaces.Message;

public class EchoReply extends BaseMessage implements Message {

	public EchoReply() {
		name = "Reply";
		protocolId = 22;
		id = 1;
		addParameter("text", "string");
		addParameter("number_of_letters", "int");

	}

	public EchoReply(String  text , int  numberOfLetters ) {
		this();
		set("text", text);
		set("number_of_letters", numberOfLetters);

	}

	public String  getText() {
		return (String) get("text");
	}
	
	public void setText(String  text) {
		set("text", text);
	}

	public int  getNumberOfLetters() {
		return (Integer) get("number_of_letters");
	}
	
	public void setNumberOfLetters(int  numberOfLetters) {
		set("number_of_letters", numberOfLetters);
	}

}

 