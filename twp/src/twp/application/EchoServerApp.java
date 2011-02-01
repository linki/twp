package twp.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import twp.core.SignatureHandler;
import twp.generated.EchoHandler;
import twp.generated.EchoProtocol;
import twp.generated.EchoReply;
import twp.generated.EchoRequest;
import twp.generated.EchoServer;

public class EchoServerApp implements EchoHandler {
		
	private EchoServer server;
	
	public EchoServerApp(int port) {
		try {
			server = new EchoServer(port);
			server.setSignatureHandler(createSignatureHandler());
			server.listen(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Starting Server...");
		EchoServerApp esa = new EchoServerApp(12347);
		System.out.println("Server is running...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		esa.stop();
		System.out.println("Stopped Server");
	}

	public void stop() {
		server.stop();
	}
	
	@Override
	public void onEchoReply(EchoReply reply) {
		// do nothing
	}

	@Override
	public void onEchoRequest(EchoRequest request) {
		EchoProtocol prot = request.getProtocol();
		String text = request.getText().replaceAll("\\s", "");
		List<Character> chars = new ArrayList<Character>();
		for (int i = 0; i < text.length(); i++) {
			if (!chars.contains(text.charAt(i)))
				chars.add(text.charAt(i));
		}
		try {
			prot.sendReply(request.getText(), chars.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private SignatureHandler createSignatureHandler() {
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
		if (cert != null && key != null)
			return new SignatureHandler(cert, key);
		else
			return null;
	}
}
