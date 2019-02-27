package com.shrub.render;

import org.lwjgl.opengl.GL11;

import com.shrub.main.Main;
import com.shrub.model.ModelComputerBasic;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderComputerBasic extends TileEntitySpecialRenderer {
	
	public static final ResourceLocation texture = new ResourceLocation(Main.modID + ":textures/model/ComputerBasic.png");
	
	private ModelComputerBasic model;
	
	public RenderComputerBasic() {
		this.model = new ModelComputerBasic();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180, 0F, 0F, 1F);
		
		this.bindTexture(texture);
		
		GL11.glPushMatrix();
		this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();
	}

}
