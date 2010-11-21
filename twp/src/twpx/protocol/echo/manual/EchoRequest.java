package twpx.protocol.echo.manual;

import twpx.core.BaseMessage;
import twpx.core.Message;

public class EchoRequest extends BaseMessage implements Message {

	public EchoRequest() {
		name = "Request";
		protocol = EchoProtocol.instance();
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
