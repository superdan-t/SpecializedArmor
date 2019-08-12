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
	
	public static final Item advanced_stick = null;
	public static final Item ceramic_brick = null;
	//public static final Item graphene = null;
	public static final Item hafnium_oxide = null;
	public static final Item hafnium_carbide = null;
	public static final Item kaolinite = null;
	public static final Item moissanite = null;
	public static final Item silicon_carbide = null;
	public static final Item tantalum_ingot = null;
	public static final Item zircon = null;
	public static final Item zirconium_carbide = null;
	public static final Item zirconium_dioxide = null;

	@EventBusSubscriber(modid = SpecializedArmor.MODID)
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerItems(Register<Item> event) {
			final Item[] items = {
					getItemToRegister("advanced_stick", CreativeTabs.MISC),
					getItemToRegister("ceramic_brick", CreativeTabs.MISC),
					//getItemToRegister("graphene", CreativeTabs.MISC),
					getItemToRegister("hafnium_oxide", CreativeTabs.MISC),
					getItemToRegister("hafnium_carbide", CreativeTabs.MISC),
					getItemToRegister("kaolinite", CreativeTabs.MISC),
					getItemToRegister("moissanite", CreativeTabs.MISC),
					getItemToRegister("silicon_carbide", CreativeTabs.MISC),
					getItemToRegister("tantalum_ingot", CreativeTabs.MISC),
					getItemToRegister("zircon", CreativeTabs.MISC),
					getItemToRegister("zirconium_carbide", CreativeTabs.MISC),
					getItemToRegister("zirconium_dioxide", CreativeTabs.MISC)
			};

			event.getRegistry().registerAll(items);
		}
		
		private static Item getItemToRegister(String name, CreativeTabs tab) {
			return new Item().setRegistryName(SpecializedArmor.MODID, name).setTranslationKey(SpecializedArmor.MODID + "." + name).setCreativeTab(tab);
		}
		
	}
	
}