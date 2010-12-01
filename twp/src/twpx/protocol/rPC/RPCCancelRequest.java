package twpx.protocol.rPC;

import twpx.core.BaseMessage;
import twpx.core.Message;

public class RPCCancelRequest extends BaseMessage implements Message {

	public RPCCancelRequest() {
		name = "CancelRequest";
		protocolId = 1;
		id = 2;
		addParameter("request_id", "int");

	}

	public RPCCancelRequest(int  requestId ) {
		this();
		set("request_id", requestId);

	}

	public int  getRequestId() {
		return (Integer) get("request_id");
	}
	
	public void setRequestId(int  requestId) {
		set("request_id", requestId);
	}

}

 