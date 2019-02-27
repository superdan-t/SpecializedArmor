package com.shrub.inventory.gui;

import com.shrub.inventory.container.ContainerComputer;
import com.shrub.inventory.container.ContainerFoundry;
import com.shrub.main.Main;
import com.shrub.tileentity.TileEntityComputer;
import com.shrub.tileentity.TileEntityFoundry;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity != null)
			switch(ID) {
			
			case Main.guiIDFoundry:
				if (entity instanceof TileEntityFoundry)
					return new ContainerFoundry(player.inventory, (TileEntityFoundry) entity);
				return null;
			case Main.guiIDComputer:
				if (entity instanceof TileEntityComputer)
					return new ContainerComputer(player.inventory, (TileEntityComputer) entity);
				return null;
			}
		
		return null;		
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);	
		if (entity != null)
			switch(ID) {
			
			case Main.guiIDFoundry:
				if (entity instanceof TileEntityFoundry)
					return new GuiFoundry(player.inventory, (TileEntityFoundry) entity);
				return null;
			case Main.guiIDComputer:
				if (entity instanceof TileEntityComputer)
					return new GuiComputer(player.inventory, (TileEntityComputer) entity);
			}
			
		return null;
	}

}
