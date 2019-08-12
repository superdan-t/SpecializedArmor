package cc.sdservers.spar.main;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SpecializedArmor.MODID, name = SpecializedArmor.NAME, version = SpecializedArmor.VERSION)

public class SpecializedArmor {
	
    public static final String MODID = "sdspar";
    public static final String NAME = "SuperDan's Specialized Armor";
    public static final String VERSION = "1.0.0";
    public static final String MC_VERSION = "[1.12.2]";
    
    public static final Logger LOGGER = LogManager.getLogger(SpecializedArmor.MODID);
    
    @Instance
    public static SpecializedArmor instance = new SpecializedArmor();
    
    @SidedProxy(clientSide="cc.sdservers.spar.main.ClientProxy", serverSide="cc.sdservers.spar.main.ServerProxy")
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