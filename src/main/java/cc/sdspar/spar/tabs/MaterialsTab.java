package cc.sdspar.spar.tabs;

import cc.sdspar.spar.item.ModItems;
import cc.sdspar.spar.main.Ref;
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
