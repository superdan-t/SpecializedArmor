package cc.sdspar.spar.network.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import cc.sdspar.spar.main.Ref;

public class Version implements INetAssembled {
	
	public String version;
	public String tagline;
	public URL url;

	public Version() { }
	
	public static List<Version> getVersions() throws MalformedURLException, IOException {
        List<Version> list = new ArrayList<Version>();
        List<INetAssembled> inet = NetworkAPIUtils.readObjects(new URL(Ref.API_URL + "/update.map"));
        for (INetAssembled i : inet) {
        	if (i instanceof Version) {
        		list.add((Version)i);
        	}
        }
        return list;
	}
	
	public boolean isNewerThan(Version v) {
		String[] semc = v.version.split(".");
		int[] cVersion = {Integer.valueOf(semc[0]), Integer.valueOf(semc[1]), Integer.valueOf(semc[2])};
		semc = version.split(".");
		int[] tVersion = {Integer.valueOf(semc[0]), Integer.valueOf(semc[1]), Integer.valueOf(semc[2])};
		
		return (tVersion[0] > cVersion[0]) || (tVersion[0] == cVersion[0] && tVersion[1] > cVersion[1]) ||  (tVersion[0] == cVersion[0] && tVersion[1] == cVersion[1] && tVersion[2] > cVersion[2]);
		
	}

	@Override
	public void setProperty(String name, String value) {
		
	}

	@Override
	public void setProperties(Dictionary<String, String> props) {
		String value = (String)props.get("url");
		if (value != null) {
			try {
				url = new URL(value);
			} catch (MalformedURLException e) {}
		}
		value = (String)props.get("version");
		if (value != null) {
			version = value;
		}
		value = (String)props.get("tag");
		if (value != null) {
			tagline = value;
		}
	}

}
