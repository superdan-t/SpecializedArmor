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
	private int progress = 0;
	private ItemStackHandlerAlloyFurnace fHandler;

	public TileEntityAlloyFurnace() {
		super(new ItemStackHandlerAlloyFurnace(), capacity, chargeRate);
		this.fHandler = (ItemStackHandlerAlloyFurnace) this.handler;
		this.fHandler.furnace = this;
	}

	@Override
	public void update() {
		
		if (activated) {
			/*
			 * if operation is valid, then:
			 * 		if charged, then:
			 * 			if operation complete, then:
			 * 				processInputs();
			 * 				progress = 0;
			 * 			else (operation not complete), then:
			 * 				consumeEnergy();
			 * 				progress++;
			 * 		else (not charged), then:
			 * 			if progress > 0, then:
			 * 				progress--;
			 */
		} else {
			progress = 0; // Deactivation may handle this, potentially remove
		}
		
	}
	
	public void inputChanged(int index) {
		System.out.println(MaterialHelper.getCraftingValue(fHandler.getInput(index)));
	}
	
	public boolean inputsValid() {
		/*
		 * For inputs to be valid, they must all have the same crafting value (same type, like all dust or all ingots), and
		 * the total crafting capability of all inputs must be a multiple of 9 so there's no confusion as to what inputs to mix
		 */
		return (MaterialHelper.getCraftingValue(fHandler.getInput(0)) == MaterialHelper.getCraftingValue(fHandler.getInput(1)) &&
				MaterialHelper.getCraftingValue(fHandler.getInput(0)) == MaterialHelper.getCraftingValue(fHandler.getInput(2)) &&
				MaterialHelper.getCraftingValue(fHandler.getInput(0)) == MaterialHelper.getCraftingValue(fHandler.getInput(3)) &&
				MaterialHelper.getCraftingCapability(fHandler.getInput(0)) % 9 == 0 &&
				MaterialHelper.getCraftingCapability(fHandler.getInput(1)) % 9 == 0 &&
				MaterialHelper.getCraftingCapability(fHandler.getInput(2)) % 9 == 0 &&
				MaterialHelper.getCraftingCapability(fHandler.getInput(3)) % 9 == 0);
		
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
