package disable;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Chat extends JavaPlugin implements Listener{
	
	    private boolean chatDisabled = false;

	    public ArrayList<String> worlds = new ArrayList<String>();
	    //public static boolean deathMessages;
	    //public static boolean joinMessages;
	    //public static boolean leaveMessages;
	    
	    FileConfiguration config = null;

	    public void onEnable() {
	    	config =  getConfig();
	    	getConfig().options().copyDefaults(true);
	        saveDefaultConfig();
	    	
	    	Bukkit.getServer().getPluginManager().registerEvents(this, this);
	    }
	    
	    public void onDisable() {
	    	reloadConfig();
	    }

	    
	    public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] arg) {
	    	String noPerms = ChatColor.RED + "You dont have permissions!";
	    	if (cmd.getName().equalsIgnoreCase("chatreload")) {
	    		if (sender.hasPermission("chat.reload")) {
	    			reloadConfig();
	    			sender.sendMessage(ChatColor.GOLD + "Config reloaded!");
	    		}else {
	    			sender.sendMessage(noPerms);
	    			return true;
	    		}
	    		return true;
	    	}
	      if (cmd.getName().equalsIgnoreCase("chatenable")) {
	    	  if (sender.hasPermission("chat.enable")) {
	    	  worlds.removeAll(worlds);
	        chatDisabled = false;
	        Bukkit.getServer().broadcastMessage(getConfig().getString("enableMessage").replaceAll("&", "§").replace("{player}", sender.getName()));
	        return true;
	    	  }else {
	    		  sender.sendMessage(noPerms);
	    		  return true;
	    	  }
	      }
	      if (cmd.getName().equalsIgnoreCase("chatdisable")) {
	    	  if (sender.hasPermission("chat.disable")) {
	        chatDisabled = true;
	        Bukkit.getServer().broadcastMessage(config.getString("disableMessage").replaceAll("&", "§").replace("{player}", sender.getName()));
	        return true;
	    	  }else {
	    		  sender.sendMessage(noPerms);
	    			return true;
	    	  }
	      }
	      if (cmd.getName().equalsIgnoreCase("chatclear")) {
	    	  if (sender.hasPermission("chat.clear")) {
	        for (int i = 0; i < 54; i++) {
	          Bukkit.broadcastMessage(" ");
	          Bukkit.broadcastMessage(" ");
	        }
	        Bukkit.getServer().broadcastMessage(getConfig().getString("clearMessage").replaceAll("&", "§").replace("{player}", sender.getName()));
	    	  }else {
	    		  sender.sendMessage(noPerms);
	    			return true;
	    	  }
	      }
	      return false;
	    }

	    @EventHandler
	    public void onChat(AsyncPlayerChatEvent e) {
	      Player p = e.getPlayer();
	      World w = p.getWorld();
	      String world = w.toString();

	      if (((chatDisabled) || (worlds.contains(world))) && (!p.hasPermission("chat.bypass"))) {
	        if (getConfig().getBoolean("sendMessage", true)) {
	        	 e.setCancelled(true);
	             p.sendMessage(getConfig().getString("message").replaceAll("&", "§").replace("{player}", p.getName()));
	        }else if (getConfig().getBoolean("sendMessage", false)) {
	        	 e.setCancelled(true);
	        }
	      }
	    }
}
