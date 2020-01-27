package cc.sdspar.spar.tileentity;

import cc.sdspar.spar.energy.TileEntityEnergyConsumer;
import cc.sdspar.spar.inventory.handler.ItemStackHandlerAlloyFurnace;
import cc.sdspar.spar.util.handler.materials.EnumMaterialProperty;
import cc.sdspar.spar.util.handler.materials.MaterialHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityAlloyFurnace extends TileEntityEnergyConsumer {
	
	private static final int capacity = 100;
	private static final int chargeRate = 0;	
	private static final int consumptionRate = 0;
	
	private boolean activated = false;
	private boolean inputsChanged = false;
	private boolean inputsValid = false;
	private int progress = 0;
	private ItemStackHandlerAlloyFurnace fHandler;

	public TileEntityAlloyFurnace() {
		super(new ItemStackHandlerAlloyFurnace(), capacity, chargeRate);
		this.fHandler = (ItemStackHandlerAlloyFurnace) this.handler;
		this.fHandler.furnace = this;
	}

	@Override
	public void update() {
		
		if (inputsChanged) {
			inputsChanged = false;
			inputsValid = inputsValid();
		}
		
		if (activated) {
			if (inputsValid) {
				if (storage.getCharge() > 0) {
					// TODO Operation time should scale with output quantity/type
					if (progress >= 260) {
						processInputs();
						progress = 0;
					} else {
						storage.useCharge(consumptionRate, false);
						progress++;
					}
				} else {
					if (progress > 0) {
						progress--;
					}
				}
			}
			
		} else {
			progress = 0; // Deactivation may handle this, potentially remove
		}
		
	}
	
	public void inputChanged(int index) {
			inputsChanged = true;
	}
	
	public boolean inputsValid() {
		int total = 0;
		for (int i = 0; i < 4; i++) {
			total += MaterialHelper.getCraftingCapability(fHandler.getInput(i));
		}
		return total % 9 == 0 && total != 0;
	}
	
	private void processInputs() {
		// TODO Do stuff
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("container.alloy_furnace");
	}
	
	public void setField(int id, int data) {
		switch (id) {
		case 0:
			progress = data;
			break;
		case 1:
			this.storage.setCharge(data);
			break;
		case 2:
			this.storage.setCapacity(data);
			break;
		case 3:
			this.activated = data > 0;
			break;
		}
	}
	
	public int getField(int id) {
		switch (id) {
		case 0:
			return progress;
		case 1:
			return (int) this.storage.getCharge();
		case 2:
			return (int) this.storage.getCapacity();
		case 3:
			return activated ? 1 : 0;
		default:
			return 0;
		}
	}

}
