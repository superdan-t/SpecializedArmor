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
	
	public static void setMaterialProperties(ItemStack stack, MaterialProperties props) {
		if (stack.getItem() instanceof IAlloyMaterial) {
			((IAlloyMaterial) (stack.getItem())).setMaterialProperties(stack, props);
		}
	}
	
	public static MaterialProperties combineStackMaterials(ItemStack...stacks) {
		MaterialProperties[] props = new MaterialProperties[stacks.length];
		for (int i = 0; i < stacks.length; i++) {
			props[i] = getMaterialProperties(stacks[i]);
		}
		return combineMaterials(props);
	}
	
	public static MaterialProperties combineMaterials(MaterialProperties...props) {
		int combinedPropArray[] = new int[EnumMaterialProperty.getPropertyCount()];
		for (int i = 0; i < props.length; i++) {
			int[] currentPropArray = props[i].getPropertiesAsArray();
			for (int j = 0; j < EnumMaterialProperty.getPropertyCount(); j++) {
				combinedPropArray[j] += currentPropArray[j];
			}
		}
		return new MaterialProperties(combinedPropArray);
	}
	
	public static boolean stackMaterialsEqual(ItemStack...stacks) {
		int[] s1PropArray = getMaterialProperties(stacks[0]).getPropertiesAsArray();
		for (int j = 0; j < stacks.length; j++) {
			int[] currentPropArray = getMaterialProperties(stacks[j]).getPropertiesAsArray();
			for (int i = 0; i < EnumMaterialProperty.getPropertyCount(); i++) {
				if (s1PropArray[i] != currentPropArray[i]) return false;
			}
		}
		return true;
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
