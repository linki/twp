package twp.generated;

import twp.core.AbstractRequest;


public class CalculatorRequest extends AbstractRequest {
	
	private CalculatorProtocol protocol;
	private int  requestId ;
	private Parameters  arguments ;

	public CalculatorRequest(CalculatorProtocol p, int  requestId , Parameters  arguments ) {
		this.protocol = p;
		this.requestId = requestId;
		this.arguments = arguments;

	}

	public CalculatorProtocol getProtocol() {
		return protocol;
	}
	

	public int  getRequestId() {
			return requestId;
		}
	public Parameters  getArguments() {
			return arguments;
		}
	
}
 