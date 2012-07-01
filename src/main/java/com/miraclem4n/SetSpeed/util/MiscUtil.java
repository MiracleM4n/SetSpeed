package com.miraclem4n.setspeed.util;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MiscUtil {
    public static void setPlayersSpeed(Player player, double speed, Boolean flyCheck) {
        SpoutPlayer sPlayer = (SpoutPlayer)player;
        sPlayer.setWalkingMultiplier(speed);
        sPlayer.setSwimmingMultiplier(speed);
        //sPlayer.setGravityMultiplier(1/speed*9);
        sPlayer.setAirSpeedMultiplier(speed);
        sPlayer.setCanFly(flyCheck);
    }
}
