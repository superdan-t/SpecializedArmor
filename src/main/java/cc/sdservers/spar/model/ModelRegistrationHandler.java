package cc.sdservers.spar.model;

import cc.sdservers.spar.item.ModItems;
import cc.sdservers.spar.main.SpecializedArmor;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = SpecializedArmor.MODID)
public class ModelRegistrationHandler {

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		registerModel(ModItems.advanced_stick, 0);
		registerModel(ModItems.ceramic_brick, 0);
		registerModel(ModItems.hafnium_carbide, 0);
		registerModel(ModItems.hafnium_oxide, 0);
		registerModel(ModItems.kaolinite, 0);
		registerModel(ModItems.moissanite, 0);
		registerModel(ModItems.silicon_carbide, 0);
		registerModel(ModItems.tantalum_ingot, 0);
		registerModel(ModItems.zircon, 0);
		registerModel(ModItems.zirconium_carbide, 0);
		registerModel(ModItems.zirconium_dioxide, 0);
	}

	private static void registerModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}
