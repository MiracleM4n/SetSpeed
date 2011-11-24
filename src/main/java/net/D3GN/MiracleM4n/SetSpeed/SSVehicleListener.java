package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.entity.Player;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleListener;

public class SSVehicleListener extends VehicleListener {
    SetSpeed plugin;

    public SSVehicleListener(SetSpeed callbackPlugin) {
        plugin = callbackPlugin;
    }

    public void onVehicleExit(VehicleExitEvent event) {
        Player player = (Player) event.getExited();
        for(int i = 0; i < 501; i++) {
            if (plugin.checkPermissions(player, ("setspeed.perm." + i), true)) {
                plugin.cExecutor.setPlayersSpeed(player, (double)(i), true);
            }
        }
    }
}
