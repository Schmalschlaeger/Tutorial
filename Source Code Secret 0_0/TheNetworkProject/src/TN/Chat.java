package TN;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener, CommandExecutor{

	    public ArrayList<String> worlds = new ArrayList<String>();
	    public static boolean deathMessages;
	    public static boolean joinMessages;
	    public static boolean leaveMessages;

	    public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] arg) {
	      if (cmd.getName().equalsIgnoreCase("chatenable")) {
	    	  if (sender.hasPermission("network.chat.enable")) {
	    	  worlds.removeAll(worlds);
	        Main.chatDisabled = false;
	        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Chat enabled! You can talk again");
	        return true;
	    	  }
	      }if (cmd.getName().equalsIgnoreCase("chatdisable")) {
	    	  if (sender.hasPermission("network.chat.disable")) {
	    		  Main.chatDisabled = true;
	        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Chat disabled! Only staff can talk now!");
	        return true;
	    	  }
	      }if (cmd.getName().equalsIgnoreCase("chatclear")) {
	    	  if (sender.hasPermission("network.chat.clear")) {
	        for (int i = 0; i < 44; i++) {
	          Bukkit.broadcastMessage(" ");
	          Bukkit.broadcastMessage(" ");
	        }
	        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Chat was cleared by " + ChatColor.RED + sender.getName()
	        		+ ChatColor.GOLD + "!");
	      }
	      }
	      return false;
	    }

	    @EventHandler
	    public void onChat(AsyncPlayerChatEvent e) {
	      if (Main.chatDisabled == true && !e.getPlayer().hasPermission("network.chat.bypass")) {
	        e.setCancelled(true);
	      }
	    }
	    
	    @EventHandler
	    public void onChat1(AsyncPlayerChatEvent e) {
	    	String msg = e.getMessage().trim();
	    	e.setMessage(("" + msg.charAt(0)).toUpperCase() + msg.substring(1));
	    }
}
