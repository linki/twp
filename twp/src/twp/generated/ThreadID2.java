package twp.generated;

import java.net.InetAddress;
import java.net.UnknownHostException;

import twp.core.Container;
import twp.core.Extension;
import twp.core.GenericRegisteredExtension;
import twp.core.Parameter;
import twp.core.ParameterType;
import twp.core.TWPContainer;

public class ThreadID2 implements Container, Extension {
	
	public static final int ID = 30;
	
	private byte[] initiator; /* client ip address (IPv4 or IPv6) */
	private int port; /* client tcp port */
	private int seconds;

	public ThreadID2(GenericRegisteredExtension regex) {
		initiator = (byte[]) regex.getElements().get(0);
		port = (Integer) regex.getElements().get(1);
		seconds = (Integer) regex.getElements().get(2);
	}
	
	public byte[] getInitiator() {
		return initiator;
	}

	public void setInitiator(byte[] initiator) {
		this.initiator = initiator;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	@Override
	public ParameterType getParameterType() {
		return ParameterType.REGISTERED_EXTENSION;
	}
	
	@Override
	public TWPContainer toContainer() {
		TWPContainer container = new TWPContainer(ParameterType.REGISTERED_EXTENSION);
		container.setId(ID);
		container.add(new Parameter(ParameterType.LONG_BINARY, initiator));
		container.add(new Parameter(ParameterType.LONG_INTEGER, port));
		container.add(new Parameter(ParameterType.LONG_INTEGER, seconds));
		return container;
	}

	@Override
	public int getId() {
		return ID;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		try {
			InetAddress addr = InetAddress.getByAddress(initiator);
			sb.append(addr.getHostAddress());
			sb.append(":");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		sb.append(port);
		sb.append(":");
		sb.append(seconds);
		return sb.toString();
	}
}
