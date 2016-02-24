package speedrun;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class invisClock implements Listener{
	
    static Main plugin;
    
    public invisClock(Main m) {
        plugin = m;
    }
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSlimeClick5(PlayerInteractEvent e) {
		final Player player = (Player) e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getItem().hasItemMeta() || e.getItem().getItemMeta().hasDisplayName() || e.getItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Player Visibility! " + ChatColor.GRAY + "(Right click to toggle)")) {
				if (e.getItem().getType() == Material.WATCH) {
				if (plugin.usingClock.contains(e.getPlayer().getName())) { // Turning it off.
					plugin.usingClock.remove(e.getPlayer().getName());
					player.sendMessage(ChatColor.GOLD + "All player are enabled!");
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        if (p != e.getPlayer()) {
                            e.getPlayer().showPlayer(p);                                   
                        }
					}
				}else { // Turning it on.
					plugin.usingClock.add(e.getPlayer().getName());
                    player.sendMessage(ChatColor.GOLD + "All player are disabled!");
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                            if (p != e.getPlayer()) {
                                    e.getPlayer().hidePlayer(p);
                            }
                        }
			    	}
		    	}
	        }
    	}
    }
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p != e.getPlayer()) {
		if (plugin.usingClock.contains(p.getName())) {
			e.getRecipients().remove(p);
		}
            }
		}
	}
	
	 @SuppressWarnings("deprecation")
	@EventHandler
     public void onPlayerJoin(PlayerJoinEvent e) { // This could be expensive to run every time someone joins.            
             for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                     if (p != e.getPlayer()) {
                             if (plugin.usingClock.contains(p.getName())) {
                                     p.hidePlayer(e.getPlayer()); // If they are currently using the clock, hide the new player.
                             }
                            
                             else {
                                     p.showPlayer(e.getPlayer()); // Else, show the new player.
                             }
                     }
             }
     }
}

