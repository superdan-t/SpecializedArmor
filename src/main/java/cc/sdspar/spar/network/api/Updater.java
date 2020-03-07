package cc.sdspar.spar.network.api;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import cc.sdspar.spar.main.Main;
import cc.sdspar.spar.main.Ref;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class Updater {

	public static List<Version> distros;
	
	public static ModUpgradeHandler installer;
	
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
	
	public static boolean install(ICommandSender sender, String version) {
		if (installer == null || installer.failed) {
			installer = new ModUpgradeHandler(sender, version);
			installer.run();
			return true;
		} else {
			return false;
		}
	}
	
	public static void cancel() {
		if (installer != null) {
			if (!installer.failed) {
				Runtime.getRuntime().removeShutdownHook(installer.upgrader);
			}
			installer = null;
		}
	}
	
	private static class ModUpgradeHandler implements Runnable {
		
		private ICommandSender sender;
		private Thread upgrader;
		private String versionToGet;
		public boolean failed = false;;
		
		public ModUpgradeHandler(ICommandSender sender, String v) {
			this.sender = sender;
			this.versionToGet = v;
		}
		
		public void reply(ITextComponent message) {
			if (sender != null) sender.sendMessage(message);
		}

		@Override
		public void run() {
			
			updateReleaseMappings();
			
			Version target;
			
			if (versionToGet == null) {
				target = getNewest();
			} else {
				target = getVersion(versionToGet);
			}
			
			if (target == null) {
				reply(new TextComponentTranslation("command.update.invalid"));
				failed = true;
				return;
			}
			
			upgrader = new Thread(new ModUpgrader(target));
			
			Runtime.getRuntime().addShutdownHook(upgrader);
			
			reply(new TextComponentTranslation("command.update.pending"));
			
		}
		
		private static class ModUpgrader implements Runnable {
			
			private Version target;
			
			private ModUpgrader(Version target) {
				this.target = target;
			}

			@Override
			public void run() {
				Main.logger.info("The mod will be updated once the game is closed.");
				ProcessBuilder modupgrade = new ProcessBuilder("java", "-jar", Paths.get(System.getProperty("user.dir")).resolve("mods/ModUpgrade/modupgrade-1.0.jar").toString(), 
						"--get", target.url.toString(), 
						"--mcversion", "1.12.2",  "--path", System.getProperty("user.dir"), "--modid", Ref.MOD_ID);
				try {
					modupgrade.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
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
	
	public static Version getVersion(String versionString) {
		for (Version v : distros) {
			if (v.version.equals(versionString)) {
				return v;
			}
		}
		return null;
	}

}
