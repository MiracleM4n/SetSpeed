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
    public Integer noSpeedValue = 0;
    private Integer hardMaxSpeed = 500;
    
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
    	if (!(sender instanceof Player)) {
			return true;
		}
    	Player player = ((Player) sender);
    	if (label.equalsIgnoreCase("setspeed")) {
			if(args.length == 0) {
				return false;
			}
			if(args.length > 0) {
				try {
					(plugin.speed) = new Double(args[0]);
				} catch (NumberFormatException e) {
					(plugin.speed) = 1;
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.notNumber) + ".");
				    return true;
				}
				if ((SetSpeed.Permissions == null && player.isOp()) || 
						(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, "setspeed.admin"))) {
					if ((plugin.speed) > hardMaxSpeed) {
						(plugin.speed) = 1;
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true; 
					}
					if ((plugin.speed) < noSpeedValue) {
						(plugin.speed) = 1;
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
						return true; 
					}
					if((plugin.speed) <= plugin.maxAdminSpeed) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
						return true;
					}
					if((plugin.speed) > plugin.maxAdminSpeed) {
						(plugin.speed) = 1;
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					} else {
						(plugin.speed) = 1;
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					}
				} else if ((SetSpeed.Permissions == null && (player.isOp() == false)) || 
						(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, "setspeed.mod"))) {
					if ((plugin.speed) > hardMaxSpeed) {
						(plugin.speed) = 1;
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true; 
					}
					if ((plugin.speed) < noSpeedValue) {
						(plugin.speed) = 1;
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
						return true; 
					}
					if((plugin.speed) <= plugin.maxSpeed) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
						return true;
					}
					if((plugin.speed) > plugin.maxSpeed) {
						(plugin.speed) = 1;
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					} else {
						(plugin.speed) = 1;
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.unKnown) + ".");
						return true;
					}
				} else {
					(plugin.speed) = 1;
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noPermissions) + ".");
					return true;
				}
			} else {
				return true;
			}
		}
    	return true; 
	}
}

