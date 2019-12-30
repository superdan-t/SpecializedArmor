package cc.sdspar.spar.util.handler.recpies;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VacuumArcRecipe {
	
	public Item input1, input2;
	public ItemStack output1, output2;
	
	public VacuumArcRecipe(Item input1, Item input2, ItemStack output1, ItemStack output2) {
		if (input1 == null) input1 = Items.AIR;
		if (input2 == null) input2 = Items.AIR;
		if (output1 == null) output1 = ItemStack.EMPTY;
		if (output2 == null) output2 = ItemStack.EMPTY;
		this.input1 = input1;
		this.input2 = input2;
		this.output1 = output1;
		this.output2 = output2;
	}

}
