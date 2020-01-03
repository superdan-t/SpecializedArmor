package cc.sdspar.spar.energy;

import cc.sdspar.spar.item.ModItems;
import cc.sdspar.spar.util.ModConfig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.CapabilityEnergy;

public class EnergyHelper {
	
	public static boolean isChargeProvider(ItemStack stack) {
		if (stack.hasCapability(CapabilityEnergy.ENERGY, null) && stack.getCapability(CapabilityEnergy.ENERGY, null).canExtract()
				|| ModConfig.DEVELOPER_MODE && stack.getItem() == ModItems.ADVANCED_STICK) {
			return true;
		}
		return false;
	}
	
	public static long extractCharge(ItemStack stack, long charge, boolean simulate) {
		if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) {
			return stack.getCapability(CapabilityEnergy.ENERGY, null).extractEnergy((int) charge, simulate);
		} else if (ModConfig.DEVELOPER_MODE && stack.getItem() == ModItems.ADVANCED_STICK) {
			return charge;
		}
		return 0;
	}
	
}
