package twp.generated.manual;

import twp.core.TWPHandler;

public interface EchoHandler extends TWPHandler {
	public void onEchoRequest(EchoRequest request);
	public void onEchoReply(EchoReply reply);
}
