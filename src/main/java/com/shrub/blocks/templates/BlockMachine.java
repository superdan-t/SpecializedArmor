package com.shrub.blocks.templates;

import com.shrub.blocks.ModBlocks;
import com.shrub.main.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMachine extends Block {

	public BlockMachine(String unlocalizedName, Material mat) {
		super(mat);
		this.setBlockName(unlocalizedName);
		this.setCreativeTab(ModBlocks.tabBlocks);
		this.setHardness(5F);
		this.setResistance(10F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName(Main.modID + ":" + unlocalizedName);
	}

}
