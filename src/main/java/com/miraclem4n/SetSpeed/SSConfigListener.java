package com.miraclem4n.setspeed;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

public class SSConfigListener {
    SetSpeed plugin;

    public SSConfigListener(SetSpeed  plugin) {
        this.plugin = plugin;
    }

    Boolean hasChanged = false;

    void checkConfig() {
        YamlConfiguration config = plugin.config;
        YamlConfigurationOptions configO = config.options();

        //Strings
        checkOption(config, "NotNumb", plugin.notNumber);
        checkOption(config, "noInt", plugin.noInteger);
        checkOption(config, "NegValue", plugin.negativeInteger);
        checkOption(config, "TooHigh", plugin.tooHigh);
        checkOption(config, "unKnownSpeed", plugin.unKnown);
        checkOption(config, "NoPerms", plugin.noPermissions);
        checkOption(config, "speedSet", plugin.speedSetMessage);
        checkOption(config, "speedOff", plugin.speedOff);
        checkOption(config, "speedOn", plugin.speedOn);
        checkOption(config, "speedReset", plugin.speedReset);
        checkOption(config, "noSpeedSet", plugin.noSpeedSet);
            
        //Intergers
        checkOption(config, "Max_Mod_Speed", plugin.maxSpeed);
        checkOption(config, "Max_Admin_Speed", plugin.maxAdminSpeed);
        checkOption(config, "Max_SetOther_Speed", plugin.maxOtherSpeed);
        checkOption(config, "Max_SetWorld_Speed", plugin.maxWorldSpeed);
        checkOption(config, "Speed_ItemNumber", plugin.speedItem);
        checkOption(config, "Boot_ItemNumber", plugin.bootItem);
        checkOption(config, "Leg_ItemNumber", plugin.legItem);
        checkOption(config, "Chest_ItemNumber", plugin.chestItem);
        checkOption(config, "Head_ItemNumber", plugin.helmItem);
            
        //Booleans
        checkOption(config, "DefSpeed", plugin.defaSpeed);
        checkOption(config, "Sneak_Enabled", plugin.sneakAble);
            
        //Doubles
        checkOption(config, "Default_Speed", plugin.defSpeed);

        if (hasChanged) {
            configO.header("SetSpeed  Configuration File, Enjoy!!");

            System.out.println("[" + plugin.pdfFile.getName() + "]" + " config.yml has been updated.");

            try {
                config.save(plugin.configF);
            } catch (IOException ignored) {}
        }
    }


    void readConfig() {
        YamlConfiguration config = plugin.config;

        //Strings
        plugin.notNumber  = config.getString("NotNumb", plugin.notNumber);
        plugin.noInteger = config.getString("noInt", plugin.noInteger);
        plugin.negativeInteger  = config.getString("NegValue", plugin.negativeInteger);
        plugin.tooHigh  = config.getString("TooHigh", plugin.tooHigh);
        plugin.unKnown  = config.getString("unKnownSpeed", plugin.unKnown);
        plugin.noPermissions  = config.getString("NoPerms", plugin.noPermissions);
        plugin.speedSet  = config.getString("speedSet", plugin.speedSet);
        plugin.speedOff = config.getString("speedOff", plugin.speedOff);
        plugin.speedOn = config.getString("speedOn", plugin.speedOn);
        plugin.speedReset = config.getString("speedReset", plugin.speedReset);
        plugin.noSpeedSet = config.getString("noSpeedSet", plugin.noSpeedSet);
            
        //Intergers
        plugin.maxSpeed = config.getInt("Max_Mod_Speed", plugin.maxSpeed);
        plugin.maxAdminSpeed = config.getInt("Max_Admin_Speed", plugin.maxAdminSpeed);
        plugin.maxOtherSpeed = config.getInt("Max_SetOther_Speed", plugin.maxOtherSpeed);
        plugin.maxWorldSpeed = config.getInt("Max_SetWorld_Speed", plugin.maxWorldSpeed);
        plugin.speedItem = config.getInt("Speed_ItemNumber", plugin.speedItem);
        plugin.bootItem = config.getInt("Boot_ItemNumber", plugin.bootItem);
        plugin.legItem = config.getInt("Leg_ItemNumber", plugin.legItem);
        plugin.chestItem = config.getInt("Chest_ItemNumber", plugin.chestItem);
        plugin.helmItem = config.getInt("Head_ItemNumber", plugin.helmItem);
            
        //Booleans
        plugin.defaSpeed = config.getBoolean("DefSpeed", plugin.defaSpeed);
        plugin.sneakAble = config.getBoolean("Sneak_Enabled", plugin.sneakAble);
            
        //Doubles
        plugin.defSpeed = config.getDouble("Default_Speed", plugin.defSpeed);
    }

    void checkOption(YamlConfiguration config, String option, Object defValue) {
        if (!config.isSet(option)) {
            config.set(option, defValue);
            hasChanged = true;
        }
    }
}
