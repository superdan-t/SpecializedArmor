package com.shrub.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.shrub.blocks.tileentity.TileEntityFoundry;
import com.shrub.inventory.container.ContainerFoundry;
import com.shrub.main.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFoundry extends GuiContainer {
	
	public static final ResourceLocation texture = new ResourceLocation(Main.modID + ":textures/gui/GUIFoundry.png");
	
	private TileEntityFoundry foundry;

	public GuiFoundry(InventoryPlayer inventoryPlayer, TileEntityFoundry entity) {
		super(new ContainerFoundry(inventoryPlayer, entity));
		foundry = entity;
		
		this.xSize = 176;
		this.ySize = 176;
		
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = this.foundry.hasCustomInventoryName() ? this.foundry.getInventoryName() : I18n.format(this.foundry.getInventoryName());
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		int s = foundry.foundryBurnTimeScaled(13);
		drawTexturedModalRect(guiLeft + 63, guiTop + 74, 176, 31, foundry.heatLevel / 94, 16);
		drawTexturedModalRect(guiLeft + 141 - s, guiTop + 75, 189 - s, 0, s + 1, 14);
		drawTexturedModalRect(guiLeft + 78, guiTop + 25, 176, 14, foundry.progressDone == 0 ? 0 : 38 * foundry.progress / foundry.progressDone, 16);
		drawTexturedModalRect(guiLeft + 118, guiTop + 60, 176 + foundry.pressure / 168, 47, 33, 3);

	}
	
	@Override
	public void initGui() {
		super.initGui();
		//this.buttonList.add(new GuiButton(1, guiLeft + 118, guiTop + 60, 33, 6, "NOVRAIN"));
	}
	
	@Override
	protected void actionPerformed(GuiButton b) {
		switch (b.id) {
		case 1:
			System.out.println("tHe BUttOn waS CLickED");
			break;
		}
	}

}
