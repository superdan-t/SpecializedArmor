package com.shrub.items.tools;

import java.util.List;

import com.shrub.items.ModItems;
import com.shrub.main.Main;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemZirconiumToolTip extends Item {
	
	public IIcon[] icons = new IIcon[7];

	public ItemZirconiumToolTip(String unlocalizedName) {
		super();
		this.setHasSubtypes(true);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(ModItems.tabArmorParts);
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
		for (int i = 0; i < 7; i++)
			this.icons[i] = reg.registerIcon(Main.modID + ":zirconiumToolTip_" + i);
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
	    if (meta > 6)
	        meta = 0;

	    return this.icons[meta];
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list) {
	    for (int i = 0; i < 7; i ++)
	        list.add(new ItemStack(item, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
	
}