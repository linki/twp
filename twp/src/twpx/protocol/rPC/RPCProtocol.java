package twpx.protocol.rPC;

import twpx.core.BaseProtocol;
import twpx.core.Protocol;

public class RPCProtocol extends BaseProtocol implements Protocol {

	public RPCProtocol() {
		id = 1;
		addMessage(0, new RPCRequest());
		addMessage(1, new RPCReply());
		addMessage(2, new RPCCancelRequest());
		addMessage(4, new RPCCloseConnection());

	}
}
 