package com.shrub.info;

import java.util.List;
import com.shrub.main.Text;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class VacuumArcFurnaceInformation extends ItemBlock {

	public VacuumArcFurnaceInformation(Block block) {
		super(block);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)	{
		list.add(Text.Color.GREEN + I18n.format("lore.vacuumArcFurnace.info_0"));
		list.add(Text.Color.GRAY + I18n.format("lore.vacuumArcFurnace.info_1"));
		list.add(Text.Color.GRAY + I18n.format("lore.vacuumArcFurnace.info_2"));
	}
	
}
