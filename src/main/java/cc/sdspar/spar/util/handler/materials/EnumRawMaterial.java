package cc.sdspar.spar.util.handler.materials;

public enum EnumRawMaterial {
	
	IRON(0, 0, 0, 0, 0, 0, 0, 0),
	GOLD(0, 0, 0, 0, 0, 0, 0, 0),
	DIAMOND(0, 0, 0, 0, 0, 0, 0, 0),
	REDSTONE(0, 0, 0, 0, 0, 0, 0, 0),
	ALUMINUM(710, 0, 480, 650, 100, 100, 300, 680),
	CERAMIC(0, 0, 0, 0, 0, 0, 0, 0),
	COPPER(0, 0, 0, 0, 0, 0, 0, 0),
	ENDERINE_CRYSTAL(0, 0, 0, 0, 0, 0, 0, 0),
	ENDERINE_DIAMOND(0, 0, 0, 0, 0, 0, 0, 0),
	ENDERINE_DUST(0, 0, 0, 0, 0, 0, 0, 0),
	ENDERINE_GOLD(0, 0, 0, 5, 0, 0, 3, 0),
	ENDERINE_QUARTZ(0, 0, 0, 0, 0, 0, 0, 0),
	HAFNIUM_CARBIDE(0, 0, 0, 0, 0, 0, 0, 0),
	LEAD(0, 0, 0, 0, 0, 0, 0, 0),
	NETHER_CRYSTAL(0, 0, 0, 0, 0, 0, 0, 0),
	SILICON_CARBIDE(0, 0, 0, 0, 0, 0, 0, 0),
	TANTALUM(0, 0, 0, 0, 0, 0, 0, 0),
	TIN(0, 0, 0, 0, 0, 0, 0, 0),
	TITANIUM(0, 0, 0, 0, 0, 0, 0, 0),
	ZIRCONIUM_CARBIDE(0, 0, 0, 0, 0, 0, 0, 0);
	
	private final int propArray[] = new int[8];

	EnumRawMaterial(int conductivity, int capacity, int durability, int strength, int weight, int enchantability, int refractivity, int resilience) {
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.CONDUCTIVITY, conductivity);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.CAPACITY, capacity);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.DURABILITY, durability);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.STRENGTH, strength);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.WEIGHT, weight);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.ENCHANTABILITY, enchantability);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.REFRACTIVITY, refractivity);
		EnumMaterialProperty.setPropertyInArray(propArray, EnumMaterialProperty.RESILIENCE, resilience);
	}
	
	public MaterialProperties getMaterialProperties() {
		return new MaterialProperties(propArray);
	}
	
	

}
