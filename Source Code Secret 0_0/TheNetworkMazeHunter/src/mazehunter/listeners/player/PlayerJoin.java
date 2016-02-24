package mazehunter.listeners.player;

import mazehunter.MazeHunter;
import mazehunter.handlers.Game;
import mazehunter.listeners.MGListener;
import mazehunter.utils.LocationUtilities;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin extends MGListener{

	public PlayerJoin(MazeHunter pl) {
		super(pl);
	
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Game.setCanStart(Bukkit.getOnlinePlayers().length >= 8);
		LocationUtilities.teleportToSpawn(e.getPlayer());
	}
	
}
