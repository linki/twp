package twp.application;

import java.io.IOException;

import twp.generated.FAMServer;

public class FAMServerApp {

	public static void main(String[] args) {
		try {
			FAMServer server = new FAMServer(12347);
			server.listen(new FAMServerHandler());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
