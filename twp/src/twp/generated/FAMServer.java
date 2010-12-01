package twp.generated;

import java.io.IOException;
import java.net.Socket;

import twp.core.TWPServer;

public class FAMServer extends TWPServer {

	public FAMServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void openConnection(Socket socket) {
		if (handler != null)
			try {
				new FAMProtocol(socket, (FAMHandler) handler);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
 