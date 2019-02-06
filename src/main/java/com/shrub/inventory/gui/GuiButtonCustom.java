package com.shrub.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.shrub.main.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class GuiButtonCustom extends GuiButton {
	
	protected static final ResourceLocation buttonTextures = new ResourceLocation(Main.modID + ":textures/gui/buttons.png");
	
	public boolean textureVisible = true;
	
	public int fontColor = 0xFFFFFF;
	
	/**
	 * 0 - Left, 1 - Center, 2 - Right
	 */
	public int textAlign = 1;
	
	public boolean textVisible = true;

	/**
	 * Makes a button with a texture that you can actually change. Can also be set to invisible without disabling the button.
	 * @param ID
	 * @param x 
	 * @param y
	 * @param width
	 * @param height
	 * @param displayText
	 */
	public GuiButtonCustom(int id, int x, int y, int width, int height, String displayText) {
		super(id, x, y, width, height, displayText);

	}
	
	@Override
	public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
		if (!visible)
			return;
        if (this.textureVisible) {
            p_146112_1_.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, k * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, k * 20, this.width / 2, this.height);
            this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
        }
        if (this.textVisible) {
            FontRenderer fontrenderer = p_146112_1_.fontRenderer;
            int l = fontColor;

            if (packedFGColour != 0)
            {
                l = packedFGColour;
            }
            else if (!this.enabled)
            {
                l = 10526880;
            }
            else if (this.field_146123_n)
            {
                l = 16777120;
            }
            
            if (this.textAlign == 1)
            	this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
            else if (this.textAlign == 0)
            	this.drawString(fontrenderer, this.displayString, this.xPosition + 22, this.yPosition + (this.height - 8) / 2, l);
            
        }
	}
	
	@Override
    public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
        return this.enabled && p_146116_2_ >= this.xPosition && p_146116_3_ >= this.yPosition && p_146116_2_ < this.xPosition + this.width && p_146116_3_ < this.yPosition + this.height;
    }

}
