package me.jusjus.src.magicsaga;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public void onEnable() {		
		getCommand("fallingblock").setExecutor(new Commands()); 
		getCommand("block").setExecutor(new Commands()); 
		getCommand("developers").setExecutor(new Commands()); 
		getCommand("phantasiacraft").setExecutor(new Commands()); 
	}

}
