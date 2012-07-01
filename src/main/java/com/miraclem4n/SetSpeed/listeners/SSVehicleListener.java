package com.miraclem4n.setspeed.listeners;

import com.miraclem4n.setspeed.SetSpeed;
import com.miraclem4n.setspeed.util.MiscUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class SSVehicleListener implements Listener {
    SetSpeed plugin;

    public SSVehicleListener(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        Player player = (Player) event.getExited();

        for(int i = 0; i < 501; i++)
            if (plugin.checkPermissions(player, ("setspeed.perm." + i), true))
                MiscUtil.setPlayersSpeed(player, (double)(i), true);
    }
}
