package twp3.app;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import twp.generated.EchoResponse;
import twp3.custom.Specification;
import twp3.generated.LocalImpl;
import twp3.generated.EchoResult;
import twp3.generated.LocalStub;
import twp3.generated.SimpleEchoResult;

public class ClientApp {
	public static void main(String[] args) throws IOException {
		Specification spec = new LocalImpl();

		EchoResult echoResult = spec.echo("ranzdreck");
		System.out.println(echoResult.text + ", " + echoResult.number_of_letters);

		SimpleEchoResult simpleEchoResult = spec.simpleEcho("ranzdreck");
		System.out.println(simpleEchoResult.text);
		
		//System.out.println(result.get(1));
		
/*		Iterator iterator = result.iterator(); 
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		System.out.println(result.getText());
		//System.out.println(result.getNumberOfLetters());
		*/
	}
}