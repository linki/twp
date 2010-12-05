import java.io.IOException;
import java.net.UnknownHostException;

import twp.Client;
import twp.Message;
import twp.Parameter;
import twp.Protocol;
import twp.Specification;

public class SecondTest {

	// usage
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Specification specification = new Specification();
		Protocol protocol = new Protocol("Echo", 2);
		
		Message message1 = new Message("Request", 0);
		message1.addParameter(new Parameter("text", "string"));
		
		protocol.addMessage(message1);
		
		Message message2 = new Message("Reply", 1);
		message2.addParameter(new Parameter("text", "string"));
		message2.addParameter(new Parameter("number_of_letters", "int"));
		
		protocol.addMessage(message2);
		
		specification.addProtocol(protocol);
		
		/*Iterator<Protocol> iterator = specification.getProtocols().iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getName());
		}*/
		
		Client client = new Client(specification);
		String result = client.invoke("echo", "oink");
		System.out.println(result);
		
	}

}
