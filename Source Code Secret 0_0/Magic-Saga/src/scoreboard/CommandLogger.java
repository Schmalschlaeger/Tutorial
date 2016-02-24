package scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandLogger implements Listener {
	
	@EventHandler
	public void onPlayerCommandTyp(PlayerCommandPreprocessEvent e) {
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
			if (all.isOp()) {
	           	all.sendMessage(ChatColor.GOLD + e.getPlayer().getName() + ChatColor.RED + " have typed the command " + ChatColor.BLUE + e.getMessage());
			}
		}
	}

}
