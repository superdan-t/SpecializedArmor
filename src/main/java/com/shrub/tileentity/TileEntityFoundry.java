package com.shrub.tileentity;

import com.shrub.blocks.ModBlocks;
import com.shrub.blocks.machine.BlockTileFoundryHatch;
import com.shrub.items.ModItems;
import com.shrub.main.MachineRecipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;

public class TileEntityFoundry extends TileEntityMachineTemplate {

	private static String localizedName;
	
	private static final int[] slots_side = new int[] {11};
	
	public int heatLevel; // How hot the lava core is
	public int burnTime; // Burn time remaining on added fuel
	public int burnTimeMax;
	public int pressure; // Pressure builds until explosion if exhaust is blocked
	public int progress; // Crafting progress
	public int progressDone;
	public int chimneyx;
	public int chimneyy;
	public int chimneyz;
	public boolean complete = false;
	public boolean chimneyBlocked = false;
	public int godMod = 0;
	public byte direction = 0;
	
	public TileEntityFoundry() {
		super(localizedName, 16);
	}
	
	@Override
	public void updateEntity() {
		
		boolean flag = this.isHot();
		boolean flag1 = false;
		
		if (!this.worldObj.isRemote) {
			
			if (slots[10] != null && slots[10].getItem() == ModItems.advancedStick)
				this.godMod = 12;
			else
				this.godMod = 0;
			
			if (pressure > 7200 && godMod == 0)
				worldObj.createExplosion(null, chimneyx, yCoord, chimneyz, 13.5F, true);
			
			if (foundryBurnTime(slots[11]) > 0 && burnTime == 0) {
				burnTime = foundryBurnTime(slots[11]);
				burnTimeMax = foundryBurnTime(slots[11]);
				decrStackSize(11, 1);
				flag1 = true;
			}
			
			this.chimneyBlocked = chimneyBlocked();
			
			if (burnTime > 0) {
				pressure += 4 + godMod;
				burnTime -= 3;
				if (heatLevel < 6014) {
					heatLevel += 3 + godMod * 1.5;
				}
				else if (heatLevel >= 6014) {
					heatLevel = 6016;
					burnTime += 2;
					pressure -= 3;
				}
			} else {
				if (heatLevel > 0)
					heatLevel--;
			}
					
			int recipeID = MachineRecipes.getFoundryRecipeID(slots, slots[9]);
			if (recipeID > 0) {
				if (MachineRecipes.getFoundryHeatRequired(recipeID) <= heatLevel) {
					if (canProcess(recipeID)) {
						progress += 100 + godMod * 240 + (100 * (heatLevel - MachineRecipes.getFoundryHeatRequired(recipeID))) / MachineRecipes.getFoundryHeatRequired(recipeID);
						progressDone = MachineRecipes.getFoundryProcessingTime(recipeID) * 100;
						pressure += 4;
						
						if (progress >= MachineRecipes.getFoundryProcessingTime(recipeID) * 100) {
							addResults(recipeID);
							reduceGrid();
							progress = 0;
							progressDone = 0;
							flag1 = true;
						}
					}
				}
			} else {
				progress = 0;
				progressDone = 0;
			}
			
			if (!chimneyBlocked && pressure > 0)
				pressure -= 2;
			
			if (flag != isHot()) {
				flag1 = true;
				BlockTileFoundryHatch.updateBlockState(isHot(), worldObj, xCoord, yCoord, zCoord);
			}
			updateLavaTank((int)Math.floor((double)heatLevel / 401D));
			
		}
		
		if (flag1)
			this.markDirty();
		
		
	}
	
