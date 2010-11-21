package twpx.app;

import java.io.IOException;

import twpx.core.TWPClient;
import twpx.protocol.echo.manual.EchoReply;
import twpx.protocol.echo.manual.EchoRequest;

public class Client {
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
		
		// create a client
		// short:
		TWPClient client = new TWPClient("localhost", 1234);

		// verbose, that's happening:
		// TWPClient client = new TWPClient(new TWPConnection("www.dcl.hpi.uni-potsdam.de", 80));
		// TWPClient client = new TWPClient(new TWPConnection("localhost", 1234));
		
		// send and receive messages
		// verbose:
		client.send(new EchoRequest("dreck"));
		
		// we need to cast the returned Message in order to say getText() and getNumberOfLetters() later on
		// a non-casty-way would be nice, though, especially if we want to support some kind of error messages nicely
		EchoReply reply = (EchoReply) client.receive();
		
		// short
		// nice chaining is possible:
		// EchoReply reply = (EchoReply) client.send(new EchoRequest("dreck")).receive();
		// or:
		// EchoReply reply = (EchoReply) client.sendAndReceive(new EchoRequest("dreck"));
		
		// lets use our response as usual
		System.out.println(reply.getText());
		System.out.println(reply.getNumberOfLetters());	
	}
}
