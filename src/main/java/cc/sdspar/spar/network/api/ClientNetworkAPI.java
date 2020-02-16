package cc.sdspar.spar.network.api;

public class ClientNetworkAPI {
	
	private static Thread messenger;

	public static void startAPI() {
		if (messenger != null) messenger.interrupt();
		messenger = new Thread(new NetworkMessageChecker());
		messenger.start();
	}

}
