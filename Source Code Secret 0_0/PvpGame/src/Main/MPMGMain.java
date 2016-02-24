package Main;

import org.bukkit.plugin.java.JavaPlugin;

import Events.BukkitEvents;
import Utils.GameManager;

public class MPMGMain extends JavaPlugin {

	//Global Server Variables
	private static String pluginVersion = "BETA -- 1.0";
	private static boolean debugMessages = true;
	private static int minPlayers = 2;
	private static int maxPlayers = 16;
	private static String getWorld = "world";

	//Load objects
	public static MPMGMain plugin;
	private GameManager gameManager = new GameManager(null);

	//Plugin is being enabled, lets do something.
	public void onEnable() {
		plugin = this;

		//Register BukkitEvent listener class
		new BukkitEvents(this);
		new GameManager(this);

		//Start Core logic of MiniGame
		gameManager.gameManagerTask();
		
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
	}

	//Getters used for global settings
	public static String getPluginVersion() {
		return pluginVersion;
	}
	public static String getWorld() {
		return getWorld;
	}
	public static boolean isDebugMessages() {
		return debugMessages;
	}
	public static int getMinPlayers() {
		return minPlayers;
	}
	public static int getMaxPlayers() {
		return maxPlayers;
	}

}