package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SSPlayerListener extends PlayerListener {
    SetSpeed plugin;
	
    public SSPlayerListener(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }
    
	public void onPlayerMove(PlayerMoveEvent event) {
    	Player player = event.getPlayer();
        String pName = player.getName();
    	Double players = plugin.players.get(pName);
    	if (plugin.isSpeedOn.get(pName) == null) {
			plugin.isSpeedOn.put(pName, false);
		}
        if (!plugin.isSpeedOn.get(pName)) {
    	    if ((players) != 1) {
    		    if ((player.getInventory().getBoots().getTypeId() == (plugin.bootItem)) ||
    				(player.getInventory().getLeggings().getTypeId() == (plugin.legItem)) ||
    				(player.getInventory().getChestplate().getTypeId() == (plugin.chestItem)) ||
    				(player.getInventory().getHelmet().getTypeId() == (plugin.helmItem))) {
    				player.performCommand("speedon");
                    player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOn) + ".");
    				plugin.isSpeedOn.put(pName, true);
    			}
            }
    	}
    }
    
	public void onPlayerInteract(PlayerInteractEvent event) {
    	Player player = event.getPlayer();
        String pName = player.getName();
    	Action action = event.getAction();
    	Double players = plugin.players.get(pName);
    	
    	if ((players) == null) {
    		plugin.players.put(pName, 1.0);
    	}
    	if ((players) != 1) {
    		if (plugin.isSpeedOn.get(pName)) {
            	if (((action == Action.LEFT_CLICK_AIR) || 
            			(action == Action.LEFT_CLICK_BLOCK)) && 
            			(player.getItemInHand().getTypeId() == (plugin.speedItem))) {
            		player.performCommand("speedoff");
                    player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOff) + ".");
            	}
    		} else if (!plugin.isSpeedOn.get(pName)) {
        		if (((action == Action.LEFT_CLICK_AIR) || 
        			(action == Action.LEFT_CLICK_BLOCK)) && 
        			(player.getItemInHand().getTypeId() == (plugin.speedItem))) {
        		player.performCommand("speedon");
                player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOn) + ".");
        		}
        	}
        }
    }
	
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
        String pName = player.getName();
		Double players = plugin.players.get(pName);
		
		if ((players) != 1) {
			player.performCommand("speedoff");
            player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOff) + ".");
		}
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
        String pName = player.getName();
		Double players = plugin.players.get(pName);
		
		if ((players) != 1) {
			player.performCommand("speedoff");
            player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOff) + ".");
		}
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
        String pName = player.getName();
		plugin.players.put(pName,(double) 1);
		for(int i = 0; i < 501; i++) {
			if (plugin.checkPermissions(player, ("setspeed.perm." + i), true)) {
				plugin.cExecutor.setPlayersSpeed(player,(double)(i));
			}
		}
	}
}
