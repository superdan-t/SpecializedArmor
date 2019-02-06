package com.shrub.info;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class TantalumInfo extends ItemBlock {

	public TantalumInfo(Block block) {
		super(block);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)	{
		list.add("\u00A7bComposition:");
		list.add("\u00A78Tantalum Carbide");
		list.add("\u00A7aAlloy with Hafnium Carbide");
		list.add("\u00A7afor insane thermal resistance.");
	}
}	