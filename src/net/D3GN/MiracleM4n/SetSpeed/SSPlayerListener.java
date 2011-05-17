package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
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
    		if (plugin.players.get(player) != null)
    		{
    			int material = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ()).getTypeId();
    			if (material != 0 && material != 8 && material != 9 && material != 50 && material != 65)
    			{
    				Vector dir = player.getLocation().getDirection().multiply(((plugin.speed)*(0.3))/2).setY(0);
    				player.setVelocity(dir);
    				
    			}
    		} else  {
    			plugin.players.put(player, new Double(100));
    		}
    	} else if (!(player.isSneaking())) {
    		if (plugin.isSpeedOn) {
        		if (plugin.players.get(player) != null)
        		{
        			int material = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ()).getTypeId();
        			if (material != 0 && material != 8 && material != 9 && material != 50 && material != 65)
        			{
        				Vector dir = player.getLocation().getDirection().multiply(((plugin.speed)*(0.3))/2).setY(0);
        				player.setVelocity(dir);
        				
        			}
        		} else  {
        			plugin.players.put(player, new Double(100));
        		}
    		} else {
    			return;
    		}
    	}
    	if (plugin.noMove) {
    		event.setTo(event.getFrom());
		}
    }
    
	public void onPlayerInteract(PlayerInteractEvent event) {
    	Player player = event.getPlayer();
    	Action action = event.getAction();
    	
    	if ((plugin.speed) >= 2) {
        	if (plugin.isSpeedOn) {
            	if (((action == Action.LEFT_CLICK_AIR) || 
            			(action == Action.LEFT_CLICK_BLOCK)) && 
            			(player.getItemInHand().getTypeId() == (plugin.speedItem))) {
            		player.performCommand("speedoff");
            	}
        	} else if (((action == Action.LEFT_CLICK_AIR) || 
        			(action == Action.LEFT_CLICK_BLOCK)) && 
        			(player.getItemInHand().getTypeId() == (plugin.speedItem))) {
        		player.performCommand("speedon");
        	}
    	} else {
    		return;
    	}
    }
	
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if ((plugin.speed) >= 2) {
			player.performCommand("speedoff");
		}
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if ((plugin.speed) >= 2) {
			player.performCommand("speedoff");
		}
	}
}
