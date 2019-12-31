package cc.sdspar.spar.energy;

public class ConsumerEnergyStorage extends BaseModEnergyStorage {

	/**
	 * For mod consumers to handle energy
	 * @param charge
	 * @param capacity
	 * @param chargeRate
	 */
	public ConsumerEnergyStorage(long charge, long capacity, long chargeRate) {
		super(charge, capacity, chargeRate, 0);
		
	}
	
	/**
	 * For when the consumer needs to use its own energy
	 * @param charge The charge to use up
	 * @param simulate
	 * @return How much charge was (or would've been) used
	 */
	public long useCharge(long charge, boolean simulate) {
		long chargeUsed = Math.min(charge, this.charge);
		if (!simulate) {
			this.charge -= chargeUsed;
		}
		return chargeUsed;
	}
	
	/**
	 * For updating the charge level, typically for client/server exchange
	 * @param charge
	 * @return
	 */
	public void setCharge(long charge) {
		this.charge = Math.min(charge, capacity);
	}
	
	public void setCapacity(long capacity) {
		this.capacity = capacity;
		if (charge > capacity) {
			charge = capacity;
		}
	}

}
