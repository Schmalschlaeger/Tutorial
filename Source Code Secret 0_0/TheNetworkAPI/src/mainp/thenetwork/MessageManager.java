package mainp.thenetwork;

import org.bukkit.ChatColor;

public class MessageManager {
	
	private static String namePrefix = ChatColor.AQUA + "[INFO] " ;
	
	public enum MsgType{
        MESSAGETOPLAYER(ChatColor.GRAY + "" + ChatColor.BOLD),
        BROADCAST(namePrefix + ChatColor.GRAY);
     
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
