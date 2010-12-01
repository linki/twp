package twpx.protocol.rPC;

import twpx.core.BaseMessage;
import twpx.core.Message;

public class RPCRequest extends BaseMessage implements Message {

	public RPCRequest() {
		name = "Request";
		protocolId = 1;
		id = 0;
		addParameter("request_id", "int");
		addParameter("response_expected", "int");
		addParameter("operation", "string");
		addParameter("parameters", "operation");

	}

	public RPCRequest(int  requestId , int  responseExpected , String  operation , Operation  parameters ) {
		this();
		set("request_id", requestId);
		set("response_expected", responseExpected);
		set("operation", operation);
		set("parameters", parameters);

	}

	public int  getRequestId() {
		return (Integer) get("request_id");
	}
	
	public void setRequestId(int  requestId) {
		set("request_id", requestId);
	}

	public int  getResponseExpected() {
		return (Integer) get("response_expected");
	}
	
	public void setResponseExpected(int  responseExpected) {
		set("response_expected", responseExpected);
	}

	public String  getOperation() {
		return (String) get("operation");
	}
	
	public void setOperation(String  operation) {
		set("operation", operation);
	}

	public Operation  getParameters() {
		return (Operation) get("parameters");
	}
	
	public void setParameters(Operation  parameters) {
		set("parameters", parameters);
	}

}

 