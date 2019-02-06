package com.shrub.inventory.container;

import com.shrub.blocks.tileentity.TileEntityComputer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerComputer extends Container {
	
	private TileEntityComputer computer;

	public ContainerComputer(InventoryPlayer inventory, TileEntityComputer tileEntity) {
		
		computer = tileEntity;
		
		this.addSlotToContainer(new Slot(tileEntity, 0, 80, 102));
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 124 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 182));
		}
	}

	@Override
	public void addCraftingToCrafters (ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
	}
	
	//Finds where to put an ItemStack when a player shift-clicks on it.
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return computer.isUseableByPlayer(p_75145_1_);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		/*for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
		
		}*/
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int slot, int newValue) {
		
	}

}
