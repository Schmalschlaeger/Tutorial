package PVPGame.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import PVPGame.Addons.Kits;
import PVPGame.Utils.TeamManager;


public class LeaveEvent implements Listener {

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		//GameManager.getManager().removePlayer(e.getPlayer());
		
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(null);
		
		TeamManager.removeFromAllTeams(e.getPlayer());
		Kits.removeKits(e.getPlayer());
		e.getPlayer().setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
	}

}
