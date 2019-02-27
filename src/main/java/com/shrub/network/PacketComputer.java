package com.shrub.network;

import com.shrub.tileentity.TileEntityComputer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class PacketComputer implements IMessage {
	
	int x;
	int y;
	int z;
	int func;
	int variable;
	int newValue;
	
	/**
	 * 
	 */
	public PacketComputer() { };
	
	/**
	 * Edit the property of a control chip in the computer. Recommended to use the method that allows text input, as it improves readability of the code.
	 * @deprecated
	 * @param x
	 * @param y
	 * @param z
	 * @param variable id
	 * @param newValue
	 */
	public PacketComputer(int x, int y, int z, int variable, int newValue) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.variable = variable;
		this.newValue = newValue;
	}
	
	/**
	 * Edit a property of the control chip in the computer by name.
	 * @param x
	 * @param y
	 * @param z
	 * @param variable
	 * @param newValue
	 */
	public PacketComputer(int x, int y, int z, String variable, int newValue) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.func = 0;
		this.variable = getVariableIdFromName(variable);
		this.newValue = newValue;
	}
	
	/**
	 * Removes a property of the control chip in the computer by name.
	 * @param x
	 * @param y
	 * @param z
	 * @param variable
	 */
	public PacketComputer(int x, int y, int z, String variable) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.func = 1;
		this.variable = getVariableIdFromName(variable);
		this.newValue = 0;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		func = buf.readInt();
		variable = buf.readInt();
		newValue = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(func);
		buf.writeInt(variable);
		buf.writeInt(newValue);
	}
	
	private int getVariableIdFromName(String variable) {
		if (variable == "clear")
			return 1;
		else if (variable == "drillSize")
			return 2;
		else if (variable == "augerSize")
			return 3;
		else if (variable == "plowSize")
			return 4;
		else
			return 0;
	}
	
	public static class Handler implements IMessageHandler<PacketComputer, IMessage> {

		@Override
		public IMessage onMessage(PacketComputer message, MessageContext ctx) {
			
				if (ctx.side == Side.CLIENT)
					return null;
				
				TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
				
				if (tileEntity != null && tileEntity instanceof TileEntityComputer) {
					
					TileEntityComputer computer = (TileEntityComputer) tileEntity;
					
					if (message.func == 0)
						computer.programValue(message.variable, message.newValue);
					else if (message.func == 1)
						computer.removeValue(message.variable);
					
				}
				
				return null;
				
		}
		
	}

}
