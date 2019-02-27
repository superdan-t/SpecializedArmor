package com.shrub.inventory.container;

import com.shrub.tileentity.TileEntityFoundry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerFoundry extends Container {
	
	private TileEntityFoundry foundry;
	private int heatLevel; //Ticks the furnace will keep burning.
	private int burnTime;
	private int burnTimeMax;
	private int progress;
	private int progressDone;
	private int pressure;

	public ContainerFoundry(InventoryPlayer inventory, TileEntityFoundry tileentity) {
		this.foundry = tileentity;
		
		this.addSlotToContainer(new Slot(tileentity, 0, 16, 18));
		this.addSlotToContainer(new Slot(tileentity, 1, 34, 18));
		this.addSlotToContainer(new Slot(tileentity, 2, 52, 18));
		this.addSlotToContainer(new Slot(tileentity, 3, 16, 36));
		this.addSlotToContainer(new Slot(tileentity, 4, 34, 36));
		this.addSlotToContainer(new Slot(tileentity, 5, 52, 36));
		this.addSlotToContainer(new Slot(tileentity, 6, 16, 54));
		this.addSlotToContainer(new Slot(tileentity, 7, 34, 54));
		this.addSlotToContainer(new Slot(tileentity, 8, 52, 54));
		this.addSlotToContainer(new Slot(tileentity, 10, 34, 74));
		this.addSlotToContainer(new Slot(tileentity, 11, 144, 74));
		this.addSlotToContainer(new Slot(tileentity, 9, 86, 42));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 12, 126, 18));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 13, 144, 18));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 14, 126, 36));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 15, 144, 36));
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 94 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 152));
		}
	}
	
	@Override
	public void addCraftingToCrafters (ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.foundry.heatLevel);
		icrafting.sendProgressBarUpdate(this, 1, this.foundry.burnTime);
		icrafting.sendProgressBarUpdate(this, 2, this.foundry.progress);
		icrafting.sendProgressBarUpdate(this, 3, this.foundry.burnTimeMax);
		icrafting.sendProgressBarUpdate(this, 4, this.foundry.progressDone);
		icrafting.sendProgressBarUpdate(this, 5, this.foundry.pressure);
	}
	
	//Finds where to put an ItemStack when a player shift-clicks on it.
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return foundry.isUseableByPlayer(p_75145_1_);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			
			if (this.heatLevel !=  this.foundry.heatLevel) {
				icrafting.sendProgressBarUpdate(this, 0, this.foundry.heatLevel);
			}
			
			if (this.burnTime !=  this.foundry.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1, this.foundry.burnTime);
			}
			
			if (this.progress !=  this.foundry.progress) {
				icrafting.sendProgressBarUpdate(this, 2, this.foundry.progress);
			}
			
			if (this.burnTimeMax != this.foundry.burnTimeMax) {
				icrafting.sendProgressBarUpdate(this, 3, this.foundry.burnTimeMax);
			}
			
			if (this.progressDone != this.foundry.progressDone) {
				icrafting.sendProgressBarUpdate(this, 4, this.foundry.progressDone);
			}
			
			if (this.pressure != this.foundry.pressure) {
				icrafting.sendProgressBarUpdate(this, 5, this.foundry.pressure);
			}
			
		}
		
		this.heatLevel = this.foundry.heatLevel;
		this.burnTime = this.foundry.burnTime;
		this.burnTimeMax = this.foundry.burnTimeMax;
		this.progress = this.foundry.progress;
		this.progressDone = this.foundry.progressDone;
		this.pressure = this.foundry.pressure;
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int slot, int newValue) {
		switch (slot) {
		case 0:
			foundry.heatLevel = newValue;
			break;
		case 1:
			foundry.burnTime = newValue;
			break;
		case 2:
			foundry.progress = newValue;
			break;
		case 3:
			foundry.burnTimeMax = newValue;
			break;
		case 4:
			foundry.progressDone = newValue;
			break;
		case 5:
			foundry.pressure = newValue;
			break;
		}
	}
	
}
