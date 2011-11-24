package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class SSEntityListener extends EntityListener {
    SetSpeed plugin;

    public SSEntityListener(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }
    
    public void onEntityDamage(EntityDamageEvent event) {
        Entity attacker = null;
        if (event instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent subEvent = (EntityDamageByEntityEvent) event;
                attacker = subEvent.getDamager();
        }
        if (!(event.getEntity() instanceof Player)) return;
        if (attacker instanceof Entity) return;
        Player player = (Player) event.getEntity();
        String pName = player.getName();
        if (plugin.isSpeedOn.get(pName)) {
            if(plugin.players.get(pName) >= 2) {
                event.setCancelled(true);
            }
        } else if (player.isSneaking()) {
            if(plugin.players.get(pName) >= 2) {
                event.setCancelled(true);
            }
        }
    }
}
