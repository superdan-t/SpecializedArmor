package cc.sdspar.spar.network.api;

import cc.sdspar.spar.main.Main;
import cc.sdspar.spar.util.ModConfig;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class NetworkAPI {
	
	private static Thread messenger;

	public static void startClientAPI() {
		NetworkAPIUtils.server = false;
		if (messenger != null) messenger.interrupt();
		messenger = new Thread(new NetworkMessageChecker());
		messenger.start();
		
		Thread updates = new Thread(new Updater.CheckForUpdates());
		updates.start();
		
	}
	
	public static void startServerAPI() {
		if (FMLCommonHandler.instance().getMinecraftServerInstance() instanceof IntegratedServer) return;
		if (ModConfig.RESET) {
			Main.logger.info(new TextComponentTranslation("api.firstlaunch.server"));
		}
		NetworkAPIUtils.server = true;
		if (messenger != null) messenger.interrupt();
		messenger = new Thread(new NetworkMessageChecker());
		messenger.start();
		
		Thread updates = new Thread(new Updater.CheckForUpdates());
		updates.start();
		
	}

}
