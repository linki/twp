package twpx.protocol.echo.manual;

import twpx.core.BaseMessage;
import twpx.interfaces.Message;

public class EchoRequest extends BaseMessage implements Message {

	public EchoRequest() {
		name = "Request";
		protocolId = 2;
		id = 0;
		addParameter("text", "string");
	}

	public EchoRequest(String text) {
		this();
		set("text", text);
	}

	public String getText() {
		return (String) get("text");
	}
	
	public void setText(String text) {
		set("text", text);
	}
}
