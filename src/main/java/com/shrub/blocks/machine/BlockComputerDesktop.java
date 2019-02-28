package com.shrub.blocks.machine;

import com.shrub.tileentity.TileEntityComputerDesktop;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockComputerDesktop extends BlockComputerTemplate {

	public BlockComputerDesktop() {
		super("computerDesktop");
		
	}
	
	public int getRenderType() {
		return -1;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityComputerDesktop();
	}

}
