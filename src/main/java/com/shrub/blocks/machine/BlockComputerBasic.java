package com.shrub.blocks.machine;

import com.shrub.main.Main;
import com.shrub.tileentity.TileEntityComputer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockComputerBasic extends BlockComputerTemplate {

	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	public BlockComputerBasic() {
		super("computerBasic");
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityComputer();
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon("iron_block");
		this.iconFront = reg.registerIcon(Main.modID + ":computer_front");
		this.iconTop = reg.registerIcon("iron_block");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return metadata == 0 && side == 3 ? this.iconFront : (side == metadata ? this.iconFront : (side == 1 ? this.iconTop : this.blockIcon));
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
		
	}

}
