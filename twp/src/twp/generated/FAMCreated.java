package twp.generated;
public class FAMCreated {
	
	private FAMProtocol protocol;
	private Path  directory ;
	private String  filename ;

	public FAMCreated(FAMProtocol p, Path  directory , String  filename ) {
		this.protocol = p;
		this.directory = directory;
		this.filename = filename;

	}

	public FAMProtocol getProtocol() {
		return protocol;
	}
	

	public Path  getDirectory() {
			return directory;
		}
	public String  getFilename() {
			return filename;
		}
	
}
 