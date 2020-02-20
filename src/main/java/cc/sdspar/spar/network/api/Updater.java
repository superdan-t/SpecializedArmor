package cc.sdspar.spar.network.api;

import java.util.List;

import cc.sdspar.spar.main.Ref;

public class Updater {

	public static List<Version> distros;
	
	public static class CheckForUpdates implements Runnable {

		@Override
		public void run() {
			
			updateDistroMappings();
			System.out.println(distros);
			
			Version newest = getNewest();
			Version current = new Version();
			current.version = Ref.VERSION;
			if (newest != null && newest.isNewerThan(current)) {
	
				NetworkAPIUtils.notifyUser("&0[&dSpecialized Armor&0] &7An update is available for the mod.\nNew Version: " + newest.version + 
						"\nCurrent Version: " + current.version + "\nUpdate Details: " + newest.tagline + "\nAutomatically update with: '/spup latest' ");
			} else {
				NetworkAPIUtils.notifyUser("&0[&dSpecialized Armor&0] &7Mod is up to date");
			}
			
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
		best.version = "0.0.0";
		for (Version v : distros) {
			if (v.isNewerThan(best)) {
				best = v;
			}
		}
		if (best.version != "0.0.0") return best;
		else return null;
	}

}
