package cc.sdspar.spar.inventory.container;

import cc.sdspar.spar.tileentity.TileEntityShredder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerShredder extends Container {

	private final TileEntityShredder shredder;
	
	private int progress;
	private int charge;
	private int capacity;
	private int bladeState;

	public ContainerShredder(InventoryPlayer inventory, TileEntityShredder tileEntity) {
		shredder = tileEntity;
		IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotItemHandler(handler, 0, 62, 8));
		addSlotToContainer(new SlotItemHandler(handler, 1, 80, 8));
		addSlotToContainer(new SlotItemHandler(handler, 2, 98, 8));
		addSlotToContainer(new SlotItemHandler(handler, 3, 62, 62));
		addSlotToContainer(new SlotItemHandler(handler, 4, 80, 62));
		addSlotToContainer(new SlotItemHandler(handler, 5, 98, 62));
		addSlotToContainer(new SlotItemHandler(handler, 6, 8, 62));
		addSlotToContainer(new SlotItemHandler(handler, 7, 152, 62));

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
		this.shredder.setField(id, data);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); i++) {
        	
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.progress != this.shredder.progress) {
                icontainerlistener.sendWindowProperty(this, 0, this.shredder.progress);
            }
            
            if (this.charge != (int) this.shredder.storage.getCharge()) {
            	icontainerlistener.sendWindowProperty(this, 1, (int) this.shredder.storage.getCharge());
            }

            if (this.capacity != (int) this.shredder.storage.getCapacity()) {
                icontainerlistener.sendWindowProperty(this, 2, (int) this.shredder.storage.getCapacity());
            }
            
            if (this.bladeState != this.shredder.getField(3)) {
            	icontainerlistener.sendWindowProperty(this, 3, this.shredder.getField(3));
            }

        }

        this.progress = shredder.getField(0);
        this.charge = shredder.getField(1);
        this.capacity = shredder.getField(2);
        this.bladeState = shredder.getField(3);
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