package cc.sdservers.spar.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMineral extends BlockBase {

	public BlockMineral(String name, int harvestLevel) {
		super(name, Material.IRON);
		setHardness(5.0F);
		setResistance(10.0F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", harvestLevel);
	}

}
