package hubpl;

import hubpl.MessageManager.MsgType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class Maintenance implements Listener, CommandExecutor {

	private Hub plugin;
	 
	public Maintenance(Hub instance) {
	    this.plugin = instance;
	}	

	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (cmd.getName().equalsIgnoreCase("maintenance"))
	    {
	      int localPlayer1;
	      Player player;
	      if ((sender instanceof Player)) {
	        Player p = (Player)sender;

	        if (plugin.getConfig().getBoolean("maintenance_on")) {
	          if (!sender.hasPermission("network.owner")) {
	            p.sendMessage(ChatColor.RED + "You don't have the permissions to execute this command!");
	            return true;
	          }

	          plugin.getConfig().set("maintenance_on", Boolean.valueOf(false));
	          plugin.saveConfig();
	          Bukkit.broadcastMessage("§6An admin turned off the maintenace, people can join again!");
	          return true;
	        }

	        if (!plugin.getConfig().getBoolean("maintenance_on")) {
	          if (!sender.hasPermission("network.owner")) {
	            p.sendMessage(ChatColor.RED + "You don't have the permissions to execute this command!");
	            return true;
	          }

	          plugin.getConfig().set("maintenance_on", Boolean.valueOf(true));
	          plugin.saveConfig();
	          Bukkit.broadcastMessage(MsgType.NORMAL + "You turned on the maintencance! Poeple cant join!");
	          Player[] arrayOfPlayer2;
	          int i = (arrayOfPlayer2 = Bukkit.getOnlinePlayers()).length; for (localPlayer1 = 0; localPlayer1 < i; localPlayer1++) { player = arrayOfPlayer2[localPlayer1];
	            if (!player.hasPermission("maintenance.bypass")) {
	              String kickmsg = plugin.getConfig().getString("maintenance_kickmessage").replaceAll("&", "§");
	              player.kickPlayer(kickmsg);
	            }
	          }

	          return true;
	        }
	      }
	      else if ((sender instanceof ConsoleCommandSender)) {
	        if (plugin.getConfig().getBoolean("maintenance_on")) {
	        	plugin.getConfig().set("maintenance_on", Boolean.valueOf(false));
	        	plugin.saveConfig();
	          Bukkit.broadcastMessage(sender.getName() + " turned off the maintenace, people can now join again!");
	          return true;
	        }

	        if (!plugin.getConfig().getBoolean("maintenance_on")) {
	        	plugin.getConfig().set("maintenance_on", Boolean.valueOf(true));
	        	plugin.saveConfig();
	          Bukkit.broadcastMessage("§6An admin turned on the maintenancemode, people without OP or permissions can't join anymore! §7§o");
	          Player[] arrayOfPlayer1;
	          int i = (arrayOfPlayer1 = Bukkit.getOnlinePlayers()).length; for (localPlayer1 = 0; localPlayer1 < i; localPlayer1++) { player = arrayOfPlayer1[localPlayer1];
	            if (!player.hasPermission("maintenance.bypass")) {
	              String kickmsg = plugin.getConfig().getString("maintenance_kickmessage").replaceAll("&", "§");
	              player.kickPlayer(kickmsg);
	            }
	          }
	          return true;
	        }
	      }
	    }
	    return false;
	  }

	  @EventHandler
	  public void onLogin(PlayerLoginEvent e) {
	    if ((plugin.getConfig().getBoolean("maintenance_on")) && (!e.getPlayer().hasPermission("maintenance.bypass"))) {
	      Player p = e.getPlayer();
	      String n = p.getName();

	      String kickmsg = plugin.getConfig().getString("maintenance_kickmessage").replaceAll("&", "§");
	      e.disallow(PlayerLoginEvent.Result.KICK_OTHER, kickmsg);

	      Bukkit.broadcastMessage("§8" + n + "§7 just tried to join the server!");
	    }
	  }

	  @EventHandler
	  public void onJoin(PlayerJoinEvent e) {
	    final Player p = e.getPlayer();

	    if (plugin.getConfig().getBoolean("maintenance_on")) {
	      Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        public void run() {
	          p.sendMessage(MsgType.NORMAL + "Server is in Maintenance mode! Poeple cant join, only admins and owners!");
	        }
	      }
	      , 10L);
	    }
	  }
	  
	  

	  @EventHandler
	  public void ServerPing(ServerListPingEvent e)
	  {
	    if (plugin.getConfig().getBoolean("maintenance_on")) {
	      String IP = e.getAddress().getHostAddress();

	      if (plugin.getServer().getIPBans().contains(IP)) {
	        String motd = plugin.getConfig().getString("ipbanned_motd").replaceAll("&", "§");
	        e.setMotd(motd);
	      }
	      else
	      {
	        String motd = plugin.getConfig().getString("maintenance_motd").replaceAll("&", "§");
	        e.setMotd(motd);
	      }

	    }
	    else if (!plugin.getConfig().getBoolean("maintenance_on")) {
	      String IP = e.getAddress().getHostAddress();

	      if (plugin.getServer().getIPBans().contains(IP)) {
	        String motd = plugin.getConfig().getString("ipbanned_motd").replaceAll("&", "§");
	        e.setMotd(motd);
	      }
	      else
	      {
	        String motd = plugin.getConfig().getString("normal_motd").replaceAll("&", "§");
	        e.setMotd(motd);
	      }
	    }
	  }   
}
