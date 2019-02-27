package com.shrub.blocks.generic;

import com.shrub.blocks.ModBlocks;
import com.shrub.main.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockFoundryCore extends Block {
	
	private final String coreLevel;
	
    @SideOnly(Side.CLIENT)
    private IIcon side;
    
    @SideOnly(Side.CLIENT)
    private IIcon top;
    
    @SideOnly(Side.CLIENT)
    private IIcon bottom;

	public BlockFoundryCore(String coreLevel) {
		super(Material.rock);
		this.setBlockName("foundryCore" + coreLevel);
		this.setCreativeTab(ModBlocks.tabBlocks);
		this.setHardness(5F);
		this.setResistance(10F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName(Main.modID + ":" + "chimney");
		this.coreLevel = coreLevel;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(Main.modID + ":foundryCore" + coreLevel + "_sides");
		this.side = reg.registerIcon(Main.modID + ":foundryCore" + coreLevel + "_sides");
		this.top = reg.registerIcon(Main.modID + ":foundryCore" + coreLevel + "_top");
		this.bottom = reg.registerIcon(Main.modID + ":foundryCore" + coreLevel + "_bottom");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		switch (side) {
		case 0:
			return this.bottom;
		case 1:
			return this.top;
		default:
			return this.side;
		}
	}

}
