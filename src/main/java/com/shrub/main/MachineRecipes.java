package com.shrub.main;

import com.shrub.items.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MachineRecipes {

	// Utilities
	
	/**
	 * Counts how many slots are empty. Inclusive of end.
	 * @param slots
	 * @param startSlot
	 * @param endSlot
	 * @return Number of empty slots
	 */
	private static int getEmptySlots(ItemStack[] inputs, int start, int end) {
		int emptySlots = 0;
		for (int i = start; i <= end && i < inputs.length; i++) {
			if (inputs[i] == null)
				emptySlots++;
		}
		return emptySlots;
		
	}
	
	/**
	 * Checks whether an ItemStack contains the specified items.
	 * @param itemStack
	 * @param item
	 * @return True or False
	 */
	private static boolean compareItems(ItemStack itemStack, Item item) {
		if (itemStack == null || item == null)
			return false;
		else if (itemStack.getItem() == item)
			return true;
		else
			return false;
	}
	
	/**
	 * Counts how many slots have the specified item. If exclusive, return will be 0 if a different item is found.
	 * @param slots
	 * @param startSlot
	 * @param endSlot (inclusive)
	 * @param item
	 * @param exclusive
	 */
	private static int countSlotsOfItem(ItemStack[] slots, int startSlot, int endSlot, Item item, boolean exclusive) {
		int slotsOfItem = 0;
		for (int i = startSlot; i <= endSlot && i < slots.length; i++) {
			if (slots[i] != null && slots[i].getItem() == item)
				slotsOfItem++;
			else if (slots[i] != null && slots[i].getItem() != item && exclusive)
				return 0;
		}
		return slotsOfItem;
	}
	
	// Foundry Recipes
	
	/**Get the ID number of the smelting recipe currently in the furnace.
	 * 
	 * @param inputs
	 * @param primer
	 * @return
	 */
	public static int getFoundryRecipeID(ItemStack[] inputs, ItemStack primer) {
		if ((compareItems(inputs[0], ModItems.zirconiumCarbide) && compareItems(inputs[1], ModItems.zirconiumCarbide) && compareItems(inputs[2], ModItems.zirconiumCarbide) ||
				compareItems(inputs[3], ModItems.zirconiumCarbide) && compareItems(inputs[4], ModItems.zirconiumCarbide) && compareItems(inputs[5], ModItems.zirconiumCarbide) ||
				compareItems(inputs[6], ModItems.zirconiumCarbide) && compareItems(inputs[7], ModItems.zirconiumCarbide) && compareItems(inputs[8], ModItems.zirconiumCarbide))
				&& getEmptySlots(inputs, 0, 9) == 7)
			return 1;
		else if ((compareItems(inputs[0], ModItems.zirconiumCarbide) && compareItems(inputs[3], ModItems.zirconiumCarbide) ||
				compareItems(inputs[1], ModItems.zirconiumCarbide) && compareItems(inputs[4], ModItems.zirconiumCarbide) ||
				compareItems(inputs[2], ModItems.zirconiumCarbide) && compareItems(inputs[5], ModItems.zirconiumCarbide) ||
				compareItems(inputs[3], ModItems.zirconiumCarbide) && compareItems(inputs[6], ModItems.zirconiumCarbide) ||
				compareItems(inputs[4], ModItems.zirconiumCarbide) && compareItems(inputs[7], ModItems.zirconiumCarbide) ||
				compareItems(inputs[5], ModItems.zirconiumCarbide) && compareItems(inputs[8], ModItems.zirconiumCarbide))
				&& getEmptySlots(inputs, 0, 9) == 8)
			return 2;
		else if (countSlotsOfItem(inputs, 0, 8, ModItems.zirconiumCarbide, true) == 1 && inputs[9] == null)
			return 7;
		else if (((compareItems(inputs[0], ModItems.zirconiumCarbide) && compareItems(inputs[3], ModItems.zirconiumCarbide) && compareItems(inputs[6], ModItems.zirconiumCarbide)) ||
				(compareItems(inputs[1], ModItems.zirconiumCarbide) && compareItems(inputs[4], ModItems.zirconiumCarbide) && compareItems(inputs[7], ModItems.zirconiumCarbide)) ||
				(compareItems(inputs[1], ModItems.zirconiumCarbide) && compareItems(inputs[4], ModItems.zirconiumCarbide) && compareItems(inputs[7], ModItems.zirconiumCarbide)))
				&& getEmptySlots(inputs, 0, 9) == 7)
			return 4;
		else if ((compareItems(inputs[1], ModItems.zirconiumCarbide) && compareItems(inputs[4], ModItems.zirconiumCarbide) && (compareItems(inputs[0], ModItems.zirconiumCarbide) || compareItems(inputs[2], ModItems.zirconiumCarbide)) ||
				compareItems(inputs[4], ModItems.zirconiumCarbide) && compareItems(inputs[7], ModItems.zirconiumCarbide) && (compareItems(inputs[3], ModItems.zirconiumCarbide) || compareItems(inputs[5], ModItems.zirconiumCarbide)) ||
				compareItems(inputs[0], ModItems.zirconiumCarbide) && compareItems(inputs[1], ModItems.zirconiumCarbide) && compareItems(inputs[3], ModItems.zirconiumCarbide) ||
				compareItems(inputs[3], ModItems.zirconiumCarbide) && compareItems(inputs[4], ModItems.zirconiumCarbide) && compareItems(inputs[6], ModItems.zirconiumCarbide) ||
				compareItems(inputs[1], ModItems.zirconiumCarbide) && compareItems(inputs[2], ModItems.zirconiumCarbide) && compareItems(inputs[5], ModItems.zirconiumCarbide) ||
				compareItems(inputs[4], ModItems.zirconiumCarbide) && compareItems(inputs[5], ModItems.zirconiumCarbide) && compareItems(inputs[8], ModItems.zirconiumCarbide))
				&& getEmptySlots(inputs, 0, 9) == 7)
			return 5;
		else if ((compareItems(inputs[1], ModItems.zirconiumCarbide) && (compareItems(inputs[0], ModItems.zirconiumCarbide) || compareItems(inputs[2], ModItems.zirconiumCarbide)) ||
				compareItems(inputs[4], ModItems.zirconiumCarbide) && (compareItems(inputs[3], ModItems.zirconiumCarbide) || compareItems(inputs[5], ModItems.zirconiumCarbide)) ||
				compareItems(inputs[7], ModItems.zirconiumCarbide) && (compareItems(inputs[6], ModItems.zirconiumCarbide) || compareItems(inputs[8], ModItems.zirconiumCarbide)))
				&& getEmptySlots(inputs, 0, 9) == 8)
			return 6;
		return 0;
	}
	
	public static int getFoundryHeatRequired(int recipeID) {
		switch (recipeID) {
		case 1:
			return 4500;
		case 2:
			return 4500;
		case 3:
			return 4500;
		case 4:
			return 4500;
		case 5:
			return 4500;
		case 6:
			return 4500;
		case 7:
			return 4500;
		}
		return 0;
	}
	
	public static int getFoundryProcessingTime(int recipeID) {
		switch (recipeID) {
		case 1:
			return 400;
		case 2:
			return 300;
		case 3:
			return 100;
		case 4:
			return 400;
		case 5:
			return 400;
		case 6:
			return 300;
		case 7:
			return 100;
		}
		return 0;
	}
	
	public static ItemStack[] getFoundryResult(int recipeID) {
		ItemStack[] results = new ItemStack[4];
		switch (recipeID) {
		case 1:
			results[0] = new ItemStack(ModItems.zirconiumToolTip, 1, 5);
			return results;
		case 2:
			results[0] = new ItemStack(ModItems.zirconiumToolTip, 1, 1);
			return results;
		case 3:
			results[0] = new ItemStack(ModItems.zirconiumToolTip, 1, 0);
			return results;
		case 4:
			results[0] = new ItemStack(ModItems.zirconiumToolTip, 1, 2);
			return results;
		case 5:
			results[0] = new ItemStack(ModItems.zirconiumToolTip, 1, 3);
			return results;
		case 6:
			results[0] = new ItemStack(ModItems.zirconiumToolTip, 1, 4);
			return results;
		case 7:
			results[0] = new ItemStack(ModItems.zirconiumToolTip, 1, 6);
			return results;
		}
		return null;
	}
	
	//Vacuum Arc Furnace Recipes
	
	public static int getVacuumArcFurnaceRecipeID(ItemStack[] inputs) {
		if (countSlotsOfItem(inputs, 0, inputs.length - 1, ModItems.zirconIngot, true) == 1) {
			return 1;
		}
		return 0;
	}
	
	public static ItemStack[] getVacuumArcFurnaceResult(int recipeID) {
		ItemStack[] results = new ItemStack[2];
		switch (recipeID) {
		case 1:
			results[0] = new ItemStack(ModItems.zirconiumCarbide, 2);
			return results;
		}
		return null;
	}

}
