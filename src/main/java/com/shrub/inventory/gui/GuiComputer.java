package com.shrub.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.shrub.inventory.container.ContainerComputer;
import com.shrub.main.Main;
import com.shrub.network.ModPacketHandler;
import com.shrub.network.PacketComputer;
import com.shrub.tileentity.TileEntityComputer;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiSlider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiComputer extends GuiContainer {

	public static final ResourceLocation texture = new ResourceLocation(Main.modID + ":textures/gui/GUIComputer.png");
	
	private TileEntityComputer computer;
	
	private boolean updateButtons = true;
	
	private boolean circuitLoadedPrevious;
	
	private int guiMode = 0;
	
	private String logMessage = "";

	public GuiComputer(InventoryPlayer inventoryPlayer, TileEntityComputer entity) {
		super(new ContainerComputer(inventoryPlayer, entity));
		computer = entity;
		
		this.xSize = 176;
		this.ySize = 206;
		
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		//String name = this.computer.hasCustomInventoryName() ? this.computer.getInventoryName() : I18n.format(this.computer.getInventoryName());
		
		//this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if (this.circuitLoadedPrevious != computer.circuitLoaded) {
			updateButtons = true;
			this.circuitLoadedPrevious = computer.circuitLoaded;
		}
		
		if (!computer.circuitLoaded) {
			
			//Startup prompt screen
			drawTexturedModalRect(guiLeft + 32, guiTop + 20, 0, 206, 112, 32);
			fontRendererObj.drawString(I18n.format("gui.insertDevice"), guiLeft + this.xSize /  2 - fontRendererObj.getStringWidth(I18n.format("gui.insertDevice")) / 2, 68 + fontRendererObj.FONT_HEIGHT + guiTop, 0xFFFFFF);
			fontRendererObj.drawString("Random IDE", guiLeft + this.xSize / 2 - fontRendererObj.getStringWidth("Random IDE") / 2, guiTop + 64, 0xFFFFFF);
			if (updateButtons) {
				removeButtons();
				guiMode = 0;
				logMessage = "";
				updateButtons = false;
			}
			
		} else if (guiMode == 0) {
			
			//Menu
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 2; j++) {
					drawTexturedModalRect(guiLeft + 10 + j * 79, guiTop + 8 + i * 22, 176, 0, 79, 22);
					drawTexturedModalRect(guiLeft + 12 + j * 79, guiTop + 10 + i * 22, i * 16 + j * 64, 238, 16, 16);
					if (updateButtons) {
						GuiButtonCustom guiButton = new GuiButtonCustom(i * 2 + j, guiLeft + 10 + j * 79, guiTop + 8 + i * 22, 79, 22, getButtonName(i * 2 + j));
						guiButton.textureVisible = false;
						guiButton.visible = true;
						guiButton.textAlign = 0;
						buttonList.add(guiButton);
						logMessage = "";
					}
				}
			}
			updateButtons = false;
			
		} else if (guiMode == 4) {

			fontRendererObj.drawString(I18n.format("gui.system") + "> " + logMessage, guiLeft + 12, guiTop + 93 - fontRendererObj.FONT_HEIGHT * 2, 0xFFFFFF);
			fontRendererObj.drawString(I18n.format("gui.system") + "> " + (computer.animationStage < 10 ? "_" : ""), guiLeft + 12, guiTop + 95 - fontRendererObj.FONT_HEIGHT, 0xFFFFFF);
			
			if (updateButtons) {
				removeButtons();
				buttonList.add(new GuiSlider(8, guiLeft + 10, guiTop + 8, 149, 20, I18n.format("gui.drill") + ": ", " " + I18n.format("gui.block_plural"), 0, 10, computer.slots[0].stackTagCompound.getInteger("drillSize"), false, true));
				buttonList.add(new GuiButtonExt(9, guiLeft + 158, guiTop + 8, 10, 10, "+"));
				buttonList.add(new GuiButtonExt(10, guiLeft + 158, guiTop + 18, 10, 10, "-"));
				buttonList.add(new GuiSlider(11, guiLeft + 10, guiTop + 28, 149, 20, I18n.format("gui.auger") + ": ", " " + I18n.format("gui.block_plural"), 0, 6, computer.slots[0].stackTagCompound.getInteger("augerSize"), false, true));
				buttonList.add(new GuiButtonExt(12, guiLeft + 158, guiTop + 28, 10, 10, "+"));
				buttonList.add(new GuiButtonExt(13, guiLeft + 158, guiTop + 38, 10, 10, "-"));
				buttonList.add(new GuiSlider(14, guiLeft + 10, guiTop + 48, 149, 20, I18n.format("gui.plow") + ": ", " " + I18n.format("gui.block_plural"), 0, 6, computer.slots[0].stackTagCompound.getInteger("plowSize"), false, true));
				buttonList.add(new GuiButtonExt(15, guiLeft + 158, guiTop + 48, 10, 10, "+"));
				buttonList.add(new GuiButtonExt(16, guiLeft + 158, guiTop + 58, 10, 10, "-"));
				logMessage = "start 'tools.exe'";
				updateButtons = false;
			}
		
			//System.out.println(((GuiSlider)buttonList.get(0)).sliderValue); 
			//System.out.println(((GuiButton)buttonList.get(0)).id);
			
		}

	}
	
	@Override
	protected void actionPerformed(GuiButton b) {
		System.out.println("Button ID: " + b.id);

		switch (b.id) {
		case 4:
			guiMode = 4;
			removeButtons();
			updateButtons = true;
			break;
		case 6:
			setComputerValue("clear", 0);
			break;
		case 9:
			setComputerValue("drillSize", (GuiSlider) buttonList.get(0));
			break;
		case 10:
			removeComputerValueGui("drillSize");
			break;
		case 12:
			setComputerValue("augerSize", (GuiSlider) buttonList.get(3));
			break;
		case 13:
			removeComputerValueGui("augerSize");
			break;
		case 15:
			setComputerValue("plowSize", (GuiSlider) buttonList.get(6));
			break;
		case 16:
			removeComputerValueGui("plowSize");
			break;
		}
	}
	
	/**
	 * Change the value of a variable on the computer side.
	 * @param valueId
	 * @param newValue
	 * 
	private void setComputerValue(int valueId, int newValue) {
		ModPacketHandler.wrapper.sendToServer(new PacketComputer(computer.xCoord, computer.yCoord, computer.zCoord, valueId, newValue));		
	}*/

	/**
	 * Programs a value to the chip in the computer as run by the GUI.
	 * @param valueName
	 * @param guiSlider
	 */
	private void setComputerValue(String valueName, GuiSlider guiSlider) {
		if (computer.usage >= computer.capacity) {
			logMessage = I18n.format("message.errorMemory");
			return;
		}
		setComputerValue(valueName, (int) Math.round(guiSlider.sliderValue * (guiSlider.maxValue - guiSlider.minValue) + guiSlider.minValue));
		logMessage = I18n.format("message.valueStored");
	}
	
	/**
	 * Change the value of a variable on the computer side.
	 * @param valueName
	 * @param newValue
	 */
	private void setComputerValue(String valueName, int newValue) {
		ModPacketHandler.wrapper.sendToServer(new PacketComputer(computer.xCoord, computer.yCoord, computer.zCoord, valueName, newValue));
	}
	
	private void removeComputerValueGui(String valueName) {
		if (!computer.slots[0].stackTagCompound.getBoolean(valueName + "Exists")) {
			logMessage = I18n.format("message.errorNoExist");
			return;
		}
		removeComputerValue(valueName);
		logMessage = I18n.format("message.valueRemoved");
	}
	
	/**
	 * Removes the value of a variable on the computer side
	 * @param valueName
	 */
	private void removeComputerValue(String valueName) {
		ModPacketHandler.wrapper.sendToServer(new PacketComputer(computer.xCoord, computer.yCoord, computer.zCoord, valueName));
	}

	/**
	 * Clear the button list
	 */
	private void removeButtons() {
		this.buttonList.clear();
	}
	
	/**
	 * @param buttonID
	 * @return The display name of a button
	 */
	private String getButtonName(int buttonID) {
		switch (buttonID) {
		case 0:
			return I18n.format("gui.home");
		case 1:
			return I18n.format("gui.helment");
		case 2:
			return I18n.format("gui.weapon");
		case 3:
			return I18n.format("gui.chestplate");
		case 4:
			return I18n.format("gui.tool");
		case 5:
			return I18n.format("gui.leggings");
		case 6:
			return I18n.format("gui.erase");
		case 7:
			return I18n.format("gui.boots");
		default:
			return "";
		}
	}

}
