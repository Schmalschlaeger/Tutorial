package RealPVPGame;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import RealPVPGame.Addons.BungeeCoord;
import RealPVPGame.Listeners.DeathEvent;
import RealPVPGame.Listeners.InteractEvent;
import RealPVPGame.Listeners.JoinEvent;
import RealPVPGame.Listeners.LeaveEvent;

public class Main extends JavaPlugin{
	
   	 static FileConfiguration config = null;
     BungeeCoord bungee = new BungeeCoord(this);
	
	 private static File stats;
	 private static FileConfiguration statsConfig;
	 
	 private static File coords;
	 private static FileConfiguration coordsConfig;
	 
	 private static String WorldGame = statsConfig.getString("worldName:");
	
	public void onEnable() {
		config = getConfig();
		config.options().copyDefaults(true);
        saveDefaultConfig();
		
		stats = new File(getDataFolder(), "statsCache.yml");
		statsConfig = YamlConfiguration.loadConfiguration(stats);
    	saveCustomConfig(); 
    	
    	coords = new File(getDataFolder(), "coordinatenCache.yml");
    	coordsConfig = YamlConfiguration.loadConfiguration(coords);
    	saveCustomConfig2();
    	
    	Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new InteractEvent(), this);
	}
	
	@SuppressWarnings("deprecation")
	public void onDisable() {
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
		saveCustomConfig();
		saveDefaultConfig();
		saveCustomConfig2();
		
		bungee.connectToServer(config.getString("BungeeLobbyServer"), all);			
		}
	}
	
	public void saveCustomConfig(){
	   	try{
	   		statsConfig.save(stats);
	   	}catch(Exception e){
	      	e.printStackTrace();
	    	}
	   	}
	
	public void saveCustomConfig2(){
	   	try{
	   		coordsConfig.save(coords);
	   	}catch(Exception e){
	      	e.printStackTrace();
	    	}
	   	}
	
	public static String getWorldGame() {
		return WorldGame;
	}
	
	public static FileConfiguration getStatsConfiguration() {
		return statsConfig;
	}
	
	public static FileConfiguration getDefaultConfig() {
		return config;
	}
	
}
