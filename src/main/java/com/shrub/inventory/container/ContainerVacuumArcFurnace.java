package com.shrub.inventory.container;

import com.shrub.tileentity.TileEntityVacuumArcFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerVacuumArcFurnace extends Container {
	
	private TileEntityVacuumArcFurnace vacuumArcFurnace;
	
	private int progress;
	
	private int energyStored;
	
	public ContainerVacuumArcFurnace(InventoryPlayer inventory, TileEntityVacuumArcFurnace tileEntity) {
		vacuumArcFurnace = tileEntity;
		addSlotToContainer(new Slot(tileEntity, 0, 52, 12));
		addSlotToContainer(new Slot(tileEntity, 1, 108, 12));
		addSlotToContainer(new SlotFurnace(inventory.player, tileEntity, 2, 65, 58));
		addSlotToContainer(new SlotFurnace(inventory.player, tileEntity, 3, 95, 58));
		addSlotToContainer(new Slot(tileEntity, 4, 8, 62));
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
		
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, vacuumArcFurnace.getEnergyStored(null));
		icrafting.sendProgressBarUpdate(this, 1, vacuumArcFurnace.progress);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return vacuumArcFurnace.isUseableByPlayer(p_75145_1_);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) crafters.get(i);
			
			if (energyStored != vacuumArcFurnace.getEnergyStored(null)) {
				icrafting.sendProgressBarUpdate(this, 0, vacuumArcFurnace.getEnergyStored(null));
			}
			
			if (progress != vacuumArcFurnace.progress) {
				icrafting.sendProgressBarUpdate(this, 1, vacuumArcFurnace.progress);
			}
			
			energyStored = vacuumArcFurnace.getEnergyStored(null);
			progress = vacuumArcFurnace.progress;
			
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int slot, int newValue) {
		switch (slot) {
		case 0:
			vacuumArcFurnace.energyStoredPlaceholder = newValue;
			break;
		case 1:
			vacuumArcFurnace.progress = newValue;
			break;
		}
	}

}
