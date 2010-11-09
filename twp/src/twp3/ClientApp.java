package twp3;

import java.io.IOException;
import java.util.Iterator;

public class ClientApp {
	public static void main(String[] args) throws IOException {
		Specification local = new LocalImpl();
		Response response = local.echo("ranzdreck");
		
		Iterator iterator = response.getParameters().iterator(); 
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println(((EchoResponse) response).getText());
		System.out.println(((EchoResponse) response).getNumberOfLetters());
	}
}