package cc.sdspar.spar.util.handler;

import cc.sdspar.spar.block.ModBlocks;
import cc.sdspar.spar.item.ModItems;
import cc.sdspar.spar.tileentity.TileEntityVacuumArcFurnace;
import cc.sdspar.spar.util.IHasModel;
import cc.sdspar.spar.util.Ref;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
		
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		
		GameRegistry.registerTileEntity(TileEntityVacuumArcFurnace.class, new ResourceLocation(Ref.MOD_ID + ":vacuum_arc_furnace"));
		
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		
		for (Item item : ModItems.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		
		for (Block block : ModBlocks.BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
		
	}
	
	public static void registerOres() {
		OreDictionary.registerOre("ingotAluminum", ModItems.ALUMINUM_INGOT);
		OreDictionary.registerOre("ingotCopper", ModItems.COPPER_INGOT);
		OreDictionary.registerOre("ingotTantalum", ModItems.TANTALUM_INGOT);
		OreDictionary.registerOre("ingotTin", ModItems.TIN_INGOT);
		OreDictionary.registerOre("ingotTitanium", ModItems.TITANIUM_INGOT);
		OreDictionary.registerOre("ingotLead", ModItems.LEAD_INGOT);
	}
	
}
