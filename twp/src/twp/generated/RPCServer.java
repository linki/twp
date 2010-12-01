package twp.generated;

import java.io.IOException;
import java.net.Socket;

import twp.core.TWPServer;

public class RPCServer extends TWPServer {

	public RPCServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void openConnection(Socket socket) {
		if (handler != null)
			try {
				new RPCProtocol(socket, (RPCHandler) handler);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
 