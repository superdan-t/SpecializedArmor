package cc.sdspar.spar.network.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;

public class DLProxy implements INetAssembled {

	public URL url;
	public String name;
	
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
		value = (String)props.get("name");
		if (value != null) {
			name = value;
		}
	}

}
