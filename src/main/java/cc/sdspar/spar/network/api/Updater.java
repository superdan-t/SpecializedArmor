package cc.sdspar.spar.network.api;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import cc.sdspar.spar.main.Main;
import cc.sdspar.spar.main.Ref;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class Updater {

	public static List<Version> distros;
	
	public static GrabVersion installer;
	
	public static class CheckForUpdates implements Runnable {

		@Override
		public void run() {
			
			updateReleaseMappings();
			
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
	
	public static class GrabVersion implements Runnable {
		
		private ICommandSender sender;
		
		public GrabVersion() {
			if (installer == null) {
				installer = this;
				this.run();
			}
		}
		
		public GrabVersion(ICommandSender sender) {
			this.sender = sender;
			if (installer == null) {
				installer = this;
				this.run();
			} else {
				reply(new TextComponentTranslation("command.update.conflict"));
			}
		}
		
		public void reply(ITextComponent message) {
			if (sender != null) sender.sendMessage(message);
		}
		
		public void reply(String str) {
			if (sender != null) sender.sendMessage(new TextComponentString(str));
		}

		@Override
		public void run() {
			
			updateReleaseMappings();
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					Main.logger.info("The update will be attempted now.");
					ProcessBuilder modupgrade = new ProcessBuilder("java", "-jar", Paths.get(System.getProperty("user.dir")).resolve("mods/ModUpgrade/modupgrade-1.0.jar").toString(), 
							"--get", "https://github.com/superdan-t/SpecializedArmor/releases/download/v1.0.0/spar-pre-release-1.0.0.jar", 
							"--mcversion", "1.12.2",  "--path", System.getProperty("user.dir"));
					try {
						modupgrade.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			
			reply(new TextComponentTranslation("command.update.pending"));
			
		}
		
	}
	
	public static void updateReleaseMappings() {
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
