package cc.sdspar.spar.tileentity;

import cc.sdspar.spar.energy.EnergyHelper;
import cc.sdspar.spar.energy.TileEntityEnergyConsumer;
import cc.sdspar.spar.inventory.handler.ItemStackHandlerShredder;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityShredder extends TileEntityEnergyConsumer {
	
	private static final int capacity = 5000;
	private static final int chargeRate = 100;
	private static final int consumptionRate = 28;
	
	public int progress = 0;
	
	public boolean charging = false;
	
	private int bladeState = 0;
	
	private ItemStackHandlerShredder sHandler;
	
	public TileEntityShredder() {
		super(new ItemStackHandlerShredder(), capacity, chargeRate);
		sHandler = (ItemStackHandlerShredder) this.handler;
		sHandler.shredder = this;
	}

	@Override
	public void update() {
		if (sHandler.validRecipe && !isJammed()) {
			if (super.storage.getCharge() > 0) {
				if (progress >= 120) {
					int resultsProcessed = sHandler.processResults();
					sHandler.getBlade().attemptDamageItem(resultsProcessed, this.world.rand, null);
					if (sHandler.getBlade().getItemDamage() >= sHandler.getBlade().getMaxDamage()) {
						handler.extractItem(7, 1, false);
					}
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
		} else {
			progress = 0;
		}
		
		if (charging) {
			this.storage.insertCharge(EnergyHelper.extractCharge(sHandler.getChargeProvider(), Math.min(TileEntityShredder.chargeRate, this.storage.getCapacity() - this.storage.getCharge()), false), false);
		}
	
	}
	
	public void jamShredder() {
		bladeState = 2;
		progress = 0;
		sHandler.getBlade().attemptDamageItem(7, this.world.rand, null);
	}
	
	public void unjamShredder() {
		bladeState = 0;
	}
	
	public boolean isJammed() {
		return bladeState == 2;
	}
	
	@Override
	public void onLoad() {
		sHandler.checkRecipes();
		sHandler.checkEnergySupply();
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
		return new TextComponentTranslation("container.shredder");
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
		case 3:
			this.bladeState = data;
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
		case 3:
			return this.bladeState;
		default:
			return 0;
		}
	}

}
