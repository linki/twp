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
import java.security.cert.X509Certificate;

public class SignatureHandler {

	public static final int SIGNATURE = 28;
	public static final int CERTIFICATE = 27;
	public static final int ERROR = 29;
	
	public static final int OTHER_ERROR = 0;
	public static final int BAD_CERTIFICATE = 1;
	public static final int UNSUPPORTED_CERTIFICATE = 2;
	public static final int CERTIFICATE_REVOKED = 3;
	public static final int CERTIFICATE_EXPIRED = 4;
	public static final int CERTIFICATE_UNKNOWN = 5;
	public static final int ILLEGAL_PARAMETER = 6;
	public static final int UNKNOWN_CA = 7;
	public static final int ACCESS_DENIED = 8;
	public static final int BAD_MAC = 9;
	public static final int CERTIFICATE_NOT_YET_VALID = 10;
	
	private Certificate cert;
	private PrivateKey key;
	private X509Certificate external;
	
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
	
	public Message getErrorMessage(int id, String details) {
		Message message = new Message(ERROR, -1);
		message.addParameter(new Parameter(ParameterType.LONG_INTEGER, id));
		message.addParameter(new Parameter(ParameterType.LONG_STRING, details));
		return message;
	}
	
	public boolean storeCertificate(Message message) throws CertificateException  {
		if (message.getType() == CERTIFICATE) {
			byte[] der = (byte[]) message.getParameters().get(0).getValue();
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			external = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(der));
			external.checkValidity();
			//external.
			return true;
		}
		return false;
	}
	
	public boolean checkSignature(Message message) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		TWPOutputStream twp = new TWPOutputStream(new DataOutputStream(out));
		Parameter signP = null;
		byte[] signature = null;
		for (Parameter param:message.getParameters()) {
			if (param.getType() == ParameterType.REGISTERED_EXTENSION &&
				((TWPContainer) param.getValue()).getId() == SIGNATURE) {
				signP = param;
				signature = (byte[]) ((TWPContainer) param.getValue()).getParameters().get(0).getValue();
				break; // just use all fields until the signature is found 
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
				case OTHER_ERROR: 
					buffer.append("other error");
					break;
				case BAD_CERTIFICATE: 
					buffer.append("bad certificate");
					break;
				case UNSUPPORTED_CERTIFICATE:
					buffer.append("unsupported certificate");
					break;
				case CERTIFICATE_REVOKED:
					buffer.append("certificate revoked");
					break;
				case CERTIFICATE_EXPIRED:
					buffer.append("certificate expired");
					break;
				case CERTIFICATE_UNKNOWN:
					buffer.append("certificate unknown");
					break;
				case ILLEGAL_PARAMETER:
					buffer.append("illegal parameter");
					break;
				case UNKNOWN_CA: 
					buffer.append("unknown ca");
					break;
				case ACCESS_DENIED: 
					buffer.append("access denied");
					break;
				case BAD_MAC: 
					buffer.append("bad MAC");
					break;
				case CERTIFICATE_NOT_YET_VALID: 
					buffer.append("certificate not yet valid");
					break;
			}
			buffer.append(" ");
			buffer.append((String) message.getParameters().get(1).getValue());
			throw new Exception(buffer.toString());
		}
	}
	
	public SignatureHandler clone() {
		return new SignatureHandler(cert, key);
	}
}
