package cc.sdspar.spar.main;

import cc.sdspar.spar.tabs.MaterialsTab;
import cc.sdspar.spar.util.Ref;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Ref.MOD_ID, name = Ref.NAME, version = Ref.VERSION)
public class Main {
     
    public static final CreativeTabs MATERIALS_TAB = new MaterialsTab();
    
    @Instance
    public static Main instance = new Main();
    
    @SidedProxy(clientSide = Ref.CLIENT_PROXY_CLASS, serverSide = Ref.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent e) {
    	proxy.preInit(e);
    }

    @EventHandler
    public static void init(FMLInitializationEvent e) {
    	proxy.init(e);
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent e) {
    
    }

}