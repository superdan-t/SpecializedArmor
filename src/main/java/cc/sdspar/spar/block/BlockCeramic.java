package cc.sdspar.spar.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCeramic extends BlockBase {

	public BlockCeramic(String name) {
		super(name, Material.ROCK);
		setHardness(1.25F);
		setResistance(7.0F);
		setSoundType(SoundType.STONE);
	}

}
