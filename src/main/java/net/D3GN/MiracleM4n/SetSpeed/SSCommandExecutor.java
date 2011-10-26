package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SSCommandExecutor implements CommandExecutor {
	SetSpeed plugin;
	
    public SSCommandExecutor(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }
    
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
    	if (!(sender instanceof Player)) {
			return true;
		}

    	Player player = ((Player) sender);
        String pName = player.getName();
    	Double players = plugin.players.get(pName);
    	
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
						plugin.players.put(pName, (plugin.speed));
                        setPlayersSpeed(player, plugin.players.get(pName));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedReset) + ".");
						return true;
					} else {
						return true;
					}
				}
				if (plugin.checkPermissions(player, "setspeed.admin", true)) {
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
						plugin.players.put(pName, (plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
                        setPlayersSpeed(player, plugin.players.get(pName));
						return true;
					}
					if((plugin.speed) > (plugin.maxAdminSpeed)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					}
				} else if (plugin.checkPermissions(player, "setspeed.mod", true)) {
					if ((plugin.speed) > (plugin.hardMaxSpeed)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					}
					if ((plugin.speed) < (plugin.noSpeedValue)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
						return true;
					}
					if((plugin.speed) <= (plugin.maxSpeed)) {
						plugin.players.put(pName, (plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
                        setPlayersSpeed(player, plugin.players.get(pName));
						return true;
					}
					if((plugin.speed) > (plugin.maxSpeed)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.unKnown) + ".");
						return true;
					}
				} else if (plugin.checkPermissions(player, plugin.speedPermValue, true)) {
					if ((plugin.speed) > (plugin.hardMaxSpeed)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
						return true;
					}
					if ((plugin.speed) < (plugin.noSpeedValue)) {
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.negativeInterger) + ".");
						return true;
					}
					if((plugin.speed) <= (plugin.speedPerm)) {
						plugin.players.put(pName, (plugin.speed));
						player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
                        setPlayersSpeed(player, plugin.players.get(pName));
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
				Player targetN = plugin.getServer().getPlayer(args[1]);
                String targetNa = targetN.getName();
				try {
					(plugin.speed) = new Double(args[0]);
					(plugin.speedPerm) = new Double (args[0]);
				} catch (NumberFormatException e) {
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.notNumber) + ".");
				    return true;
				}
				if (plugin.checkPermissions(player, "setspeed.setothers", true)) {
					if (targetN != null) {
						if (((plugin.speed) == (1))) {
							if ((players) != 1) {
								plugin.speed = 1;
								plugin.players.put(targetNa, (plugin.speed));
								if (targetN != null) {
									(targetN).sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
                                    setPlayersSpeed(targetN, plugin.players.get(targetNa));
                                    return true;
								} else {
									player.sendMessage(ChatColor.RED + "[SetSpeed] " + targetNa + " is not online" + ".");
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
							plugin.players.put(targetNa, (plugin.speed));
							if (targetN != null) {
								(targetN).sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
                                setPlayersSpeed(targetN, plugin.players.get(targetNa));
								return true;
							} else {
								player.sendMessage(ChatColor.RED + "[SetSpeed] " + targetNa + " is not online" + ".");
								return true;
							}
						}
						if((plugin.speed) > (plugin.maxOtherSpeed)) {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.tooHigh) + ".");
							return true;
						}
						if (targetN.isOnline()) {
							plugin.players.put(targetNa, (plugin.speed));
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + targetNa + "'s " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
							(targetN).sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedSet) + " To " + (plugin.speed) + ".");
                            setPlayersSpeed(targetN, plugin.players.get(targetNa));
							return true;
						} else {
							player.sendMessage(ChatColor.RED + "[SetSpeed] " + targetNa + " is not online" + ".");
							return true;
						}
					} 
				} else {
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.noPermissions) + ".");
					return true;
				}
			} else if(args.length == 3) {
				(plugin.getWorld) = args[2];
				(plugin.ssWorld) = args[1];
				try {
					(plugin.speed) = new Double(args[0]);
					(plugin.speedPerm) = new Double (args[0]);
				} catch (NumberFormatException e) {
					player.sendMessage(ChatColor.RED + "[SetSpeed] " + (plugin.notNumber) + ".");
				    return true;
				}
				if (plugin.checkPermissions(player, "setspeed.setworlds", true)) {
					if ((plugin.ssWorld).equals("-world")) {
						if (((plugin.speed) == (1))) {
							if ((players) != 1) {
								plugin.speed = 1;
								if ((plugin.getWorld).equals("-all")) {
									for(Player playerList : (plugin.getServer().getOnlinePlayers())) {
                                        String pLName = playerList.getName();
										plugin.players.put(pLName, plugin.speed);
                                        setPlayersSpeed(playerList, plugin.players.get(pName));
									}
									plugin.getServer().broadcastMessage(ChatColor.RED + "[SetSpeed] " + (plugin.speedReset) + ".");
								} else if ((plugin.getServer().getWorld(plugin.getWorld)) != null) {
									for (Player playerList : (plugin.getServer().getWorld(plugin.getWorld).getPlayers())) {
                                        String pLName = playerList.getName();
                                        plugin.players.put(pLName, (plugin.speed));
                                        setPlayersSpeed(playerList, plugin.players.get(pLName));
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
                                    String pLName = playerList.getName();
                                    plugin.players.put(pLName, (plugin.speed));
                                    setPlayersSpeed(playerList, plugin.players.get(pLName));
								}
								plugin.getServer().broadcastMessage(ChatColor.RED + "[SetSpeed] " + "Speed Set To " + (plugin.speed) + " For All Players In The Server" + ".");
								return true;
							}
							if ((plugin.getServer().getWorld(plugin.getWorld)) != null) {
								for (Player playerList : (plugin.getServer().getWorld(plugin.getWorld).getPlayers())) {
                                    String pLName = playerList.getName();
                                    plugin.players.put(pLName, (plugin.speed));
                                    setPlayersSpeed(playerList, plugin.players.get(pLName));
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
    			if (plugin.players.get(pName) != null) {
    				if (plugin.isSpeedOn.get(pName)) {
    					plugin.isSpeedOn.put(pName, false);
                        setPlayersSpeed(player, 1.0);
            			return true;
    				}
    			}
    		}
    	} else if (label.equalsIgnoreCase("speedon")) {
            if(args.length == 0) {
    			if (plugin.players.get(pName) != null) {
    				plugin.isSpeedOn.put(pName, true);
                    setPlayersSpeed(player, plugin.players.get(pName));
                    return true;
    			}
    		}
    	}
    	return true;
	}

    public void setPlayersSpeed(Player player, Double speed) {
        SpoutPlayer sPlayer = (SpoutPlayer)player;
        sPlayer.setWalkingMultiplier(speed);
        sPlayer.setSwimmingMultiplier(speed);
        //sPlayer.setGravityMultiplier(1/speed);
        sPlayer.setAirSpeedMultiplier(speed);
    }
}

