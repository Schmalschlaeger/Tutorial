package mainp.thenetwork.Economy;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import mainp.thenetwork.MainManager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Economy {
	
	private static final Logger logger = Logger.getLogger("Minecraft");
	private MainManager plugin;
	private static File config;
    public FileConfiguration customConfig;
	 
	public Economy(MainManager instance) {
	    this.plugin = instance;
	}
	
	public void createMoneyFolder(String name) {
		config = new File(plugin.getDataFolder(), name + ".yml");
		customConfig = YamlConfiguration.loadConfiguration(config);
		customConfig.options().copyDefaults(true);
    	saveCustomConfig();
	}
	
	public void saveCustomConfig(){
	   	try{
	   		customConfig.save(config);
	   	}catch(Exception e){
	      	e.printStackTrace();
	    	}
	   	}
	
	public void getPlayerMoney(String target) {
		if (customConfig.contains("Money." + target)) {
			customConfig.get("Money." + target);
		}else {
			logger.log(Level.WARNING, "Cant find the player " + target + " his money!");
		}
	}
	
	public void addMoney(String target, double money) {
		if (customConfig.contains("Money." + target)) {
			customConfig.set("Money." + target, customConfig.get("Money." + target));
		}
	}

}
