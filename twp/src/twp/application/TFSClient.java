package twp.application;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import twp.generated.ListResult;
import twp.generated.Path;
import twp.generated.RPCProtocol;
import twp.generated.RPCReply;
import twp.generated.StatResult;

// TODO: catch error: RPCException!
public class TFSClient {
	RPCProtocol protocol;
	private int id = 0;
	
	public TFSClient(String host, int port) throws UnknownHostException, IOException {
		protocol = new RPCProtocol(host, port);
	}
	
	/* path: root directory is an empty sequence */
    /* mode: 0 - read-only, 1 - truncate-and-write, 2 - append-write */
	public int open(Path directory, String file, int mode) throws IOException {
		List<Object> params = new ArrayList<Object>();
    	params.add(directory);
    	params.add(file);
    	params.add(mode);
    	protocol.sendRequest(id++, 1, "open", params);
    	RPCReply rep = protocol.receiveReply();
    	int result = (Integer) rep.getResult().get(0);
    	return result;
	}
	
	public byte[] read(int fh, int count) throws IOException {
		List<Object> params = new ArrayList<Object>();
		params.add(fh);
		params.add(count);
		protocol.sendRequest(id++, 1, "read", params);
		RPCReply rep = protocol.receiveReply(); 
		if (rep.getResult().size() == 1)
			return (byte[]) rep.getResult().get(0);
		return new byte[0];
	}
    
	public void write(int fh, byte[] data) throws IOException {
		List<Object> params = new ArrayList<Object>();
		params.add(fh);
		params.add(data);
		protocol.sendRequest(id++, 1, "write", params);
		RPCReply rep = protocol.receiveReply();
		// TODO: check for errors
    }
    
	public void seek(int fh, int offset) throws IOException {
		List<Object> params = new ArrayList<Object>();
		params.add(fh);
		params.add(offset);
		protocol.sendRequest(id++, 1, "seek", params);
		RPCReply rep = protocol.receiveReply();
		// TODO: check for errors
	}
	
	// oneway
    public void close(int fh) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(fh);
		protocol.sendRequest(id++, 1, "close", params);
		protocol.receiveReply();
    }

    // File and directory information 
    public ListResult listdir(Path directory) throws IOException {
    	List<Object> params = new ArrayList<Object>();
    	params.add(directory);
    	protocol.sendRequest(id++, 1, "listdir", params);
    	RPCReply rep = protocol.receiveReply();
    	ListResult result = new ListResult(rep.getResult());
    	return result;
    }
    
    public StatResult stat(Path directory, String file) throws IOException {
    	List<Object> params = new ArrayList<Object>();
    	params.add(directory);
    	params.add(file);
    	protocol.sendRequest(id++, 1, "stat", params);
    	RPCReply rep = protocol.receiveReply();
    	StatResult result = new StatResult(rep.getResult());
    	return result;
    }

    // File and directory manipulation 
    public void mkdir(Path directory) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(directory);
		protocol.sendRequest(id++, 1, "mkdir", params);
		protocol.receiveReply();
    }
    
    public void rmdir(Path directory) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(directory);
		protocol.sendRequest(id++, 1, "rmdir", params);
		protocol.receiveReply();
    }
    
    public void remove(Path directory, String file) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(directory);
		params.add(file);
		protocol.sendRequest(id++, 1, "remove", params);
		protocol.receiveReply();
    }

    // FAM 
    // recursive: 0 - only this directory, 1 - also subdirectories 
    public int monitor(Path directory, int recursive, byte[] host, int port) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(directory);
		params.add(recursive);
		params.add(host);
		params.add(port);
		protocol.sendRequest(id++, 1, "monitor", params);
		RPCReply rep = protocol.receiveReply();
		int result = (Integer) rep.getResult().get(0);
    	return result;
    }
    
    public void stop_monitoring(int handle) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(handle);
		protocol.sendRequest(id++, 1, "stop_monitoring", params);
		protocol.receiveReply();
    }
	
	public static void main(String[] args) {
		try {
			TFSClient client = new TFSClient("www.dcl.hpi.uni-potsdam.de", 80);
			Path p = new Path();
			p.add("test");
			
			//client.mkdir(p);
			//InetAddress addr = InetAddress.getLocalHost();
			//int monitor = client.monitor(p, 1, addr.getAddress(), 12347);
			
			int file = client.open(p, "test.txt", 1);
			System.out.println("Opened File: " + file);
			
			byte[] data = "Hallo Welt!".getBytes("UTF-8");
			client.write(file, data);
			System.out.println("Wrote to File");
			
			client.close(file);
			System.out.println("Closed File");
			
			ListResult result = client.listdir(p);
			System.out.println("=== Directories ===");
			for (String dir:result.getDirectories().getElements())
				System.out.println(dir);
			System.out.println("=== Files ===");
			for (String dir:result.getFiles().getElements())
				System.out.println(dir);
			
			StatResult stat = client.stat(p, "test.txt"); 
			System.out.println("Stats: size: " + stat.getSize() + " Byte");
			
			file = client.open(p, "test.txt", 0);
			byte[] content = client.read(file, data.length);
			System.out.println("Read File: " + new String(content, "UTF-8"));
			client.close(file);
			
			//client.stop_monitoring(monitor);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
