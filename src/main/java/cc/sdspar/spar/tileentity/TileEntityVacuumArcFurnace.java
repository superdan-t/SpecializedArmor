package cc.sdspar.spar.tileentity;

import cc.sdspar.spar.inventory.handler.ItemStackHandlerVacuumArcFurnace;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.energy.CapabilityEnergy;

public class TileEntityVacuumArcFurnace extends TileEntityMachine {
	
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
			if (super.getEnergyStored() > 0) {
				if (progress >= 160) {
					((ItemStackHandlerVacuumArcFurnace)handler).processResults();
					progress = 0;
				} else {
					progress++;
					if (super.charge > consumptionRate) {
						super.charge -= consumptionRate;
					} else {
						super.charge = 0;
					}
				}
			} else {
				if (progress > 0) {
					progress--;
				}
			}
		}
		
		if (charging) {
			if (handler.getStackInSlot(4).hasCapability(CapabilityEnergy.ENERGY, null)) {
				receiveEnergy(handler.getStackInSlot(4).getCapability(CapabilityEnergy.ENERGY, null).extractEnergy(capacity - charge < 10 ? capacity - charge : chargeRate, false), false);
			}
		}
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("container.vacuum_arc_furnace");
	}

}
