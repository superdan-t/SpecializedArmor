package cc.sdspar.spar.network.api;

import java.util.List;

import cc.sdspar.spar.main.Ref;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

public class Updater {

	public static List<Version> distros;
	
	public static class CheckForUpdates implements Runnable {

		@Override
		public void run() {
			
			updateDistroMappings();
			
			Version newest = getNewest();
			Version current = new Version();
			current.version = Ref.VERSION;
			boolean updateAvailable = newest != null && newest.isNewerThan(current);
			boolean userReached = false;
			while (!userReached) {
				if (updateAvailable) {
					userReached = NetworkAPIUtils.notifyUser(Ref.CHAT_HEADER + "An update is available for the mod.\nNew Version: &7" + newest.version + 
							"\nCurrent Version: &7" + current.version + "\nUpdate Details: &7" + newest.tagline + "\nAutomatically update with: '/spup latest' ");
				} else {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) { }
			}
			
		}
		
	}
	
	public static class GrabUpdate implements Runnable {
		
		private ICommandSender sender;
		
		public GrabUpdate() {}
		
		public GrabUpdate(ICommandSender sender) {
			this.sender = sender;
		}

		@Override
		public void run() {
			updateDistroMappings();
			if (sender != null) sender.sendMessage(new TextComponentString("Done updating mappings..."));
//			// Testing indicates old file can't be deleted here or on shutdown, so develop an external JAR to do it instead
//			File oldFile = new File("mods/sdspar-" + Ref.VERSION + ".jar");
//			Main.logger.info(oldFile.exists());
//			if (oldFile.delete()) {
//				Main.logger.info("Successfully deleted the old file.");
//			} else {
//				System.out.println("==== MUST RETRY ====");
//				Runtime.getRuntime().addShutdownHook(new Thread() {
//					public void run() {
//						File oldFile = new File("mods/sdspar-" + Ref.VERSION + ".jar");
//						Main.logger.info(oldFile.exists());
//						Main.logger.info(oldFile.delete());
//					}
//				});
//			}
		}
		
	}
	
	public static void updateDistroMappings() {
		try {
			distros = Version.getVersions();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static boolean isNewest() {
		Version current = new Version();
		current.version = Ref.VERSION;
		for (Version v : distros) {
			if (!current.isNewerThan(v) && !Ref.VERSION.equals(v.version)) return false;
		}
		return true;
	}
	
	public static Version getNewest() {
		Version best = new Version();
		for (Version v : distros) {
			if (v.isNewerThan(best)) {
				best = v;
			}
		}
		if (best.version != "0.0.0") return best;
		else return null;
	}

}
