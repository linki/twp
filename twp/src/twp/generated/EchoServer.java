package twp.generated;

import java.io.IOException;
import java.net.Socket;

import twp.core.TWPServer;

public class EchoServer extends TWPServer {

	public EchoServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void openConnection(Socket socket) {
		if (handler != null)
			try {
				new EchoProtocol(socket, handler);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
