package cc.sdspar.spar.event;

import cc.sdspar.spar.main.Main;
import cc.sdspar.spar.network.api.ClientNetworkAPI;
import cc.sdspar.spar.network.api.NetworkAPIUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;

public class StaticEventHandler {

	@SubscribeEvent
	public static void playerConnects(ClientConnectedToServerEvent e) {
		ClientNetworkAPI.startAPI();
	}
	
	@SubscribeEvent
	public static void playerConnectsToServer(ServerConnectionFromClientEvent e) {
		NetworkAPIUtils.notifyUser("I am the SERVER");
	}
	
	@SubscribeEvent
	public static void playerLeaves(ClientDisconnectionFromServerEvent e) {
		
	}
	
}
