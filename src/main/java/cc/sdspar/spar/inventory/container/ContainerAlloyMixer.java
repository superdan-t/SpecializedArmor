package cc.sdspar.spar.inventory.container;

import cc.sdspar.spar.tileentity.TileEntityAlloyMixer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAlloyMixer extends Container {
	
	private final TileEntityAlloyMixer mixer;
	private int progress;
	private int charge;
	private int capacity;

	public ContainerAlloyMixer(InventoryPlayer inventory, TileEntityAlloyMixer mixer) {
		this.mixer = mixer;
		IItemHandler handler = mixer.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotItemHandler(handler, 0,  41, 17));
		addSlotToContainer(new SlotItemHandler(handler, 1,  61, 17));
		addSlotToContainer(new SlotItemHandler(handler, 2,  81, 17));
		addSlotToContainer(new SlotItemHandler(handler, 3, 101, 17));
		addSlotToContainer(new SlotItemHandler(handler, 4,   8, 69));
		addSlotToContainer(new SlotItemHandler(handler, 5, 121, 78));
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
	public void updateProgressBar(int id, int data) {
		this.mixer.setField(id, data);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); i++) {
        	
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.progress != this.mixer.progress) {
                icontainerlistener.sendWindowProperty(this, 0, this.mixer.progress);
            }
            
            if (this.charge != (int) this.mixer.storage.getCharge()) {
            	icontainerlistener.sendWindowProperty(this, 1, (int) this.mixer.storage.getCharge());
            }

            if (this.capacity != (int) this.mixer.storage.getCapacity()) {
                icontainerlistener.sendWindowProperty(this, 2, (int) this.mixer.storage.getCapacity());
            }

        }

        this.progress = mixer.getField(0);
        this.charge = mixer.getField(1);
        this.capacity = mixer.getField(2);
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
