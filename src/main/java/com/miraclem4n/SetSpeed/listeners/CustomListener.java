package com.miraclem4n.setspeed.listeners;

import com.miraclem4n.setspeed.SetSpeed;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.inventory.InventoryClickEvent;

public class CustomListener implements Listener {
    SetSpeed plugin;

    public CustomListener(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = event.getPlayer();
        String pName = player.getName();
        Double pSpeed = plugin.players.get(pName);

        if (plugin.isSpeedOn.get(pName) == null)
            plugin.isSpeedOn.put(pName, false);

        if (pSpeed == null)
            plugin.players.put(pName, 1.0);

        if (!plugin.isSpeedOn.get(pName))
            if (pSpeed != 1)
                if ((player.getInventory().getBoots().getTypeId() == (plugin.bootItem)) ||
                    (player.getInventory().getLeggings().getTypeId() == (plugin.legItem)) ||
                    (player.getInventory().getChestplate().getTypeId() == (plugin.chestItem)) ||
                    (player.getInventory().getHelmet().getTypeId() == (plugin.helmItem))) {
                    player.performCommand("speedon");
                    player.sendMessage(ChatColor.DARK_RED + "[SetSpeed] " + (plugin.speedOn) + ".");
                    plugin.isSpeedOn.put(pName, true);
                }
    }
}
