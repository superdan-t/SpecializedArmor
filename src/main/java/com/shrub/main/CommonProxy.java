package com.shrub.main;

import com.shrub.blocks.ModBlocks;
import com.shrub.items.ModItems;
import com.shrub.network.ModPacketHandler;
import com.shrub.blocks.WorldGen;
import com.shrub.inventory.gui.GuiHandler;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	
    public void preInit(FMLPreInitializationEvent e) {
    	ModBlocks.init();
    	ModItems.init();
    	ModPacketHandler.registerPackets();
    	
    }

    public void init(FMLInitializationEvent e) {
    	CraftingRecipes.init();
    	ModBlocks.initTileEntities();
    	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
    	GameRegistry.registerWorldGenerator(new WorldGen(), 0);

    }

    public void postInit(FMLPostInitializationEvent e) {
    	
    	//Added to see if this was running properly on the very first build of all time.
    	//Don't you dare remove it. *points gun* *pulls the trigger now you're dead*
    	System.out.println("\nIs this the real life?\nIs this just fantasy?");
    	
    }
    
}
