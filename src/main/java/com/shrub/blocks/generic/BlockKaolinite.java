package com.shrub.blocks.generic;

import java.util.Random;

import com.shrub.blocks.ModBlocks;
import com.shrub.items.ModItems;
import com.shrub.main.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockKaolinite extends Block {

	public BlockKaolinite(String unlocalizedName) {
		super(Material.clay);		this.setBlockName(unlocalizedName);
		this.setCreativeTab(ModBlocks.tabBlocks);
		this.setHardness(0.5F);
		this.setBlockTextureName(Main.modID + ":" + unlocalizedName);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeGravel);
		this.setHarvestLevel("shovel", 0);
		
	}
	
	@Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return ModItems.kaoliniteClay;
    }

	@Override
    public int quantityDropped(Random p_149745_1_) {
        return 4;
    }

}
