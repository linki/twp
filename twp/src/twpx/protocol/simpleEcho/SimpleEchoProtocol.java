package twpx.protocol.simpleEcho;

import twpx.core.BaseProtocol;
import twpx.interfaces.Protocol;

public class SimpleEchoProtocol extends BaseProtocol implements Protocol {

	public SimpleEchoProtocol() {
		id = 23;
		addMessage(0, new SimpleEchoRequest());
		addMessage(1, new SimpleEchoReply());

	}
}
 