package cc.sdspar.spar.inventory.container;

import cc.sdspar.spar.tileentity.TileEntityAlloyFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAlloyFurnace extends Container {
	
	private final TileEntityAlloyFurnace furnace;

	public ContainerAlloyFurnace(InventoryPlayer inventory, TileEntityAlloyFurnace tile) {
		this.furnace = tile;
		IItemHandler handler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotItemHandler(handler, 0, 41, 17));
		addSlotToContainer(new SlotItemHandler(handler, 1, 61, 17));
		addSlotToContainer(new SlotItemHandler(handler, 2, 81, 17));
		addSlotToContainer(new SlotItemHandler(handler, 3, 101, 17));
		addSlotToContainer(new SlotItemHandler(handler, 4, 121, 78));
		addSlotToContainer(new SlotItemHandler(handler, 5, 8, 69));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 100 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 158));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
