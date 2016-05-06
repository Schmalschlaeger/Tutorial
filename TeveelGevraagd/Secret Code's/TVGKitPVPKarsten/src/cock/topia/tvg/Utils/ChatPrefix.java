package cock.topia.tvg.Utils;

import me.robin.battlelevels.api.BattleLevelsAPI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cock.topia.tvg.Main;

public class ChatPrefix implements Listener{
	
	Main plugin;
	public ChatPrefix(Main plugin) {
		this.plugin = plugin;
	}
	
	public ChatColor getLevelColor(int level) {
		if (level >= 10 && level < 30) {
			return ChatColor.GOLD;
		}else if (level >= 30 && level < 50) {
				return ChatColor.BLUE;
		}else if (level >= 50 && level < 70) {
			return ChatColor.RED;
		}else if (level >= 70) {
			return ChatColor.DARK_PURPLE;
		}else if (level >= 70) {
			return ChatColor.DARK_RED;
		}else {
		return ChatColor.DARK_GRAY;
		}
	}
	
	public String playerColor(Player sender) {
		if (sender.hasPermission("TVGGlobal.colornick")) {
	          String colorname = "";
	          int i = 1;
	          char[] nickname = sender.getName().toCharArray();
	          for (char aletter : nickname) {
	            i++;
	            if (i == 8) {
	              i++;
	            }
	            if (i > 9) {
	              i = 2;
	            }
	            colorname = colorname + '§' + i + aletter;
	          }
	          return colorname;
	        }
		return sender.getName();
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		String levelRank = ChatColor.GRAY + "Lvl. " + getLevelColor(BattleLevelsAPI.getLevel(e.getPlayer().getUniqueId())) + Integer.toString(BattleLevelsAPI.getLevel(e.getPlayer().getUniqueId())) + " §7| ";
		Player p = e.getPlayer();
		String pName = playerColor(p);
		Stats stats = new Stats(plugin);
		String message = e.getMessage().replaceAll("%", ChatColor.RED + "procent");
		
		if (stats.isStaff(p)) {
			e.setFormat(levelRank + stats.getRankColor(p) + stats.getRank(p) + " " + p.getName() + ChatColor.WHITE + ": " + stats.getRankMessageColor(p) + message);
			return;
		}
		
		if (stats.isInRank(p)) {
			e.setFormat(levelRank + stats.getRankColor(p) + stats.getRank(p) + " " + pName + ChatColor.WHITE + ": " + stats.getRankMessageColor(p) + message);
		}else {
			e.setFormat(levelRank + ChatColor.WHITE + p.getName() + ChatColor.WHITE + ": " + stats.getRankMessageColor(p) + message);
		}
	}
	
	/*
	 * wood
	 * coal
	 * lapis
	 * iron
	 * redstone
	 * gold
	 * diamond
	 * emerald
	 */

}
