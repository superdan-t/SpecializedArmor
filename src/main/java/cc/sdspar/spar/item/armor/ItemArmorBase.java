package cc.sdspar.spar.item.armor;

import cc.sdspar.spar.item.ModItems;
import cc.sdspar.spar.main.Main;
import cc.sdspar.spar.util.Ref;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemArmorBase extends ItemArmor {

	public ItemArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setTranslationKey(Ref.MOD_ID + '.' + name);
		setRegistryName(Ref.MOD_ID, name);
		setCreativeTab(Main.MATERIALS_TAB);
		ModItems.ITEMS.add(this);
	}

}
