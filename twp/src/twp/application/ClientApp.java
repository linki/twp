package twp.application;
import java.io.IOException;
import java.net.UnknownHostException;

import twp.generated.EchoRequest;
import twp.generated.EchoResponse;
import twp.generated.Protocol;
import twp.generated.RequestHandler;


public class ClientApp implements RequestHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Protocol protocol;
		try {
			protocol = Protocol.startClient("www.dcl.hpi.uni-potsdam.de", 80);
			EchoResponse res;
			res = protocol.echo("foobar");
			System.out.println(res.getNumberOfLetters());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public EchoResponse onEcho(EchoRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
