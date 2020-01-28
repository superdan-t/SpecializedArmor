package cc.sdspar.spar.util.handler.materials;

import net.minecraft.item.ItemStack;

/**
 * New items with properties should implement this, but 
 * Properties should be retrieved/set through MaterialHelper to support vanilla items
 * and potential interface changes
 *
 */
public interface IAlloyMaterial {
	
	public int getProperty(ItemStack stack, EnumMaterialProperty prop);
	
	public MaterialProperties getMaterialProperties(ItemStack stack);
	
	public int getCraftingValue(ItemStack stack);
	
	public void setProperty(ItemStack stack, EnumMaterialProperty prop, int value);

	public void setMaterialProperties(ItemStack stack, MaterialProperties props);

}
