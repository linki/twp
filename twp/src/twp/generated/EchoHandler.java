package twp.generated;

import twp.core.TWPHandler;

public interface EchoHandler extends TWPHandler {
	public void onEchoRequest(EchoRequest message);
	public void onEchoReply(EchoReply message);

}