	private boolean canProcess(int recipeID) {
		
		complete = structureComplete();
		
		if (!complete)
			return false;
		
		ItemStack[] results = MachineRecipes.getFoundryResult(recipeID);
		
		for (int i = 0; i < 4; i++) {	
			if (results[i] != null) {
				if (slots[i + 12] != null) {
					if (results[i].isItemEqual(slots[i + 12])) {
						if (slots[i + 12].getMaxStackSize() - slots[i + 12].stackSize < results[i].stackSize)
							return false;
					} else {
						return false;
					}
				}
			}
		}
		
		return true;
		
	}
/*	
	private int emptyOutputSlots() {
		int emptySlotsCount = 0;
		for (int i = 0; i < 9; i++)
			if (slots[i] == null)
				emptySlotsCount++;
		return emptySlotsCount;
	}
*/
	private void addResults(int recipeID) {
		
		ItemStack[] results = MachineRecipes.getFoundryResult(recipeID);
		
		for (int i = 0; i < 4; i++) {
			if (results[i] != null) {
				if (slots[i + 12] == null) {
					slots[i + 12] = results[i].copy();
				}
				else if (slots[i + 12].isItemEqual(results[i]) && 64 - slots[i + 12].stackSize >= results[i].stackSize) {
					slots[i + 12].stackSize += results[i].stackSize;
				}
			}
		}
	}
	
	private void reduceGrid() {
		for (int i = 0; i < 10; i++) {
			if (slots[i] != null) {
				slots[i].stackSize--;
				if (slots[i].stackSize == 0)
					slots[i] = null;
			}
		}
	}
	
	public boolean isHot() {
		if (heatLevel > 1215)
			return true;
		return false;
	}

	public void setGUIDisplayName(String displayName) {
		TileEntityFoundry.localizedName = displayName;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		if (slot >= 12) {
			return false;
		}
		else if (slot == 10) {
			if (itemStack.getItem() != Items.lava_bucket)
				return false;
			return true;
		}
		else if (slot == 11) {
			if (GameRegistry.getFuelValue(itemStack) > 0)
				return true;
			return false;
		}
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return var1 != 0 && var1 != 1 ? slots_side : null;
	}

	public void onEntityActivated(EntityPlayer player) {
		complete = structureComplete();
		if (!complete)
			player.addChatMessage(new ChatComponentText("Foundry structure is incorrect."));
			
	}
	
	public int foundryBurnTimeScaled(int scale) {
		if (burnTimeMax == 0)
			return 0;
		return (scale * burnTime / burnTimeMax);
	}
	
	private int foundryBurnTime(ItemStack itemStack) {
		
		if (itemStack == null)
			return 0;
		
		Item item = itemStack.getItem();
		
        if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
        {
            Block block = Block.getBlockFromItem(item);

            if (block == Blocks.wooden_slab)
            {
                return 11;
            }

            if (block.getMaterial() == Material.wood)
            {
                return 225;
            }

            if (block == Blocks.coal_block)
            {
                return 12000;
            }
        }

        if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
        if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
        if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
        if (item == Items.stick) return 100;
        if (item == Items.coal) return 1200;
        if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
        if (item == Items.blaze_rod) return 2400;
		return GameRegistry.getFuelValue(itemStack) / 4 * 3;
	}

	private void updateLavaTank(int fillLevel) {
		
		int xLava = 0;
		int zLava = 0;
		
		if (fillLevel > 14)
			fillLevel = 14;
		
		if (direction == 1) {
			xLava = xCoord + 1;
			zLava = zCoord + 1;
		}
		else if (direction == 2) {
			xLava = xCoord + 1;
			zLava = zCoord + 1;
		}
		else if (direction == 3) {
			xLava = xCoord + 1;
			zLava = zCoord - 1;
		}
		else if (direction == 4) {
			xLava = xCoord - 1;
			zLava = zCoord + 1;
		}
		
		if (worldObj.getBlockMetadata(xLava, yCoord, zLava) == fillLevel) {
			return;
		}
		
		worldObj.setBlock(xLava, yCoord, zLava, ModBlocks.lavaTank);
		worldObj.setBlockMetadataWithNotify(xLava, yCoord, zLava, fillLevel, 0);
		
		if (direction == 1) {
			xLava = xCoord - 1;
			zLava = zCoord + 1;
		}
		else if (direction == 2) {
			xLava = xCoord + 1;
			zLava = zCoord - 1;
		}
		else if (direction == 3) {
			xLava = xCoord - 1;
			zLava = zCoord - 1;
		}
		else if (direction == 4) {
			xLava = xCoord - 1;
			zLava = zCoord - 1;
		}
		
		worldObj.setBlock(xLava, yCoord, zLava, ModBlocks.lavaTank);
		worldObj.setBlockMetadataWithNotify(xLava, yCoord, zLava, fillLevel, 0);
		
	}
	
