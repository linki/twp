package twp.generated;
public class FAMChanged {
	
	private FAMProtocol protocol;
	private Path  directory ;
	private String  filename ;

	public FAMChanged(FAMProtocol p, Path  directory , String  filename ) {
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
 