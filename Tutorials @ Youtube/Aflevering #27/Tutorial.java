package tuts;


import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Tutorial extends JavaPlugin implements Listener {
	
	API h = new API(this);
	
	File customConfig;
	FileConfiguration newCustomConfig;
	
	public void onEnable() {
		customConfig = new File(getDataFolder(), "warps.yml");
		newCustomConfig = YamlConfiguration.loadConfiguration(customConfig);
		saveCustomConfig();
  
        Bukkit.getServer().getPluginManager().registerEvents(this, this);    
        
    }
	
	public void saveCustomConfig() {
		try{
			newCustomConfig.save(customConfig);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player p = (Player) sender;
		if (!(sender instanceof Player)) {
			p.sendMessage("Sorry, but you aren't a player");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("createwarp")) {
			h.createWarp(p, args[0]);
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("warp")) {
			h.teleportToWarp(p, args[0]);
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("warps")) {
			h.listWarps(p);
			return true;
		}
		return false;
	}
	
}