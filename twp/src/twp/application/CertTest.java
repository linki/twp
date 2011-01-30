package twp.application;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

public class CertTest {
	
	public static void main(String[] args) {
		InputStream inStream;
		try {
			/*inStream = new FileInputStream("/Users/gert/Documents/Uni/Master/MDS/cert.der");///Users/gert/Documents/hpi_cert.cer
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate)cf.generateCertificate(inStream);
			inStream.close();
			//System.out.println(cert);*/
			Signature signature = Signature.getInstance("SHA1withRSA");
			byte[] blank = readFile("security/data.bin");
			System.out.println(new String(blank));
			KeyStore ks = KeyStore.getInstance("JKS");
			inStream = new FileInputStream("security/keystore.jks");
			ks.load(inStream, new String("foobar").toCharArray());
			
			Certificate cert = ks.getCertificate("1");
			System.out.println(cert);
			Key key = ks.getKey("1", new String("foobar").toCharArray());
			System.out.println(key);
			signature.initSign((PrivateKey) key);
			signature.update(blank);
			byte[] signed = signature.sign();
			byte[] test = readFile("security/signature.bin");
			Signature verifier = Signature.getInstance("SHA1withRSA");
			verifier.initVerify(cert);
			verifier.update(blank);
			System.out.println(verifier.verify(signed));
			System.out.println(new String(test).equals(new String(signed)));
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static byte[] readFile(String filename) {
		File file = new File(filename);
		long size = file.length();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		FileInputStream in;
		DataInputStream din;
		try {
			in = new FileInputStream(file);
			din = new DataInputStream(in);
			while (size > 0) {
				byte[] chunk;
				if (size >= 1024) 
					chunk = new byte[1024];
				else
					chunk = new byte[(int) size];
				din.readFully(chunk);
				out.write(chunk);
				size -= chunk.length;
			}
			in.close();
			din.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

}
