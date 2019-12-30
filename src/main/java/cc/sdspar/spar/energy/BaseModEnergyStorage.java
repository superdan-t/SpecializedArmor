package cc.sdspar.spar.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public class BaseModEnergyStorage implements IModEnergyHandler {

	protected long charge;
	
	protected long capacity;
	
	protected long chargeRate;
	
	protected long outputRate;
	
	/**
	 * Constructor for energy storage within this mod only, designed for
	 * compatibility with RF, ForgeEnergy, and Tesla
	 * 
	 * @param charge Current charge level, normally zero on new objects
	 * @param capacity Maximum energy that can be stored
	 * @param chargeRate Maximum charge accepted in one operation
	 * @param outputRate Maximum charge supplied in one operation
	 */
	public BaseModEnergyStorage(long charge, long capacity, long chargeRate, long outputRate) {
		this.charge = charge <= capacity ? charge : capacity;
		this.capacity = capacity;
		this.chargeRate = chargeRate;
		this.outputRate = outputRate;
	}
	
	public long getCharge() {
		return this.charge;
	}

	@Override
	public long getCapacity() {
		return this.capacity;
	}

	@Override
	public long insertCharge(long charge, boolean simulate) {
		long addedCharge = Math.min(Math.min(charge, this.chargeRate), this.capacity - this.charge);
		if (!simulate) {
			this.charge += addedCharge;
		}
		return addedCharge;
	}

	@Override
	public long extractCharge(long charge, boolean simulate) {
		long removedCharge = Math.min(Math.min(charge, this.outputRate), this.charge);
		if (!simulate) {
			this.charge -= removedCharge;
		}
		return removedCharge;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setLong("MCharge", this.charge);
		tag.setLong("MCapacity", this.capacity);
		tag.setLong("MChargeRate", this.chargeRate);
		tag.setLong("MOutputRate", this.outputRate);
		return tag;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.charge = nbt.getLong("MCharge");
		this.capacity = nbt.getLong("MCapacity");
		this.chargeRate = nbt.getLong("MChargeRate");
		this.outputRate = nbt.getLong("MOutputRate");

	}
	
	@Override
	public boolean providesCapability(Capability<?> capability) {
		if (capability == CapabilityEnergy.ENERGY) return true;
		else return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability) {
		if (capability == CapabilityEnergy.ENERGY) return (T) this;
		else return null;
	}
	
}
