package com.shrub.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.shrub.inventory.container.ContainerVacuumArcFurnace;
import com.shrub.main.Main;
import com.shrub.tileentity.TileEntityVacuumArcFurnace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiVacuumArcFurnace extends GuiContainer {
	
	public static final ResourceLocation texture = new ResourceLocation(Main.modID + ":textures/gui/GUIVacuumArcFurnace.png");
	
	private TileEntityVacuumArcFurnace vacuumArcFurnace;

	public GuiVacuumArcFurnace(InventoryPlayer inventoryPlayer, TileEntityVacuumArcFurnace entity) {
		super(new ContainerVacuumArcFurnace(inventoryPlayer, entity));
		
		vacuumArcFurnace = entity;
		
		xSize = 176;
		ySize = 166;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = vacuumArcFurnace.hasCustomInventoryName() ? vacuumArcFurnace.getInventoryName() : I18n.format(vacuumArcFurnace.getInventoryName());
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 4, 4210752);
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		drawTexturedModalRect(guiLeft + 59, guiTop + 29, 176, 0, 58, 24 * vacuumArcFurnace.progress / 300);
		
	}

}
