package cc.sdservers.spar.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block KAOLINITE_BLOCK = new BlockKaolinite("kaolinite_block");
	public static final Block MOISSANITE_ORE = new BlockOre("moissanite_ore", 2);
	public static final Block TANTALUM_ORE = new BlockOre("tantalum_ore", 2);
	public static final Block ZIRCON_ORE = new BlockOre("zircon_ore", 2);
	
}
