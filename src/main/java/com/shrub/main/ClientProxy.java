package com.shrub.main;

import com.shrub.render.RenderComputerBasic;
import com.shrub.tileentity.TileEntityComputer;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        
        //ComputerBasic
        TileEntitySpecialRenderer render = new RenderComputerBasic();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityComputer.class, render);
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
