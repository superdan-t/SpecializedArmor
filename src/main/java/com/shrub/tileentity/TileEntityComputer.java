package com.shrub.tileentity;

import com.shrub.inventory.ControlChipUtility;
import com.shrub.items.ModItems;

public class TileEntityComputer extends TileEntityMachineTemplate {
	
	private static String localizedName;
	private ControlChipUtility ctrlChipUtil = new ControlChipUtility();
	public int usage;
	public int capacity;
	public int errorCode;
	public boolean circuitLoaded;
	
	public TileEntityComputer() {
		super(localizedName, 1);
	}
	
	public void updateEntity() {
		circuitLoaded = circuitLoaded();
		if (!circuitLoaded())
			return;
			
		this.usage = slots[0].stackTagCompound.getInteger("usage");
		this.capacity = slots[0].stackTagCompound.getInteger("capacity");
		
	}
	
	public boolean circuitLoaded() {
		if (slots[0] != null && slots[0].getItem() == ModItems.controlChip)
			return true;
		return false;
	}
	
	public void programValue(int vNameInt, int nValue) {
		ctrlChipUtil.programValue(slots[0], vNameInt, nValue);
		//programValue(getVariableNameFromId(vNameInt), nValue);
	}
	
	public void programValue(String vName, int nValue) {
		ctrlChipUtil.programValue(slots[0], vName, nValue);
		
		/*
		updateEntity();
		
		if (!circuitLoaded || !ctrlChipUtil.vNameValid(vName))
			return;
		
		System.out.println("Setting value " + vName + " to " + Integer.toString(nValue));
		
		NBTTagCompound nbt = slots[0].getTagCompound();
		
		if (vName == "clear") {
			nbt = new NBTTagCompound();
			nbt.setInteger("capacity", capacity);
		} else if (nbt.getBoolean(vName + "Exists")) {
			nbt.setInteger(vName, nValue);
		} else if (!nbt.getBoolean(vName + "Exists")) {
			if (usage >= capacity)
				return;
			nbt.setBoolean(vName + "Exists", true);
			nbt.setInteger(vName, nValue);
			nbt.setInteger("usage", ++usage);
		}
		
		slots[0].setTagCompound(nbt);
		*/
		
	}
	
	public void removeValue(int vNameInt) {
		ctrlChipUtil.removeValue(slots[0], vNameInt);
	}
	
}
