package com.shrub.blocks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public abstract class ModTileEntityMachine extends TileEntity implements ISidedInventory {
	
	/**
	 * The inventory slots in this TileEntity.
	 */
	public ItemStack[] slots;
	
	public String localizedName;

	public ModTileEntityMachine() { }
	
	public ModTileEntityMachine(String localizedName) {
		this.localizedName = localizedName;
	}
	
	public ModTileEntityMachine(String localizedName, int slotsCount) {
		this.slots = new ItemStack[slotsCount];
	}

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {
		return slots[p_70301_1_];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (slots[slot] == null)
			return null;
		ItemStack itemStack;
		if (slots[slot].stackSize <= amount) {
			itemStack = slots[slot];
			slots[slot] = null;
			return itemStack;
		}
		itemStack = slots[slot].splitStack(amount);
		if (slots[slot].stackSize == 0)
			slots[slot] = null;
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (slots[slot] == null)
			return null;
		ItemStack itemStack = slots[slot];
		slots[slot] = null;
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		slots[slot] = itemStack;
		
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.localizedName : "container.foundry";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return localizedName != null && localizedName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false
				: p_70300_1_.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D,
						(double)zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
		//Does anyone really know?
	}

	@Override
	public void closeInventory() {
		//This too

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return null;
	}
	
	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		return isItemValidForSlot(p_102007_1_, p_102007_2_);
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return true;
	}

}
