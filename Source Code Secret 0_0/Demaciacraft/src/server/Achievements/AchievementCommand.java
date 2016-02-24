package server.Achievements;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import server.MessageManager.MsgType;

public class AchievementCommand implements CommandExecutor{
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("achievements")) {
				 if (args.length == 3) {
					 if (args[0].equalsIgnoreCase("enable")) {
						 Player target = Bukkit.getServer().getPlayer(args[1]);
							if (target == null) {
								p.sendMessage(MsgType.WARNING + "I cant find player " + args[0] + " for you. Im sorry!");
								return true;
							}
						 if (args[2].equalsIgnoreCase("serverjoiner")) {
							//if (target.getName() == p.getName()) {
							//	p.sendMessage(MsgType.WARNING + "Did you know you can type /feed for yourself ;)");
							//}
							Achievements.setAchievementDone("serverjoiner", target, "Server Joiner");
							p.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + target.getName() + ChatColor.GOLD + " has now completed " 
							+ ChatColor.DARK_AQUA + "Server Joiner");
							return true;
						 }
						 else if (args[2].equalsIgnoreCase("populair5")) {
							 Achievements.setAchievementDone("populair5", target, "You are Populair");
								p.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + target.getName() + ChatColor.GOLD + " has now completed " 
								+ ChatColor.DARK_AQUA + "You are Populair");
								return true;
						 }
						 else if (args[2].equalsIgnoreCase("infobot")) {
							 Achievements.setAchievementDone("infobot", target, "Meet " + ChatColor.DARK_AQUA + "InfoBot");
								p.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + target.getName() + ChatColor.GOLD + " has now completed " 
								+ ChatColor.DARK_AQUA + "Meet InfoBot");
								return true;
						 }
						 else if (args[2].equalsIgnoreCase("achievements10")) {
							 Achievements.setAchievementDone("achievements10", target, "Complete 10 Achievements");
								p.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + target.getName() + ChatColor.GOLD + " has now completed " 
								+ ChatColor.DARK_AQUA + "Complete 10 Achievements");
								return true;
						 }
					 }
					 //################
					 if (args[0].equalsIgnoreCase("disable")) {
						 Player target = Bukkit.getServer().getPlayer(args[1]);
						 if (args[2].equalsIgnoreCase("serverjoiner")) {
								if (target == null) {
									p.sendMessage(MsgType.WARNING + "I cant find player " + args[0] + " for you. Im sorry!");
									return true;
								}
							//if (target.getName() == p.getName()) {
							//	p.sendMessage(MsgType.WARNING + "Did you know you can type /feed for yourself ;)");
							//}
							Achievements.setAchievementNotReached("serverjoiner", target);
							p.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + target.getName() + ChatColor.GOLD + " has now disabled " 
							+ ChatColor.DARK_AQUA + "Server Joiner");
							return true;
						 }
						 else if (args[2].equalsIgnoreCase("populair5")) {
							 Achievements.setAchievementNotReached("populair5", target);
							 
								p.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + target.getName() + ChatColor.GOLD + " has now disabled " 
								+ ChatColor.DARK_AQUA + "You are Populair");
								return true;
						 }
						 else if (args[2].equalsIgnoreCase("infobot")) {
							 Achievements.setAchievementNotReached("infobot", target);
								p.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + target.getName() + ChatColor.GOLD + " has now disabled " 
								+ ChatColor.DARK_AQUA + "Meet InfoBot");
								return true;
						 }
						 else if (args[2].equalsIgnoreCase("achievements10")) {
							 Achievements.setAchievementNotReached("achievements10", target);
								p.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + target.getName() + ChatColor.GOLD + " has now disabled " 
								+ ChatColor.DARK_AQUA + "Complete 10 Achievements");
								return true;
						 }
					 }
				 }
			 }
		 }
		 return false;
	}

}
