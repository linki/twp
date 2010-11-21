package twpx.app;

import java.io.IOException;

import twpx.core.TWPServer;
import twpx.interfaces.Handler;
import twpx.interfaces.Message;
import twpx.protocol.echo.manual.EchoProtocol;
import twpx.protocol.echo.manual.EchoReply;
import twpx.protocol.echo.manual.EchoRequest;

// the custom handler class for the server
class MyEchoHandler implements Handler {
	
	@Override
	public Message handle(Message message) {
		
		// that's still bad: find out what the message is (inside the protocol), then reply with a new message
		if (message.getName().equals("Request")) {
			
			// cast for convenience
			EchoRequest request = (EchoRequest) message;
			
			return new EchoReply(request.getText(), request.getText().length());
		}
		
		throw new IllegalArgumentException("Unknown Message!" ); 
	}
}

public class Server {
	public static void main(String[] args) throws IOException {
		
		// create a new server instance
		TWPServer server = new TWPServer();
		
		// register a protocol and assign it its own handler
		server.register(EchoProtocol.instance(), new MyEchoHandler());
		
		// start the server on the specified port
		server.listen(1234);
	}
}
