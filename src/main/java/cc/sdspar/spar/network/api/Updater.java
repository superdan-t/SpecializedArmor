package cc.sdspar.spar.network.api;

import java.util.List;

import cc.sdspar.spar.main.Ref;

public class Updater {

	public static List<Version> distros;
	
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

}
