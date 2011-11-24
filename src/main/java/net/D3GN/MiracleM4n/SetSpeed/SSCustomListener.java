package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.inventory.InventoryClickEvent;
import org.getspout.spoutapi.event.inventory.InventoryListener;

public class SSCustomListener extends InventoryListener {
    SetSpeed plugin;

    public SSCustomListener(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }

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
