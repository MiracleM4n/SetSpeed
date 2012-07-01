package com.miraclem4n.setspeed;

import java.io.File;
import java.util.HashMap;

import com.miraclem4n.setspeed.commands.SetSpeedCommand;
import com.miraclem4n.setspeed.configs.ConfigListener;
import com.miraclem4n.setspeed.listeners.CustomListener;
import com.miraclem4n.setspeed.listeners.EntityListener;
import com.miraclem4n.setspeed.listeners.PlayerListener;
import com.miraclem4n.setspeed.listeners.VehicleListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SetSpeed extends JavaPlugin {
    public PluginManager pm;
    public PluginDescriptionFile pdfFile;

    // Configs
    public YamlConfiguration config = null;
    public File configF = null;

    // Booleans
    public Boolean defaSpeed = false;
    public Boolean sneakAble = true;

    // Doubles
    public Double defSpeed = 1.8;

    public double speed = 1;
    public double speedPerm = 1;

    // Integers
    public Integer bootItem = 317;
    public Integer legItem = 316;
    public Integer chestItem = 315;
    public Integer helmItem = 314;
    public Integer speedItem = 50;
    public Integer maxSpeed = 5;
    public Integer maxAdminSpeed = 10;
    public Integer maxOtherSpeed = 10;
    public Integer maxWorldSpeed = 10;
    public Integer noSpeedValue = 0;
    public Integer hardMaxSpeed = 50;

    // Strings
    public String notNumber = "That Is Not A Number";
    public String noInteger = "Cant Use 0";
    public String negativeInteger = "Cant Use Negative Values";
    public String tooHigh = "Speed Too High";
    public String unKnown = "Weird... Not Able To Set Speed";
    public String noPermissions = "You Don't Have Permissions To Use This";
    public String speedSetMessage = "Speed Set To";
    public String speedOff = "Speed is off";
    public String speedOn = "Speed is on";
    public String speedReset = "Speed reset";
    public String noSpeedSet = "No Speed value set";
    public String speedSet = ((speedSetMessage) + " " + (int) speed);
    public String speedPermValue = "setspeed." + (speedPerm);

    // Hashes
    public HashMap<String, Double> players = new HashMap<String, Double>();
    public HashMap<String, Boolean> isSpeedOn = new HashMap<String, Boolean>();

    public void onEnable() {
        pdfFile = getDescription();
        pm = getServer().getPluginManager();

        configF = new File(getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configF);

        if (getSpout()) {
            pm.registerEvents(new PlayerListener(this), this);
            pm.registerEvents(new EntityListener(this), this);
            pm.registerEvents(new CustomListener(this), this);
            pm.registerEvents(new VehicleListener(this), this);

            getCommand("setspeed").setExecutor(new SetSpeedCommand(this));
            getCommand("speedoff").setExecutor(new SetSpeedCommand(this));
            getCommand("speedon").setExecutor(new SetSpeedCommand(this));

            System.out.println("[" + (pdfFile.getName()) + "]" + " version " + pdfFile.getVersion() + " is enabled!");

            new ConfigListener(this).checkConfig();
            new ConfigListener(this).readConfig();

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
        Plugin permTest = pm.getPlugin("Spout");

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

