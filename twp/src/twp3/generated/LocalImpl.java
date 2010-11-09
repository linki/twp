package twp3.generated;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import twp.TWPClient;
import twp3.custom.Specification;

public class LocalImpl implements Specification {
	@Override
	public EchoResult echo(String text) throws IOException {
		TWPClient twp = new TWPClient("localhost", 6666);
		twp.connect();
		
		twp.writeMagicBytes();
		twp.writeProtocol(22);
		twp.writeMessage(0);
		twp.writeString(text);
		twp.writeEndOfMessage();
		
		int messageId = twp.readMessage();
		String returnedText = twp.readString();
		int length = twp.readInteger();
		twp.readEndOfMessage();
		
		twp.disconnect();
		
		EchoResult result = new EchoResult();
		result.text = returnedText;
		result.number_of_letters = length;
		
		return result;
	}

	@Override
	public SimpleEchoResult simpleEcho(String text) throws IOException {
		TWPClient twp = new TWPClient("localhost", 6666);
		twp.connect();
		
		twp.writeMagicBytes();
		twp.writeProtocol(23);
		twp.writeMessage(0);
		twp.writeString(text);
		twp.writeEndOfMessage();
		
		int messageId = twp.readMessage();
		String returnedText = twp.readString();
		twp.readEndOfMessage();
		
		twp.disconnect();
		
		SimpleEchoResult result = new SimpleEchoResult();
		result.text = returnedText;
		
		return result;
	}
}
