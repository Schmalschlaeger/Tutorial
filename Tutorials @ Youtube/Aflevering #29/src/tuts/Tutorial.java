package tuts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Tutorial extends JavaPlugin implements Listener {
	
	private boolean chatDisabled = false;
	
	public void onEnable() {  
        Bukkit.getServer().getPluginManager().registerEvents(this, this);     
    }

	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] arg) {
		if (cmd.getName().equalsIgnoreCase("chatclear")) {
			if (sender.hasPermission("chat.clear") || sender.isOp()) {
				for (int i = 0; i < 50; i++) {
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage(" ");
				}
				Bukkit.broadcastMessage(ChatColor.GOLD + "Chat has been cleared! by: " + sender.getName());
				return true;
			}else {
				sender.hasPermission(ChatColor.RED + "No permissions");
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("chatenable")) {
			if (sender.hasPermission("chat.enable") || sender.isOp()) {
				chatDisabled = true;
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("chatdisable")) {
			if (sender.hasPermission("chat.disable") || sender.isOp()) {
				chatDisabled = false;
				return true;
			}
		}
		return false;
	}
	
	@EventHandler
	public void onplayerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if ((chatDisabled) && !p.hasPermission("chat.bypass") || !p.isOp()) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.GOLD + "You cant talk right now. Chat is disabled!");
		}
	}
	
}