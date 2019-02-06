package com.shrub.info;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ZirconInfo extends ItemBlock {

	public ZirconInfo(Block block) {
		super(block);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)	{
		list.add("\u00A7bComposition:");
		list.add("\u00A78Up to 20% Hafnium");
		list.add("\u00A78Mostly Zirconium");
		list.add("\u00A7aZirconium and Hafnium are");
		list.add("\u00A7anearly indistinguishable.");
	}

}
