package cc.sdspar.spar.util.handler.materials;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Use this to do any work with materials, don't handle materials with the
 * items/blocks directly. 
 *
 */
public class MaterialHelper {
	
	public static int getProperty(ItemStack stack, EnumMaterialProperty prop) {
		if (stack.getItem() instanceof IAlloyMaterial) {
			return ((IAlloyMaterial) (stack.getItem())).getProperty(stack, prop);
		} else {
			if (!stack.isEmpty()) System.out.println(OreDictionary.getOreIDs(stack));
			return 0;
		}
	}
	
	public static void setProperty(ItemStack stack, EnumMaterialProperty prop) {
		
	}
	
	/**
	 * Crafting value refers to the individual item only
	 * @param stack
	 * @return Dust - 1, Ingot - 9, Block - 81 
	 */
	public static int getCraftingValue(ItemStack stack) {
		if (stack.getItem() instanceof IAlloyMaterial) {
			return ((IAlloyMaterial) (stack.getItem())).getCraftingValue(stack);
		} else if (stack.getItem() instanceof ItemBlock) {
			return 81;
		} else if (!stack.isEmpty()){
			return 9;
		} else {
			return 0;
		}
	}
	
	/**
	 * Crafting capability is all of the crafting values added together
	 * @param stack
	 * @return Combined crafting values of the whole stack
	 */
	public static int getCraftingCapability(ItemStack stack) {
		return getCraftingValue(stack) * stack.getCount();
	}

}
