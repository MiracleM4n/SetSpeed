package net.D3GN.MiracleM4n.SetSpeed;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Logger;

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

	protected final static Logger logger = Logger.getLogger("Minecraft");
	public static final String name = "SetSpeed";
	SSPlayerListener playerListener = new SSPlayerListener(this);
	SSEntityListener entityListener = new SSEntityListener(this);
	SSCommandExecutor commandexecutor = new SSCommandExecutor(this);
	
	
	//Changeable
	//Strings
	public String notNumber = "That Is Not A Number";
	public String noInterger = "Cant Use 0";
	public String negativeInterger = "Cant Use Negative Values";
	public String tooHigh = "Speed Too High";
	public String unKnown = "Weird... Not Able To Set Speed";
	public String noPermissions = "You Don't Have Permissions To Use This";
	public String speedSetMessage = "Speed Set To";
	public String speedOff = "Speed is off";
	public String speedOn = "Speed is on";
	public String speedReset = "Speed reset";
	public String noSpeedSet = "No Speed value set";
	
	//Integers
	public Integer bootItem = 317;
	public Integer legItem = 316;
	public Integer chestItem = 315;
	public Integer helmItem = 314;
	public Integer speedItem = 50;
	public Integer maxSpeed = 5;
	public Integer maxAdminSpeed = 10;
	
	//Booleans
	public Boolean defaSpeed = false;
	public Boolean sneakAble = true;
	
	//Doubles
	public double defSpeed = 1.8;
	
	
	
	//Non-Changeable
	//Booleans
	public Boolean isSpeedOn = false;
	//public Boolean noMove = false;
	
	//Hashes
	public HashMap<Player, Double> players = new HashMap<Player, Double>();

	
	//Doubles
	public double speed = 1;
	public double speedPerm = 1;
	
	//Integers
	public int speedInt = (int) speed;
	public int noSpeedValue = 0;
	public int hardMaxSpeed = 500;
	public int permSpeed = 1;
    
	//Strings
	public String speedSet = ((speedSetMessage) + " " + (speedInt));
	public String speedPermValue = "setspeed." + (speedPerm);
	public String permSpeedValue = "setspeed.perm." + (permSpeed);
	
	    
	public static PermissionHandler Permissions;
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_RESPAWN, playerListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.VEHICLE_EXIT, playerListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Highest, this);
		getCommand("setspeed").setExecutor(commandexecutor);
		getCommand("speedoff").setExecutor(commandexecutor);
		getCommand("speedon").setExecutor(commandexecutor);
	        
		setupPermissions();
		
		System.out.println("[" + (pdfFile.getName()) + "]" + " version " + 
			pdfFile.getVersion() + " is enabled!");
		
		moveFiles();
		readConfig();
	}
	
	public void moveFiles(){
		getDataFolder().mkdir();
		getDataFolder().setWritable(true);
		getDataFolder().setExecutable(true);
		extractFile("config.yml");
	}
	
	private void extractFile(String name) {
		PluginDescriptionFile pdfFile = getDescription();
		File actual = new File(getDataFolder(), name);
		if (!actual.exists()) {
			InputStream input = getClass().getResourceAsStream("/Config/" + name);
			if (input != null) {
				FileOutputStream output = null;
				try
				{
					output = new FileOutputStream(actual);
					byte[] buf = new byte[8192];
					int length = 0;
					while ((length = input.read(buf)) > 0) {
						output.write(buf, 0, length);
					}
					System.out.println("[" + (pdfFile.getName()) + "]" + "Default file written: " + name);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (input != null)
							input.close();
					} catch (Exception e) {
					} try {
						if (output != null)
							output.close();
					} catch (Exception e) {
					}
				}
			}
		}
	}
	
	public void readConfig() {
		Configuration config = new Configuration(new File(getDataFolder(), "config.yml"));
		config.load();
		
		//Strings
		notNumber  = config.getString("NotNumb", notNumber);
        noInterger = config.getString("noInt", noInterger);
		negativeInterger  = config.getString("NegValue", negativeInterger);
		tooHigh  = config.getString("TooHigh", tooHigh);
		unKnown  = config.getString("unKnownSpeed", unKnown);
		noPermissions  = config.getString("NoPerms", noPermissions);
		speedSet  = config.getString("speedSet", speedSet);
        speedOff = config.getString("speedOff", speedOff);
        speedOn = config.getString("speedOn", speedOn);
		speedReset = config.getString("speedReset", speedReset);
        noSpeedSet = config.getString("noSpeedSet", noSpeedSet);
        
		//Intergers
		maxSpeed = config.getInt("Max_Mod_Speed", maxSpeed);
		maxAdminSpeed = config.getInt("Max_Admin_Speed", maxAdminSpeed);
        speedItem = config.getInt("Speed_ItemNumber", speedItem);
        bootItem = config.getInt("Boot_ItemNumber", bootItem);
        legItem = config.getInt("Leg_ItemNumber", legItem);
        chestItem = config.getInt("Chest_ItemNumber", chestItem);
        helmItem = config.getInt("Head_ItemNumber", helmItem);
        
        //Booleans
        defaSpeed = config.getBoolean("DefSpeed", defaSpeed);
        sneakAble = config.getBoolean("Sneak_Enabled", sneakAble);
        
        //Doubles
        defSpeed = config.getDouble("Default_Speed", defSpeed);
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

