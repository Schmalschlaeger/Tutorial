package DoubleJump;

import org.bukkit.ChatColor;



public class MessageManager {
	
	public enum MsgType{
        NORMAL(ChatColor.RED + "[JusSuperJump] " + ChatColor.GOLD),
        DENIED(ChatColor.RED + "[JusSuperJump] " + ChatColor.RED + "You can not use this command!"),
        ERROR(ChatColor.RED + "[JusSuperJump] " + ChatColor.DARK_RED + "ERROR: "),
        JOIN(ChatColor.RED + "[JusSuperJump] " + ChatColor.GREEN),
        NOPERMS(ChatColor.RED + "[JusSuperJump] " + "You dont have permissions!"),
        CONSOLE("[JusSuperJump] ");
     
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


