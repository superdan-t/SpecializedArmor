package cc.sdspar.spar.network;

import cc.sdspar.spar.tileentity.TileEntityAlloyMixer;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketAlloyMixer implements IMessage {
	
	int x, y, z;

	public PacketAlloyMixer() { }
	
	public PacketAlloyMixer(BlockPos pos) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
	
	public static class Handler implements IMessageHandler<PacketAlloyMixer, IMessage> {

		@Override
		public IMessage onMessage(PacketAlloyMixer message, MessageContext ctx) {
			if (ctx.side == Side.SERVER) {
				TileEntity tile = ctx.getServerHandler().player.world.getTileEntity(new BlockPos(message.x, message.y, message.z));
				if (tile != null && tile instanceof TileEntityAlloyMixer) {
					((TileEntityAlloyMixer)tile).processing = !((TileEntityAlloyMixer)tile).processing;
				}
			}
			return null;
		}
		
	}
	

}
