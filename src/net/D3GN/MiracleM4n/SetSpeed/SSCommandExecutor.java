package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SSCommandExecutor implements CommandExecutor {
	
	private final SetSpeed plugin;
	
    public SSCommandExecutor(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }
    
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
    	if (!(sender instanceof Player)) {
			return true;
		}
    	Player player = ((Player) sender);
    	Double players = plugin.players.get(player);
    	
    	if (label.equalsIgnoreCase("setspeed")) {
			if(args.length == 0) {
				return false;
			}
			if(args.length == 1) {
				try {
					(plugin.speed) = new Double(args[0]);
					(plugin.speedPerm) = new Double (args[0]);
				} catch (NumberFormatException e) {
					(plugin.speedPerm) = 0;
					(plugin.speed) = 1;
					plugin.players.put(player,(plugin.speed));
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.notNumber) + ".");
				    return true;
				}
				if (((plugin.speed) == (1))) {
					if ((players) != 1) {
						plugin.speed = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedReset) + ".");
						return true;
					} else {
						return true;
					}
				}
				if ((SetSpeed.Permissions == null && player.isOp()) || 
						(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, "setspeed.admin"))) {
					if ((plugin.speed) > (plugin.hardMaxSpeed)) {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true; 
					}
					if ((plugin.speed) == (plugin.noSpeedValue)) {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noInterger) + ".");
						return true; 
					}
					if ((plugin.speed) < (plugin.noSpeedValue)) {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
						return true; 
					}
					if((plugin.speed) <= plugin.maxAdminSpeed) {
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
						return true;
					}
					if((plugin.speed) > (plugin.maxAdminSpeed)) {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					} else {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					}
				} else if ((SetSpeed.Permissions == null && (player.isOp() == false)) || 
						(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, "setspeed.mod"))) {
					if ((plugin.speed) > (plugin.hardMaxSpeed)) {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true; 
					}
					if ((plugin.speed) < (plugin.noSpeedValue)) {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
						return true; 
					}
					if((plugin.speed) <= (plugin.maxSpeed)) {
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
						return true;
					}
					if((plugin.speed) > (plugin.maxSpeed)) {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					} else {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.unKnown) + ".");
						return true;
					}
				} else if ((SetSpeed.Permissions == null && (player.isOp() == false)) || 
						(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, (plugin.speedPermValue)))) {
					if ((plugin.speed) > (plugin.hardMaxSpeed)) {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true; 
					}
					if ((plugin.speed) < (plugin.noSpeedValue)) {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
						return true; 
					}
					if((plugin.speed) <= (plugin.speedPerm)) {
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
						return true;
					}
					if((plugin.speed) > (plugin.speedPerm)) {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					} else {
						(plugin.speed) = 1;
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.unKnown) + ".");
						return true;
					}
				} else {
					(plugin.speed) = 1;
					plugin.players.put(player,(plugin.speed));
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noPermissions) + ".");
					return true;
				}
			} 
			if(args.length > 1) {
				//Going to be changed soon.....Maybe not...
				return true; 
			} else {
				return true;
			}
		} else if (label.equalsIgnoreCase("speedoff")) {
    		if(args.length == 0) {
    			if ((players) != 1) {
    				if (plugin.isSpeedOn = true) {
            			plugin.isSpeedOn = false;
            			player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOff) + ".");
            			return true;
    				}
    			}
    		}
    	} else if (label.equalsIgnoreCase("speedon")) {
    		if(args.length == 0) {
    			if ((players) != 1) {
        			plugin.isSpeedOn = true;
        			player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOn) + ".");
        			return true;
    			} else {
    				player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noSpeedSet) + ".");
    			}
    		}
    	}
    	return true;
	}
}

