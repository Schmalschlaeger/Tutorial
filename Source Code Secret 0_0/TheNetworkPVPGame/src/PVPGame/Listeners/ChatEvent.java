package PVPGame.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import PVPGame.Main;

public class ChatEvent implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if(Main.getRedTeam().hasPlayer(player)){
			event.setFormat(ChatColor.RED + "%s" + ": " + ChatColor.WHITE + "%s");
		} else if (Main.getBlueTeam().hasPlayer(player)) {
			event.setFormat(ChatColor.BLUE + "%s" + ": " + ChatColor.WHITE + "%s");
		} else {
			event.setFormat(ChatColor.YELLOW + "%s" + ": " + ChatColor.WHITE + "%s");
		}
	}

}
