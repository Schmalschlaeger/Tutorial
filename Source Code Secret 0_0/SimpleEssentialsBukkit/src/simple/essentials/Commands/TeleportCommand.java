package simple.essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import simple.essentials.Main;
import simple.essentials.Utils.MessageManager.MsgType;

public class TeleportCommand implements CommandExecutor {
	
	Main plugin;
	
	public TeleportCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("teleportall")) {
				 if (p.hasPermission(Main.teleportAllPerms)) {
					 for (Player all : Bukkit.getServer().getOnlinePlayers()) {
						 if (Bukkit.getOnlinePlayers().size() < 2) {
							 p.sendMessage(MsgType.WARNING + "Sorry, there must be atleast 2 players to tp everyone!");
							 return true;
						 }else if (Bukkit.getOnlinePlayers().size() >= 2) {
							 Location loc = p.getLocation();
							 all.teleport(loc);
							 p.sendMessage(MsgType.SENDMESSAGE + "Teleporting all players......");
						 return true;
						 }
					 }
					 return true;
				 }else {
					 p.sendMessage(MsgType.NO_PERMS + "Sorry, no permissions for you!");
					 return true;
				 }
			 }
			 if (cmd.getName().equalsIgnoreCase("tp")) {
				 if (args.length == 0) {
					 p.sendMessage(MsgType.WARNING + "Sorry, use /tp <player> [player]");
					 return true;
				 }
				 if (args.length == 1) {
					 Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							p.sendMessage(MsgType.WARNING + "We cant find player " + args[0] + " for you. Im sorry!");
							return true;
						}
						if (target.getName() == p.getName()) {
							p.sendMessage(MsgType.WARNING + "You cant tp to yourself!");
							return true;
						}
						p.teleport(target.getLocation());
						p.sendMessage(MsgType.SENDMESSAGE + "Teleported to " + ChatColor.RED + target.getName());
						return true;
				 }
				 if (args.length == 2) {
					 Player target = Bukkit.getServer().getPlayer(args[1]);
					 Player target2 = Bukkit.getServer().getPlayer(args[0]);
						if (target == null || target2 == null) {
							p.sendMessage(MsgType.WARNING + "We cant find player " + args[1] + " for you. Im sorry!");
							return true;
						}
						
						target2.teleport(target.getLocation());
						target2.sendMessage(MsgType.SENDMESSAGE + "You were teleported to " + ChatColor.RED + target.getName());
						return true;
				 }
				 if (args.length >= 3) {
					 p.sendMessage(MsgType.WARNING + "Too many arguments! Use /tp <player> [player]");
					 return true;
				 }
			 }
		 }
		 return false;
	 }

}
