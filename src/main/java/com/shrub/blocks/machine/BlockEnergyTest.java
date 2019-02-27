package com.shrub.blocks.machine;

import com.shrub.blocks.ModBlocks;
import com.shrub.tileentity.TileEntityEnergyTest;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergyTest extends BlockContainer {

	public BlockEnergyTest() {
		super(Material.sponge);
		this.setCreativeTab(ModBlocks.tabBlocks);
		this.setBlockName("energyTest");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int integer) {
		return new TileEntityEnergyTest();
	}

}
