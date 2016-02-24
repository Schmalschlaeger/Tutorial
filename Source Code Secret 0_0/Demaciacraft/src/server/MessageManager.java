package server;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import server.Achievements.Achievements;

public class MessageManager {
	
	private static String messagePrefix = ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "InfoBot" + ChatColor.GRAY + "] " + ChatColor.RESET;
	public static String infoBotMessage = "             " + ChatColor.AQUA + "◄" + ChatColor.DARK_AQUA + "DemaciaCraft - InfoBot" + ChatColor.AQUA + "►";
	public static String infoBotAchievementMessage = ChatColor.GREEN + "  ACHIEVEMENT " + ChatColor.AQUA + "◄" 
	+ ChatColor.DARK_AQUA + "DemaciaCraft - InfoBot" + ChatColor.AQUA + "►" + ChatColor.GREEN + " ACHIEVEMENT  ";
	
	public enum MsgType{
        WARNING(messagePrefix + ChatColor.RED),
        SENDMESSAGE(messagePrefix + ChatColor.GOLD),
        JOIN(ChatColor.GRAY + ""),
        NO_PERMS(messagePrefix + ChatColor.RED),
        ACHIEVEMENT(messagePrefix + ChatColor.GOLD);
        
        private String prefix;
          
        MsgType(String prefix){
            this.prefix = prefix;
        }
     
        @Override
        public String toString(){
            return this.prefix;
        }    
    }
	
	public static void broadCastAEmptyLineOfText(Player p) {
		for (int i = 0; i < 44; i++) {
			p.sendMessage(" ");
			p.sendMessage(" ");
		}
	}
	
	
	public static void giveAchievementMessage(Player p, String achievement) {
		p.sendMessage(infoBotAchievementMessage);
		p.sendMessage(ChatColor.GOLD + "Congratulations " + p.getName() + " you earned a achievement!");
		p.sendMessage(ChatColor.GOLD + "Achievement: " + ChatColor.GREEN + achievement);
		p.sendMessage(ChatColor.GOLD + "You now have " + ChatColor.RED + Integer.toString(Achievements.getAchievementFromUser(p)) + ChatColor.GOLD + " of the " 
		+ ChatColor.RED + Achievements.totalAchievements + ChatColor.GOLD + " achievements!");
		p.sendMessage("  ");
	}
	
	public static void broadcastAEmptyLineOfText() {
		for (int i = 0; i < 44; i++) {
			Bukkit.broadcastMessage(" ");
		}
	}

}
