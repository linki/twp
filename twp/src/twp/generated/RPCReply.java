package twp.generated;

import java.util.List;

public class RPCReply {
	
	private RPCProtocol protocol;
	private int  requestId ;
	private List<Object>  result ;

	public RPCReply(RPCProtocol p, int  requestId , List<Object>  result ) {
		this.protocol = p;
		this.requestId = requestId;
		this.result = result;

	}

	public RPCProtocol getProtocol() {
		return protocol;
	}
	

	public int  getRequestId() {
			return requestId;
		}
	public List<Object>  getResult() {
			return result;
		}
	
}
 