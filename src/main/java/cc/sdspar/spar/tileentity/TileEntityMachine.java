package cc.sdspar.spar.tileentity;

import cc.sdspar.spar.util.Ref;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileEntityMachine extends TileEntity implements ITickable, IEnergyStorage {
	
	public ItemStackHandler handler = new ItemStackHandler();
	
	protected int capacity;
	protected int charge;
	protected int chargeRate;
	
	/**
	 * For tile entities that are machines and consume energy
	 * @param slots
	 * 	Number of slots this machine has
	 * @param capacity
	 * 	Maximum level of charge
	 * @param chargeRate
	 * 	Maximum charge rate (how much charge can be added at once?)
	 */
	public TileEntityMachine(int slots, int capacity, int chargeRate) {
		this.handler = new ItemStackHandler(slots);
		this.capacity = Math.round(capacity * Ref.ENERGY_MULT);
		this.chargeRate = Math.round(chargeRate * Ref.ENERGY_MULT);
	}
	
	public TileEntityMachine(ItemStackHandler handler, int capacity, int chargeRate) {
		this.handler = handler;
		this.capacity = Math.round(capacity * Ref.ENERGY_MULT);
		this.chargeRate = Math.round(chargeRate * Ref.ENERGY_MULT);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		else if (capability == CapabilityEnergy.ENERGY) return true;
		else return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		else if (capability == CapabilityEnergy.ENERGY) return (T) this;
		else return super.getCapability(capability, facing);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("charge", charge);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.charge = compound.getInteger("charge");
		
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int received = Math.min(capacity - charge, Math.min(maxReceive, chargeRate));
		if (!simulate) charge += received;
		return received;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored() {
		return charge;
	}

	@Override
	public int getMaxEnergyStored() {
		return capacity;
	}

	@Override
	public boolean canExtract() {
		return false;
	}

	@Override
	public boolean canReceive() {
		return true;
	}

}
