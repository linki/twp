package twpx.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TWPServer {
	
	private Map<Integer, Handler> handlers = new HashMap<Integer, Handler>();
	
	public void listen(int port) throws IOException {
		ServerSocket server = new ServerSocket(port);
		while (true) {
			Socket socket = server.accept();
			TWPConnection con = new TWPConnection(socket);
			Message message = MessageManager.receive(con);
			Message answer = handle(message);
			answer.setConnection(con);
			answer.send(false); // no header
			socket.close();
			// more messages...
		}	
	}

	public void register(Protocol protocol, Handler handler) {
		MessageManager.register(protocol);
		setHandler(protocol, handler);
	}
	
	private void setHandler(Protocol protocol, Handler handler) {
		handlers.put(protocol.getId(), handler);
	}

	private Message handle(Message message) {
		return handlers.get(message.getProtocolId()).handle(message);
	}
}
