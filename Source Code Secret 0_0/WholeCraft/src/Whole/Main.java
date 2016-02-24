package Whole;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {		
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		System.out.println("Works");
	}

}
