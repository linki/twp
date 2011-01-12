package twp.generated;

import java.io.IOException;
import java.net.Socket;

import twp.core.TWPServer;

public class LoggingServer extends TWPServer {

	public LoggingServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void openConnection(Socket socket) {
		if (handler != null)
			try {
				new LoggingProtocol(socket, (LoggingHandler) handler);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
 