import java.io.IOException;
import java.net.UnknownHostException;

import twp.TWPClient;

class Example {
	
	public String echo(String text) throws UnknownHostException, IOException {
	
		TWPClient twp = new TWPClient("www.dcl.hpi.uni-potsdam.de", 80);
		twp.connect();
		
		twp.writeMagicBytes();
		twp.writeProtocol(2);
		twp.writeMessageId(0);
		twp.writeString(text);
		twp.writeEndOfMessage();
		
		// if message is something like exception or close connection,
		// we'll have to deal with that
		int message = twp.readMessageId();
		
		String returnedText = twp.readString();
		int length = twp.readInteger();
		twp.readEndOfMessage();
		
		twp.disconnect();
		
		return returnedText + ", " + length;
	}
	
}

public class FirstTest {

	// usage
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Example ex = new Example();
		String result = ex.echo("oink");
		System.out.println(result);
		
	}

}
