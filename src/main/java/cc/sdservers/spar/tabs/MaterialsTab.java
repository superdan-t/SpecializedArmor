package cc.sdservers.spar.tabs;

import cc.sdservers.spar.item.ModItems;
import cc.sdservers.spar.util.Ref;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MaterialsTab extends CreativeTabs {
	
	public MaterialsTab() {
		super(Ref.MOD_ID + ".materials");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.ZIRCON);
	}

}
