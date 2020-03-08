package cc.sdspar.spar.util;

import cc.sdspar.spar.block.ModBlocks;
import cc.sdspar.spar.item.ModItems;
import cc.sdspar.spar.util.handler.recpies.RecipeHandler;
import cc.sdspar.spar.util.handler.recpies.ShredderRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	public static void init() {
		
		//Ores
		GameRegistry.addSmelting(ModBlocks.ALUMINUM_ORE, new ItemStack(ModItems.ALUMINUM_INGOT), 0.5F);
		GameRegistry.addSmelting(ModBlocks.COPPER_ORE, new ItemStack(ModItems.COPPER_INGOT), 0.8F);
		GameRegistry.addSmelting(ModBlocks.LEAD_ORE, new ItemStack(ModItems.LEAD_INGOT), 0.9F);
		GameRegistry.addSmelting(ModBlocks.MOISSANITE_ORE, new ItemStack(ModItems.MOISSANITE), 3.0F);
		GameRegistry.addSmelting(ModBlocks.TANTALUM_ORE, new ItemStack(ModItems.TANTALUM_INGOT), 0.7F);
		GameRegistry.addSmelting(ModBlocks.TIN_ORE, new ItemStack(ModItems.TIN_INGOT), 0.7F);
		GameRegistry.addSmelting(ModBlocks.TITANIUM_ORE, new ItemStack(ModItems.TITANIUM_INGOT), 1.0F);
		GameRegistry.addSmelting(ModBlocks.ZIRCON_ORE, new ItemStack(ModItems.ZIRCON), 1.8F);
		
		//Materials
		GameRegistry.addSmelting(ModItems.KAOLINITE_BALL, new ItemStack(ModItems.CERAMIC_BRICK), 0.3F);
		GameRegistry.addSmelting(ModBlocks.KAOLINITE_BLOCK, new ItemStack(ModBlocks.CERAMIC_BLOCK), 0.35F);
		
		//Vacuum Arc Furnace

		
		//Shredder
		RecipeHandler.shredderRecipes.add(new ShredderRecipe(Item.getItemFromBlock(Blocks.STONE), new ItemStack(Item.getItemFromBlock(Blocks.GRAVEL)), 10));
		RecipeHandler.shredderRecipes.add(new ShredderRecipe(Item.getItemFromBlock(Blocks.SANDSTONE), new ItemStack(Item.getItemFromBlock(Blocks.SAND)), 10));
		
	}

}
