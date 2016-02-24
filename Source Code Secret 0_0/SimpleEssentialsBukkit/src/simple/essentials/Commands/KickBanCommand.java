package simple.essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import simple.essentials.Main;
import simple.essentials.Utils.MessageManager.MsgType;
import simple.essentials.Utils.saveUtil;

public class KickBanCommand implements CommandExecutor, Listener{
		
    @SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kick")) {
			if (!(s.hasPermission(Main.kickPerms))) {
				s.sendMessage(MsgType.NO_PERMS + "You dont have permission to kick a player!");
				return true;
			}
			if (args.length == 0) {
				s.sendMessage(MsgType.NO_PERMS + "Please specify a Player");
				return true;
			}
			
			if(args.length < 2)
            {
                s.sendMessage(MsgType.WARNING + " Not enough arguments! Please specify an reason?");
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
				s.sendMessage(MsgType.WARNING + "Player " + args[0] + " is not online!");
				return true;
			}
			target.kickPlayer(ChatColor.RED + "[" + ChatColor.YELLOW + "Kicked" + ChatColor.RED + "] " + ChatColor.GOLD + message);
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "Player " + ChatColor.GREEN + "" + ChatColor.BOLD + s.getName() + ChatColor.YELLOW + " has kicked " + ChatColor.DARK_PURPLE + args[0]);
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Reason| " + ChatColor.ITALIC + ChatColor.YELLOW + ("" + message.charAt(0)).toUpperCase() + message.substring(1));
			
			
		}
		if (cmd.getName().equalsIgnoreCase("ban")) {
			if (!(s.hasPermission(Main.banPerms))) {
				s.sendMessage(MsgType.WARNING + "You dont have permission to ban a player!");
				return true;
			}
			if (args.length == 0) {
				s.sendMessage(MsgType.WARNING + "Please specify a Player");
				return true;
			}
			if(args.length < 2)
            {
                s.sendMessage(MsgType.WARNING + " Not enough arguments! Please specify an reason?");
                return true;
            }
			OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
			if (target == null) {
				s.sendMessage(MsgType.WARNING + "Could not find player " + ChatColor.AQUA + args[0] + ChatColor.RED + "!");
				return true;
			}
			if (target.isBanned()) {
				s.sendMessage(MsgType.WARNING + "Player " + target.getName() + " is already banned");
				return true;
			}
			
			StringBuilder b = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                if (i != 1)
                    b.append(" ");
                b.append(args[i]);
            }
         
            String message = b.toString();
            
			target.setBanned(true);
			if (target.isOnline()) {
				((Player) target).kickPlayer(ChatColor.RED + "[" + ChatColor.YELLOW + "Banned" + ChatColor.RED + "] " + ChatColor.GOLD + message);
			}
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "Player " + ChatColor.GREEN + "" + ChatColor.BOLD + s.getName() + ChatColor.YELLOW + " has banned " + ChatColor.DARK_PURPLE + args[0]);
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Reason | " + ChatColor.ITALIC + ChatColor.YELLOW + ("" + message.charAt(0)).toUpperCase() + message.substring(1));
			saveUtil.setBanReason("Players." + target.getUniqueId(), ("" + message.charAt(0)).toUpperCase() + message.substring(1), target);
		}
		if (cmd.getName().equalsIgnoreCase("unban")) {
			if (!(s.hasPermission(Main.unbanPerms))) {
				s.sendMessage(MsgType.NO_PERMS + "You dont have permission to unban a player!");
				return true;
			}
			if (args.length == 0) {
				s.sendMessage(MsgType.WARNING + "Please specify a Player");
				return true;
			}
			if(args.length < 2)
            {
                s.sendMessage(MsgType.WARNING + " Not enough arguments! Please specify an reason?");
                return true;
            }
			OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
			
			if (target == null) {
				s.sendMessage(MsgType.WARNING + "Could not find player " + args[0] + "!");
				return true;
			}
			if (!target.isBanned()) {
				s.sendMessage(MsgType.WARNING + "Player " + target.getName() + " is not banned");
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
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Reason | " + ChatColor.ITALIC +  ChatColor.YELLOW + ("" + message.charAt(0)).toUpperCase() + message.substring(1));
		}
		return true;
	}

}
