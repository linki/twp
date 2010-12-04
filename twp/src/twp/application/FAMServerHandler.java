package twp.application;

import twp.generated.FAMChanged;
import twp.generated.FAMCreated;
import twp.generated.FAMDeleted;
import twp.generated.FAMHandler;
import twp.generated.FAMStartExecuting;
import twp.generated.FAMStopExecuting;
import twp.generated.Path;

public class FAMServerHandler implements FAMHandler {

	private void printFileName(Path directory, String filename) {
		for (String part:directory.getElements()) 
			System.out.print(part + "/");
		System.out.println(filename);
	}
	
	@Override
	public void onFAMChanged(FAMChanged message) {
		System.out.print("Changed File: /");
		printFileName(message.getDirectory(), message.getFilename());
	}

	@Override
	public void onFAMCreated(FAMCreated message) {
		System.out.print("Created File: /");
		printFileName(message.getDirectory(), message.getFilename());
	}

	@Override
	public void onFAMDeleted(FAMDeleted message) {
		System.out.print("Deleted File: /");
		printFileName(message.getDirectory(), message.getFilename());
	}

	@Override
	public void onFAMStartExecuting(FAMStartExecuting message) {
		System.out.print("Started executing File: /");
		printFileName(message.getDirectory(), message.getFilename());
	}

	@Override
	public void onFAMStopExecuting(FAMStopExecuting message) {
		System.out.print("Stopped Executing File: /");
		printFileName(message.getDirectory(), message.getFilename());
	}

}
