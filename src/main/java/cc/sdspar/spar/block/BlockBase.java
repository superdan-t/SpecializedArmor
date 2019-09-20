package cc.sdspar.spar.block;

import cc.sdspar.spar.item.ModItems;
import cc.sdspar.spar.main.Main;
import cc.sdspar.spar.util.IHasModel;
import cc.sdspar.spar.util.Ref;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {

	public BlockBase(String name, Material materialIn) {
		super(materialIn);
		setTranslationKey(Ref.MOD_ID + '.' + name);
		setRegistryName(Ref.MOD_ID, name);
		setCreativeTab(Main.MATERIALS_TAB);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		
	}

}
