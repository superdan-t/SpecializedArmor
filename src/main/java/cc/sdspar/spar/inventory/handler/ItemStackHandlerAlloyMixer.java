package cc.sdspar.spar.inventory.handler;

import cc.sdspar.spar.energy.EnergyHelper;
import cc.sdspar.spar.tileentity.TileEntityAlloyMixer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerAlloyMixer extends ItemStackHandler {
	
	
	public boolean validInputs = false;
	
	public TileEntityAlloyMixer mixer;
	
	public ItemStackHandlerAlloyMixer() {
		super(6);
	}
	
	@Override
	public void onContentsChanged(int slot) {
		mixer.markDirty();
		if (slot == 4) {
			checkEnergySupply();
		} else if (slot <= 3) {
			mixer.progress = 0;
		}
	}
	
	@Override
	public boolean isItemValid(int slot, ItemStack stack) {
		//TODO Prevent overloading
		return (slot <= 3 || (slot == 4 && EnergyHelper.isChargeProvider(stack)));
	}
	
	public void processResults() {
		//TODO Make an AlloyMaterial helper, get the results from there
	}
	
	public ItemStack getChargeProvider() {
		return this.getStackInSlot(4);
	}
	
    public void checkEnergySupply() {
		if (getChargeProvider().isEmpty()) {
			mixer.charging = false;
		} else {
			mixer.charging = true;
		}
    }

}
