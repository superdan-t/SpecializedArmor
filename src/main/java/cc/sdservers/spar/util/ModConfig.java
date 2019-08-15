package cc.sdservers.spar.util;

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

	@Name("Generate copper ore?")
	public static boolean GENERATE_COPPER = true;
	
	@Name("Generate lead ore?")
	public static boolean GENERATE_LEAD = true;
	
	@Name("Generate tin ore?")
	public static boolean GENERATE_TIN = true;
	
	@Name("Ore generation chance multiplier")
	@Comment("The default and recommended value is 1")
	@RangeInt(min = 1, max = 10)
	public static int ORE_CHANCE_MULTIPLIER = 1;

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
