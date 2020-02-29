package cc.sdspar.spar.main;

import cc.sdspar.spar.event.ArmorEventHandler;
import cc.sdspar.spar.event.StaticEventHandler;
import cc.sdspar.spar.inventory.handler.GuiHandler;
import cc.sdspar.spar.network.PacketHandler;
import cc.sdspar.spar.util.ModRecipes;
import cc.sdspar.spar.util.handler.RegistryHandler;
import cc.sdspar.spar.world.ModWorldGen;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id) {}
	
    public void preInit(FMLPreInitializationEvent e) {
    	GameRegistry.registerWorldGenerator(new ModWorldGen(), 0);
    	PacketHandler.registerPackets();
    }
	
	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new ArmorEventHandler());
		MinecraftForge.EVENT_BUS.register(StaticEventHandler.class);
    	ModRecipes.init();
    	RegistryHandler.registerOres();
    	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
}
