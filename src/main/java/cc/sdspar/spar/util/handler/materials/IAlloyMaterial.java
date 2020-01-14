package cc.sdspar.spar.util.handler.materials;

import net.minecraft.item.ItemStack;

public interface IAlloyMaterial {
	
	public int getProperty(ItemStack stack, EnumMaterialProperty prop);
	
	public void setProperty(ItemStack stack, EnumMaterialProperty prop, int value);

}
