package com.shrub.main;

import com.shrub.blocks.ModBlocks;
import com.shrub.render.block.RenderComputerBasic;
import com.shrub.render.item.ItemRenderComputerDesktop;
import com.shrub.tileentity.TileEntityComputerDesktop;

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
        TileEntitySpecialRenderer render = new RenderComputerBasic();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityComputerDesktop.class, render);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.computerDesktop), new ItemRenderComputerDesktop(render, new TileEntityComputerDesktop()));
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
