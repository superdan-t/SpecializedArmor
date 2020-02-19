package cc.sdspar.spar.event;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ArmorEventHandler {
/*	
	@SubscribeEvent
	public void playerDamaged(LivingDamageEvent event) {
		
	}
	
	@SubscribeEvent
	public void playerHurt(LivingHurtEvent e) {

	}
	
	@SubscribeEvent
	public void playerKnockback(LivingKnockBackEvent e) {
		//e.setStrength(30000.0F);
	}
*/
	@SubscribeEvent
	public void join(EntityJoinWorldEvent e) {
		
	}

}
