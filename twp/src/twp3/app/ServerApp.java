package twp3.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import twp3.core.Server;
import twp3.custom.RemoteImpl;
import twp3.custom.Specification;

public class ServerApp {
	public static void main(String[] args) throws IOException {
		Specification remote = new RemoteImpl();
		Server server = new Server(remote);
		server.start();
	}
}