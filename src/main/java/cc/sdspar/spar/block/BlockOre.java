package cc.sdspar.spar.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockOre extends BlockBase {

	public BlockOre(String name, int harvestLevel) {
		super(name, Material.ROCK);
		setHarvestLevel("pickaxe", harvestLevel);
		setHardness(3.0F);
		setResistance(5.0F);
		setSoundType(SoundType.STONE);
	}

}
