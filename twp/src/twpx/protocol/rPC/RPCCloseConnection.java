package twpx.protocol.rPC;

import twpx.core.BaseMessage;
import twpx.core.Message;

public class RPCCloseConnection extends BaseMessage implements Message {

	public RPCCloseConnection() {
		name = "CloseConnection";
		protocolId = 1;
		id = 4;
	}

	public RPCCloseConnection() {
		this();
	}
}

 