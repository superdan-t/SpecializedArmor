package com.shrub.items.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemLavaTankBlock extends ItemBlock {

	public ItemLavaTankBlock(Block p_i45328_1_) {
		super(p_i45328_1_);
		this.setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		
		int i = itemstack.getItemDamage();
		
		if (i < 0 || i >= 15)
			i = 0;
		
		return super.getUnlocalizedName() + "_" + Integer.toString(i); 
		
	}

	public int getMetadata(int meta) {
		return meta;
	}

}
