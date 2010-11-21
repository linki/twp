package twpx.protocol.echo.manual;

import twpx.core.BaseProtocol;
import twpx.interfaces.Protocol;

public class EchoProtocol extends BaseProtocol implements Protocol {

	public EchoProtocol() {
		id = 2;
		addMessage(0, new EchoRequest());
		addMessage(1, new EchoReply());
	}
}
