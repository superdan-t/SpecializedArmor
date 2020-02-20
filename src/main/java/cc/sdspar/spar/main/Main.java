package cc.sdspar.spar.main;

import org.apache.logging.log4j.Logger;

import cc.sdspar.spar.command.CommandMessages;
import cc.sdspar.spar.network.api.NetworkAPI;
import cc.sdspar.spar.tabs.MaterialsTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Ref.MOD_ID, name = Ref.NAME, version = Ref.VERSION)
public class Main {
     
    public static final CreativeTabs MATERIALS_TAB = new MaterialsTab();
    public static Logger logger;
    
    @Instance
    public static Main instance = new Main();
    
    @SidedProxy(clientSide = Ref.CLIENT_PROXY_CLASS, serverSide = Ref.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent e) {
    	logger = e.getModLog();
    	proxy.preInit(e);
    }

    @EventHandler
    public static void init(FMLInitializationEvent e) {
    	proxy.init(e);
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent e) {
    
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent e) {
    	e.registerServerCommand(new CommandMessages());
    }
    
	@EventHandler
	public void serverStarted(FMLServerStartedEvent e) {
		NetworkAPI.startServerAPI();
	}
	

}