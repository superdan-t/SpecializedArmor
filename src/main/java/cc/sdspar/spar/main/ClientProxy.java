package cc.sdspar.spar.main;

import cc.sdspar.spar.command.CommandMessages;
import cc.sdspar.spar.command.CommandUpdateClient;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
    
    public void registerItemRenderer(Item item, int meta, String id) {
    	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
    
    @Override
    public void init(FMLInitializationEvent e) {
    	super.init(e);
    	ClientCommandHandler.instance.registerCommand(new CommandUpdateClient());
    	ClientCommandHandler.instance.registerCommand(new CommandMessages());
    }
	
	
}
