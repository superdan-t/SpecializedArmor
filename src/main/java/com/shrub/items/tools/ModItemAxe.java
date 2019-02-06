package com.shrub.items.tools;

import com.shrub.items.ModItems;
import com.shrub.main.Main;
import net.minecraft.item.ItemAxe;

public class ModItemAxe extends ItemAxe {

	public ModItemAxe(String unlocalizedName, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(ModItems.tabSpecializedTools);
		this.setTextureName(Main.modID + ":" + unlocalizedName);
	}

}
