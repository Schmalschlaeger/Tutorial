package scoreboard;

import org.bukkit.ChatColor;

public class MessageManager {
	
	static String lobbyhub = "" ;
	static String sign = "" ;
	static String Accesoiries = "";
	
	public enum MsgType{
        NORMAL(lobbyhub + ChatColor.LIGHT_PURPLE),
        DENIED(lobbyhub + ChatColor.DARK_RED),
        ERROR(lobbyhub + ChatColor.DARK_GRAY),
        ARENA(lobbyhub + ChatColor.AQUA),
        RELOAD(lobbyhub + ChatColor.YELLOW),
        SHOW(lobbyhub + ChatColor.GOLD),
        CONSOLE("[LobbyHub] " + ChatColor.WHITE),
        OWNER(ChatColor.DARK_RED + "Owner/Admin"),
        STAFF(ChatColor.RED + "Member from the staff"),
        ACCESOIRIES(Accesoiries + ChatColor.GOLD),
        SIGN(sign + ChatColor.GOLD),
        VISITOR(ChatColor.GREEN + "Visitor");
     
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
