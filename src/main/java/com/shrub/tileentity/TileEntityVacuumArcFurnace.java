package com.shrub.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityVacuumArcFurnace extends TileEntityMachineTemplate implements IEnergyHandler {
	
	private static String localizedName;
	
	protected EnergyStorage storage = new EnergyStorage(10000);
	
	public TileEntityVacuumArcFurnace() {
		super(localizedName, 4);
		
	}
	
	@Override
	public void updateEntity() {
		
	}

	public void setGUIDisplayName(String displayName) {
		TileEntityVacuumArcFurnace.localizedName = displayName;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		storage.readFromNBT(nbt);
		
		if (nbt.hasKey("CustomName")) {
			TileEntityVacuumArcFurnace.localizedName = nbt.getString("CustomName");
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		storage.writeToNBT(nbt);

		if (this.hasCustomInventoryName()) {
			nbt.setString("CustomName", TileEntityVacuumArcFurnace.localizedName);
		}

	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		if (from == ForgeDirection.getOrientation(this.blockMetadata) || from == ForgeDirection.UP)
			return false;
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getMaxEnergyStored();
	}
	
}
