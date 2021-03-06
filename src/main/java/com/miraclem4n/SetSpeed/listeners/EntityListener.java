package com.miraclem4n.setspeed.listeners;

import com.miraclem4n.setspeed.SetSpeed;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityListener implements Listener {
    SetSpeed plugin;

    public EntityListener(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event instanceof EntityDamageByEntityEvent))
            return;

        EntityDamageByEntityEvent subEvent = (EntityDamageByEntityEvent) event;
        Entity attacker = subEvent.getDamager();

        if (!(event.getEntity() instanceof Player))
            return;

        if (attacker instanceof Entity)
            return;

        Player player = (Player) event.getEntity();
        String pName = player.getName();

        if (plugin.isSpeedOn.get(pName))
            if(plugin.players.get(pName) >= 2)
                event.setCancelled(true);

        else if (player.isSneaking())
            if(plugin.players.get(pName) >= 2)
                event.setCancelled(true);
    }
}
