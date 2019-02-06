package com.shrub.items.utility;

import java.util.List;

import com.shrub.items.ModItems;
import com.shrub.main.Main;
import com.shrub.main.Text;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemControlChip extends Item {

	public ItemControlChip() {
		this.setUnlocalizedName("controlChip");
		this.setCreativeTab(ModItems.tabSpecializedTools);
		this.setTextureName(Main.modID + ":controlChip");
		this.maxStackSize = 1;
	}
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		itemStack.stackTagCompound = new NBTTagCompound();
		itemStack.stackTagCompound.setInteger("capacity", 2);
		itemStack.stackTagCompound.setInteger("usage", 0);
	}
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int metadata, boolean bool) {
		if (itemStack.stackTagCompound == null) {
			itemStack.stackTagCompound = new NBTTagCompound();
			itemStack.stackTagCompound.setInteger("capacity", 2);
			itemStack.stackTagCompound.setInteger("usage", 0);
			itemStack.stackTagCompound.setInteger("drillSize", 0);
		}
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		String prefix = "";
		switch (itemStack.hasTagCompound() ? itemStack.stackTagCompound.getInteger("capacity") : 2) {
		case 0:
			prefix = I18n.format("prefix.broken");
		case 2:
			prefix = I18n.format("prefix.basic");
		}
		return prefix + " " + I18n.format("item.controlChip.name");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
		if (itemStack.stackTagCompound == null || itemStack.stackTagCompound.getInteger("usage") == 0) {
			list.add(I18n.format("lore.controlChip.blank_0"));
			list.add(I18n.format("lore.controlChip.blank_1"));
		} else {
			list.add(Text.Color.GREEN + Integer.toString(itemStack.stackTagCompound.getInteger("usage")) + Text.Color.BLUE + " " + I18n.format("lore.controlChip.usage_0") + " " + Text.Color.GREEN + Integer.toString(itemStack.stackTagCompound.getInteger("capacity")) + " " + Text.Color.BLUE + I18n.format("lore.controlChip.usage_1"));
		}
	}
	
/*	
	private String name(int capacity) {
		switch (capacity) {
		case 2:
			return "Basic";
		default:
			return "Default";
		}
	}
*/
}
