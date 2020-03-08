package cc.sdspar.spar.energy;

import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.CapabilityEnergy;

public class EnergyHelper {
	
	public static boolean isChargeProvider(ItemStack stack) {
		return stack.hasCapability(CapabilityEnergy.ENERGY, null) && stack.getCapability(CapabilityEnergy.ENERGY, null).canExtract();
	}
	
	public static long extractCharge(ItemStack stack, long charge, boolean simulate) {
		if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
			return stack.getCapability(CapabilityEnergy.ENERGY, null).extractEnergy((int) charge, simulate);
		}
		return 0;
	}
	
}
