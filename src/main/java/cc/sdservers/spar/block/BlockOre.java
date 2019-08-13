package cc.sdservers.spar.block;

import net.minecraft.block.material.Material;

public class BlockOre extends BlockBase {

	public BlockOre(String name, int harvestLevel) {
		super(name, Material.ROCK);
		setHarvestLevel("pickaxe", harvestLevel);
	}

}
