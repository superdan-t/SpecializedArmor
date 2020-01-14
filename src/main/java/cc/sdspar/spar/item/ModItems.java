package cc.sdspar.spar.item;

import java.util.ArrayList;
import java.util.List;

import cc.sdspar.spar.util.Ref;
import cc.sdspar.spar.util.handler.materials.EnumRawMaterial;
import net.minecraft.item.Item;

public class ModItems {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Alloy Materials
	public static final Item ALLOY_INGOT = new ItemAlloyIngot();
	public static final Item ALUMINUM_INGOT = new ItemRawMaterial("aluminum_ingot", EnumRawMaterial.ALUMINUM);
	public static final Item CERAMIC_BRICK = new ItemRawMaterial("ceramic_brick", EnumRawMaterial.CERAMIC);
	public static final Item COPPER_INGOT = new ItemRawMaterial("copper_ingot", EnumRawMaterial.COPPER);
	public static final Item ENDERINE_CRYSTAL = new ItemRawMaterial("enderine_crystal", EnumRawMaterial.ENDERINE_CRYSTAL);
	public static final Item ENDERINE_DIAMOND = new ItemRawMaterial("enderine_diamond", EnumRawMaterial.ENDERINE_DIAMOND);
	public static final Item ENDERINE_DUST = new ItemRawMaterial("enderine_dust", EnumRawMaterial.ENDERINE_DUST);
	public static final Item ENDERINE_GOLD_INGOT = new ItemRawMaterial("enderine_gold_ingot", EnumRawMaterial.ENDERINE_GOLD);
	public static final Item ENDERINE_QUARTZ = new ItemRawMaterial("enderine_quartz", EnumRawMaterial.ENDERINE_QUARTZ);
	public static final Item LEAD_INGOT = new ItemRawMaterial("lead_ingot", EnumRawMaterial.LEAD);
	public static final Item NETHER_CRYSTAL = new ItemRawMaterial("nether_crystal", EnumRawMaterial.NETHER_CRYSTAL);
	public static final Item SILICON_CARBIDE = new ItemRawMaterial("silicon_carbide", EnumRawMaterial.SILICON_CARBIDE);
	public static final Item TANTALUM_INGOT = new ItemRawMaterial("tantalum_ingot", EnumRawMaterial.TANTALUM);
	public static final Item TIN_INGOT = new ItemRawMaterial("tin_ingot", EnumRawMaterial.TIN);
	public static final Item TITANIUM_INGOT = new ItemRawMaterial("titanium_ingot", EnumRawMaterial.TITANIUM);
	public static final Item ZIRCONIUM_CARBIDE = new ItemRawMaterial("zirconium_carbide", EnumRawMaterial.ZIRCONIUM_CARBIDE);
	
	//Generic Items
	public static final Item ADVANCED_STICK = new ItemBase("advanced_stick");
	public static final Item HAFNIUM_DIOXIDE = new ItemBase("hafnium_dioxide");
	public static final Item HAFNIUM_CARBIDE = new ItemBase("hafnium_carbide");
	public static final Item KAOLINITE_BALL = new ItemBase("kaolinite_ball");
	public static final Item MOISSANITE = new ItemBase("moissanite");
	public static final Item SHREDDER_BLADE_ALUMINUM = new ItemShredderBlade("aluminum");
	public static final Item SHREDDER_BLADE_COPPER = new ItemShredderBlade("copper");
	public static final Item SHREDDER_BLADE_DIAMOND = new ItemShredderBlade("diamond");
	public static final Item SHREDDER_BLADE_GOLD = new ItemShredderBlade("gold");
	public static final Item SHREDDER_BLADE_IRON = new ItemShredderBlade("iron");
	public static final Item SHREDDER_BLADE_TIN = new ItemShredderBlade("tin");
	public static final Item SHREDDER_BLADE_TITANIUM = new ItemShredderBlade("titanium");
	public static final Item SHREDDED_SCRAP = new ItemBase("shredded_scrap");
	public static final Item ZIRCON = new ItemBase("zircon");
	public static final Item ZIRCONIUM_DIOXIDE = new ItemBase("zirconium_dioxide");
	
}