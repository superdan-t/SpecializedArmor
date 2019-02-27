package com.shrub.blocks.machine;

import com.shrub.blocks.ModBlocks;
import com.shrub.main.Main;
import com.shrub.tileentity.TileEntityComputer;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTileComputer extends BlockContainer {

	private final boolean on;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	public BlockTileComputer(String blockName, boolean on) {
		super(Material.iron);
		this.setBlockName(blockName + (on ? "On" : "Off"));
		this.setHardness(2F);
		this.setResistance(4F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 0);
		this.on = on;
		if (on)
			this.setLightLevel(0.625F);
		else
			this.setCreativeTab(ModBlocks.tabBlocks);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityComputer();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ) {
		if (world.isRemote)
			return true;
		else if (player.isSneaking())
			return false;
		
		TileEntityComputer tile = (TileEntityComputer)world.getTileEntity(x, y, z);
		
		if (tile.isUseableByPlayer(player))
			FMLNetworkHandler.openGui(player, Main.instance, Main.guiIDComputer, world, x, y, z);

		
		return true;
		
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(Main.modID + ":computer_sides");
		this.iconFront = reg.registerIcon(Main.modID + ":computer_front_" + (this.on ? "on" : "off"));
		this.iconTop = reg.registerIcon(Main.modID + ":computer_sides");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return metadata == 0 && side == 3 ? this.iconFront : (side == metadata ? this.iconFront : (side == 1 ? this.iconTop : this.blockIcon));
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
