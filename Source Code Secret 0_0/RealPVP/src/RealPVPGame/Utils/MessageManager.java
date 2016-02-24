package RealPVPGame.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageManager {
	
	private static String messagePrefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "RealFFA" + ChatColor.GRAY + "] " + ChatColor.RESET;
	
	public enum MsgType{
        BROADCAST(messagePrefix + ChatColor.GOLD),
        WARNING(messagePrefix + ChatColor.RED),
        SENDMESSAGE(messagePrefix + ChatColor.DARK_AQUA),
        JOIN(ChatColor.GRAY + ""),
        COMMAND_NOT_FOUND(ChatColor.AQUA + "Command not found, use /rf or /realffa help!"),
        KILL(messagePrefix + ChatColor.GRAY),
        NO_PERMS(messagePrefix + ChatColor.DARK_RED);
     
        private String prefix;
          
        MsgType(String prefix){
            this.prefix = prefix;
        }
     
        @Override
        public String toString(){
            return this.prefix;
        }    
    }
	
	public static void broadcastAEmptyLineOfText() {
		for (int i = 0; i < 44; i++) {
			Bukkit.broadcastMessage(" ");
		}
	}

}
