package twp.generated;

import twp.core.AbstractRequest;


public class CalculatorError extends AbstractRequest {
	
	private CalculatorProtocol protocol;
	private String  text ;

	public CalculatorError(CalculatorProtocol p, String  text ) {
		this.protocol = p;
		this.text = text;

	}

	public CalculatorProtocol getProtocol() {
		return protocol;
	}
	

	public String  getText() {
			return text;
		}
	
}
 