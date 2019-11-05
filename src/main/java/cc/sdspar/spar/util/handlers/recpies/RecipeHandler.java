package cc.sdspar.spar.util.handlers.recpies;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class RecipeHandler {
	
	public static final List<VacuumArcRecipe> vacuumArcRecipes = new ArrayList<VacuumArcRecipe>();
	
	public static final VacuumArcRecipe getVacuumArcFurnaceResult(ItemStack input1, ItemStack input2) {
		
		for (VacuumArcRecipe recipe : vacuumArcRecipes) {
			if (recipe.input1 == input1.getItem() && recipe.input2 == input2.getItem() ||
					recipe.input1 == input2.getItem() && recipe.input2 == input1.getItem()) {
				return recipe;
			}
		}
		
		return null;
			
		
	}

}
