package com.shrub.main;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.modID, name = Main.modName, version = Main.version)

public class Main {
	
    public static final String modID = "spar";
    public static final String modName = "Random Shrub's Specialized Armor";
    public static final String version = "0.2.1";
    
    public static final int guiIDFoundry = 0;
    public static final int guiIDComputer = 1;
    
    @Instance
    public static Main instance = new Main();
    
    @SidedProxy(clientSide="com.shrub.main.ClientProxy", serverSide="com.shrub.main.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

}
