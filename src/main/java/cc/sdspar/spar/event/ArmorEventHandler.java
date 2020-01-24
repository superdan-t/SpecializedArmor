package cc.sdspar.spar.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ArmorEventHandler {
	
	@SubscribeEvent
	public void playerDamaged(LivingDamageEvent event) {
		System.out.println("An entity was damaged");
	}
	
	@SubscribeEvent
	public void playerHurt(LivingHurtEvent e) {
		if (e.getEntity() instanceof EntityPlayer) {
			e.setAmount(0.01F);
		}
	}
	
	@SubscribeEvent
	public void playerKnockback(LivingKnockBackEvent e) {
		//e.setStrength(30000.0F);
	}

}
