package cc.sdspar.spar.item;

import java.util.List;

import cc.sdspar.spar.util.handler.materials.EnumMaterialProperty;
import cc.sdspar.spar.util.handler.materials.EnumRawMaterial;
import cc.sdspar.spar.util.handler.materials.IAlloyMaterial;
import cc.sdspar.spar.util.handler.materials.MaterialProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRawMaterial extends ItemBase implements IAlloyMaterial {
	
	private MaterialProperties properties;
	
	public ItemRawMaterial(String name, MaterialProperties properties) {
		super(name);
		this.properties = properties;
	}
	
	public ItemRawMaterial(String name, EnumRawMaterial material) {
		this(name, material.getMaterialProperties());
	}
	
	@Override
	public int getProperty(ItemStack stack, EnumMaterialProperty prop) {
		return properties.getProperty(prop);
	}
	
	@Override
	public int getCraftingValue(ItemStack stack) {
		return 9;
	}
	
	@Override
	public void setProperty(ItemStack stack, EnumMaterialProperty prop, int value) {
		properties.setProperty(prop, value);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
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

}
