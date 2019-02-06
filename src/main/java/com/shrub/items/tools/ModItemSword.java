package com.shrub.items.tools;

import com.shrub.items.ModItems;
import com.shrub.main.Main;
import net.minecraft.item.ItemSword;

public class ModItemSword extends ItemSword {

	public ModItemSword(String unlocalizedName, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(ModItems.tabSpecializedTools);
		this.setTextureName(Main.modID + ":" + unlocalizedName);
	}

}
