package twp.generated;
public class RPCCancelRequest {
	
	private RPCProtocol protocol;
	private int  requestId ;

	public RPCCancelRequest(RPCProtocol p, int  requestId ) {
		this.protocol = p;
		this.requestId = requestId;

	}

	public RPCProtocol getProtocol() {
		return protocol;
	}
	

	public int  getRequestId() {
			return requestId;
		}
	
}
 