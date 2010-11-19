package twp.generated;

import twpx.protocol.echo.EchoRequest;

public interface RequestHandler {
	public EchoResponse onEcho(EchoRequest request);
}
