package twp.generated;

import twp.core.TWPHandler;

public interface CalculatorHandler extends TWPHandler {
	public void onCalculatorRequest(CalculatorRequest message);
	public void onCalculatorReply(CalculatorReply message);
	public void onCalculatorError(CalculatorError message);

}