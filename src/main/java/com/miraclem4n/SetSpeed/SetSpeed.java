package com.miraclem4n.setspeed;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SetSpeed extends JavaPlugin {
    PluginManager pm;
    PluginDescriptionFile pdfFile;

    // Configs
    YamlConfiguration config = null;
    File configF = null;

    // Booleans
    Boolean defaSpeed = false;
    Boolean sneakAble = true;

    // Doubles
    Double defSpeed = 1.8;

    double speed = 1;
    double speedPerm = 1;

    // Integers
    Integer bootItem = 317;
    Integer legItem = 316;
    Integer chestItem = 315;
    Integer helmItem = 314;
    Integer speedItem = 50;
    Integer maxSpeed = 5;
    Integer maxAdminSpeed = 10;
    Integer maxOtherSpeed = 10;
    Integer maxWorldSpeed = 10;
    Integer noSpeedValue = 0;
    Integer hardMaxSpeed = 50;

    // Strings
    String notNumber = "That Is Not A Number";
    String noInteger = "Cant Use 0";
    String negativeInteger = "Cant Use Negative Values";
    String tooHigh = "Speed Too High";
    String unKnown = "Weird... Not Able To Set Speed";
    String noPermissions = "You Don't Have Permissions To Use This";
    String speedSetMessage = "Speed Set To";
    String speedOff = "Speed is off";
    String speedOn = "Speed is on";
    String speedReset = "Speed reset";
    String noSpeedSet = "No Speed value set";
    String speedSet = ((speedSetMessage) + " " + (int) speed);
    String speedPermValue = "setspeed." + (speedPerm);

    // Hashes
    HashMap<String, Double> players = new HashMap<String, Double>();
    HashMap<String, Boolean> isSpeedOn = new HashMap<String, Boolean>();

    public void onEnable() {
        pdfFile = getDescription();
        pm = getServer().getPluginManager();

        configF = new File(getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configF);

        if (getSpout()) {
            pm.registerEvents(new SSPlayerListener(this), this);
            pm.registerEvents(new SSEntityListener(this), this);
            pm.registerEvents(new SSCustomListener(this), this);
            pm.registerEvents(new SSVehicleListener(this), this);

            getCommand("setspeed").setExecutor(new SSCommandExecutor(this));
            getCommand("speedoff").setExecutor(new SSCommandExecutor(this));
            getCommand("speedon").setExecutor(new SSCommandExecutor(this));

            System.out.println("[" + (pdfFile.getName()) + "]" + " version " + pdfFile.getVersion() + " is enabled!");

            new SSConfigListener(this).checkConfig();
            new SSConfigListener(this).readConfig();

            for (Player player : getServer().getOnlinePlayers()) {
                players.put(player.getName(), 1.0);
                isSpeedOn.put(player.getName(), false);
            }

        } else
            pm.disablePlugin(this);
    }
    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        System.out.println("[" + (pdfFile.getName()) + "]" + " version " + pdfFile.getVersion() + " is disabled!");
    }

    protected Boolean getSpout() {
        Plugin permTest = pm.getPlugin("SpoutPlugin");

        if (permTest != null) {
            System.out.println("[" + pdfFile.getName() + "] SpoutPlugin " + (permTest.getDescription().getVersion()) + " found hooking in.");
            return true;
        } else {
            System.out.println("[" + pdfFile.getName() + "] SpoutPlugin not found Disabling SetSpeed.");
            return false;
        }
    }

    public Boolean checkPermissions(Player player, String node, Boolean useOp) {
        if (useOp)
            if (player.isOp())
                return true;

        return player.hasPermission(node);
    }
}
