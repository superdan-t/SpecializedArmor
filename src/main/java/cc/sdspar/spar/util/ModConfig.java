package cc.sdspar.spar.util;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Ref.MOD_ID)
public class ModConfig {
	
	@Name("Industrial mode")
	@Comment("Change machine energy usage to balance with industrial mods")
	public static boolean INDUSTRIAL_MODE = true;
	
	@Name("Modpack mode")
	@Comment("Reduce the generation of copper, tin, and aluminum since they are commonly added in other mods")
	public static boolean MODPACK_MODE;

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
