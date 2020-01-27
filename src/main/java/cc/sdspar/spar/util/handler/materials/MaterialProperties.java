package cc.sdspar.spar.util.handler.materials;

import net.minecraft.nbt.NBTTagCompound;

/**
 * MaterialProperties is a uniform way to share properties as a group,
 * not for modifying properties. It should only be passed by making a copy
 *
 */
public class MaterialProperties implements Cloneable {
	
	private int[] propArray = EnumMaterialProperty.beginPropertyArray();
	
	public MaterialProperties() {}
	
	/**
	 * Make the properties object from an ordered array.
	 * Please only pass arrays created with EnumMaterialProperty
	 * @param propArray
	 */
	public MaterialProperties(int[] propArray) {
		this.propArray = propArray.clone();
	}
	
	public MaterialProperties(int conductivity, int capacity, int durability, int strength, int weight, int enchantability, int refractivity, int resilience) {
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.CONDUCTIVITY, conductivity);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.CAPACITY, capacity);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.DURABILITY, durability);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.STRENGTH, strength);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.WEIGHT, weight);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.ENCHANTABILITY, enchantability);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.REFRACTIVITY, refractivity);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.RESILIENCE, resilience);
	}

	public int getProperty(EnumMaterialProperty prop) {
		return EnumMaterialProperty.getPropertyFromArray(propArray, prop);
	}

	public void setProperty(EnumMaterialProperty prop, int value) {
		EnumMaterialProperty.setPropertyInArray(propArray, prop, value);
	}
	
	public int[] getPropertiesAsArray() {
		return propArray.clone();
	}
	
	public NBTTagCompound serializeNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setIntArray("PropArray", propArray);
		return tag;
	}
	
	public void deserializeNBT(NBTTagCompound tag) {
		if (tag.hasKey("MaterialPropertiesArray")) {
			propArray = tag.getIntArray("PropArray");
		}
	}
	
	@Override
	public MaterialProperties clone() {
		return new MaterialProperties(propArray);
	}

}
