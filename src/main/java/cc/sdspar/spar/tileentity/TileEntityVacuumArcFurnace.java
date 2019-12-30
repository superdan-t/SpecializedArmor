package cc.sdspar.spar.tileentity;

import cc.sdspar.spar.energy.TileEntityEnergyConsumer;
import cc.sdspar.spar.inventory.handler.ItemStackHandlerVacuumArcFurnace;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityVacuumArcFurnace extends TileEntityEnergyConsumer {
	
	private static final int capacity = 10000;
	private static final int chargeRate = 100;
	private static final int consumptionRate = 8;
	
	public int progress = 0;
	
	public boolean charging = false;

	public TileEntityVacuumArcFurnace() {
		super(new ItemStackHandlerVacuumArcFurnace(), capacity, chargeRate);
		((ItemStackHandlerVacuumArcFurnace)handler).vaf = this;
	}

	@Override
	public void update() {
		if (((ItemStackHandlerVacuumArcFurnace)handler).validRecipe) {
			if (super.storage.getCharge() > 0) {
				if (progress >= 160) {
					((ItemStackHandlerVacuumArcFurnace)handler).processResults();
					progress = 0;
				} else {
					progress++;
					if (super.storage.getCharge() > consumptionRate) {
						super.storage.useCharge(consumptionRate, false);
					} else {
						super.storage.useCharge(super.storage.getCharge(), false);
					}
				}
			} else {
				if (progress > 0) {
					progress--;
				}
			}
		}
		
		if (charging) {
			//TODO Item charging support
		}
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("container.vacuum_arc_furnace");
	}
	
	public void setField(int id, int data) {
		switch (id) {
		case 0:
			this.progress = data;
			break;
		case 1:
			this.storage.setCharge(data);
			break;
		case 2:
			this.storage.setCapacity(data);
			break;
		}
	}

}
