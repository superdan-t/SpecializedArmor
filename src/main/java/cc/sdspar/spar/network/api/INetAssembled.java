package cc.sdspar.spar.network.api;

import java.util.Dictionary;

public interface INetAssembled {
	
	public void setProperty(String name, String value);
	
	public void setProperties(Dictionary<String,String> dict);

}
