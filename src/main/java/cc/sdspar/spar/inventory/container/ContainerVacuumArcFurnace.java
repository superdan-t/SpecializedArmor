package cc.sdspar.spar.inventory.container;

import cc.sdspar.spar.tileentity.TileEntityVacuumArcFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerVacuumArcFurnace extends Container {

	TileEntityVacuumArcFurnace vaf;

	public ContainerVacuumArcFurnace(InventoryPlayer inventory, TileEntityVacuumArcFurnace tileEntity) {
		vaf = tileEntity;
		IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotItemHandler(handler, 0, 52, 17));
		addSlotToContainer(new SlotItemHandler(handler, 1, 108, 17));
		addSlotToContainer(new SlotItemHandler(handler, 2, 65, 59));
		addSlotToContainer(new SlotItemHandler(handler, 3, 95, 59));
		addSlotToContainer(new SlotItemHandler(handler, 4, 8, 62));

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
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return ItemStack.EMPTY;
	}

}