package com.shrub.items;

import com.shrub.items.tools.ItemTools;
import com.shrub.items.tools.ItemZirconiumToolTip;
import com.shrub.items.tools.ModItemAxe;
import com.shrub.main.Main;

import cpw.mods.fml.common.registry.GameRegistry;
import com.shrub.items.tools.ModItemCeramicArmor;
import com.shrub.items.tools.ModItemDrill;
import com.shrub.items.tools.ModItemGrapheneArmor;
import com.shrub.items.tools.ModItemHoe;
import com.shrub.items.tools.ModItemPickaxe;
import com.shrub.items.tools.ModItemSpade;
import com.shrub.items.tools.ModItemSword;
import com.shrub.items.utility.ItemControlChip;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public final class ModItems {
	
	public static final CreativeTabs tabArmorParts = new CreativeTabs("armorParts") {
	    @Override public Item getTabIconItem() {
	        return ModItems.capacitor;
	    }
	};
	
	public static final CreativeTabs tabSpecializedTools = new CreativeTabs("specializedTools") {
		@Override public Item getTabIconItem() {
			return ModItems.zirconiumScythe;
		}
	};
	
	public static final CreativeTabs tabSpecializedArmor = new CreativeTabs("specializedArmor") {
		@Override public Item getTabIconItem() {
			return ModItems.ceramicChestplate;
		}
	};

	public static Item advancedStick;
	public static Item capacitor;
	public static Item ceramic;
	public static Item ceramicBoots;
	public static Item ceramicChestplate;
	public static Item ceramicLeggings;
	public static Item ceramicHelmet;
	public static Item controlChip;
	public static Item grapheneHelmet;
	public static Item grapheneChestplate;
	public static Item grapheneLeggings;
	public static Item grapheneBoots;
	public static Item hafniumCarbide;
	public static Item hafniumOxide;
	public static Item kaoliniteClay;
	public static Item moissanite;
	public static Item nickelIngot;
	public static Item pipes;
	public static Item ppBoots;
	public static Item ppChestplate;
	public static Item ppLeggings;
	public static Item ppHelmet;
	public static Item refractiveCeramicBoots;
	public static Item refractiveCeramicChestplate;
	public static Item refractiveCeramicLeggings;
	public static Item refractiveCeramicHelmet;
	public static Item siliconCarbide;
	public static Item solarPanel;
	public static Item strengthenedCeramicBoots;
	public static Item strengthenedCeramicChestplate;
	public static Item strengthenedCeramicLeggings;
	public static Item strengthenedCeramicHelmet;
	public static Item tantalumCarbide;
	public static Item zirconIngot;
	public static Item zirconiumAxe;
	public static Item zirconiumCarbide;
	public static Item zirconiumHoe;
	public static Item zirconiumKnife;
	public static Item zirconiumOxide;
	public static Item zirconiumPickaxe;
	public static Item zirconiumScythe;
	public static Item zirconiumSpade;
	public static Item zirconiumSword;
	public static Item zirconiumToolTip;
	
	//Armor Materials
	
	public static ArmorMaterial ceramicArmor = EnumHelper.addArmorMaterial("ceramicArmor", 2, new int[] {1, 5, 4, 1}, 8);
	public static ArmorMaterial grapheneArmor = EnumHelper.addArmorMaterial("grapheneArmor", 50, new int[] {1,  3, 2, 1}, 12);
	public static ArmorMaterial refractiveCeramicArmor = EnumHelper.addArmorMaterial("refractiveCeramicArmor", 8, new int[] {2, 5, 4, 2}, 7);
	public static ArmorMaterial strengthenedCeramicArmor = EnumHelper.addArmorMaterial("strengthenedCeramicArmor", 5, new int[] {2, 6, 5, 2}, 7);
	public static ArmorMaterial ppe = EnumHelper.addArmorMaterial("ppe", 12, new int[] {3, 6, 6, 2}, 10);

	public static final void init()	{
		
		//Components
		
		GameRegistry.registerItem(advancedStick = new Item().setUnlocalizedName("advancedStick").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":advancedStick"), "advancedStick");
		GameRegistry.registerItem(capacitor = new Item().setUnlocalizedName("capacitor").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":capacitor"), "capacitor");
		GameRegistry.registerItem(ceramic = new Item().setUnlocalizedName("ceramic").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":ceramic"), "ceramic");
		GameRegistry.registerItem(hafniumCarbide = new Item().setUnlocalizedName("hafniumCarbide").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":hafniumCarbide"), "hafniumCarbide");
		GameRegistry.registerItem(hafniumOxide = new Item().setUnlocalizedName("hafniumOxide").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":hafniumOxide"), "hafniumOxide");
		GameRegistry.registerItem(kaoliniteClay = new Item().setUnlocalizedName("kaoliniteClay").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":kaoliniteClay"), "kaoliniteClay");
		GameRegistry.registerItem(moissanite = new Item().setUnlocalizedName("moissanite").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":moissanite"), "moissanite");
		GameRegistry.registerItem(nickelIngot = new Item().setUnlocalizedName("nickelIngot").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":nickelIngot"), "nickelIngot");
		GameRegistry.registerItem(pipes = new Item().setUnlocalizedName("pipes").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":pipes"), "pipes");
		GameRegistry.registerItem(siliconCarbide = new Item().setUnlocalizedName("siliconCarbide").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":siliconCarbide"), "siliconCarbide");
		GameRegistry.registerItem(solarPanel = new Item().setUnlocalizedName("solarPanel").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":solarPanel"), "solarPanel");
		GameRegistry.registerItem(tantalumCarbide = new Item().setUnlocalizedName("tantalumCarbide").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":tantalumCarbide"), "tantalumCarbide");
		GameRegistry.registerItem(zirconIngot = new Item().setUnlocalizedName("zirconIngot").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":zirconIngot"), "zirconIngot");
		GameRegistry.registerItem(zirconiumCarbide = new Item().setUnlocalizedName("zirconiumCarbide").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":zirconiumCarbide"), "zirconiumCarbide");
		GameRegistry.registerItem(zirconiumOxide = new Item().setUnlocalizedName("zirconiumOxide").setCreativeTab(tabArmorParts).setTextureName(Main.modID + ":zirconiumOxide"), "zirconiumOxide");
		GameRegistry.registerItem(zirconiumToolTip = new ItemZirconiumToolTip("zirconiumToolTip"), "zirconiumToolTip");
		
		
		//Armors
		
		GameRegistry.registerItem(ceramicHelmet = new ModItemCeramicArmor("ceramicHelmet", ceramicArmor, "ceramic", 0), "ceramicHelmet");
		GameRegistry.registerItem(ceramicChestplate = new ModItemCeramicArmor("ceramicChestplate", ceramicArmor, "ceramic", 1), "ceramicChestplate");
		GameRegistry.registerItem(ceramicLeggings = new ModItemCeramicArmor("ceramicLeggings", ceramicArmor, "ceramic", 2), "ceramicLeggings");
		GameRegistry.registerItem(ceramicBoots = new ModItemCeramicArmor("ceramicBoots", ceramicArmor, "ceramic", 3), "ceramicBoots");
		GameRegistry.registerItem(grapheneHelmet = new ModItemGrapheneArmor("grapheneHelmet", grapheneArmor, "ceramic", 0), "grapheneHelmet");
		GameRegistry.registerItem(grapheneChestplate = new ModItemGrapheneArmor("grapheneChestplate", grapheneArmor, "graphene", 1), "grapheneChestplate");
		GameRegistry.registerItem(grapheneLeggings = new ModItemGrapheneArmor("grapheneLeggings", grapheneArmor, "graphene", 2), "grapheneLeggings");
		GameRegistry.registerItem(grapheneBoots = new ModItemGrapheneArmor("grapheneBoots", grapheneArmor, "graphene", 3), "grapheneBoots");
		GameRegistry.registerItem(ppBoots = new ModItemCeramicArmor("ppBoots", ppe, "ppe", 3), "ppBoots");
		GameRegistry.registerItem(ppChestplate = new ModItemCeramicArmor("ppChestplate", ppe, "ppe", 1), "ppChestplate");
		GameRegistry.registerItem(ppHelmet = new ModItemCeramicArmor("ppHelmet", ppe, "ppe", 0), "ppHelmet");
		GameRegistry.registerItem(ppLeggings = new ModItemCeramicArmor("ppLeggings", ppe, "ppe", 2), "ppLeggings");
		GameRegistry.registerItem(refractiveCeramicBoots = new ModItemCeramicArmor("refractiveCeramicBoots", refractiveCeramicArmor, "refractiveCeramic", 3), "refractiveCeramicBoots");
		GameRegistry.registerItem(refractiveCeramicChestplate = new ModItemCeramicArmor("refractiveCeramicChestplate", refractiveCeramicArmor, "refractiveCeramic", 1), "refractiveCeramicChestplate");
		GameRegistry.registerItem(refractiveCeramicHelmet = new ModItemCeramicArmor("refractiveCeramicHelmet", refractiveCeramicArmor, "refractiveCeramic", 0), "refractiveCeramicHelmet");
		GameRegistry.registerItem(refractiveCeramicLeggings = new ModItemCeramicArmor("refractiveCeramicLeggings", refractiveCeramicArmor, "refractiveCeramic", 2), "refractiveCeramicLeggings");
		GameRegistry.registerItem(strengthenedCeramicBoots = new ModItemCeramicArmor("strengthenedCeramicBoots", strengthenedCeramicArmor, "strengthenedCeramic", 3), "strengthenedCeramicBoots");
		GameRegistry.registerItem(strengthenedCeramicChestplate = new ModItemCeramicArmor("strengthenedCeramicChestplate", strengthenedCeramicArmor, "strengthenedCeramic", 1), "strengthenedCeramicChestplate");
		GameRegistry.registerItem(strengthenedCeramicHelmet = new ModItemCeramicArmor("strengthenedCeramicHelmet", strengthenedCeramicArmor, "strengthenedCeramic", 0), "strengthenedCeramicHelmet");
		GameRegistry.registerItem(strengthenedCeramicLeggings = new ModItemCeramicArmor("strengthenedCeramicLeggings", strengthenedCeramicArmor, "strengthenedCeramic", 2), "strengthenedCeramicLeggings");
		
		//Tools
		
		GameRegistry.registerItem(zirconiumAxe = new ModItemAxe("zirconiumAxe", ItemTools.zirconium), "zirconiumAxe");
		GameRegistry.registerItem(zirconiumHoe = new ModItemHoe("zirconiumHoe", ItemTools.zirconium), "zirconiumHoe");
		GameRegistry.registerItem(zirconiumKnife = new ModItemSword("zirconiumKnife", ItemTools.zirconiumBasic), "zirconiumKnife");
		GameRegistry.registerItem(zirconiumPickaxe = new ModItemDrill("zirconiumPickaxe", ItemTools.zirconium), "zirconiumPickaxe");
		GameRegistry.registerItem(zirconiumScythe = new ModItemSword("zirconiumScythe", ItemTools.zirconiumUpgrade), "zirconiumScythe");
		GameRegistry.registerItem(zirconiumSpade = new ModItemSpade("zirconiumSpade", ItemTools.zirconium), "zirconiumSpade");
		GameRegistry.registerItem(zirconiumSword = new ModItemSword("zirconiumSword", ItemTools.zirconium), "zirconiumSword");
		
		//Utilities
		
		GameRegistry.registerItem(controlChip = new ItemControlChip(), "controlChip");
		
	}
}
