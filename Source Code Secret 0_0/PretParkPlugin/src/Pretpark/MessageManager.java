package Pretpark;

import org.bukkit.ChatColor;

public class MessageManager {
	
	public enum MsgType{
        NORMAL(ChatColor.RED + "[" + ChatColor.AQUA + "Theme" + ChatColor.DARK_AQUA + "Park" + ChatColor.RED + "] " + ChatColor.YELLOW),
        DENIED(ChatColor.RED + "[" + ChatColor.AQUA + "Theme" + ChatColor.DARK_AQUA + "Park" + ChatColor.RED + "] " + ChatColor.RED + "You can not use this command!"),
        ERROR(ChatColor.RED + "[" + ChatColor.AQUA + "Theme" + ChatColor.DARK_AQUA + "Park" + ChatColor.RED + "] " + ChatColor.DARK_RED + "ERROR: "),
        SPAWN(ChatColor.RED + "[" + ChatColor.AQUA + "Theme" + ChatColor.DARK_AQUA + "Park" + ChatColor.RED + "] " + ChatColor.GOLD),
        NOPERMS(ChatColor.RED + "[" + ChatColor.AQUA + "Theme" + ChatColor.DARK_AQUA + "Park" + ChatColor.RED + "] " + "You dont have permissions!");
     
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



