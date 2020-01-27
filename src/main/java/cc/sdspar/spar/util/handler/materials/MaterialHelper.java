package cc.sdspar.spar.util.handler.materials;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

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
			//TODO Properties for vanilla materials
			return 0;
		}
	}
	
	public static MaterialProperties getMaterialProperties(ItemStack stack) {
		if (stack.getItem() instanceof IAlloyMaterial) {
			return ((IAlloyMaterial) (stack.getItem())).getMaterialProperties(stack);
		} else {
			//TODO Properties for vanilla materials
			return new MaterialProperties();
		}
	}
	
	public static void setProperty(ItemStack stack, EnumMaterialProperty prop, int value) {
		if (stack.getItem() instanceof IAlloyMaterial) {
			((IAlloyMaterial) (stack.getItem())).setProperty(stack, prop, value);
		}
	}
	
	public static MaterialProperties combineStackMaterials(ItemStack s1, ItemStack s2) {
		
		int s1PropArray[] = getMaterialProperties(s1).getPropertiesAsArray();
		int s2PropArray[] = getMaterialProperties(s2).getPropertiesAsArray();
		
		int combinedPropArray[] = new int[EnumMaterialProperty.getPropertyCount()];
		
		for (int i = 0; i < combinedPropArray.length; i++) {
			combinedPropArray[i] = s1PropArray[i] + s2PropArray[i];
		}
		
		return new MaterialProperties(combinedPropArray);
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
