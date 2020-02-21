package cc.sdspar.spar.network.api;

public class NetworkAPI {
	
	private static Thread messenger;

	public static void startClientAPI() {
		NetworkAPIUtils.server = false;
		if (messenger != null) messenger.interrupt();
		messenger = new Thread(new NetworkMessageChecker());
		messenger.start();
		
		Thread updates = new Thread(new Updater.CheckForUpdates());
		updates.start();
		
	}
	
	public static void startServerAPI() {
		NetworkAPIUtils.server = true;
		if (messenger != null) messenger.interrupt();
		messenger = new Thread(new NetworkMessageChecker());
		messenger.start();
		
		Thread updates = new Thread(new Updater.CheckForUpdates());
		updates.start();
		
	}

}
