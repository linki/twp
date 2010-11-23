package twp3.old;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ServerApp {
	public static void main(String[] args) throws IOException {
		Specification spec = new RemoteImpl();
		Server server = new Server(spec);
		server.start();
	}
}