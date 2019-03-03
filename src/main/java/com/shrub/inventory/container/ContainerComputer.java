package com.shrub.inventory.container;

import com.shrub.inventory.SlotInvisible;
import com.shrub.tileentity.TileEntityComputer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerComputer extends Container {
	
	private TileEntityComputer computer;

	public ContainerComputer(InventoryPlayer inventory, TileEntityComputer tileEntity) {
		
		computer = tileEntity;
		
		addSlotToContainer(new SlotInvisible(tileEntity, 0));
		
		
	}



	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return computer.isUseableByPlayer(p_75145_1_);
	}

}
