package com.shrub.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.IIcon;

public class SlotInvisible extends Slot {

	public SlotInvisible(IInventory inventory, int index) {
		super(inventory, index, 0, 0);
		
		this.backgroundIcon = null;
		
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean func_111238_b() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getBackgroundIconIndex() {
		return null;
	}
	
	@Override
	public void setBackgroundIcon(IIcon icon) {
		backgroundIcon = null;
	}

}
