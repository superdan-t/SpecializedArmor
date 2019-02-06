package com.shrub.blocks.templates;

import java.util.List;

import com.shrub.blocks.ModBlocks;
import com.shrub.main.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockLavaTank extends Block {
	
    @SideOnly(Side.CLIENT)
    private IIcon[] sides;
    
    @SideOnly(Side.CLIENT)
    private IIcon top;
    
	public BlockLavaTank() {
		super(Material.iron);
		this.setBlockName("lavaTank");
		this.setHardness(5F);
		this.setResistance(10F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName(Main.modID + ":lavaTank");
		this.setCreativeTab(ModBlocks.tabBlocks);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.top = reg.registerIcon(Main.modID + ":foundryCase");
		this.blockIcon = reg.registerIcon(Main.modID + ":foundryCase");
		this.sides = new IIcon[15];
		for (int i = 0; i < 15; i++)
			this.sides[i] = reg.registerIcon(Main.modID + ":lavaTank_" + Integer.toString(i));
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (side == 0 || side == 1)
			return this.top;
		return this.sides[metadata < 15 ? metadata : 14];
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
           list.add(new ItemStack(block, 1, 0));
    }
    
    public int damageDropped(int meta) {
    	return 0;
    }

}
