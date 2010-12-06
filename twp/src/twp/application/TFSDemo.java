package twp.application;

import java.io.IOException;
import java.net.UnknownHostException;

import twp.generated.ListResult;
import twp.generated.Path;
import twp.generated.StatResult;

public class TFSDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TFSClient client = new TFSClient("www.dcl.hpi.uni-potsdam.de", 80);
			Path p = new Path();
			
			ListResult result = client.listdir(p);
			System.out.println("=== Directories ===");
			for (String dir:result.getDirectories().getElements())
				System.out.println(dir);
			System.out.println("=== Files ===");
			for (String dir:result.getFiles().getElements())
				System.out.println(dir);
			System.out.println("=============");
			
			p.add("demodir");
			
			if (!result.getDirectories().getElements().contains("demodir"))
				client.mkdir(p);
			//InetAddress addr = InetAddress.getLocalHost();
			//int monitor = client.monitor(p, 1, addr.getAddress(), 12347);
			
			int file = client.open(p, "test.txt", 1);
			System.out.println("Opened File: " + file);
			
			byte[] data = "Hallo Welt!".getBytes("UTF-8");
			client.write(file, data);
			System.out.println("Wrote to File");
			
			client.close(file);
			System.out.println("Closed File");
			
			StatResult stat = client.stat(p, "test.txt"); 
			System.out.println("Stats: size: " + stat.getSize() + " Byte");
			
			file = client.open(p, "test.txt", 0);
			byte[] content = client.read(file, stat.getSize());
			System.out.println("Read File: " + new String(content, "UTF-8"));
			client.close(file);
			
			client.remove(p, "test.txt");
			client.rmdir(p);
			
			p = new Path();
			p.add("test");
			
			System.out.println("Disk Usage");
			DiskUsage du = new DiskUsage(client);
			//du.diskUsage(p, true);
			//client.stop_monitoring(monitor);
			
			client.mkdir(p);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
