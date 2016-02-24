package RealPVPGame.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import RealPVPGame.Main;

public class JoinEvent implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		p.teleport(Bukkit.getServer().getWorld(Main.getWorldGame()).getSpawnLocation());
	}

}
