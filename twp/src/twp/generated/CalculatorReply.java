package twp.generated;

import twp.core.AbstractRequest;


public class CalculatorReply extends AbstractRequest {
	
	private CalculatorProtocol protocol;
	private int  requestId ;
	private Double  result ;

	public CalculatorReply(CalculatorProtocol p, int  requestId , Double  result ) {
		this.protocol = p;
		this.requestId = requestId;
		this.result = result;

	}

	public CalculatorProtocol getProtocol() {
		return protocol;
	}
	

	public int  getRequestId() {
			return requestId;
		}
	public Double  getResult() {
			return result;
		}
	
}
 