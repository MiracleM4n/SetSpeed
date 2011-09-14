package net.D3GN.MiracleM4n.SetSpeed;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.util.config.Configuration;

import java.io.File;

public class SSConfigListener {
    SetSpeed plugin;

       public SSConfigListener(SetSpeed  plugin) {
		       this.plugin = plugin;
       }

       Boolean hasChanged = false;

       public void checkConfig() {
		       PluginDescriptionFile pdfFile = plugin.getDescription();
		       Configuration config = plugin.config;
		       config.load();
               /*
		       if (config.getProperty("NotNumb") == null) {
		           config.setProperty("NotNumb", plugin.notNumber);
		           hasChanged = true;
		       }
		       if (config.getProperty("NegValue") == null) {
		           config.setProperty("NegValue", plugin.negativeInterger);
		           hasChanged = true;
		       }
		       if (config.getProperty("TooFar") == null) {
		           config.setProperty("TooFar", plugin.farAway);
		           hasChanged = true;
		       }
		       if (config.getProperty("CantFind") == null) {
		           config.setProperty("CantFind", plugin.cantFind);
		           hasChanged = true;
		       }
		       if (config.getProperty("NoPerms") == null) {
		           config.setProperty("NoPerms", plugin.noPermissions);
		           hasChanged = true;
		       }
		       if (config.getProperty("ItemKill") == null) {
		           config.setProperty("ItemKill", plugin.itemKill);
		           hasChanged = true;
		       }
		       if (config.getProperty("Max_Kill_Radius") == null) {
		           config.setProperty("Max_Kill_Radius", plugin.maxKillRadius);
		           hasChanged = true;
		       }
		       if (config.getProperty("Max_Admin_Kill_Radius") == null) {
		           config.setProperty("Max_Admin_Kill_Radius", plugin.maxAdminKillRadius);
		           hasChanged = true;
		       }
		       if (hasChanged) {
		           config.setHeader("#DropClear  Configuration File, Enjoy!!");
		           plugin.console.log(Level.INFO, "[" + pdfFile.getName() + "]" + " config.yml has been updated.");
		           config.save();
		       }
		       */
       }

	
	    public void readConfig() {
		    Configuration config = plugin.config;
		    config.load();
		    /*
		    //Strings
		    plugin.notNumber  = config.getString("NotNumb", plugin.notNumber);
		    plugin.noInterger = config.getString("noInt", plugin.noInterger);
		    plugin.negativeInterger  = config.getString("NegValue", plugin.negativeInterger);
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
		    */
	    }
   }
