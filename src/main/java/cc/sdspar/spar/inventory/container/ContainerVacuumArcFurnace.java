package cc.sdspar.spar.inventory.container;

import cc.sdspar.spar.tileentity.TileEntityVacuumArcFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerVacuumArcFurnace extends Container {

	private final TileEntityVacuumArcFurnace vaf;
	
	private int progress;
	private int charge;
	private int capacity;

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
	public void updateProgressBar(int id, int data) {
		this.vaf.setField(id, data);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); i++) {
        	
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.progress != this.vaf.progress) {
                icontainerlistener.sendWindowProperty(this, 0, this.vaf.progress);
            }
            
            if (this.charge != (int) this.vaf.storage.getCharge()) {
            	icontainerlistener.sendWindowProperty(this, 1, (int) this.vaf.storage.getCharge());
            }

            if (this.capacity != (int) this.vaf.storage.getCapacity()) {
                icontainerlistener.sendWindowProperty(this, 2, (int) this.vaf.storage.getCapacity());
            }

        }

        this.progress = vaf.getField(0);
        this.charge = vaf.getField(1);
        this.capacity = vaf.getField(2);
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