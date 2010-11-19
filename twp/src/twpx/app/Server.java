package twpx.app;

import java.io.IOException;

import twpx.core.Message;
import twpx.core.Handler;
import twpx.core.TWPServer;
import twpx.protocol.echo.EchoProtocol;
import twpx.protocol.echo.EchoReply;
import twpx.protocol.echo.EchoRequest;

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
		server.register(new EchoProtocol(), new MyEchoHandler());
		
		// start the server on the specified port
		server.listen(1234);
	}
}
