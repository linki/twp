package twpx.protocol.rPC;

import twpx.core.BaseMessage;
import twpx.core.Message;

public class RPCReply extends BaseMessage implements Message {

	public RPCReply() {
		name = "Reply";
		protocolId = 1;
		id = 1;
		addParameter("request_id", "int");
		addParameter("result", "request_id");

	}

	public RPCReply(int  requestId , RequestId  result ) {
		this();
		set("request_id", requestId);
		set("result", result);

	}

	public int  getRequestId() {
		return (Integer) get("request_id");
	}
	
	public void setRequestId(int  requestId) {
		set("request_id", requestId);
	}

	public RequestId  getResult() {
		return (RequestId) get("result");
	}
	
	public void setResult(RequestId  result) {
		set("result", result);
	}

}

 