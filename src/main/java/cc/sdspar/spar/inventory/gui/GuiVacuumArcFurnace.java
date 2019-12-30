package cc.sdspar.spar.inventory.gui;

import org.lwjgl.opengl.GL11;

import cc.sdspar.spar.inventory.container.ContainerVacuumArcFurnace;
import cc.sdspar.spar.tileentity.TileEntityVacuumArcFurnace;
import cc.sdspar.spar.util.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiVacuumArcFurnace extends GuiContainer {
	
	public static final ResourceLocation texture = new ResourceLocation(Ref.MOD_ID + ":textures/gui/gui_vacuum_arc_furnace.png");
	
	private TileEntityVacuumArcFurnace vacuumArcFurnace;

	public GuiVacuumArcFurnace(InventoryPlayer inventoryPlayer, TileEntityVacuumArcFurnace entity) {
		super(new ContainerVacuumArcFurnace(inventoryPlayer, entity));
		
		vacuumArcFurnace = entity;
		
		xSize = 176;
		ySize = 166;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = vacuumArcFurnace.getDisplayName().getUnformattedText();
		//String name = "TODO - Add custom name support";
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		int scaledEnergyStored = 48 * vacuumArcFurnace.getEnergyStored() / vacuumArcFurnace.getMaxEnergyStored();
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		drawTexturedModalRect(guiLeft + 59, guiTop + 34, 176, 0, 58, 24 * vacuumArcFurnace.progress / 160);
		drawTexturedModalRect(guiLeft + 8, guiTop + 56 - scaledEnergyStored, 176, 72 - scaledEnergyStored, 16, scaledEnergyStored);
		
	}

}