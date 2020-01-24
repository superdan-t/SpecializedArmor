package cc.sdspar.spar.tileentity;

import cc.sdspar.spar.energy.EnergyHelper;
import cc.sdspar.spar.energy.TileEntityEnergyConsumer;
import cc.sdspar.spar.inventory.handler.ItemStackHandlerAlloyMixer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityAlloyMixer extends TileEntityEnergyConsumer {
	
	private static final int capacity = 15000;
	private static final int chargeRate = 100;
	private static final int consumptionRate = 57;
	
	private ItemStackHandlerAlloyMixer mHandler;
	
	public int progress = 0;
	public boolean charging = false;
	public boolean processing = false;

	public TileEntityAlloyMixer() {
		super(new ItemStackHandler(6), capacity, chargeRate);
		//mHandler = (ItemStackHandlerAlloyMixer) this.handler;
		//mHandler.mixer = this;
	}

	@Override
	public void update() {
		if (processing) {
			if (this.storage.getCharge() > 0) {
				if (progress >= 260) {
					mHandler.processResults();
				} else {
					progress++;
					this.storage.useCharge(consumptionRate, false);
				}
			} else {
				progress = 0;
			}
		}
		
		if (charging) {
			this.storage.insertCharge(EnergyHelper.extractCharge(this.handler.getStackInSlot(4), Math.min(TileEntityAlloyMixer.chargeRate, this.storage.getCapacity() - this.storage.getCharge()), false), false);
		}

	}
	
	@Override
	public void onLoad() {
		//mHandler.checkEnergySupply();
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
		return new TextComponentTranslation("container.alloy_mixer");
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
