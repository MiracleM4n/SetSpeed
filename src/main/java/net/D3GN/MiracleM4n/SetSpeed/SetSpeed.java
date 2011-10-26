package net.D3GN.MiracleM4n.SetSpeed;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;

public class SetSpeed extends JavaPlugin {
    PluginManager pm;
    PluginDescriptionFile pdfFile;

	SSPlayerListener pListener = null;
	SSEntityListener eListener = null;
	SSCommandExecutor cExecutor = null;
    SSConfigListener cListener = null;
    SSVehicleListener vListener = null;

    // Permissions
    public PermissionHandler permissions;
    Boolean permissionsB = false;

    // GroupManager
    public AnjoPermissionsHandler gmPermissions;
    Boolean gmPermissionsB = false;
	
	// Configs
    YamlConfiguration config = null;
    File configF = null;

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
	public Integer speedInt = (int) speed;
	public Integer noSpeedValue = 0;
	public Integer hardMaxSpeed = 500;

	// Strings
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
	public String getWorld = "";
	public String ssWorld = "-world";
	public String speedSet = ((speedSetMessage) + " " + (speedInt));
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
            pListener = new SSPlayerListener(this);
	        eListener = new SSEntityListener(this);
	        cExecutor = new SSCommandExecutor(this);
            cListener = new SSConfigListener(this);
            vListener = new SSVehicleListener(this);

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
        } else {
            pm.disablePlugin(this);
        }
	}
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		System.out.println("[" + (pdfFile.getName()) + "]" + " version " + 
				pdfFile.getVersion() + " is disabled!");
	}

    protected void setupPermissions() {
        Plugin permTest = pm.getPlugin("Permissions");

        if(permTest != null) {
            permissions = ((Permissions) permTest).getHandler();
            permissionsB = true;
            System.out.println("[" + pdfFile.getName() + "] Permissions " + (permTest.getDescription().getVersion()) + " found hooking in.");
        } else {
            permissionsB = false;
            setupGroupManager();
        }
    }

    protected void setupGroupManager() {
        Plugin permTest = pm.getPlugin("GroupManager");

        if (permTest != null) {
            gmPermissionsB = true;
            System.out.println("[" + pdfFile.getName() + "] GroupManager " + (permTest.getDescription().getVersion()) + " found hooking in.");
        } else {
            gmPermissionsB = false;
            System.out.println("[" + pdfFile.getName() + "] No Legacy Permissions plugins were found defaulting to SuperPerms");
        }
    }

    protected Boolean getSpout() {
        Plugin permTest = pm.getPlugin("Spout");

        if (permTest != null) {
            System.out.println("[" + pdfFile.getName() + "] Spout " + (permTest.getDescription().getVersion()) + " found hooking in.");
            return true;
        } else {
            System.out.println("[" + pdfFile.getName() + "] Spout not found Disabling SetSpeed.");
            return false;
        }
    }

    public Boolean checkPermissions(Player player, String node, Boolean useOp) {
        if (permissionsB)
            if (permissions.has(player, node))
                return true;

        if (gmPermissionsB)
            if (gmPermissions.has(player, node))
                return true;

        if (useOp)
            if (player.isOp())
                return true;

        return player.hasPermission(node);

    }
}

