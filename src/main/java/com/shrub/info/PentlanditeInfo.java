package com.shrub.info;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class PentlanditeInfo extends ItemBlock {

	public PentlanditeInfo(Block block) {
		super(block);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)	{
		list.add("\u00A7bComposition:");
		list.add("\u00A7850% Iron");
		list.add("\u00A7850% Nickel");
		list.add("\u00A7aVery brittle and not useful for");
		list.add("\u00A7amuch, but the nickel can be extracted.");
	}
	
}
