package PVPGame.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import PVPGame.Utils.TeamManager;

public class PlayerDeathListener implements Listener{
	
	@EventHandler
	public void onDamage(PlayerDeathEvent e) {
		Player player = e.getEntity().getPlayer();
		Player killer = e.getEntity().getKiller();
		e.setDroppedExp(0);
		e.setKeepLevel(false);
		e.setNewTotalExp(0);
		e.setDeathMessage(ChatColor.GRAY + TeamManager.getTeamName(player) + ChatColor.GRAY + " has been slain by " + TeamManager.getTeamName(killer));
	}
}
