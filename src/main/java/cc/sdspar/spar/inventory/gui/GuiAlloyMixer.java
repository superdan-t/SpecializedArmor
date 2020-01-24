package cc.sdspar.spar.inventory.gui;

import org.lwjgl.opengl.GL11;

import cc.sdspar.spar.inventory.container.ContainerAlloyMixer;
import cc.sdspar.spar.main.Ref;
import cc.sdspar.spar.network.PacketAlloyMixer;
import cc.sdspar.spar.network.PacketHandler;
import cc.sdspar.spar.tileentity.TileEntityAlloyMixer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiAlloyMixer extends GuiContainer {
	
	public static final ResourceLocation texture = new ResourceLocation(Ref.MOD_ID + ":textures/gui/gui_alloy_mixer.png");
	
	private TileEntityAlloyMixer mixer;

	public GuiAlloyMixer(InventoryPlayer inventoryPlayer, TileEntityAlloyMixer mixer) {
		super(new ContainerAlloyMixer(inventoryPlayer, mixer));
		
		this.mixer = mixer;
		
		xSize = 176;
		ySize = 182;
		
	}
	
	@Override
	public void initGui() {
		super.initGui();
		//this.buttonList.add(new GuiButtonExt(0, guiLeft + 100, guiTop + 75, 8, 15, ""));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = mixer.getDisplayName().getUnformattedText();
		//String name = "TODO - Add custom name support";
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		
		int scaledCharge = getScaledCharge(46);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		drawTexturedModalRect(guiLeft + 78, guiTop + 83, 176, 81, 42 * mixer.progress / 260, 5);
		drawTexturedModalRect(guiLeft + 8, guiTop + 62 - scaledCharge, 176, 45 - scaledCharge, 16, scaledCharge);
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
//	@Override
//	protected void actionPerformed(GuiButton b) {
//		switch (b.id) {
//		case 0:
//			PacketHandler.wrapper.sendToServer(new PacketAlloyMixer(mixer.getPos()));
//			break;
//		}
//	}
	
	private int getScaledCharge(int scale) {
		return (int) (scale * this.mixer.storage.getCharge() / this.mixer.storage.getCapacity());
	}
	
}
