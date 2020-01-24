package cc.sdspar.spar.item;

import java.util.List;

import cc.sdspar.spar.util.handler.materials.EnumMaterialProperty;
import cc.sdspar.spar.util.handler.materials.IAlloyMaterial;
import cc.sdspar.spar.util.handler.materials.MaterialProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAlloyMaterial extends ItemBase implements IAlloyMaterial {
	
	private final int craftingValue;
	
	/**
	 * For items with custom materials
	 * @param type dust/ingot natively supported
	 */
	public ItemAlloyMaterial(String type) {
		super("alloy_" + type);
		if (type == "dust") craftingValue = 1;
		else if (type == "ingot") craftingValue = 9;
		else craftingValue = 0;
	}

	@Override
	public int getProperty(ItemStack stack, EnumMaterialProperty prop) {
		MaterialProperties mat = new MaterialProperties();
		mat.deserializeNBT(stack.getTagCompound());
		return mat.getProperty(prop);
	}
	
	@Override
	public int getCraftingValue(ItemStack stack) {
		return craftingValue;
	}

	@Override
	public void setProperty(ItemStack stack, EnumMaterialProperty prop, int value) {
		MaterialProperties mat = new MaterialProperties();
		mat.deserializeNBT(stack.getTagCompound());
		mat.setProperty(prop, value);
		stack.getTagCompound().setTag("MaterialProperties", mat.serializeNBT());
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if (stack.hasTagCompound()) {
			MaterialProperties properties = new MaterialProperties();
			properties.deserializeNBT(stack.getTagCompound());
			tooltip.add("Raw crafting material:");
			tooltip.add(Integer.toString(properties.getProperty(EnumMaterialProperty.CAPACITY)));
			tooltip.add(Integer.toString(properties.getProperty(EnumMaterialProperty.CONDUCTIVITY)));
			tooltip.add(Integer.toString(properties.getProperty(EnumMaterialProperty.DURABILITY)));
			tooltip.add(Integer.toString(properties.getProperty(EnumMaterialProperty.ENCHANTABILITY)));
			tooltip.add(Integer.toString(properties.getProperty(EnumMaterialProperty.REFRACTIVITY)));
			tooltip.add(Integer.toString(properties.getProperty(EnumMaterialProperty.RESILIENCE)));
			tooltip.add(Integer.toString(properties.getProperty(EnumMaterialProperty.STRENGTH)));
			tooltip.add(Integer.toString(properties.getProperty(EnumMaterialProperty.WEIGHT)));
		}
		tooltip.add("An alloy made of nothing... Cheater!");
	}	
	

}
