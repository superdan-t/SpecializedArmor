package com.shrub.network;

import com.shrub.main.Main;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class ModPacketHandler {

	public static final SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Main.modID);
	
	public static final void registerPackets() {
		
		int i = 0;
		wrapper.registerMessage(PacketComputer.Handler.class, PacketComputer.class, i++, Side.SERVER);
		
	}

}
