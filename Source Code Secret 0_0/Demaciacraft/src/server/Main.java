package server;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import server.Achievements.AchievementCommand;
import server.Achievements.AchievementsMenu;
import server.Achievements.JoinAchievement;
import server.Events.BasicEvents;
import server.Events.JoinEvent;
import server.Inventory.PlayerVisibility;
import server.Systems.InfoBot;

public class Main extends JavaPlugin{
	
	public static String MainWorld = "Custom2";
	
	public ArrayList<String> usingClock;
	
    private static File infoBConfigr;
    public static FileConfiguration infoBotConfig;
    
    private static File achievementC;
    public static FileConfiguration AchievementConfig;
	
	public void onEnable() {
		infoBConfigr = new File(getDataFolder(), "infoBotData.yml");
		infoBotConfig = YamlConfiguration.loadConfiguration(infoBConfigr);
    	saveCustomPlayerConfig();
    	
    	achievementC = new File(getDataFolder(), "achievements.yml");
    	AchievementConfig = YamlConfiguration.loadConfiguration(achievementC);
    	saveCustomPlayerConfig();
    	
		Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerVisibility(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BasicEvents(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new InfoBot(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new AchievementsMenu(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinAchievement(), this);
	
	    this.usingClock = new ArrayList<String>();
	    
	    getCommand("infobot").setExecutor(new InfoBot(this));
	    getCommand("achievements").setExecutor(new AchievementCommand());
	    
	    //getCommand("balloon").setExecutor(new Balloon());
	}
	
	 public static void saveCustomPlayerConfig(){
	    	try{
	    		infoBotConfig.save(infoBConfigr);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		}
	    	}
	 
	 public static void saveCustomAchievementConfig(){
	    	try{
	    		AchievementConfig.save(achievementC);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		}
	    	}

}
