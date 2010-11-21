package twp.generated;

import twp.core.TWPHandler;

public interface SimpleEchoHandler extends TWPHandler {
	public void onSimpleEchoRequest(SimpleEchoRequest message);
	public void onSimpleEchoReply(SimpleEchoReply message);

}