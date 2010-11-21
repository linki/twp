package twp.generated;

import java.io.IOException;
import java.net.Socket;

import twp.core.TWPServer;

public class SimpleEchoServer extends TWPServer {

	public SimpleEchoServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void openConnection(Socket socket) {
		if (handler != null)
			try {
				new SimpleEchoProtocol(socket, (SimpleEchoHandler) handler);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
 