package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.util.Vector;

public class SSPlayerListener extends PlayerListener {
    
	private final SetSpeed plugin;
	
    public SSPlayerListener(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }
    
    @SuppressWarnings("static-access")
	public void onPlayerMove(PlayerMoveEvent event) {
    	Player player = event.getPlayer();
    	if (player.isSneaking()) {
    		if (plugin.sneakAble == true) {
    			if (plugin.players.get(player) != null) {
    				int material = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ()).getTypeId();
    				if (material != 0 && material != 8 && material != 9 && material != 50 && material != 65)
    				{
    					if (plugin.defaSpeed) {
    						Vector dir = player.getLocation().getDirection().multiply(((plugin.defSpeed)*(0.3))/2).setY(0);
    						player.setVelocity(dir);
    					} else {
    						Vector dir = player.getLocation().getDirection().multiply(((plugin.speed)*(0.3))/2).setY(0);
    						player.setVelocity(dir);
    					}
    				}
    			} else  {
    				plugin.players.put(player, new Double(100));
    			}
    		} else {
    			return;
    		}
    	} else if (!(player.isSneaking())) {
    		if (plugin.isSpeedOn) {
        		if (plugin.players.get(player) != null)
        		{
        			int material = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ()).getTypeId();
        			if (material != 0 && material != 8 && material != 9 && material != 50 && material != 65)
        			{
        				if (plugin.defaSpeed) {
        					Vector dir = player.getLocation().getDirection().multiply(((plugin.defSpeed)*(0.3))/2).setY(0.1);
            				player.setVelocity(dir);
        				} else {
            				Vector dir = player.getLocation().getDirection().multiply(((plugin.speed)*(0.3))/2).setY(0.1);
            				player.setVelocity(dir);
        				}
        			}
        		} else  {
        			plugin.players.put(player, new Double(100));
        		}
    		} else {
    			return;
    		}
    	}
    	if ((plugin.speed) != 1) {
    		if ((player.getInventory().getBoots().getTypeId() == (plugin.bootItem)) ||
    				(player.getInventory().getLeggings().getTypeId() == (plugin.legItem)) ||
    				(player.getInventory().getChestplate().getTypeId() == (plugin.chestItem)) ||
    				(player.getInventory().getHelmet().getTypeId() == (plugin.helmItem))) {
    			if (plugin.isSpeedOn == false) {
    				player.performCommand("speedon");
    				plugin.isSpeedOn = true;
    			} else {
    				return;
    			} 
    		}
    	} else {
    		return;
    	}
    }
    
	public void onPlayerInteract(PlayerInteractEvent event) {
    	Player player = event.getPlayer();
    	Action action = event.getAction();
    	
    	if ((plugin.speed) != 1) {
        	if (plugin.isSpeedOn) {
            	if (((action == Action.LEFT_CLICK_AIR) || 
            			(action == Action.LEFT_CLICK_BLOCK)) && 
            			(player.getItemInHand().getTypeId() == (plugin.speedItem))) {
            		player.performCommand("speedoff");
            	}
        	} else if (plugin.isSpeedOn == false) { 
        		if (((action == Action.LEFT_CLICK_AIR) || 
        			(action == Action.LEFT_CLICK_BLOCK)) && 
        			(player.getItemInHand().getTypeId() == (plugin.speedItem))) {
        		player.performCommand("speedon");
        		}
        	}
        } else {
        	return;
    	}
    }
	
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if ((plugin.speed) != 1) {
			player.performCommand("speedoff");
		}
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if ((plugin.speed) != 1) {
			player.performCommand("speedoff");
		}
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		for(int i = 0; i < 501; i++) {
			if ((SetSpeed.Permissions == null && player.isOp()) || 
					(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, ("setspeed.perm." + i)))) {
				(plugin.speed) = (i);
			}
		}
	}
	
	public void onVehicleExit(VehicleExitEvent event) {
		Player player = (Player) event.getExited();
		for(int i = 0; i < 501; i++) {
			if ((SetSpeed.Permissions == null && player.isOp()) || 
					(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, ("setspeed.perm." + i)))) {
				(plugin.speed) = (i);
			}
		}
	}
}
