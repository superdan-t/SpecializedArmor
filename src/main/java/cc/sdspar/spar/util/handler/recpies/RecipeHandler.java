package cc.sdspar.spar.util.handler.recpies;

import java.util.ArrayList;
import java.util.List;

import cc.sdspar.spar.item.ItemShredderBlade;
import cc.sdspar.spar.item.ModItems;
import cc.sdspar.spar.util.ModConfig;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeHandler {
	
	public static final List<VacuumArcRecipe> vacuumArcRecipes = new ArrayList<VacuumArcRecipe>();
	public static final List<ShredderRecipe> shredderRecipes = new ArrayList<ShredderRecipe>();
	
	public static final VacuumArcRecipe getVacuumArcFurnaceResult(ItemStack input1, ItemStack input2) {

		if (input1 != ItemStack.EMPTY || input2 != ItemStack.EMPTY) {

			for (VacuumArcRecipe recipe : vacuumArcRecipes) {
				if (recipe.input1 == input1.getItem() && recipe.input2 == input2.getItem()
						|| recipe.input1 == input2.getItem() && recipe.input2 == input1.getItem()) {
					return recipe;
				}
			}

		}

		return null;

	}
	
	public static final ShredderRecipe getShredderResult(ItemStack input) {
		if (input != ItemStack.EMPTY) {
			for (ShredderRecipe recipe : shredderRecipes) {
				if (recipe.input == input.getItem()) {
					return recipe;
				}
			}
			return new ShredderRecipe(input.getItem(), new ItemStack(ModItems.SHREDDED_SCRAP), 5);
		} else {
			return null;
		}
	}
	
	public static final boolean isShredderBlade(ItemStack stack) {
		return (ModConfig.DEVELOPER_MODE && (stack.getItem() == ModItems.ADVANCED_STICK || stack.getItem() == Items.GOLDEN_HOE) || stack.getItem() instanceof ItemShredderBlade);
	}


}
