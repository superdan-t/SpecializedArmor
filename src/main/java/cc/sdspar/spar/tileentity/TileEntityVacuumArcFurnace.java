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
					this.markDirty();
				}
			} else {
				if (progress > 0) {
					progress--;
				}
			}
		}
		
		if (charging) {
			//TODO Item charging support
			this.storage.insertCharge(10, false);
		}
	
	}
	
	@Override
	public void onLoad() {
		// The onLoad function for ItemStackHandlers only runs client side :(
		((ItemStackHandlerVacuumArcFurnace)handler).checkRecipes();
		((ItemStackHandlerVacuumArcFurnace)handler).checkEnergySupply();
	}
	
	public void syncUpdates() {
		this.world.markBlockRangeForRenderUpdate(this.pos, this.pos);
		this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos),
				3);
		this.world.scheduleBlockUpdate(this.pos, this.getBlockType(), 0, 0);
		markDirty();
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
	
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.progress;
		case 1:
			return (int) this.storage.getCharge();
		case 2:
			return (int) this.storage.getCapacity();
		default:
			return 0;
		}
	}

}
