package twp3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import twp.TWPClient;

public class LocalImpl implements Specification {
	@Override
	public Response echo(String text) throws IOException {
		TWPClient twp = new TWPClient("localhost", 6666);
		twp.connect();
		
		twp.writeMagicBytes();
		twp.writeProtocol(2);
		twp.writeMessage(0);
		twp.writeString(text);
		twp.writeEndOfMessage();
		
		int messageId = twp.readMessage();
		String returnedText = twp.readString();
		int length = twp.readInteger();
		twp.readEndOfMessage();
		
		twp.disconnect();
		
		EchoResponse response = new EchoResponse();
		response.addParameter(returnedText);
		response.addParameter(length);
		
		return response;
	}
}
