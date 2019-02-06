package com.shrub.items.tools;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.shrub.inventory.ControlChipUtility;
import com.shrub.main.Text;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModItemDrill extends ModItemPickaxe {
	
	private int drillStrength;

	public ModItemDrill(String unlocalizedName, ToolMaterial material) {
		super(unlocalizedName, material);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
		list.add(I18n.format("lore.drill.info_0"));
		list.add(I18n.format("lore.drill.info_1"));
		list.add(I18n.format("lore.drill.info_2"));
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			list.add(I18n.format("lore.drill.info_3"));
			list.add(I18n.format("lore.drill.info_4"));	
		} else {
			list.add(Text.Color.BLUE + I18n.format("lore.shiftMoreInfo"));
		}
		
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
		if (!world.isRemote ) {
			if (entity instanceof EntityPlayer) {
				itemStack.attemptDamageItem(1, itemRand);
				EntityPlayer player = (EntityPlayer) entity;
				ControlChipUtility ctrlChip = new ControlChipUtility(player);
				drillStrength = ctrlChip.getValue("drillSize");
				if (drillStrength > 10)
					drillStrength = 10;
				if (!ctrlChip.variableExists("drillSize") || drillStrength <= 1 || player.isSneaking())
					return true;
				Double yaw = (double) player.rotationYawHead;
				int direction;
				if (yaw >= 135 && yaw <= 225)
					direction = 2;
				else if (yaw > 225 && yaw < 315)
					direction = 3;
				else if (yaw >= 315 || yaw <= 45)
					direction = 0;
				else
					direction = 1;
				
				int blocksBroken = 0;
				
				switch (direction) {
				case 0:
					for (int i = 1; i < drillStrength; i++) {
						block = world.getBlock(x, y, z + i);
						if (!canBreakBlock(block, world, x, y, z + i))
							break;
						block.harvestBlock(world, player, x, y, z + i, world.getBlockMetadata(x, y, z + i));
						world.setBlockToAir(x, y, z + i);
						blocksBroken++;
					}
					break;
				case 1:
					for (int i = 1; i < drillStrength; i++) {
						block = world.getBlock(x - i, y, z);
						if (!canBreakBlock(block, world, x - i, y, z))
							break;
						block.harvestBlock(world, player, x - i, y, z, world.getBlockMetadata(x, y, z + i));
						world.setBlockToAir(x - i, y, z);
						blocksBroken++;
					}
					break;
				case 2:
					for (int i = 1; i < drillStrength; i++) {
						block = world.getBlock(x, y, z - i);
						if (!canBreakBlock(block, world, x, y, z - i))
							break;
						block.harvestBlock(world, player, x, y, z - i, world.getBlockMetadata(x, y, z - i));
						world.setBlockToAir(x, y, z - i);
						blocksBroken++;
					}
					break;
				case 3:
					for (int i = 1; i < drillStrength; i++) {
						block = world.getBlock(x + i, y, z);
						if (!canBreakBlock(block, world, x + i, y, z))
							break;
						block.harvestBlock(world, player, x + i, y, z, world.getBlockMetadata(x, y, z + i));
						world.setBlockToAir(x + i, y, z);
						blocksBroken++;
					}
					break;
				}
				
				//System.out.println(Math.round(blocksBroken * Math.pow(2.0F, blocksBroken / 2.0F) + 0.4F));
				
				itemStack.attemptDamageItem((int) (Math.round(blocksBroken * Math.pow(2.0F, blocksBroken / 2.0F) + 0.4F)), itemRand);
				
			}
		}
		return true;
	}
	
	private boolean canBreakBlock(Block block, World world, int x, int y, int z) {
		if (block == Blocks.bedrock || block == Blocks.water || block == Blocks.lava || block.getBlockHardness(world, x, y, z) >= 7.0F || block == Blocks.air)
			return false;
		return true;
	}

}
