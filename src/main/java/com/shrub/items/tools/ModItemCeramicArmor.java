package com.shrub.items.tools;

import com.shrub.items.ModItems;
import com.shrub.main.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ModItemCeramicArmor extends ItemArmor {
	
	public String textureName;
	
	public ModItemCeramicArmor(String unlocalizedName, ArmorMaterial material, String textureName, int type) {
		super(material, 0, type);
		this.textureName = textureName;
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName(Main.modID + ":" + unlocalizedName);
		this.setCreativeTab(ModItems.tabSpecializedArmor);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		
	    return Main.modID + ":textures/armor/" + this.textureName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
	    
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		
		int fireResistance = 0;		
		if (player.getCurrentArmor(0) != null && (player.getCurrentArmor(0).getItem() == ModItems.ceramicBoots ||
				player.getCurrentArmor(0).getItem() == ModItems.strengthenedCeramicBoots ||
				player.getCurrentArmor(0).getItem() == ModItems.refractiveCeramicBoots ||
				player.getCurrentArmor(0).getItem() == ModItems.ppBoots))
			fireResistance++;
		if (player.getCurrentArmor(1) != null && (player.getCurrentArmor(1).getItem() == ModItems.ceramicLeggings ||
				player.getCurrentArmor(1).getItem() == ModItems.strengthenedCeramicLeggings ||
				player.getCurrentArmor(1).getItem() == ModItems.refractiveCeramicLeggings ||
				player.getCurrentArmor(1).getItem() == ModItems.ppLeggings))
			fireResistance++;
		if (player.getCurrentArmor(2) != null && (player.getCurrentArmor(2).getItem() == ModItems.ceramicChestplate ||
				player.getCurrentArmor(2).getItem() == ModItems.strengthenedCeramicChestplate ||
				player.getCurrentArmor(2).getItem() == ModItems.refractiveCeramicChestplate ||
				player.getCurrentArmor(2).getItem() == ModItems.ppChestplate))
			fireResistance++;
		if (player.getCurrentArmor(3) != null && (player.getCurrentArmor(3).getItem() == ModItems.ceramicHelmet ||
				player.getCurrentArmor(3).getItem() == ModItems.strengthenedCeramicHelmet ||
				player.getCurrentArmor(3).getItem() == ModItems.refractiveCeramicHelmet ||
				player.getCurrentArmor(3).getItem() == ModItems.ppHelmet))
			fireResistance++;
		if (fireResistance == 4)
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 20));
		fireResistance = 0;
		
		if ((player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == ModItems.ppBoots) ||
				(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ModItems.ppLeggings) ||
				(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ModItems.ppChestplate) ||
				(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() == ModItems.ppHelmet))
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 2));
		
	}
	
}
