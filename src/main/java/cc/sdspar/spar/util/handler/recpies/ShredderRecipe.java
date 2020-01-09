package cc.sdspar.spar.util.handler.recpies;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ShredderRecipe {
	
	public Item input;
	public ItemStack output;
	public int hardness;
	
	public ShredderRecipe(Item input, ItemStack output, int hardness) {
		this.input = input;
		this.output = output;
		this.hardness = hardness;
	}
	

}
