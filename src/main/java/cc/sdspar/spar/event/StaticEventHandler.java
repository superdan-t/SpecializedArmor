package cc.sdspar.spar.event;

import cc.sdspar.spar.network.api.NetworkAPI;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;

public class StaticEventHandler {

	@SubscribeEvent
	public static void playerConnects(ClientConnectedToServerEvent e) {
		NetworkAPI.startClientAPI();
	}
	
}
