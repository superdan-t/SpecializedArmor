package com.shrub.items.itemblocks;

import com.shrub.main.Main;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemChimneyBlock extends ItemBlock {
	
    static final String[] names = new String[] {"brick", "stone", "stonebrick", "iron_block", Main.modID + ":foundryCase"};

	public ItemChimneyBlock(Block p_i45328_1_) {
		super(p_i45328_1_);
		this.setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		
		int i = itemstack.getItemDamage();
		
		if (i < 0 || i >= names.length)
			i = 0;
		else if (i == 4)
			return super.getUnlocalizedName() + "_foundryCase";
		
		return super.getUnlocalizedName() + "_" + names[i]; 
		
	}

	public int getMetadata(int meta) {
		return meta;
	}
	
}
