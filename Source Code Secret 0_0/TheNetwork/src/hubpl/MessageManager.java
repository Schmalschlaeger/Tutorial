package hubpl;

import org.bukkit.ChatColor;

public class MessageManager {
	
	static String lobbyhub = ChatColor.BLUE + "[!] " ;
	
	public enum MsgType{
        NORMAL(lobbyhub + ChatColor.LIGHT_PURPLE),
        DENIED(lobbyhub + ChatColor.DARK_RED),
        ERROR(lobbyhub + ChatColor.DARK_GRAY),
        ARENA(lobbyhub + ChatColor.AQUA),
        RELOAD(lobbyhub + ChatColor.YELLOW),
        CREATE(lobbyhub + ChatColor.AQUA),
        CONSOLE("[LobbyHub] " + ChatColor.WHITE);
     
        private String prefix;
          
        MsgType(String prefix){
            this.prefix = prefix;
        }
     
        @Override
        public String toString(){
            return this.prefix;
        }    
    }

}
