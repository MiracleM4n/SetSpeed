package net.D3GN.MiracleM4n.SetSpeed;

import java.io.File;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class SetSpeed extends JavaPlugin {

	SSPlayerListener pListener = new SSPlayerListener(this);
	SSEntityListener eListener = new SSEntityListener(this);
	SSCommandExecutor cExecutor = new SSCommandExecutor(this);
    SSConfigListener cListener = new SSConfigListener(this);

    Configuration config = null;
	
	//Changeable
	//Strings
	 String notNumber = "That Is Not A Number";
	 String noInterger = "Cant Use 0";
	 String negativeInterger = "Cant Use Negative Values";
	 String tooHigh = "Speed Too High";
	 String unKnown = "Weird... Not Able To Set Speed";
	 String noPermissions = "You Don't Have Permissions To Use This";
	 String speedSetMessage = "Speed Set To";
	 String speedOff = "Speed is off";
	 String speedOn = "Speed is on";
	 String speedReset = "Speed reset";
	 String noSpeedSet = "No Speed value set";
	
	//Integers
	 Integer bootItem = 317;
	 Integer legItem = 316;
	 Integer chestItem = 315;
	 Integer helmItem = 314;
	 Integer speedItem = 50;
	 Integer maxSpeed = 5;
	 Integer maxAdminSpeed = 10;
	 Integer maxOtherSpeed = 10;
	 Integer maxWorldSpeed = 10;
	
	//Booleans
	 Boolean defaSpeed = false;
	 Boolean sneakAble = true;
	
	//Doubles
	 double defSpeed = 1.8;
	
	//Hashes
	 HashMap<Player, Double> players = new HashMap<Player, Double>();
	 HashMap<Player, Boolean> isSpeedOn = new HashMap<Player, Boolean>();
	
	//Doubles
	 double speed = 1;
	 double speedPerm = 1;
	
	//Integers
	 int speedInt = (int) speed;
	 int noSpeedValue = 0;
	 int hardMaxSpeed = 500;
	 int permSpeed = 1;
    
	//Strings
	 String getWorld = "";
	 String ssWorld = "-world";
	 String playerName = "";
	 String speedSet = ((speedSetMessage) + " " + (speedInt));
	 String speedPermValue = "setspeed." + (speedPerm);;
	
	    
	public static PermissionHandler Permissions;
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		PluginManager pm = getServer().getPluginManager();
        config = new Configuration(new File(getDataFolder(), "config.yml"));
		pm.registerEvent(Event.Type.PLAYER_MOVE, pListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, pListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_RESPAWN, pListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, pListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, pListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.VEHICLE_EXIT, pListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, eListener, Event.Priority.Highest, this);
		getCommand("setspeed").setExecutor(cExecutor);
		getCommand("speedoff").setExecutor(cExecutor);
		getCommand("speedon").setExecutor(cExecutor);
	        
		setupPermissions();
		
		System.out.println("[" + (pdfFile.getName()) + "]" + " version " + 
			pdfFile.getVersion() + " is enabled!");

		cListener.checkConfig();
		cListener.readConfig();
	}
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		System.out.println("[" + (pdfFile.getName()) + "]" + " version " + 
				pdfFile.getVersion() + " is disabled!");
	}
	   	
	private void setupPermissions() {
		Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
			
		if (SetSpeed.Permissions == null) {
			if (test != null) {
				SetSpeed.Permissions = ((Permissions)test).getHandler();
				System.out.println("[SetSpeed] Permissions found hooking in.");
			} else {
				System.out.println("[SetSpeed] Permissions plugin not found, defaulting to ops.txt.");
			}
		}
	}
}

