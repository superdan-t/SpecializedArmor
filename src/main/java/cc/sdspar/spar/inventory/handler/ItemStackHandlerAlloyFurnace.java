package cc.sdspar.spar.inventory.handler;

import cc.sdspar.spar.energy.EnergyHelper;
import cc.sdspar.spar.tileentity.TileEntityAlloyFurnace;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerAlloyFurnace extends ItemStackHandler {
	
	public TileEntityAlloyFurnace furnace;

	public ItemStackHandlerAlloyFurnace() {
		super(6);
	}
	
	@Override
	public boolean isItemValid(int slot, ItemStack stack) {
		return (slot <= 3 || (slot == 5 && EnergyHelper.isChargeProvider(stack)));
	}
	
	@Override
	public void onContentsChanged(int slot) {
		this.furnace.markDirty();
		if (slot <= 3) furnace.inputChanged(slot);
	}
	
	public ItemStack getInternalChargeProvider() {
		return this.stacks.get(5);
	}
	
	public ItemStack getInput(int index) {
		return index <= 3 ? this.stacks.get(index) : ItemStack.EMPTY;
	}
	
	public ItemStack getResult(int index) {
		return index == 0 ? this.stacks.get(4) : ItemStack.EMPTY;
	}
	
	/**
	 * For results processing by the TileEntity
	 * @param index Included on provision of future interfacing
	 * @param result
	 * @return
	 */
	public ItemStack addResult(int index, ItemStack result) {
		return index == 0 ? insertItem(4, result, false) : result;
	}

}
