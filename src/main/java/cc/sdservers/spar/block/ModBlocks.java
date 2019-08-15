package cc.sdservers.spar.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Ores
	public static final Block ALUMINUM_ORE = new BlockOre("aluminum_ore", 1);
	public static final Block COPPER_ORE = new BlockOre("copper_ore", 1);
	public static final Block LEAD_ORE = new BlockOre("lead_ore", 2);
	public static final Block MOISSANITE_ORE = new BlockOre("moissanite_ore", 2);
	public static final Block NETHER_CRYSTAL_ORE = new BlockOre("nether_crystal_ore", 3);
	public static final Block TANTALUM_ORE = new BlockOre("tantalum_ore", 2);
	public static final Block TIN_ORE = new BlockOre("tin_ore", 1);
	public static final Block TITANIUM_ORE = new BlockOre("titanium_ore", 2);
	public static final Block ZIRCON_ORE = new BlockOre("zircon_ore", 2);
	
	//Mineral blocks
	public static final Block ALUMINUM_BLOCK = new BlockMineral("aluminum_block", 1);
	public static final Block COPPER_BLOCK = new BlockMineral("copper_block", 1);
	public static final Block LEAD_BLOCK = new BlockMineral("lead_block", 2);
	public static final Block MOISSANITE_BLOCK = new BlockMineral("moissanite_block", 2);
	public static final Block NETHER_CRYSTAL_BLOCK = new BlockMineral("nether_crystal_block", 3);
	public static final Block TANTALUM_BLOCK = new BlockMineral("tantalum_block", 2);
	public static final Block TIN_BLOCK = new BlockMineral("tin_block", 1);
	public static final Block TITANIUM_BLOCK = new BlockMineral("titanium_block", 2);
	public static final Block ZIRCON_BLOCK = new BlockMineral("zircon_block", 2);
	
	//Building blocks
	public static final Block CERAMIC_BLOCK = new BlockCeramic("ceramic_block");
	public static final Block KAOLINITE_BLOCK = new BlockKaolinite("kaolinite_block");
	
}
