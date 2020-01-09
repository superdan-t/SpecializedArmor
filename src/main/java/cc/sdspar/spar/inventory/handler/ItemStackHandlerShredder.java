package cc.sdspar.spar.inventory.handler;

import cc.sdspar.spar.energy.EnergyHelper;
import cc.sdspar.spar.tileentity.TileEntityShredder;
import cc.sdspar.spar.util.handler.recpies.RecipeHandler;
import cc.sdspar.spar.util.handler.recpies.ShredderRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerShredder extends ItemStackHandler {
	
	public boolean validRecipe = false;
	
	public TileEntityShredder shredder;
	
	public ShredderRecipe[] recipes = new ShredderRecipe[3];
	
	public ItemStackHandlerShredder() {
		super(8);
	}
	
	@Override
	public boolean isItemValid(int slot, ItemStack stack) {
		return (slot <= 2 || (slot == 6 && EnergyHelper.isChargeProvider(stack)) || (slot == 7 && RecipeHandler.isShredderBlade(stack)));
	}
	
    @Override
    protected void onContentsChanged(int slot) {
    	shredder.markDirty();
    	if (slot == 6) {
    		checkEnergySupply();
    	} else if (slot == 7) {
    		shredder.unjamShredder();
    		checkRecipes();
    	} else {
    		checkRecipes();
    	}
    	
    }
	
    public void checkEnergySupply() {
		if (getStackInSlot(6).isEmpty()) {
			shredder.charging = false;
		} else {
			shredder.charging = true;
		}
    }
    
    public void checkRecipes() {
    	recipes[0] = RecipeHandler.getShredderResult(getStackInSlot(0));
    	recipes[1] = RecipeHandler.getShredderResult(getStackInSlot(1));
    	recipes[2] = RecipeHandler.getShredderResult(getStackInSlot(2));
    	
    	validRecipe = (recipes[0] != null || recipes[1] != null || recipes[2] != null) && RecipeHandler.isShredderBlade(getStackInSlot(7));
    	
    }
    
    public int processResults() {
    	int recipesProcessed = 0;
    	recipesProcessed += addResult(0);
    	recipesProcessed += addResult(1);
    	recipesProcessed += addResult(2);
    	
    	extractItem(0, 1, false);
    	extractItem(1, 1, false);
    	extractItem(2, 1, false);
    	
    	return recipesProcessed;

    }
    
    private int addResult(int index) {
    	if (recipes[index] == null) return 0;
    	ItemStack remainder = insertItem(3, recipes[index].output.copy(), false);
    	if (remainder.getCount() > 0) {
    		remainder = insertItem(4, remainder, false);
    		if (remainder.getCount() > 0) {
    			remainder = insertItem(5, remainder, false);
    			if (remainder.getCount() > 0) {
    				shredder.jamShredder();
    			}
    		}
    	}
    	return 1;
    }
    
    public ItemStack getBlade() {
    	return this.getStackInSlot(7);
    }
    
    public ItemStack getChargeProvider() {
    	return this.getStackInSlot(6);
    }

}
