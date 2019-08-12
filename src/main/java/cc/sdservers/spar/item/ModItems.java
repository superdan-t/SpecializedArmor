package cc.sdservers.spar.item;

import cc.sdservers.spar.main.SpecializedArmor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(SpecializedArmor.MODID)
public class ModItems {
	
	public static Item advanced_stick;
	public static Item ceramic_brick;
	public static Item graphene;
	public static Item kaolinite;
	public static Item moissanite;
	public static Item silicon_carbide;
	public static Item silicon_oxide;
	public static Item zircon;
	public static Item zirconium_carbide;
	public static Item zirconium_oxide;

	@EventBusSubscriber(modid = SpecializedArmor.MODID)
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerItems(Register<Item> event) {
			final Item[] items = {
					getItemToRegister(new Item(), "advanced_stick", CreativeTabs.MISC),
					getItemToRegister(new Item(), "ceramic_brick", CreativeTabs.MISC),
					getItemToRegister(new Item(), "graphene", CreativeTabs.MISC),
					getItemToRegister(new Item(), "kaolinite", CreativeTabs.MISC),
					getItemToRegister(new Item(), "moissanite", CreativeTabs.MISC),
					getItemToRegister(new Item(), "silicon_carbide", CreativeTabs.MISC),
					getItemToRegister(new Item(), "silicon_oxide", CreativeTabs.MISC),
					getItemToRegister(new Item(), "zircon", CreativeTabs.MISC),
					getItemToRegister(new Item(), "zirconium_carbide", CreativeTabs.MISC),
					getItemToRegister(new Item(), "zirconium_oxide", CreativeTabs.MISC)
			};

			event.getRegistry().registerAll(items);
		}
		
		private static Item getItemToRegister(Item item, String name, CreativeTabs tab) {
			return item.setRegistryName(SpecializedArmor.MODID, name).setTranslationKey(SpecializedArmor.MODID + "." + name).setCreativeTab(tab);
		}
		
	}
	
}