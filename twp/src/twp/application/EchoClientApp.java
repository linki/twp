package twp.application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import twp.generated.EchoHandler;
import twp.generated.EchoProtocol;
import twp.generated.EchoReply;
import twp.generated.EchoRequest;


public class EchoClientApp implements EchoHandler {

	private EchoProtocol protocol;
	private boolean autoHandler;
	
	public EchoClientApp(String host, int port, boolean flag) {
		autoHandler = flag;
		try {
			if (flag)
				protocol = new EchoProtocol(host, port, this);
			else
				protocol = new EchoProtocol(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EchoClientApp client = new EchoClientApp("localhost", 12347, false);
		//EchoClientApp client = new EchoClientApp("localhost", 12347, true);
		client.start();
		
	}

	public void start() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while (true) {
			try {
				System.out.print(">>");
				input = reader.readLine();
				if (!input.equals(".exit")) {
					protocol.sendRequest(input);
					if (!autoHandler) {
						EchoReply rep = protocol.receiveReply();
						System.out.println(">> " + rep.getText() + " has " + rep.getNumberOfLetters() + " letters");
					}
				} else 
					break;
			} catch (IOException e) {
				System.out.println("Error while reading input.");
				break;
			}
		}
		try {
			protocol.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onEchoReply(EchoReply reply) {
		System.out.println(">>" + reply.getText() + " has " + reply.getNumberOfLetters() + " letters");
	}

	@Override
	public void onEchoRequest(EchoRequest request) {
		// do nothing
	}

}
