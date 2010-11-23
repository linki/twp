package twp3.old;

import java.io.IOException;


public class ClientApp {
	public static void main(String[] args) throws IOException {
		Specification spec = new LocalImpl();

		EchoResult echoResult = spec.echo("ranzdreck");
		System.out.println(echoResult.text + ", " + echoResult.number_of_letters);

		SimpleEchoResult simpleEchoResult = spec.simpleEcho("ranzdreck");
		System.out.println(simpleEchoResult.text);
	}
}