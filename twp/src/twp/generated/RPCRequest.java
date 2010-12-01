package twp.generated;

import java.util.ArrayList;
import java.util.List;

public class RPCRequest {
	
	private RPCProtocol protocol;
	private int  requestId ;
	private int  responseExpected ;
	private String  operation ;
	private List<Object>  parameters = new ArrayList<Object>();

	public RPCRequest(RPCProtocol p, int  requestId , int  responseExpected , String  operation , List<Object>  parameters ) {
		this.protocol = p;
		this.requestId = requestId;
		this.responseExpected = responseExpected;
		this.operation = operation;
		this.parameters = parameters;

	}
	
	public RPCRequest(RPCProtocol p) {
		protocol = p;
	}

	public RPCProtocol getProtocol() {
		return protocol;
	}
	

	public int  getRequestId() {
			return requestId;
		}
	
	public void setRequestId(int id) {
		requestId = id;
	}
	
	public int  getResponseExpected() {
			return responseExpected;
		}
	
	public void setResponseExpected(int id) {
		responseExpected = id;
	}
	
	public String  getOperation() {
			return operation;
		}
	
	public void setOperation(String content) {
		operation = content;
	}
	
	public List<Object>  getParameters() {
			return parameters;
		}
	
	public void setParameters(List<Object> params) {
		parameters = params;
	}
	
}
 