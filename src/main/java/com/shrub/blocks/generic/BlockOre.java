package com.shrub.blocks.generic;

import com.shrub.blocks.ModBlocks;
import com.shrub.main.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOre extends Block {
	
	public BlockOre(String unlocalizedName, Material material, int hvstLevel) {
		super(material);
		this.setBlockName(unlocalizedName);
		this.setHarvestLevel("pickaxe", hvstLevel);
		this.setCreativeTab(ModBlocks.tabBlocks);
		this.setHardness(3);
		this.setBlockTextureName(Main.modID + ":" + unlocalizedName);
		this.setResistance(24);
		this.setStepSound(soundTypeStone);
	}
	
}