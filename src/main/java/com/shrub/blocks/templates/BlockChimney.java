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

public class BlockChimney extends Block {
	
    @SideOnly(Side.CLIENT)
    private IIcon[] sides;
    
    @SideOnly(Side.CLIENT)
    private IIcon top;
    
    static final String[] names = new String[] {"brick", "stone", "stonebrick", "iron_block", Main.modID + ":foundryCase"};


	public BlockChimney(String type) {
		super(Material.rock);
		this.setBlockName("chimney");
		this.setCreativeTab(ModBlocks.tabBlocks);
		this.setHardness(5F);
		this.setResistance(10F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName(Main.modID + ":" + "chimney");
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.top = reg.registerIcon(Main.modID + ":chimney_top");
		this.blockIcon = reg.registerIcon(Main.modID + ":chimney_top");
		this.sides = new IIcon[names.length];
		for (int i = 0; i < 5; i++)
			this.sides[i] = reg.registerIcon(names[i]);
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (side == 0 || side == 1)
			return this.top;
		return this.sides[metadata];
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < 5; ++i) {
            list.add(new ItemStack(block, 1, i));
        }
    }
    
    public int damageDropped(int meta) {
    	return meta;
    }

}
