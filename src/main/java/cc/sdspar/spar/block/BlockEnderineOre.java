package cc.sdspar.spar.block;

import java.util.Random;

import cc.sdspar.spar.item.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockEnderineOre extends BlockOre {
	
	private String name = "";

	public BlockEnderineOre(String name) {
		super(name, 3);
		super.setResistance(2000.0F);
		super.setHardness(30.0F);
		this.name = name;
		
	}
	
	public int quantityDroppedWithBonus(int fortune, Random rand) {
		if (name == "enderine_dust_ore") {
			return 2 + Math.round(fortune * rand.nextFloat());
		} else if (name == "enderine_crystal_ore") {
			return 1 + Math.round(fortune * rand.nextFloat() * 0.49F);
		} else if (name == "enderine_quartz_ore") {
			return 2 + Math.round(fortune * rand.nextFloat());
		} else {
			return 1;
		}
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (name == "enderine_dust_ore") {
			return ModItems.ENDERINE_DUST;
		} else if (name == "enderine_crystal_ore") {
			return ModItems.ENDERINE_CRYSTAL;
		} else if (name == "enderine_quartz_ore") {
			return ModItems.ENDERINE_QUARTZ;
		} else {
			return Item.getItemFromBlock(this);
		}
	}

}
