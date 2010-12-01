package twp.generated;

import twp.core.TWPHandler;

public interface RPCHandler extends TWPHandler {
	public void onRPCRequest(RPCRequest message);
	public void onRPCReply(RPCReply message);
	public void onRPCCancelRequest(RPCCancelRequest message);
	public void onRPCCloseConnection(RPCCloseConnection message);

}