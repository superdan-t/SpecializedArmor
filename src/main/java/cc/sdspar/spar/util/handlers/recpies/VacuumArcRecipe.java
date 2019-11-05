package cc.sdspar.spar.util.handlers.recpies;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VacuumArcRecipe {
	
	Item input1, input2;
	ItemStack output1, output2;
	
	public VacuumArcRecipe(Item input1, Item input2, ItemStack output1, ItemStack output2) {
		this.input1 = input1;
		this.input2 = input2;
		this.output1 = output1;
		this.output2 = output2;
	}

}
