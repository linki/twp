package twp3.app;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import twp3.custom.Specification;
import twp3.generated.LocalImpl;
import twp3.generated.EchoResult;
import twp3.generated.LocalStub;
import twp3.generated.SimpleEchoResult;

public class ClientApp {
	public static void main(String[] args) throws IOException {
		/*Specification local = new LocalImpl();
		List<Object> response = local.echo("ranzdreck");
		
		System.out.println(response.get(0));
		System.out.println(response.get(1));
		
		EchoResult result = new EchoResult(response);
		
		Iterator iterator = result.getResponse().iterator(); 
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		System.out.println(result.getText());
		System.out.println(result.getNumberOfLetters());
*/		
		LocalStub local = new LocalStub();
		SimpleEchoResult result = local.simpleEcho("ranzdreck");
		
		System.out.println(result.get(0));
		//System.out.println(result.get(1));
		
		Iterator iterator = result.iterator(); 
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		System.out.println(result.getText());
		//System.out.println(result.getNumberOfLetters());
		
	}
}