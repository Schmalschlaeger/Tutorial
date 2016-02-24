package Kick_ban;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Kick_Ban extends JavaPlugin implements Listener{
	
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBan(PlayerLoginEvent e) {
    	if (e.getPlayer().isBanned()) {
    		e.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.DARK_RED + "You are banned from this server!"
        	+ ChatColor.RED + " Banned by an staff member!");
    	}
    }
    
    @Override
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kick")) {
			if (!(s.hasPermission(Main.))) {
				s.sendMessage(ChatColor.DARK_RED + "You dont have permission to kick a player!");
				return true;
			}
			if (args.length == 0) {
				s.sendMessage(ChatColor.RED + "Please specify a Player");
				return true;
			}
			
			if(args.length < 2)
            {
                s.sendMessage(ChatColor.RED + " Not enough arguments! Please specify an reason?");
                return true;
            }

            StringBuilder b = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                if (i != 1)
                    b.append(" ");
                b.append(args[i]);
            }
         
            String message = b.toString();
			
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null) {
				s.sendMessage(ChatColor.RED + "Player " + args[0] + " is not online!");
				return true;
			}
			target.kickPlayer(ChatColor.RED + "[" + ChatColor.YELLOW + "Kicked" + ChatColor.RED + "] " + ChatColor.GOLD + message);
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "Player " + ChatColor.GREEN + "" + ChatColor.BOLD + s.getName() + ChatColor.YELLOW + " has kicked " + ChatColor.DARK_PURPLE + args[0]);
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Reason -- " + ChatColor.YELLOW + message);
			
			
		}
		if (cmd.getName().equalsIgnoreCase("ban")) {
			if (!(s.hasPermission("network.ban"))) {
				s.sendMessage(ChatColor.DARK_RED + "You dont have permission to ban a player!");
				return true;
			}
			if (args.length == 0) {
				s.sendMessage(ChatColor.RED + "Please specify a Player");
				return true;
			}
			if(args.length < 2)
            {
                s.sendMessage(ChatColor.RED + " Not enough arguments! Please specify an reason?");
                return true;
            }
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null) {
				s.sendMessage(ChatColor.DARK_RED + "Could not find player " + ChatColor.AQUA + args[0] + ChatColor.RED + "!");
				return true;
			}
			if (target.isBanned()) {
				s.sendMessage(ChatColor.RED + "Player " + target.getName() + " is already banned");
				return true;
			}
			
			StringBuilder b = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                if (i != 1)
                    b.append(" ");
                b.append(args[i]);
            }
         
            String message = b.toString();
            
			target.kickPlayer(ChatColor.YELLOW + "You have been banned!");
			target.setBanned(true);
			target.kickPlayer(ChatColor.RED + "[" + ChatColor.YELLOW + "Banned" + ChatColor.RED + "] " + ChatColor.GOLD + message);
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "Player " + ChatColor.GREEN + "" + ChatColor.BOLD + s.getName() + ChatColor.YELLOW + " has banned " + ChatColor.DARK_PURPLE + args[0]);
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Reason -- " + ChatColor.YELLOW + message);
		}
		if (cmd.getName().equalsIgnoreCase("unban")) {
			if (!(s.hasPermission("network.unban"))) {
				s.sendMessage(ChatColor.DARK_RED + "You dont have permission to unban a player!");
				return true;
			}
			if (args.length == 0) {
				s.sendMessage(ChatColor.RED + "Please specify a Player");
				return true;
			}
			if(args.length < 2)
            {
                s.sendMessage(ChatColor.RED + " Not enough arguments! Please specify an reason?");
                return true;
            }
			OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
			
			if (target == null) {
				s.sendMessage(ChatColor.DARK_RED + "Could not find player " + args[0] + "!");
				return true;
			}
			if (!target.isBanned()) {
				s.sendMessage(ChatColor.RED + "Player " + target.getName() + " is not banned");
				return true;
			}
			
			StringBuilder b = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                if (i != 1)
                    b.append(" ");
                b.append(args[i]);
            }
            String message = b.toString();
            
            
			target.setBanned(false);
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "Player " + ChatColor.GREEN + "" + ChatColor.BOLD + s.getName() + ChatColor.YELLOW + " has unbanned " + ChatColor.DARK_PURPLE + args[0]);
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Reason -- " + ChatColor.YELLOW + message);
		}
		return true;
	}


}
