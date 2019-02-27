package com.shrub.blocks;

import com.shrub.blocks.generic.BlockChimney;
import com.shrub.blocks.generic.BlockFoundryCore;
import com.shrub.blocks.generic.BlockKaolinite;
import com.shrub.blocks.generic.BlockLavaTank;
import com.shrub.blocks.generic.BlockMachineComponent;
import com.shrub.blocks.generic.BlockOre;
import com.shrub.blocks.machine.*;
import com.shrub.info.*;
import com.shrub.items.itemblocks.ItemChimneyBlock;
import com.shrub.items.itemblocks.ItemLavaTankBlock;
import com.shrub.main.Main;
import com.shrub.tileentity.TileEntityComputer;
import com.shrub.tileentity.TileEntityEnergyTest;
import com.shrub.tileentity.TileEntityFoundry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class ModBlocks {
	
	public static Block coolFurnaceIdle;
	public static Block coolFurnaceActive;
	public static Block chimney;
	public static Block computerOff;
	public static Block computerOn;
	public static Block demoTile;
	public static Block energyTest;
	public static Block foundryCase;
	public static Block foundryCoreAdvanced;
	public static Block foundryCoreBasic;
	public static Block foundryHatchCool;
	public static Block foundryHatchHot;
	public static Block heater;
	public static Block heatGenerator;
	public static Block kaoliniteClayBlock;
	public static Block lavaTank;
	public static Block moissaniteOre;
	public static Block pentlanditeOre;
	public static Block smelteryCase;
	public static Block smelteryComputer;
	public static Block smelteryCore;
	public static Block tantalumOre;
	public static Block vacuumArcFurnace_off;
	public static Block vacuumArcFurnace_on;
	public static Block zirconOre;
	
	public static final CreativeTabs tabBlocks = new CreativeTabs("blocks") {
	    @Override public Item getTabIconItem() {
	        return Item.getItemFromBlock(ModBlocks.zirconOre);
	    }
	};
	
	public static final void init()	{
		
//		GameRegistry.registerBlock(coolFurnaceIdle = new BlockCoolFurnace(false), CoolFurnaceInformation.class, "coolFurnaceIdle");
//		GameRegistry.registerBlock(coolFurnaceActive = new BlockCoolFurnace(true), "coolFurnaceActive");
//		GameRegistry.registerBlock(computerOff = new BlockTileComputer(false), "computerOff");
		GameRegistry.registerBlock(chimney = new BlockChimney("brick"), ItemChimneyBlock.class, chimney.getUnlocalizedName().substring(5));
//		GameRegistry.registerBlock(demoTile = new BlockTileBomb("demoTile"), "demoTile");
		GameRegistry.registerBlock(foundryCase = new BlockMachineComponent("foundryCase", Material.iron), "foundryCase");
		GameRegistry.registerBlock(foundryCoreAdvanced = new BlockFoundryCore("Advanced"), "foundryCoreAdvanced");
		GameRegistry.registerBlock(foundryCoreBasic = new BlockFoundryCore("Basic"), "foundryCoreBasic");
		GameRegistry.registerBlock(foundryHatchCool = new BlockTileFoundryHatch(false), "foundryHatchCool");
		GameRegistry.registerBlock(foundryHatchHot = new BlockTileFoundryHatch(true), "foundryHatchHot");
//		GameRegistry.registerBlock(heater = new BlockMachine("heater", Material.rock), "heater");
//		GameRegistry.registerBlock(heatGenerator = new BlockMachine("heatGenerator", Material.rock), "heatGenerator");
		GameRegistry.registerBlock(kaoliniteClayBlock = new BlockKaolinite("kaoliniteClayBlock"), "kaoliniteClayBlock");
		GameRegistry.registerBlock(lavaTank = new BlockLavaTank(), ItemLavaTankBlock.class, "lavaTank");
		GameRegistry.registerBlock(moissaniteOre = new BlockOre("moissaniteOre", Material.rock, 2), MoissaniteInfo.class, "moissaniteOre");
		GameRegistry.registerBlock(pentlanditeOre = new BlockOre("pentlanditeOre", Material.rock, 1), PentlanditeInfo.class, "pentlanditeOre");
//		GameRegistry.registerBlock(smelteryCase = new BlockMachine("smelteryCase", Material.rock), "smelteryCase");
//		GameRegistry.registerBlock(smelteryComputer = new BlockMachine("smelteryComputer", Material.rock), "smelteryComputer");
//		GameRegistry.registerBlock(smelteryCore = new BlockTileSmeltery("smelteryCore"), "smelteryCore");
		GameRegistry.registerBlock(tantalumOre = new BlockOre("tantalumOre", Material.rock, 2), TantalumInfo.class, "tantalumOre");
		GameRegistry.registerBlock(energyTest = new BlockEnergyTest(), "energyTest");
//		GameRegistry.registerBlock(vacuumArcFurnace_off = new BlockVacuumArcFurnace(false), VacuumArcFurnaceInformation.class, "vacuumArcFurnace_off");
//		GameRegistry.registerBlock(vacuumArcFurnace_on = new BlockVacuumArcFurnace(true), "vacuumArcFurnace_on");
		GameRegistry.registerBlock(zirconOre = new BlockOre("zirconOre", Material.rock, 2), ZirconInfo.class, "zirconOre");
		
	}
	
	public static final void initTileEntities() {
		GameRegistry.registerTileEntity(TileEntityComputer.class, Main.modID + ":computer");
		GameRegistry.registerTileEntity(TileEntityFoundry.class, Main.modID + ":foundry");
		GameRegistry.registerTileEntity(TileEntityEnergyTest.class, Main.modID + ":energyTest");
	}

}
