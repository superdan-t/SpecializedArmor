package cc.sdspar.spar.inventory.handler;

import cc.sdspar.spar.inventory.container.ContainerVacuumArcFurnace;
import cc.sdspar.spar.inventory.gui.GuiVacuumArcFurnace;
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
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case Ref.GUI_VACUUM_ARC_FURNACE:
			return new GuiVacuumArcFurnace(player.inventory, (TileEntityVacuumArcFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}

}
