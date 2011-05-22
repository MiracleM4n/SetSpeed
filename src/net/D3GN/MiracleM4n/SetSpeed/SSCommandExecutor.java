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
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true; 
					}
					if ((plugin.speed) == (plugin.noSpeedValue)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noInterger) + ".");
						return true; 
					}
					if ((plugin.speed) < (plugin.noSpeedValue)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
						return true; 
					}
					if((plugin.speed) <= plugin.maxAdminSpeed) {
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
						return true;
					}
					if((plugin.speed) > (plugin.maxAdminSpeed)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					}
				} else if ((SetSpeed.Permissions == null && (player.isOp() == false)) || 
						(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, "setspeed.mod"))) {
					if ((plugin.speed) > (plugin.hardMaxSpeed)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true; 
					}
					if ((plugin.speed) < (plugin.noSpeedValue)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
						return true; 
					}
					if((plugin.speed) <= (plugin.maxSpeed)) {
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
						return true;
					}
					if((plugin.speed) > (plugin.maxSpeed)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.unKnown) + ".");
						return true;
					}
				} else if ((SetSpeed.Permissions == null && (player.isOp() == false)) || 
						(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, (plugin.speedPermValue)))) {
					if ((plugin.speed) > (plugin.hardMaxSpeed)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true; 
					}
					if ((plugin.speed) < (plugin.noSpeedValue)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
						return true; 
					}
					if((plugin.speed) <= (plugin.speedPerm)) {
						plugin.players.put(player,(plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
						return true;
					}
					if((plugin.speed) > (plugin.speedPerm)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.unKnown) + ".");
						return true;
					}
				} else {
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noPermissions) + ".");
					return true;
				}
			} else if(args.length == 2) {
				(plugin.playerName) = new String (args[1]);
				try {
					(plugin.speed) = new Double(args[0]);
					(plugin.speedPerm) = new Double (args[0]);
				} catch (NumberFormatException e) {
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.notNumber) + ".");
				    return true;
				}
				if ((SetSpeed.Permissions == null && player.isOp()) || 
						(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, "setspeed.setothers"))) {
					if (plugin.getServer().getPlayer(plugin.playerName) != null) {
						if (((plugin.speed) == (1))) {
							if ((players) != 1) {
								plugin.speed = 1;
								plugin.players.put((plugin.getServer().getPlayer(plugin.playerName)),(plugin.speed));
								if (plugin.getServer().getPlayer(plugin.playerName) != null) {
									(plugin.getServer().getPlayer(plugin.playerName)).sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
									return true;
								} else {
									player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.playerName) + " is not online" + ".");
									return true;
								}
							} else {
								return true;
							}
						}
						if ((plugin.speed) > (plugin.hardMaxSpeed)) {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
							return true; 
						}
						if ((plugin.speed) == (plugin.noSpeedValue)) {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noInterger) + ".");
							return true; 
						}
						if ((plugin.speed) < (plugin.noSpeedValue)) {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
							return true; 
						}
						if((plugin.speed) <= plugin.maxOtherSpeed) {
							plugin.players.put((plugin.getServer().getPlayer(plugin.playerName)),(plugin.speed));
							if (plugin.getServer().getPlayer(plugin.playerName) != null) {
								(plugin.getServer().getPlayer(plugin.playerName)).sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
								return true;
							} else {
								player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.playerName) + " is not online" + ".");
								return true;
							}
						}
						if((plugin.speed) > (plugin.maxOtherSpeed)) {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
							return true;
						}
						if (plugin.getServer().getPlayer(plugin.playerName).isOnline()) {
							plugin.players.put((plugin.getServer().getPlayer(plugin.playerName)),(plugin.speed));
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + plugin.getServer().getPlayer(plugin.playerName).getName() + "'s " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
							(plugin.getServer().getPlayer(plugin.playerName)).sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
							return true;
						} else {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.playerName) + " is not online" + ".");
							return true;
						}
					} 
				} else {
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noPermissions) + ".");
					return true;
				}
			} else if(args.length == 3) {
				(plugin.getWorld) = new String (args[2]);
				(plugin.ssWorld) = new String (args[1]);
				try {
					(plugin.speed) = new Double(args[0]);
					(plugin.speedPerm) = new Double (args[0]);
				} catch (NumberFormatException e) {
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.notNumber) + ".");
				    return true;
				}
				if ((SetSpeed.Permissions == null && player.isOp()) || 
							(SetSpeed.Permissions != null && SetSpeed.Permissions.has(player, "setspeed.setworlds"))) {
					if ((plugin.ssWorld).equals("-world")) {
						if (((plugin.speed) == (1))) {
							if ((players) != 1) {
								plugin.speed = 1;
								if ((plugin.getWorld).equals("-all")) {
									for(Player playerList : (plugin.getServer().getOnlinePlayers())) {
										plugin.players.put(playerList,(plugin.speed));
									}
									plugin.getServer().broadcastMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedReset) + ".");
								} else if ((plugin.getServer().getWorld(plugin.getWorld)) != null) {
									for (Player playerList : (plugin.getServer().getWorld(plugin.getWorld).getPlayers())) {
										plugin.players.put(playerList,(plugin.speed));
										playerList.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedReset) + ".");
									}
								}
								return true;
							} else {
								return true;
							}
						}
						if ((plugin.speed) > (plugin.hardMaxSpeed)) {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
							return true; 
						}
						if ((plugin.speed) == (plugin.noSpeedValue)) {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noInterger) + ".");
							return true; 
						}
						if ((plugin.speed) < (plugin.noSpeedValue)) {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
							return true; 
						}
						if((plugin.speed) <= plugin.maxWorldSpeed) {
							if ((plugin.getWorld).equals("-all")) {
								for(Player playerList : (plugin.getServer().getOnlinePlayers())) {
									plugin.players.put(playerList,(plugin.speed));
								}
								plugin.getServer().broadcastMessage(ChatColor.RED + "[SetSpeed] " + "Speed Set To " + (plugin.speed) + " For All Players In The Server" + ".");
								return true;
							}
							if ((plugin.getServer().getWorld(plugin.getWorld)) != null) {
								for (Player playerList : (plugin.getServer().getWorld(plugin.getWorld).getPlayers())) {
									plugin.players.put(playerList,(plugin.speed));
									playerList.sendMessage(ChatColor.RED + "[SetSpeed] " + "Speed Set To " + (plugin.speed) + " For All Players In Your World" + ".");
								}
								player.sendMessage(ChatColor.RED + "[SetSpeed] " + "Speed Set To " + (plugin.speed) + " For All Players In " + (plugin.getServer().getWorld(plugin.getWorld).getName()) + ".");
								return true;
							} else {
								player.sendMessage(ChatColor.RED + "[SetSpeed] " + "World Not Found" + ".");
								return true;
							}
						}
						if((plugin.speed) > (plugin.maxWorldSpeed)) {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
							return true;
						}
					} else {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + "Please Use /setspeed # -world WORLDNAME" + ".");
						return true;
					}
				} else {
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noPermissions) + ".");
					return true;
				}
			} else {
				return true;
			}
		} else if (label.equalsIgnoreCase("speedoff")) {
    		if(args.length == 0) {
    			if ((plugin.players).containsKey(sender)) {
    				if (plugin.isSpeedOn.get(player) == true) {
    					plugin.isSpeedOn.put(player, false);
            			player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedOff) + ".");
            			return true;
    				}
    			}
    		}
    	} else if (label.equalsIgnoreCase("speedon")) {
    		if(args.length == 0) {
    			if ((plugin.players).containsKey(sender)) {
    				plugin.isSpeedOn.put(player, true);
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

