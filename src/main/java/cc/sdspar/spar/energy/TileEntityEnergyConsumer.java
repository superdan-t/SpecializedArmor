package cc.sdspar.spar.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileEntityEnergyConsumer extends TileEntity implements ITickable {
	
	public ItemStackHandler handler = new ItemStackHandler();
	public ConsumerEnergyStorage storage;
	
	/**
	 * For tile entities that are machines and consume energy
	 * @param slots
	 * 	Number of slots this machine has
	 * @param capacity
	 * 	Maximum level of charge
	 * @param chargeRate
	 * 	Maximum charge rate (how much charge can be added at once?)
	 */
	public TileEntityEnergyConsumer(int slots, int capacity, int chargeRate) {
		this.handler = new ItemStackHandler(slots);
		this.storage = new ConsumerEnergyStorage(0, capacity, chargeRate);
	}
	
	public TileEntityEnergyConsumer(ItemStackHandler handler, int capacity, int chargeRate) {
		this.handler = handler;
		this.storage = new ConsumerEnergyStorage(0, capacity, chargeRate);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		else if (capability == CapabilityEnergy.ENERGY) return true;
		else return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		else if (capability == CapabilityEnergy.ENERGY) return (T) this.storage;
		else if (storage.providesCapability(capability)) return storage.getCapability(capability);
		else return super.getCapability(capability, facing);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setTag("Storage", this.storage.serializeNBT());
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.storage.deserializeNBT(compound.getCompoundTag("Storage"));
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), 0, this.writeToNBT(new NBTTagCompound()));
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.setPos(packet.getPos());
		this.readFromNBT(packet.getNbtCompound());
	}

}
