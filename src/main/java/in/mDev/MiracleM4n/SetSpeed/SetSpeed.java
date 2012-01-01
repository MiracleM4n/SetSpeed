package in.mDev.MiracleM4n.SetSpeed;

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
    SSCustomListener cUListener = null;

    // Permissions
    PermissionHandler permissions;
    Boolean permissionsB = false;

    // GroupManager
    AnjoPermissionsHandler gmPermissions;
    Boolean gmPermissionsB = false;

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
            pListener = new SSPlayerListener(this);
            eListener = new SSEntityListener(this);
            cExecutor = new SSCommandExecutor(this);
            cListener = new SSConfigListener(this);
            vListener = new SSVehicleListener(this);
            cUListener = new SSCustomListener(this);

            pm.registerEvent(Event.Type.PLAYER_INTERACT, pListener, Event.Priority.Highest, this);
            pm.registerEvent(Event.Type.PLAYER_RESPAWN, pListener, Event.Priority.Highest, this);
            pm.registerEvent(Event.Type.PLAYER_QUIT, pListener, Event.Priority.Highest, this);
            pm.registerEvent(Event.Type.PLAYER_JOIN, pListener, Event.Priority.Highest, this);
            pm.registerEvent(Event.Type.VEHICLE_EXIT, pListener, Event.Priority.Highest, this);
            pm.registerEvent(Event.Type.ENTITY_DAMAGE, eListener, Event.Priority.Highest, this);
            pm.registerEvent(Event.Type.CUSTOM_EVENT, cUListener, Event.Priority.Highest, this);

            getCommand("setspeed").setExecutor(cExecutor);
            getCommand("speedoff").setExecutor(cExecutor);
            getCommand("speedon").setExecutor(cExecutor);

            setupPermissions();

            System.out.println("[" + (pdfFile.getName()) + "]" + " version " +
                pdfFile.getVersion() + " is enabled!");

            cListener.checkConfig();
            cListener.readConfig();

            for (Player player : getServer().getOnlinePlayers()) {
                players.put(player.getName(), 1.0);
                isSpeedOn.put(player.getName(), false);
            }

        } else
            pm.disablePlugin(this);
    }
    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        System.out.println("[" + (pdfFile.getName()) + "]" + " version " +
                pdfFile.getVersion() + " is disabled!");
    }

    void setupPermissions() {
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

    void setupGroupManager() {
        Plugin permTest = pm.getPlugin("GroupManager");

        if (permTest != null) {
            gmPermissionsB = true;
            System.out.println("[" + pdfFile.getName() + "] GroupManager " + (permTest.getDescription().getVersion()) + " found hooking in.");
        } else {
            gmPermissionsB = false;
            System.out.println("[" + pdfFile.getName() + "] No Legacy Permissions plugins were found defaulting to permissions.yml/SuperPerms.");
        }
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

