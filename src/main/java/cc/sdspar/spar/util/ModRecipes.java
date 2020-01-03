package cc.sdspar.spar.util;

import cc.sdspar.spar.block.ModBlocks;
import cc.sdspar.spar.item.ModItems;
import cc.sdspar.spar.util.handler.recpies.RecipeHandler;
import cc.sdspar.spar.util.handler.recpies.VacuumArcRecipe;
import net.minecraft.init.Items;
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
		if (ModConfig.DEVELOPER_MODE) {
			//Recipes for testing purposes only
			RecipeHandler.vacuumArcRecipes.add(new VacuumArcRecipe(Items.STICK, Items.NETHER_STAR, new ItemStack(ModItems.ADVANCED_STICK), null));
			RecipeHandler.vacuumArcRecipes.add(new VacuumArcRecipe(Items.COAL, Items.ACACIA_DOOR, new ItemStack(Items.SHEARS), new ItemStack(Items.FIRE_CHARGE)));
		}
		
	}

}
