package twpx.protocol.simpleEcho;

import twpx.core.BaseMessage;
import twpx.core.Message;

public class SimpleEchoReply extends BaseMessage implements Message {

	public SimpleEchoReply() {
		name = "Reply";
		protocolId = 23;
		id = 1;
		addParameter("text", "string");

	}

	public SimpleEchoReply(String  text ) {
		this();
		set("text", text);

	}

	public String  getText() {
		return (String) get("text");
	}
	
	public void setText(String  text) {
		set("text", text);
	}

}

 