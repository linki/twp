package twp.application;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import twp.application.DomainResolver.DomainBundle;
import twp.core.GenericRegisteredExtension;
import twp.generated.ListResult;
import twp.generated.Path;
import twp.generated.RPCException;
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
	
	public TFSClient(String host) throws UnknownHostException, IOException {
		DomainResolver dr = new DomainResolver();
		if (!host.endsWith("."))
			host = host.concat(".");
		DomainBundle db = dr.resolve(host);
		if (db == null)
			throw new UnknownHostException();
		protocol = new RPCProtocol(db.host, db.port);
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
    	checkForError(rep);
    	int result = (Integer) rep.getResult().get(0);
    	return result;
	}
	
	public byte[] read(int fh, int count) throws IOException {
		List<Object> params = new ArrayList<Object>();
		params.add(fh);
		params.add(count);
		protocol.sendRequest(id++, 1, "read", params);
		RPCReply rep = protocol.receiveReply(); 
		checkForError(rep);
		return (byte[]) rep.getResult().get(0);
		
	}
    
	public void write(int fh, byte[] data) throws IOException {
		List<Object> params = new ArrayList<Object>();
		params.add(fh);
		params.add(data);
		protocol.sendRequest(id++, 1, "write", params);
		RPCReply rep = protocol.receiveReply();
		checkForError(rep);
    }
    
	public void seek(int fh, int offset) throws IOException {
		List<Object> params = new ArrayList<Object>();
		params.add(fh);
		params.add(offset);
		protocol.sendRequest(id++, 1, "seek", params);
		RPCReply rep = protocol.receiveReply();
		checkForError(rep);
	}
	
	// oneway
    public void close(int fh) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(fh);
		protocol.sendRequest(id++, 1, "close", params);
		RPCReply reply = protocol.receiveReply();
		checkForError(reply);
    }

    // File and directory information 
    public ListResult listdir(Path directory) throws IOException {
    	List<Object> params = new ArrayList<Object>();
    	params.add(directory);
    	protocol.sendRequest(id++, 1, "listdir", params);
    	RPCReply rep = protocol.receiveReply();
    	checkForError(rep);
    	ListResult result = new ListResult(rep.getResult());
    	return result;
    }
    
    public StatResult stat(Path directory, String file) throws IOException {
    	List<Object> params = new ArrayList<Object>();
    	params.add(directory);
    	params.add(file);
    	protocol.sendRequest(id++, 1, "stat", params);
    	RPCReply rep = protocol.receiveReply();
    	checkForError(rep);
    	StatResult result = new StatResult(rep.getResult());
    	return result;
    }

    // File and directory manipulation 
    public void mkdir(Path directory) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(directory);
		protocol.sendRequest(id++, 1, "mkdir", params);
		RPCReply reply = protocol.receiveReply();
		checkForError(reply);
    }
    
    public void rmdir(Path directory) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(directory);
		protocol.sendRequest(id++, 1, "rmdir", params);
		RPCReply reply = protocol.receiveReply();
		checkForError(reply);
    }
    
    public void remove(Path directory, String file) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(directory);
		params.add(file);
		protocol.sendRequest(id++, 1, "remove", params);
		RPCReply reply = protocol.receiveReply();
		checkForError(reply);
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
		checkForError(rep);
		int result = (Integer) rep.getResult().get(0);
    	return result;
    }
    
    public void stop_monitoring(int handle) throws IOException {
    	List<Object> params = new ArrayList<Object>();
		params.add(handle);
		protocol.sendRequest(id++, 1, "stop_monitoring", params);
		RPCReply reply = protocol.receiveReply();
		checkForError(reply);
    }
    
    private void checkForError(RPCReply reply) throws IOException {
    	if (reply.getResult().size() > 0 && reply.getResult().get(0) instanceof GenericRegisteredExtension) {
			RPCException ex = new RPCException((GenericRegisteredExtension) reply.getResult().get(0));
			throw new IOException(ex.getText());
		}
    }
	
}
