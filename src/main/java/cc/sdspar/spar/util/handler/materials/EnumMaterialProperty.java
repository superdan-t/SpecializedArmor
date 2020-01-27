package cc.sdspar.spar.util.handler.materials;

public enum EnumMaterialProperty {
	
	REFRACTIVITY,
	RESILIENCE,
	CONDUCTIVITY,
	CAPACITY,
	DURABILITY,
	STRENGTH,
	WEIGHT,
	ENCHANTABILITY;
	
	/**
	 * Make an array of the 8 properties that can be safely accessed by an ordinal.
	 * This just makes a new array so users aren't concerned with exact numbers
	 * @return Ordered array of length 8
	 */
	public static int[] beginPropertyArray() {
		return new int[getPropertyCount()];
	}
	
	public static int getPropertyCount() {
		return 8;
	}
	
	public static int[] setPropertyInArray(int[] propArray, EnumMaterialProperty prop, int value) {
		propArray[prop.ordinal()] = value;
		return propArray;
	}
	
	public static int getPropertyFromArray(int[] propArray, EnumMaterialProperty prop) {
		return propArray[prop.ordinal()];
	}

}
