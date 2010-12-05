package twp.generated;

import java.util.List;

public class RPCRequest {
	
	private RPCProtocol protocol;
	private int  requestId ;
	private int  responseExpected ;
	private String  operation ;
	private List<Object>  parameters ;

	public RPCRequest(RPCProtocol p, int  requestId , int  responseExpected , String  operation , List<Object>  parameters ) {
		this.protocol = p;
		this.requestId = requestId;
		this.responseExpected = responseExpected;
		this.operation = operation;
		this.parameters = parameters;

	}

	public RPCProtocol getProtocol() {
		return protocol;
	}
	

	public int  getRequestId() {
			return requestId;
		}
	public int  getResponseExpected() {
			return responseExpected;
		}
	public String  getOperation() {
			return operation;
		}
	public List<Object>  getParameters() {
			return parameters;
		}
	
}
 