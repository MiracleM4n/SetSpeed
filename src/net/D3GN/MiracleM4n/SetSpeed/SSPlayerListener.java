package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerListener;
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
    				Vector dir = player.getLocation().getDirection().multiply((plugin.speed)*(0.3)).setY(0);
    				player.setVelocity(dir);
    				
    			}
    		} else  {
    			plugin.players.put(player, new Double(100));
    		}
    	}
    }
}
