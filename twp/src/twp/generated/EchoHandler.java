package twp.generated;

import twpx.core.Handler;
import twpx.protocol.echo.EchoReply;
import twpx.protocol.echo.EchoRequest;

public interface EchoHandler extends Handler {
	public void onEchoRequest(EchoRequest request);
	public void onEchoReply(EchoReply reply);
}
