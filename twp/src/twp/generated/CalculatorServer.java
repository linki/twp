package twp.generated;

import java.io.IOException;
import java.net.Socket;

import twp.core.TWPServer;

public class CalculatorServer extends TWPServer {

	public CalculatorServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void openConnection(Socket socket) {
		if (handler != null)
			try {
				CalculatorProtocol protocol = new CalculatorProtocol(socket, (CalculatorHandler) handler);
				if (getSignatureHandler() != null) 
					protocol.setSignatureHandler(getSignatureHandler().clone());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
 