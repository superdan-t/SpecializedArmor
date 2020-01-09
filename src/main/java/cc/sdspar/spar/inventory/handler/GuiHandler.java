package cc.sdspar.spar.inventory.handler;

import cc.sdspar.spar.inventory.container.ContainerShredder;
import cc.sdspar.spar.inventory.container.ContainerVacuumArcFurnace;
import cc.sdspar.spar.inventory.gui.GuiShredder;
import cc.sdspar.spar.inventory.gui.GuiVacuumArcFurnace;
import cc.sdspar.spar.tileentity.TileEntityShredder;
import cc.sdspar.spar.tileentity.TileEntityVacuumArcFurnace;
import cc.sdspar.spar.util.Ref;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case Ref.GUI_VACUUM_ARC_FURNACE:
			return new ContainerVacuumArcFurnace(player.inventory, (TileEntityVacuumArcFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		case Ref.GUI_SHREDDER:
			return new ContainerShredder(player.inventory, (TileEntityShredder)world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case Ref.GUI_VACUUM_ARC_FURNACE:
			return new GuiVacuumArcFurnace(player.inventory, (TileEntityVacuumArcFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		case Ref.GUI_SHREDDER:
			return new GuiShredder(player.inventory, (TileEntityShredder)world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}

}
