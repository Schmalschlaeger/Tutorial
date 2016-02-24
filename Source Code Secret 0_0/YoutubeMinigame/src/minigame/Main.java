package minigame;

import minigame.Arena.ArenaManager;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		if (!getDataFolder().exists()) 
			getDataFolder().mkdir();
		
		if (getConfig() == null) 
			saveDefaultConfig();
		
		new ArenaManager(this);
		ArenaManager.getManager().loadGames();
	}
	
	public void onDisable() {
		saveConfig();
	}
	
	
	
}