package cc.sdspar.spar.network;

import cc.sdspar.spar.main.Ref;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Ref.MOD_ID);
	
	public static final void registerPackets() {
		int i = 0;
		wrapper.registerMessage(PacketAlloyMixer.Handler.class, PacketAlloyMixer.class, i++, Side.SERVER);
	}

}
