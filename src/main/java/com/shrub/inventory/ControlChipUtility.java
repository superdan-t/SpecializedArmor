package com.shrub.inventory;

import com.shrub.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * A utility used to easily implement a control chip with a TileEntity, player, tool, or other object.
 * @author Daniel T (RandomShrub)
 *
 */
public class ControlChipUtility {
	
	private EntityPlayer player;
	
	private ItemStack controlChip;
	
	private int usage;
	
	private int capacity;
	
	/**
	 * Blank constructor that can be used to access general methods.
	 */
	public ControlChipUtility() { };
	
	/**
	 * Use the utility on a player's control chip.
	 * @author Daniel T (RandomShrub)
	 * @param player
	 */
	public ControlChipUtility(EntityPlayer player) {
		this.player = player;
	}
	
	/**
	 * Use the utility on a control chip that is fed to it.
	 * @param controlChip
	 */
	public ControlChipUtility(ItemStack controlChip) {
		this.controlChip = controlChip;
	}
	
	/**
	 * Get the player's control chip. If there are multiple, the first one will be used.
	 * @return The player's current control chip, or null if they don't have one.
	 */
	public ItemStack getControlChip() {
		if (player == null)
			return controlChip;
		for (int i = 0; i < 36; i++) {
			if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == ModItems.controlChip) {
				return player.inventory.mainInventory[i];
			}
		}
		return null;
	}
	
	/**
	 * Check whether the control chip has stored a variable.
	 * @param vName
	 * @return True (Variable exists) or False (Doesn't exist)
	 */
	public boolean variableExists(String vName) {
		controlChip = getControlChip();
		return variableExists(controlChip, vName);
	}
	
	/**
	 * Check whether a control chip has stored a variable
	 * @param controlChip
	 * @param vName
	 * @return True or False
	 */
	public boolean variableExists(ItemStack controlChip, String vName) {
		if (controlChip != null && controlChip.hasTagCompound())
			return controlChip.stackTagCompound.getBoolean(vName + "Exists");
		return false;	
	}
	
	/**
	 * Check if a variable name is real or not.
	 * @param vName
	 * @return True or False
	 */
	public boolean vNameValid(String vName) {
		if (getVariableIdFromName(vName) == 0)
			return false;
		return true;
	}
	
	/**
	 * @param variable name
	 * @return variable's ID
	 */
	public int getVariableIdFromName(String variable) {
		if (variable == "clear")
			return 1;
		else if (variable == "drillSize")
			return 2;
		else if (variable == "augerSize")
			return 3;
		else if (variable == "plowSize")
			return 4;
		else
			return 0;
	}
	
	/**
	 * @param varID
	 * @return variable's name as String
	 */
	public String getVariableNameFromId(int varID) {
		if (varID == 1)
			return "clear";
		else if (varID == 2)
			return "drillSize";
		else if (varID == 3)
			return "augerSize";
		else if (varID == 4)
			return "plowSize";
		else
			return null;
	}

	public boolean programValue(int vNameInt, int nValue) {
		return programValue(getControlChip(), getVariableNameFromId(vNameInt), nValue);
	}
	
	public boolean programValue(ItemStack controlChip, int vNameInt, int nValue) {
		return programValue(controlChip, getVariableNameFromId(vNameInt), nValue);
	}
	
	public boolean programValue(String vName, int nValue) {
		return programValue(getControlChip(), vName, nValue);
	}
	
	public boolean programValue(ItemStack controlChip, String vName, int nValue) {
		
		if (!vNameValid(vName) || controlChip == null || !controlChip.hasTagCompound())
			return false;
		
		NBTTagCompound nbt = controlChip.getTagCompound();
		
		updateStats(controlChip);
		
		if (vName == "clear") {
			nbt = new NBTTagCompound();
			nbt.setInteger("capacity", capacity);
		} else if (nbt.getBoolean(vName + "Exists")) {
			nbt.setInteger(vName, nValue);
		} else if (!nbt.getBoolean(vName + "Exists")) {
			if (usage >= capacity)
				return false;
			nbt.setBoolean(vName + "Exists", true);
			nbt.setInteger(vName, nValue);
			nbt.setInteger("usage", ++usage);
		}
		
		controlChip.setTagCompound(nbt);
		
		return true;
		
	}
	
	public void removeValue(int vNameInt) {
		removeValue(getControlChip(), vNameInt);
	}
	
	public void removeValue(ItemStack controlChip, int vNameInt) {
		removeValue(controlChip, getVariableNameFromId(vNameInt));
	}
	
	public void removeValue(String vName) {
		removeValue(getControlChip(), vName);
	}
	
	public void removeValue(ItemStack controlChip, String vName) {
		updateStats(controlChip);
		if (!vNameValid(vName))
			return;
		else if (!controlChip.stackTagCompound.getBoolean(vName + "Exists"))
			return;
		controlChip.stackTagCompound.setBoolean(vName + "Exists", false);
		controlChip.stackTagCompound.setInteger("usage", usage - 1);
	}
	
	public int getValue(int vName) {
		return getValue(getVariableNameFromId(vName));
	}
	
	public int getValue(ItemStack controlChip, int vName) {
		return getValue(controlChip, getVariableNameFromId(vName));
	}
	
	public int getValue(String vName) {
		return getValue(getControlChip(), vName);
	}
	
	public int getValue(ItemStack controlChip, String vName) {
		if (vNameValid(vName) && controlChip != null && controlChip.hasTagCompound())
			return controlChip.stackTagCompound.getInteger(vName);
		return 0;
	}
	
	private void updateStats(ItemStack controlChip) {
		if (controlChip != null && controlChip.hasTagCompound()) {
			usage = controlChip.stackTagCompound.getInteger("usage");
			capacity = controlChip.stackTagCompound.getInteger("capacity");
		} else if (controlChip != null && !controlChip.hasTagCompound()) {
			controlChip.stackTagCompound = new NBTTagCompound();
			controlChip.stackTagCompound.setInteger("capacity", 2);
		}
	}
	
}
