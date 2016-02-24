package TWS;

import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class Maintenance implements Listener, CommandExecutor {
	
    static Main plugin;
    
    public Maintenance(Main m) {
        plugin = m;
    }
	
	private static boolean isInMaintenance = false;
	
	public void serverConnect(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerJoin(PlayerLoginEvent e) {
		if (isInMaintenance == true) {
			if (!e.getPlayer().hasPermission("network.rank.owner")) {
			e.disallow(Result.KICK_WHITELIST, ChatColor.GOLD + "Sorry, but this server is in maintenance! Please come back later!");
			}
		}
	}
	
	   @SuppressWarnings("deprecation")
	   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		   if (cmd.getName().equalsIgnoreCase("mainon")) {
			   if (isInMaintenance == true) {
				   sender.sendMessage(ChatColor.RED + "Maintenance already enabled!");
				   return true;
			   }
			   isInMaintenance = true;
			   sender.sendMessage(ChatColor.GOLD + "Succesfull set this server in maintenance!");
			   for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				   if (!all.hasPermission("network.rank.owner")) {
				   serverConnect("lobby", all);
				   all.sendMessage(ChatColor.GRAY + "You have send to the lobby, because this server was set on maintenance!");
				   return true;
				   }
			   }
		   }
		   if (cmd.getName().equalsIgnoreCase("mainoff")) {
			   if (isInMaintenance == false) {
				   sender.sendMessage(ChatColor.RED + "Maintenance already disabled!");
				   return true;
			   }
			   isInMaintenance = false;
			   sender.sendMessage(ChatColor.GOLD + "Succesfull set this server back online!");
			   return true;
		   }
		   return false;  
	   }

}
