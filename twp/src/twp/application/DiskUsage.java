package twp.application;

import java.io.IOException;
import java.net.UnknownHostException;

import twp.generated.ListResult;
import twp.generated.Path;
import twp.generated.StatResult;

public class DiskUsage {

	private TFSClient client;
	
	public DiskUsage(String host, int port) throws UnknownHostException, IOException {
		client = new TFSClient(host, port);
	}
	
	public DiskUsage(TFSClient c) {
		client = c;
	}
	
	public int diskUsage(Path p, boolean verbose) throws IOException {
		int size = 0;
		ListResult list = client.listdir(p);
		for (String dir:list.getDirectories().getElements()) {
			Path subdir = p.clone();
			subdir.add(dir);
			size += diskUsage(subdir, verbose);
		}
		for (String file:list.getFiles().getElements()) {
			size += diskUsage(p, file, false);
		}
		if (verbose)
			System.out.println(size + "\t" + p);
		return size;
	}
	
	public int diskUsage(Path p, String filename, boolean verbose) throws IOException {
		StatResult stat = client.stat(p, filename);
		if (verbose)
			System.out.println(stat.getSize() + "\t" + p + filename);
		return stat.getSize();
	}
	
	public static void main(String[] args) {
		try {
			DiskUsage du = new DiskUsage("www.dcl.hpi.uni-potsdam.de", 80);
			Path p = new Path();
			p.add("test");
			du.diskUsage(p, true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
