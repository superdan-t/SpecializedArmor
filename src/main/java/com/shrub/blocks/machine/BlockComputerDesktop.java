package com.shrub.blocks.machine;

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

}
