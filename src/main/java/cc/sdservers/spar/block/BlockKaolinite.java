package cc.sdservers.spar.block;

import java.util.Random;

import cc.sdservers.spar.item.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockKaolinite extends BlockBase {

	public BlockKaolinite(String name) {
		super(name, Material.CLAY);
		setSoundType(SoundType.GROUND);
		setHardness(0.6F);
		setHarvestLevel("shovel", -1);
		
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.KAOLINITE_BALL;
	}
	
    public int quantityDropped(Random random)
    {
        return 4;
    }

}
