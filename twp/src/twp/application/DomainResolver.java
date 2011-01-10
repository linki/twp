package twp.application;

import java.io.IOException;

import org.xbill.DNS.DClass;
import org.xbill.DNS.Message;
import org.xbill.DNS.Name;
import org.xbill.DNS.Record;
import org.xbill.DNS.Resolver;
import org.xbill.DNS.SRVRecord;
import org.xbill.DNS.Section;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

public class DomainResolver {
	
	public class DomainBundle {
		public String host;
		public int port;
		public DomainBundle(String host, int port) {
			this.host = host;
			this.port = port;
		}
	}

	public DomainBundle resolve(String host) throws IOException {
		Resolver resolver = new SimpleResolver();
		resolver.setTCP(true);
		Record rec;
		DomainBundle result = null;
		try {
			rec = Record.newRecord(Name.fromString(host), Type.SRV, DClass.IN);
			Message message = Message.newQuery(rec);
			Message response = resolver.send(message);
			Record[] records = response.getSectionArray(Section.ANSWER);
			if (records != null && records.length > 0) {
				result = new DomainBundle(((SRVRecord)records[0]).getTarget().toString(), 
							Integer.valueOf(((SRVRecord)records[0]).getPort()));
			}

		} catch (TextParseException e) {
			e.printStackTrace();
			
		}
		return result;
	}
	
	public static void main(String[] args) {
		DomainResolver dr = new DomainResolver();
		try {
			DomainBundle bundle = dr.resolve("tfs.dcl.hpi.uni-potsdam.de.");
			if (bundle != null)
				System.out.println(bundle.host + ":" + bundle.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
