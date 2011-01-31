package twp.application;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import twp.core.SignatureHandler;
import twp.generated.EchoHandler;
import twp.generated.EchoProtocol;
import twp.generated.EchoReply;
import twp.generated.EchoRequest;


public class EchoClientApp implements EchoHandler {

	private EchoProtocol protocol;
	private boolean autoHandler;
	
	public EchoClientApp(String host, int port, boolean flag) {
		autoHandler = flag;
		KeyStore ks;
		PrivateKey key = null;
		Certificate cert = null;
		try {
			ks = KeyStore.getInstance("JKS");
			InputStream inStream = new FileInputStream("security/private_keystore.jks");
			ks.load(inStream, new String("foobar").toCharArray());
			cert = ks.getCertificate("hasso-plattner-institut id von christian wiggert");
			key = (PrivateKey) ks.getKey("hasso-plattner-institut id von christian wiggert", new String("foobar").toCharArray());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
		
		try {
			if (flag)
				protocol = new EchoProtocol(host, port, this);
			else
				protocol = new EchoProtocol(host, port);
			if (cert != null && key != null)
				protocol.setSignatureHandler(new SignatureHandler(cert, key));
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
		EchoClientApp client = new EchoClientApp("www.dcl.hpi.uni-potsdam.de", 80, false);
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
			} catch (Exception e) {
				System.out.println("Error while reading input.");
				e.printStackTrace();
				break;
			}
		}
		try {
			protocol.stop();
		} catch (IOException e) {
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
