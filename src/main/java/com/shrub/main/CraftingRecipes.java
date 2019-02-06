package com.shrub.main;

import com.shrub.blocks.ModBlocks;
import com.shrub.items.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingRecipes {

	public static void init()	{
		
		//Block Recipes
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.chimney, 4, 0), "BPB", "BPB", 'B', Blocks.brick_block, 'P', ModItems.pipes);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.chimney, 4, 1), "BPB", "BPB", 'B', Blocks.stone, 'P', ModItems.pipes);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.chimney, 4, 2), "BPB", "BPB", 'B', Blocks.stonebrick, 'P', ModItems.pipes);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.chimney, 4, 3), "BPB", "BPB", "BPB", 'B', Items.iron_ingot, 'P', ModItems.pipes);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.chimney, 24, 3), "BPB", "BPB", 'B', Blocks.iron_block, 'P', ModItems.pipes);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.chimney, 4, 4), "BPB", "BPB", 'B', ModBlocks.foundryCase, 'P', ModItems.pipes);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.foundryHatchCool), "III", "D P", "BRI", 'I', Items.iron_ingot, 'D', Items.iron_door, 'P', ModItems.pipes, 'R', Items.redstone, 'B', Blocks.stone_button);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.foundryCase, 5), "ISI", "ISI", "ISI", 'I', Items.iron_ingot, 'S', Blocks.cobblestone);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.foundryCoreBasic), "IPI", "PFP", "RIE", 'I', Items.iron_ingot, 'P', ModItems.pipes, 'F', Blocks.furnace, 'R', Items.redstone, 'E', Items.repeater);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.lavaTank, 1, 0), "ICI", "IGP", "ICI", 'I', Items.iron_ingot, 'C', Blocks.cobblestone, 'G', Blocks.glass_pane, 'P', ModItems.pipes);
		
		//Item Recipes
		
		GameRegistry.addRecipe(new ItemStack(ModItems.advancedStick), "DED", "WRW", "EDE", 'D', Blocks.diamond_block, 'E', Blocks.emerald_block, 'W', Items.nether_star, 'R', Blocks.dragon_egg);
		GameRegistry.addRecipe(new ItemStack(ModItems.pipes, 8), "III", "   ", "III", 'I', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(ModItems.zirconiumAxe), "T", "S", "S", 'T', new ItemStack(ModItems.zirconiumToolTip, 1, 3), 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(ModItems.zirconiumHoe), "T", "S", "S", 'T', new ItemStack(ModItems.zirconiumToolTip, 1, 4), 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(ModItems.zirconiumKnife), "T", "S", 'T', new ItemStack(ModItems.zirconiumToolTip, 1, 0), 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(ModItems.zirconiumPickaxe), "T", "S", "S", 'T', new ItemStack(ModItems.zirconiumToolTip, 1, 5), 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(ModItems.zirconiumScythe), "TS", " S", " S", 'T', new ItemStack(ModItems.zirconiumToolTip, 1, 2), 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(ModItems.zirconiumSpade), "T", "S", "S", 'T', new ItemStack(ModItems.zirconiumToolTip, 1, 6), 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(ModItems.zirconiumSword), "T", "S", 'T', new ItemStack(ModItems.zirconiumToolTip, 1, 1), 'S', Items.stick);
		
		GameRegistry.addSmelting(ModItems.kaoliniteClay, new ItemStack(ModItems.ceramic), 0.2F);
		GameRegistry.addSmelting(ModBlocks.moissaniteOre, new ItemStack(ModItems.moissanite), 5.3F);
		GameRegistry.addSmelting(ModBlocks.pentlanditeOre, new ItemStack(ModItems.nickelIngot), 0.8F);
		GameRegistry.addSmelting(ModBlocks.tantalumOre, new ItemStack(ModItems.tantalumCarbide), 4.0F);
		GameRegistry.addSmelting(ModBlocks.zirconOre, new ItemStack(ModItems.zirconIngot), 0.8F);
		
	}
	
}
