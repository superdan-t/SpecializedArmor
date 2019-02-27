package com.shrub.blocks.machine;

import java.util.Random;

import com.shrub.blocks.ModBlocks;
import com.shrub.main.Main;
import com.shrub.tileentity.TileEntityFoundry;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTileFoundryHatch extends BlockContainer {
	
	private final boolean isHot;
	
	private static boolean keepInventory;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public BlockTileFoundryHatch(boolean isHot) {
		super(Material.iron);
		this.isHot = isHot;
		this.setBlockName(isHot ? "foundryHot" : "foundryCold");
		this.setHardness(5F);
		this.setResistance(10F);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 0);
		
		if (isHot)
			this.setLightLevel(0.625F);
		else
			this.setCreativeTab(ModBlocks.tabBlocks);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityFoundry();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ) {
		if (world.isRemote)
			return true;
		else if (player.isSneaking())
			return false;
		
		TileEntityFoundry tile = (TileEntityFoundry)world.getTileEntity(x, y, z);
		
		if (tile.structureComplete())	
			FMLNetworkHandler.openGui(player, Main.instance, Main.guiIDFoundry, world, x, y, z);
		else
			player.addChatMessage(new ChatComponentText("Foundry structure is incorrect."));
		
		return true;
		
	}
	
	//Get the icon textures for the icons
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(Main.modID + ":foundryHatch_sides");
		this.iconFront = reg.registerIcon(Main.modID + ":foundryHatch_front_" + (this.isHot ? "hot" : "cool"));
		this.iconTop = reg.registerIcon(Main.modID + ":foundryHatch_sides");
	}
	
	//Return the right icon depending on the side
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
	
	@Override
	public int quantityDropped(Random random) {
		return this.isHot ? 0 : 1;
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
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
	
	@SideOnly(Side.SERVER)
	public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata) {
		if (!keepInventory) {
			Random rand = new Random();
			TileEntityFoundry tileentity = (TileEntityFoundry)world.getTileEntity(x, y, z);
			if (tileentity != null) {
				for (int i = 0; i < tileentity.getSizeInventory(); i++) {
					ItemStack itemstack = tileentity.getStackInSlot(i);
					if (itemstack != null) {
						float f = rand.nextFloat() * 0.8F + 0.1F;
						float f1 = rand.nextFloat() * 0.8F + 0.1F;
						float f2 = rand.nextFloat() * 0.8F + 0.1F;
						
						while (itemstack.stackSize > 0) {
							int j = rand.nextInt(21) + 10;
							
							if (j > itemstack.stackSize) {
								j = itemstack.stackSize;
							}
							
							itemstack.stackSize-= j;
							
							EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1),  (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
							
							if (itemstack.hasTagCompound()) {
								item.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
							}
							
							world.spawnEntityInWorld(item);
						}
					}
				}
				
				world.func_147453_f(x, y, z, oldBlock);
			}
		}
		
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ModBlocks.coolFurnaceIdle)));
		
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		TileEntityFoundry foundry = (TileEntityFoundry)world.getTileEntity(x, y, z);
		if(foundry.progress > 0) {
			
			int direction = world.getBlockMetadata(x, y, z);			
			float x1 = (float)x + 0.5F;
			float y1 = (float)y + random.nextFloat();
			float z1 = (float)z + 0.5F;
			float f = 0.52F;
			float f1 = random.nextFloat() * 0.6F - 0.3F;
			
			if(direction == 4){
				world.spawnParticle("smoke", (double)(x1 - f), (double)(y1), (double)(z1 + f1), 0D, 0D, 0D);
				world.spawnParticle("flame", (double)(x1 - f), (double)(y1), (double)(z1 + f1), 0D, 0D, 0D);
			}
			
			if(direction == 5){
				world.spawnParticle("smoke", (double)(x1 + f), (double)(y1), (double)(z1 + f1), 0D, 0D, 0D);
				world.spawnParticle("flame", (double)(x1 + f), (double)(y1), (double)(z1 + f1), 0D, 0D, 0D);
			}
			
			if(direction == 2){
				world.spawnParticle("smoke", (double)(x1 + f1), (double)(y1), (double)(z1 - f), 0D, 0D, 0D);
				world.spawnParticle("flame", (double)(x1 + f1), (double)(y1), (double)(z1 - f), 0D, 0D, 0D);
			}
			
			if(direction == 3){
				world.spawnParticle("smoke", (double)(x1 + f1), (double)(y1), (double)(z1 + f), 0D, 0D, 0D);
				world.spawnParticle("flame", (double)(x1 + f1), (double)(y1), (double)(z1 + f), 0D, 0D, 0D);
			}
		}

	}

	public static void updateBlockState(boolean hot, World worldObj, int xCoord, int yCoord, int zCoord) {
		
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		
		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		
		keepInventory = true;
		
		if (hot)
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.foundryHatchHot);
		else
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.foundryHatchCool);
		
		keepInventory = false;
		
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		
		if (tileentity != null) {
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
				
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(ModBlocks.foundryHatchCool);
	}
	
}
