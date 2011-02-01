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
import twp.generated.CalculatorServer;

public class CalculatorServerManager {
	
	public static void main(String[] args) {
		List<CalculatorServer> servers = new ArrayList<CalculatorServer>();
		SignatureHandler signHandler = createSignatureHandler();
		try {
			CalculatorServer server = new CalculatorServer(12347);
			server.setSignatureHandler(signHandler);
			server.listen(new AdditionServer());
			servers.add(server);
			server = new CalculatorServer(12348);
			server.setSignatureHandler(signHandler);
			server.listen(new MultiplyServer());
			servers.add(server);
			server = new CalculatorServer(12349);
			server.setSignatureHandler(signHandler);
			server.listen(new FacultyServer());
			servers.add(server);
			server = new CalculatorServer(12350);
			server.setSignatureHandler(signHandler);
			server.listen(new SinusServer());
			servers.add(server);
			server = new CalculatorServer(12351);
			server.setSignatureHandler(signHandler);
			server.listen(new PIServer());
			servers.add(server);
			server = new CalculatorServer(12352);
			server.setSignatureHandler(signHandler);
			server.listen(new DivisionServer());
			servers.add(server);
			server = new CalculatorServer(12353);
			server.setSignatureHandler(signHandler);
			server.listen(new SubtractionServer());
			servers.add(server);
			System.out.println("Started servers");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (CalculatorServer server:servers) {
			server.stop();
		}
		System.out.println("Stopped servers");
	}
	
	private static SignatureHandler createSignatureHandler() {
		KeyStore ks;
		PrivateKey key = null;
		Certificate cert = null;
		try {
			ks = KeyStore.getInstance("JKS");
			InputStream inStream = new FileInputStream("../security/private_keystore.jks");
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
