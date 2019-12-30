package cc.sdspar.spar.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;

public interface IModEnergyHandler {
	
	public long getCharge();
	
	public long getCapacity();
	
	/**
	 * @return Amount accepted
	 */
	public long insertCharge(long charge, boolean simulate);
	
	public long extractCharge(long charge, boolean simulate);
	
	public NBTTagCompound serializeNBT();
	
	public void deserializeNBT(NBTTagCompound nbt);
	
	/**
	 * This is intentionally different from "hasCapability"
	 */
	public boolean providesCapability(Capability<?> capability);
	
	public <T> T getCapability(Capability<T> capability);
	
	
}
