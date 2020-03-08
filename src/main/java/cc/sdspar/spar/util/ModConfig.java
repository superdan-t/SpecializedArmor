package cc.sdspar.spar.util;

import cc.sdspar.spar.main.Ref;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Ref.MOD_ID)
public class ModConfig {
	
	@Name("Display author messages")
	@Comment("Whether to look for messages from the mod authors")
	public static boolean CHECK_MESSAGES = true;
	
	@Name("Message check interval")
	@Comment("How frequently to check for messages from the mod authors in seconds")
	@RangeInt(min=70)
	public static int UPDATE_INTERVAL = 270;
	
	@Name("Check for updates")
	@Comment("Whether to look for new versions or not")
	public static boolean CHECK_UPDATES = true;
	
	@EventBusSubscriber(modid = Ref.MOD_ID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Ref.MOD_ID)) {
				ConfigManager.sync(Ref.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}
