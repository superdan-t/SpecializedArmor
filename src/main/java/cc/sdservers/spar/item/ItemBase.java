package cc.sdservers.spar.item;

import cc.sdservers.spar.main.Main;
import cc.sdservers.spar.util.IHasModel;
import cc.sdservers.spar.util.Ref;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
	
	public ItemBase(String name) {
		setTranslationKey(Ref.MOD_ID + '.' + name);
		setRegistryName(Ref.MOD_ID, name);
		setCreativeTab(Main.MATERIALS_TAB);
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");

	}

}
