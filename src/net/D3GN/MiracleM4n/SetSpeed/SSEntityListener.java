package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class SSEntityListener extends EntityListener {
    
	private final SetSpeed plugin;
	
    public SSEntityListener(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }
    
    public void onEntityDamage(EntityDamageEvent event) {
    	Entity attacker = null;
    	if (event instanceof EntityDamageByEntityEvent) {
    			EntityDamageByEntityEvent subEvent = (EntityDamageByEntityEvent) event;
    			attacker = subEvent.getDamager();
    	} else if (event instanceof EntityDamageByProjectileEvent) {
    		EntityDamageByProjectileEvent subEvent = (EntityDamageByProjectileEvent) event;
    		attacker = subEvent.getDamager();
    	}
        if (!(event.getEntity() instanceof Player)) return;
        if (attacker instanceof Entity) return;
        if(plugin.speed >= 2) {
        	event.setCancelled(true);
        }
    }
}
