package tuts;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import de.inventivegames.util.title.TitleManager;

public class Tutorial extends JavaPlugin implements Listener{

	public void onEnable() { 	
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		ActionBarAPI.sendActionBar(e.getPlayer(), "Dit is onze epic server");
		
		TitleManager.sendTimings(e.getPlayer(), 20, 40, 20);
		
		TitleManager.sendSubTitle(e.getPlayer(), "{\"text\":\"\",\"extra\":[{\"text\": \"Best Server EUW\",\"color\":\"blue\"}]}");
		
		TitleManager.sendTitle(e.getPlayer(), "{\"text\":\"\",\"extra\":[{\"text\": \"play.yourserver.nl\",\"color\":\"blue\"}]}");
	}

}

