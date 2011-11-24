package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SSPlayerListener extends PlayerListener {
    SetSpeed plugin;

    public SSPlayerListener(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }
    
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String pName = player.getName();
        Action action = event.getAction();
        Double pSpeed = plugin.players.get(pName);

        if (pSpeed != 1)
            if (((action == Action.LEFT_CLICK_AIR) ||
                        (action == Action.LEFT_CLICK_BLOCK)) &&
                        (player.getItemInHand().getTypeId() == (plugin.speedItem)))
                if (plugin.isSpeedOn.get(pName)) {
                    player.performCommand("speedoff");
                    player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOff) + ".");
                } else {
                    player.performCommand("speedon");
                    player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOn) + ".");
                }
    }

    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        String pName = player.getName();
        Double pSpeed = plugin.players.get(pName);

        if (pSpeed != 1) {
            player.performCommand("speedoff");
            player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOff) + ".");
        }
    }

    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String pName = player.getName();
        Double pSpeed = plugin.players.get(pName);

        if (pSpeed != 1) {
            player.performCommand("speedoff");
            player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOff) + ".");
        }
    }

    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String pName = player.getName();

        plugin.players.put(pName, (double)1);
        plugin.isSpeedOn.put(pName, false);

        for (int i = 0; i < 501; i++)
            if (plugin.checkPermissions(player, ("setspeed.perm." + i), true))
                plugin.cExecutor.setPlayersSpeed(player,(double)(i), true);
    }
}
