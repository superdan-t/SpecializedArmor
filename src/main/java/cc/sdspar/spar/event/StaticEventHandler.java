package cc.sdspar.spar.event;

import cc.sdspar.spar.network.api.ClientNetworkAPI;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class StaticEventHandler {

	@SubscribeEvent
	public static void playerConnects(ClientConnectedToServerEvent e) {
		ClientNetworkAPI.startAPI();
	}
	
	@SubscribeEvent
	public static void playerLeaves(ClientDisconnectionFromServerEvent e) {
		
	}
	
}
