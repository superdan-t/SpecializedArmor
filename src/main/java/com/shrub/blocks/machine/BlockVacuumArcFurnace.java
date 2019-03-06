package com.shrub.blocks.machine;

import com.shrub.blocks.ModBlocks;
import com.shrub.main.Main;
import com.shrub.tileentity.TileEntityFoundry;
import com.shrub.tileentity.TileEntityVacuumArcFurnace;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockVacuumArcFurnace extends BlockContainer {
	
	private final boolean on;
	
	@SideOnly(Side.CLIENT)
	private IIcon front;
	
	@SideOnly(Side.CLIENT)
	private IIcon sides;

	@SideOnly(Side.CLIENT)
	private IIcon top;
	
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public BlockVacuumArcFurnace(boolean on) {
		super(Material.iron);
		this.setBlockName("vacuumArcFurnace_" + (on ? "on" : "off"));
		this.setHardness(3.5F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 0);
		this.on = on;
		if (!on) {
			this.setCreativeTab(ModBlocks.tabBlocks);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(Main.modID + ":vacuumArcFurnace_sides");
		this.front = reg.registerIcon(Main.modID + ":vacuumArcFurnace_front_" + (on ? "on" : "off"));
		this.sides = reg.registerIcon(Main.modID + ":vacuumArcFurnace_sides");
		this.top = reg.registerIcon(Main.modID + ":vacuumArcFurnace_top");
		this.bottom = reg.registerIcon(Main.modID + ":vacuumArcFurnace_bottom");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (side == metadata) {
			return this.front;
		} else if (side == 0) {
			return this.bottom;
		} else if (side == 1) {
			return this.top;
		} else {
			return this.sides;
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sizeZ) {
		if (world.isRemote) {
			return true;
		} else if (player.isSneaking()) {
			return false;
		}
		
		FMLNetworkHandler.openGui(player, Main.instance, Main.guiIDVacuumArcFurnace, world, x, y, z);
		
		return true;
		
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityVacuumArcFurnace();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack) {
		int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if (l == 0)
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		else if (l == 1)
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		else if (l == 2)
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		else if (l == 3)
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		
		if (itemstack.hasDisplayName())
			((TileEntityFoundry)world.getTileEntity(x, y, z)).setGUIDisplayName(itemstack.getDisplayName());
		
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	private void setDefaultDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			byte b0 = 3;
			Block b1 = world.getBlock(x, y, z - 1);
			Block b2 = world.getBlock(x, y, z + 1);
			Block b3 = world.getBlock(x - 1, y, z);
			Block b4 = world.getBlock(x + 1, y, z);	
			
			if (b1.func_149730_j() && !b2.func_149730_j())
				b0 = 3;
			else if (b2.func_149730_j() && !b1.func_149730_j())
				b0 = 2;
			else if (b3.func_149730_j() && !b4.func_149730_j())
				b0 = 5;
			else if (b4.func_149730_j() && !b3.func_149730_j())
				b0 = 4;
			
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
			
		}
	}

}
