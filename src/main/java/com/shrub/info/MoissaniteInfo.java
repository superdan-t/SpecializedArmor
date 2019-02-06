package com.shrub.info;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class MoissaniteInfo extends ItemBlock {

	public MoissaniteInfo(Block block) {
		super(block);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)	{
		list.add("\u00A7bComposition:");
		list.add("\u00A78Silicon Carbide");
		list.add("\u00A7aNot quite as hard as diamond,");
		list.add("\u00A7abut really useful in semiconductors.");
	}
}
