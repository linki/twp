package twp.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class SignatureHandler {

	public static final int SIGNATURE = 28;
	public static final int CERTIFICATE = 27;
	public static final int ERROR = 29;
	
	private Certificate cert;
	private PrivateKey key;
	private Certificate external;
	
	public SignatureHandler(Certificate cert, PrivateKey key) {
		this.cert = cert;
		this.key = key;
		this.external = null;
	}
	
	public Message sign(Message message) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		TWPOutputStream twp = new TWPOutputStream(new DataOutputStream(out));
		for (Parameter param:message.getParameters()) {
			try {
				twp.writeParameter(param);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte[] content = out.toByteArray();
		try {
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(key);
			signature.update(content);
			byte[] signed = signature.sign();
			message.addParameter(generateSignedExtension(signed));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	private Parameter generateSignedExtension(byte[] content) {
		// Registered Extension ID 28 - Signature
		TWPContainer container = new TWPContainer(ParameterType.REGISTERED_EXTENSION);
		container.setId(SIGNATURE);
		container.add(new Parameter(ParameterType.LONG_BINARY, content));
		return new Parameter(ParameterType.REGISTERED_EXTENSION, container);
	}
	
	public Message getCertificateMessage() {
		Message message = new Message(CERTIFICATE, -1);
		try {
			message.addParameter(
					new Parameter(ParameterType.LONG_BINARY,
					cert.getEncoded()));
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public void storeCertificate(Message message) {
		if (message.getType() == CERTIFICATE) {
			byte[] der = (byte[]) message.getParameters().get(0).getValue();
			try {
				CertificateFactory cf = CertificateFactory.getInstance("X.509");
				external = cf.generateCertificate(new ByteArrayInputStream(der));
			} catch (CertificateException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkSignature(Message message) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		TWPOutputStream twp = new TWPOutputStream(new DataOutputStream(out));
		Parameter signP = null;
		byte[] signature = null;
		for (Parameter param:message.getParameters()) {
			if (param.getType() == ParameterType.REGISTERED_EXTENSION &&
				((TWPContainer) param.getValue()).getId() == 28) {
				signP = param;
				signature = (byte[]) ((TWPContainer) param.getValue()).getParameters().get(0).getValue();
			
			} else {
				try {
					twp.writeParameter(param);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (signature != null && external != null) { 
			 try {
				Signature verifier = Signature.getInstance("SHA1withRSA");
				verifier.initVerify(external);
				verifier.update(out.toByteArray());
				if (verifier.verify(signature)) {
					message.getParameters().remove(signP);
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (SignatureException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void handleErrorMessage(Message message) throws Exception {
		if (message.getType() == ERROR) {
			StringBuffer buffer = new StringBuffer();
			int code = (Integer) message.getParameters().get(0).getValue();
			switch(code) {
				case 0: 
					buffer.append("other error");
					break;
				case 1: 
					buffer.append("bad certificate");
					break;
				case 2:
					buffer.append("unsupported certificate");
					break;
				case 3:
					buffer.append("certificate revoked");
					break;
				case 4:
					buffer.append("certificate expired");
					break;
				case 5:
					buffer.append("certificate unknown");
					break;
				case 6:
					buffer.append("illegal parameter");
					break;
				case 7: 
					buffer.append("unknown ca");
					break;
				case 8: 
					buffer.append("access denied");
					break;
				case 9: 
					buffer.append("bad MAC");
					break;
				case 10: 
					buffer.append("certificate not yet valid");
					break;
			}
			buffer.append(" ");
			buffer.append((String) message.getParameters().get(1).getValue());
			throw new Exception(buffer.toString());
		}
	}
}
