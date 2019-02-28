package com.shrub.blocks.machine;

import com.shrub.blocks.ModBlocks;
import com.shrub.main.Main;
import com.shrub.tileentity.TileEntityComputer;
import com.shrub.tileentity.TileEntityFoundry;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class BlockComputerTemplate extends BlockContainer {

	public BlockComputerTemplate(String blockName) {
		super(Material.iron);
		this.setBlockName(blockName);
		this.setHardness(2F);
		this.setResistance(4F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(ModBlocks.tabBlocks);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ) {
		if (world.isRemote)
			return true;
		else if (player.isSneaking())
			return false;
		
		TileEntityComputer tile = (TileEntityComputer)world.getTileEntity(x, y, z);
		
		if (tile.isUseableByPlayer(player))
			FMLNetworkHandler.openGui(player, Main.instance, Main.guiIDComputer, world, x, y, z);

		
		return true;
		
	}
	
	private void setDefaultDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			byte b0 = 3;
			Block b1 = world.getBlock(x, y, z - 1);
			Block b2 = world.getBlock(x, y, z + 1);
			Block b3 = world.getBlock(x - 1, y, z);
			Block b4 = world.getBlock(x + 1, y, z);	
			
			if (b1.func_149730_j() && !b2.func_149730_j())
				b0 = 3;
			else if (b2.func_149730_j() && !b1.func_149730_j())
				b0 = 2;
			else if (b3.func_149730_j() && !b4.func_149730_j())
				b0 = 5;
			else if (b4.func_149730_j() && !b3.func_149730_j())
				b0 = 4;
			
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
			
		}
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack) {
		int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if (l == 0)
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		else if (l == 1)
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		else if (l == 2)
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		else if (l == 3)
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		
		if (itemstack.hasDisplayName())
			((TileEntityFoundry)world.getTileEntity(x, y, z)).setGUIDisplayName(itemstack.getDisplayName());
		
	}

}
