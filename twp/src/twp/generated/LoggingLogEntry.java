package twp.generated;

import java.util.List;

public class LoggingLogEntry {
	
	private LoggingProtocol protocol;
	private int  seconds ;
	private int  useconds ;
	private String  source ;
	private String  threadId ;
	private String  text ;

	public LoggingLogEntry(LoggingProtocol p, int  seconds , int  useconds , String  source , String  threadId , String  text ) {
		this.protocol = p;
		this.seconds = seconds;
		this.useconds = useconds;
		this.source = source;
		this.threadId = threadId;
		this.text = text;

	}

	public LoggingProtocol getProtocol() {
		return protocol;
	}
	

	public int  getSeconds() {
			return seconds;
		}
	public int  getUseconds() {
			return useconds;
		}
	public String  getSource() {
			return source;
		}
	public String  getThreadId() {
			return threadId;
		}
	public String  getText() {
			return text;
		}
	
}
 