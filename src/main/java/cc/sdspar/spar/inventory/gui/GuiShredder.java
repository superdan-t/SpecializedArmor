package cc.sdspar.spar.inventory.gui;

import org.lwjgl.opengl.GL11;

import cc.sdspar.spar.inventory.container.ContainerShredder;
import cc.sdspar.spar.main.Ref;
import cc.sdspar.spar.tileentity.TileEntityShredder;
import cc.sdspar.spar.util.handler.recpies.RecipeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiShredder extends GuiContainer {
	
	public static final ResourceLocation texture = new ResourceLocation(Ref.MOD_ID + ":textures/gui/gui_shredder.png");
	
	private TileEntityShredder shredder;

	public GuiShredder(InventoryPlayer inventoryPlayer, TileEntityShredder entity) {
		super(new ContainerShredder(inventoryPlayer, entity));
		
		shredder = entity;
		
		xSize = 176;
		ySize = 166;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		
		int scaledCharge = getScaledCharge(48);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		drawTexturedModalRect(guiLeft + 69, guiTop + 29, 192, 0, 58, 27 * shredder.progress / 120);
		drawTexturedModalRect(guiLeft + 8, guiTop + 56 - scaledCharge, 176, 48 - scaledCharge, 16, scaledCharge);
		if (RecipeHandler.isShredderBlade(shredder.handler.getStackInSlot(7))) {
			switch ((shredder.progress / 4) % 3) {
			case 0:
				drawTexturedModalRect(guiLeft + 80, guiTop + 31, 176, 48, 16, 16);
				break;
			case 1:
				drawTexturedModalRect(guiLeft + 80, guiTop + 31, 176, 80, 16, 16);
				break;
			case 2:
				drawTexturedModalRect(guiLeft + 80, guiTop + 31, 176, 64, 16, 16);
				break;
			}
		}
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	private int getScaledCharge(int scale) {
		return (int) (48 * this.shredder.storage.getCharge() / this.shredder.storage.getCapacity());
	}

}