package twpx.protocol.simpleEcho;

import twpx.core.BaseMessage;
import twpx.interfaces.Message;

public class SimpleEchoRequest extends BaseMessage implements Message {

	public SimpleEchoRequest() {
		name = "Request";
		protocolId = 23;
		id = 0;
		addParameter("text", "string");

	}

	public SimpleEchoRequest(String  text ) {
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

 