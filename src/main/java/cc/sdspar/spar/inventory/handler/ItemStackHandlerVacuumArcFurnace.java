package cc.sdspar.spar.inventory.handler;

import javax.annotation.Nonnull;

import cc.sdspar.spar.item.ModItems;
import cc.sdspar.spar.tileentity.TileEntityVacuumArcFurnace;
import cc.sdspar.spar.util.handler.recpies.RecipeHandler;
import cc.sdspar.spar.util.handler.recpies.VacuumArcRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerVacuumArcFurnace extends ItemStackHandler {
	
	public boolean validRecipe;
	
	public TileEntityVacuumArcFurnace vaf;
	
	public VacuumArcRecipe recipe;
	
	public ItemStackHandlerVacuumArcFurnace() {
		super(5);
	}
	
    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if (slot <= 1) {
        	return true;
        } else if (slot == 4 && stack.getItem() == ModItems.ADVANCED_STICK) {
        	return true;
        } else {
        	return false;
        }
    }
    
    @Override
    protected void onContentsChanged(int slot) {
    	vaf.markDirty();
    	if (slot == 4) {
    		checkEnergySupply();
    	} else {
    		checkRecipes();
    	}
    	
    }

    public void checkEnergySupply() {
		if (getStackInSlot(4).isEmpty()) {
			vaf.charging = false;
		} else {
			vaf.charging = true;
		}
		return;
    }
    
    public void checkRecipes() {
    	recipe = RecipeHandler.getVacuumArcFurnaceResult(getStackInSlot(0), getStackInSlot(1));	  	
    	validRecipe = recipe != null && canFitResult();
    }
    
    /**
     * NO ERROR CHECKING - Unless called by an external source, there is no
     * reason for error checking since internal errors are prevented elsewhere
     */
    public void processResults() {
    	ItemStack remainder = insertItem(2, recipe.output1.copy(), false);
    	if (remainder.getCount() > 0) {
    		insertItem(3, remainder, false);
    	}
    	remainder = insertItem(2, recipe.output2.copy(), false);
    	if (remainder.getCount() > 0) {
    		insertItem(3, remainder, false);
    	}
    	extractItem(0, 1, false);
    	extractItem(1, 1, false);
    }
    
    private boolean canFitResult() {
    	
    	boolean slot1Used = false;
    	boolean slot2Used = false;
    	
		ItemStack simRemainder = insertItem(2, recipe.output1.copy(), true);
		if (simRemainder.getCount() > 0) {
			if (insertItem(3, simRemainder, true).getCount() > 0) {
				return false;
			}
			slot2Used = true;
		} else {
			slot1Used = true;
		}
		simRemainder = insertItem(2, recipe.output2.copy(), true);
		if (simRemainder.getCount() > 0 || slot1Used) {
			if (insertItem(3, simRemainder, true).getCount() > 0 || slot2Used) {
				return false;
			}
		}
		
		return true;
		
    }

}
