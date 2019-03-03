package com.shrub.main;

import com.shrub.blocks.ModBlocks;
import com.shrub.render.block.RenderComputerDesktop;
import com.shrub.render.block.RenderComputerModern;
import com.shrub.render.item.ItemRenderComputerDesktop;
import com.shrub.render.item.ItemRenderComputerModern;
import com.shrub.tileentity.TileEntityComputerDesktop;
import com.shrub.tileentity.TileEntityComputerModern;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        
        //ComputerBasic
        TileEntitySpecialRenderer render = new RenderComputerDesktop();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityComputerDesktop.class, render);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.computerDesktop), new ItemRenderComputerDesktop(render, new TileEntityComputerDesktop()));
        
        render = new RenderComputerModern();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityComputerModern.class, render);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.computerModern), new ItemRenderComputerModern(render, new TileEntityComputerModern()));
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
	
	
}