	public boolean structureComplete() {
		
		if (worldObj.getBlock(xCoord, yCoord, zCoord + 1) == ModBlocks.foundryCoreBasic)
			direction = 1;
		else if (worldObj.getBlock(xCoord - 1, yCoord, zCoord) == ModBlocks.foundryCoreBasic)
			direction = 4;
		else if (worldObj.getBlock(xCoord, yCoord, zCoord - 1 ) == ModBlocks.foundryCoreBasic)
			direction = 3;
		else if (worldObj.getBlock(xCoord + 1, yCoord, zCoord) == ModBlocks.foundryCoreBasic)
			direction = 2;
		else
			direction = 0;
		
		if (direction != 0) {
			
			if (direction == 1 &&
					//First Layer
					worldObj.getBlock(xCoord + 1, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord + 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord, zCoord) == ModBlocks.foundryCase && 
					worldObj.getBlock(xCoord - 1, yCoord + 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ModBlocks.foundryCase &&
					//Second Layer
					worldObj.getBlock(xCoord + 1, yCoord - 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord, zCoord + 1) == ModBlocks.lavaTank &&
					worldObj.getBlock(xCoord + 1, yCoord + 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord, zCoord + 1) == ModBlocks.lavaTank &&
					worldObj.getBlock(xCoord - 1, yCoord + 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord, zCoord + 1) == ModBlocks.foundryCoreBasic &&
					worldObj.getBlock(xCoord, yCoord + 1, zCoord + 1) == ModBlocks.chimney &&
					//Third Layer
					worldObj.getBlock(xCoord + 1, yCoord - 1, zCoord + 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord, zCoord + 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord + 1, zCoord + 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord + 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord, zCoord + 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord + 1, zCoord + 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord + 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord, zCoord + 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord + 1, zCoord + 2) == ModBlocks.foundryCase
					)
				return true;
			else if (direction == 2 &&
					//First Layer
					worldObj.getBlock(xCoord, yCoord - 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord + 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord, zCoord - 1) == ModBlocks.foundryCase && 
					worldObj.getBlock(xCoord, yCoord + 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ModBlocks.foundryCase &&
					//Second Layer
					worldObj.getBlock(xCoord + 1, yCoord - 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord, zCoord + 1) == ModBlocks.lavaTank &&
					worldObj.getBlock(xCoord + 1, yCoord + 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord - 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord, zCoord - 1) == ModBlocks.lavaTank &&
					worldObj.getBlock(xCoord + 1, yCoord + 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord, zCoord) == ModBlocks.foundryCoreBasic &&
					worldObj.getBlock(xCoord + 1, yCoord + 1, zCoord) == ModBlocks.chimney &&
					//Third Layer
					worldObj.getBlock(xCoord + 2, yCoord - 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 2, yCoord, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 2, yCoord + 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 2, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 2, yCoord, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 2, yCoord + 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 2, yCoord - 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 2, yCoord, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 2, yCoord + 1, zCoord - 1) == ModBlocks.foundryCase
					)
				return true;
			else if (direction == 3 &&
					//First Layer
					worldObj.getBlock(xCoord + 1, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord + 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord, zCoord) == ModBlocks.foundryCase && 
					worldObj.getBlock(xCoord - 1, yCoord + 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ModBlocks.foundryCase &&
					//Second Layer
					worldObj.getBlock(xCoord + 1, yCoord - 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord, zCoord - 1) == ModBlocks.lavaTank &&
					worldObj.getBlock(xCoord + 1, yCoord + 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord, zCoord - 1) == ModBlocks.lavaTank &&
					worldObj.getBlock(xCoord - 1, yCoord + 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord, zCoord - 1) == ModBlocks.foundryCoreBasic &&
					worldObj.getBlock(xCoord, yCoord + 1, zCoord - 1) == ModBlocks.chimney &&
					//Third Layer
					worldObj.getBlock(xCoord + 1, yCoord - 1, zCoord - 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord, zCoord - 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord + 1, yCoord + 1, zCoord - 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord - 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord, zCoord - 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord + 1, zCoord - 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord - 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord, zCoord - 2) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord + 1, zCoord - 2) == ModBlocks.foundryCase
					)
				return true;			
			else if (direction == 4 &&
					//First Layer
					worldObj.getBlock(xCoord, yCoord - 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord + 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord, zCoord - 1) == ModBlocks.foundryCase && 
					worldObj.getBlock(xCoord, yCoord + 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ModBlocks.foundryCase &&
					//Second Layer
					worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord, zCoord + 1) == ModBlocks.lavaTank &&
					worldObj.getBlock(xCoord - 1, yCoord + 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord, zCoord - 1) == ModBlocks.lavaTank &&
					worldObj.getBlock(xCoord - 1, yCoord + 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 1, yCoord, zCoord) == ModBlocks.foundryCoreBasic &&
					worldObj.getBlock(xCoord - 1, yCoord + 1, zCoord) == ModBlocks.chimney &&
					//Third Layer
					worldObj.getBlock(xCoord - 2, yCoord - 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 2, yCoord, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 2, yCoord + 1, zCoord + 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 2, yCoord - 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 2, yCoord, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 2, yCoord + 1, zCoord) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 2, yCoord - 1, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 2, yCoord, zCoord - 1) == ModBlocks.foundryCase &&
					worldObj.getBlock(xCoord - 2, yCoord + 1, zCoord - 1) == ModBlocks.foundryCase
					)
				return true;
			
		}
		return false;
	}

	private boolean chimneyBlocked() {
		chimneyx = 0;
		chimneyy = 0;
		chimneyz = 0;
		if (direction == 1) {
			chimneyx = this.xCoord;
			chimneyy = this.yCoord + 1;
			chimneyz = this.zCoord + 1;
		}
		else if (direction == 2) {
			chimneyx = this.xCoord + 1;
			chimneyy = this.yCoord + 1;
			chimneyz = this.zCoord;
		}
		else if (direction == 3) {
			chimneyx = this.xCoord;
			chimneyy = this.yCoord + 1;
			chimneyz = this.zCoord - 1;
		}
		else if (direction == 4) {
			chimneyx = this.xCoord - 1;
			chimneyy = this.yCoord + 1;
			chimneyz = this.zCoord;
		}
		for (int y = chimneyy; worldObj.getBlock(chimneyx, y, chimneyz) == ModBlocks.chimney && y < 256; y++) {
			chimneyy++;
		}
		chimneyy--;
		if (worldObj.getBlock(chimneyx, chimneyy + 1, chimneyz) == Blocks.air) {
			return false;
		}
		else if (worldObj.getBlock(chimneyx, chimneyy + 1, chimneyz) == Blocks.flower_pot) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		NBTTagList list = nbt.getTagList("Item", 10);
		this.slots = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
			byte b = compound.getByte("Slot");

			if (b >= 0 && b < this.slots.length) {
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}
		}

		this.burnTime = (int) nbt.getShort("BurnTime");
		this.heatLevel = (int) nbt.getShort("HeatLevel");
		this.progress = (int) nbt.getShort("Progress");
		this.pressure = (int) nbt.getShort("Pressure");

		if (nbt.hasKey("CustomName"))
			TileEntityFoundry.localizedName = nbt.getString("CustomName");

	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setShort("BurnTime", (short) this.burnTime);
		nbt.setShort("HeatLevel", (short) this.heatLevel);
		nbt.setShort("Progress", (short) this.progress);
		nbt.setShort("Pressure", (short) this.pressure);

		NBTTagList list = new NBTTagList();

		for (int i = 0; i < this.slots.length; i++) {
			if (this.slots[i] != null) {
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(compound);
				list.appendTag(compound);
			}
		}

		nbt.setTag("Item", list);

		if (this.hasCustomInventoryName()) {
			nbt.setString("CustomName", TileEntityFoundry.localizedName);
		}

	}
	
}
