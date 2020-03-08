package cc.sdspar.spar.network;

import cc.sdspar.spar.main.Ref;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler {

	public static final SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Ref.MOD_ID);
	
	public static final void registerPackets() {
		// All were experimental, saving procedure for future
	}

}
