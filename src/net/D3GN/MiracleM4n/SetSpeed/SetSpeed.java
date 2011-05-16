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
	public static final String name = "InvinciWolf";
	SSPlayerListener playerListener = new SSPlayerListener(this);
	SSCommandExecutor commandexecutor = new SSCommandExecutor(this);
	
	//Hashes
	public static HashMap<Player, Double> players = new HashMap<Player, Double>();
	
	//Doubles
	public double speed = 1;
	
	//Strings
	public String notNumber = "That is not a number";
	public String negativeInterger = "Cant Use Negative Values";
	public String tooHigh = "Speed Too High";
	public String unKnown = "Weird... Not Able";
	public String noPermissions = "You don't have permissions to use this";
	public String speedSet = ("Speed Set To " + (speed));
	
	//Integers
	public Integer maxSpeed = 5;
	public Integer maxAdminSpeed = 10;
	    
	public static PermissionHandler Permissions;
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Highest, this);
		getCommand("setspeed").setExecutor(commandexecutor);
	        
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
		negativeInterger  = config.getString("NegValue", negativeInterger);
		tooHigh  = config.getString("TooHigh", tooHigh);
		unKnown  = config.getString("unKnownSpeed", unKnown);
		noPermissions  = config.getString("NoPerms", noPermissions);
		speedSet  = config.getString("speedSet", speedSet);
		
		//Intergers
		maxSpeed = config.getInt("Max_Mod_Speed", maxSpeed);
		maxAdminSpeed = config.getInt("Max_Admin_Speed", maxAdminSpeed);
	}
	    
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		System.out.println("[InvinciWolf]" + " version " + 
				pdfFile.getVersion() + " is disabled!");
	}
	   	
	private void setupPermissions() 
	{
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

