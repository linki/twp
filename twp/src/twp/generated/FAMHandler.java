package twp.generated;

import twp.core.TWPHandler;

public interface FAMHandler extends TWPHandler {
	public void onFAMChanged(FAMChanged message);
	public void onFAMDeleted(FAMDeleted message);
	public void onFAMCreated(FAMCreated message);
	public void onFAMStartExecuting(FAMStartExecuting message);
	public void onFAMStopExecuting(FAMStopExecuting message);

}