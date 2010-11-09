package twp3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerApp {
	public static void main(String[] args) throws IOException {
		Specification remote = new RemoteImpl();
		Server server = new Server(remote);
		server.start();
	}
}