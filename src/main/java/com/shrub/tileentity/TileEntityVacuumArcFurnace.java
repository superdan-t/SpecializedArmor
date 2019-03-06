package com.shrub.tileentity;

import com.shrub.main.MachineRecipes;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityVacuumArcFurnace extends TileEntityMachineTemplate implements IEnergyHandler {
	
	private static String localizedName;
	
	protected EnergyStorage storage = new EnergyStorage(10000);
	
	@SideOnly(Side.CLIENT)
	public int energyStoredPlaceholder;
	
	public int progress;
	
	public TileEntityVacuumArcFurnace() {
		super(localizedName, 5);
		
	}
	
	@Override
	public void updateEntity() {
		
		if (worldObj.isRemote) {
			return;
		}
		
		boolean flag1 = false;
		
		int recipeID = MachineRecipes.getVacuumArcFurnaceRecipeID(slots);
		
		if (recipeID != 0) {
			if (canProcess(recipeID)) {
				progress++;
				if (progress >= 300) {
					addResults(recipeID);
					reduceGrid();
					progress = 0;
					flag1 = true;
				}
			} else {
				progress = 0;
			}
		} else {
			progress = 0;
		}
		
		if (flag1) {
			markDirty();
		}
		
	}
	
	private boolean canProcess(int recipeID) {
		ItemStack[] results = MachineRecipes.getVacuumArcFurnaceResult(recipeID);
		for (int i = 0; i < 2; i++) {
			if (slots[i + 2] != null) {
				if (results[i] != null) {
					if (results[i].isItemEqual(slots[i + 2])) {
						if (slots[i + 2].getMaxStackSize() - slots[i + 2].stackSize < results[i].stackSize) {
							System.out.println("am got here");
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
		
		return true;
		
	}
	
	private void addResults(int recipeID) {
		ItemStack[] results = MachineRecipes.getVacuumArcFurnaceResult(recipeID);
		
		for (int i = 0; i < 2; i++) {
			if (results[i] != null) {
				if (slots[i + 2] == null) {
					slots[i + 2] = results[i].copy();
				} else if (slots[i + 2].isItemEqual(results[i]) && 64 - slots[i + 2].stackSize >= results[i].stackSize) {
					slots[i + 2].stackSize += results[i].stackSize;
				}
			}
		}
		
	}
	
	private void reduceGrid() {
		for (int i = 0; i < 2; i++) {
			if (slots[i] != null) {
				slots[i].stackSize--;
				if (slots[i].stackSize == 0) {
					slots[i] = null;
				}
			}
		}
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
