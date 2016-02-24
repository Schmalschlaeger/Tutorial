package scoreboard;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import scoreboard.MessageManager.MsgType;

public class DisWords implements Listener {
	
	int kick = 0;
	final public ArrayList<Player> mute = new ArrayList<Player>();
	
	@EventHandler
	public void onPlayerSchreeuw(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String m = e.getMessage();
		 
		if (m.contains(m.toUpperCase())) {
		e.setCancelled(true);
		p.sendMessage(org.bukkit.ChatColor.RED + "You may not talk in all caps.");
		}
	}
	
	@EventHandler
	public void onPlayerChat(final AsyncPlayerChatEvent e) {
		if (e.getMessage().toLowerCase().contains("test")) {
			e.setMessage(e.getMessage().replace("test".toLowerCase() , "****"));
			e.getPlayer().sendMessage(MsgType.NORMAL + "Please dont swear1!");
		
		}else if (e.getMessage().toUpperCase().contains("test")) {
			e.setMessage(e.getMessage().replace("test".toUpperCase() , "****"));
			e.getPlayer().sendMessage(MsgType.NORMAL + "Please dont swear2!");
		}
	}
}
