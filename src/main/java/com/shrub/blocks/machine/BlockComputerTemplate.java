package com.shrub.blocks.machine;

import com.shrub.blocks.ModBlocks;
import com.shrub.main.Main;
import com.shrub.tileentity.TileEntityComputer;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
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
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityComputer();
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

}
