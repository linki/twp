package twpx.protocol.echo.manual;

import twpx.core.BaseProtocol;
import twpx.core.Protocol;

public class EchoProtocol extends BaseProtocol implements Protocol {

	public void configure() {
		id = 2;
		addMessage(0, new EchoRequest());
		addMessage(1, new EchoReply());
	}
	
	// singleton
	public static synchronized Protocol instance() { 
		if (instance == null) {
			instance = new EchoProtocol();
			instance.configure();
		}
		return instance;
	}
	
	private EchoProtocol() {} 
}
