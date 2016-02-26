package tuts;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class StaffJoin implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("server.owner")) {
			e.setJoinMessage(ChatColor.RED + e.getPlayer().getDisplayName() + " has joined this server!");
		}
		
		if (e.getPlayer().getName().contains("JusJusOneOneTwo")) {
			e.setJoinMessage(ChatColor.RED + e.getPlayer().getDisplayName() + " has joined this server!");
		}
		
		if (!e.getPlayer().isOp()) {
			e.setJoinMessage(null);
		}
	}

}
