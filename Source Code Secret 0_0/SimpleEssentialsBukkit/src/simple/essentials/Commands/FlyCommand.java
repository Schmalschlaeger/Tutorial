package simple.essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import simple.essentials.Main;
import simple.essentials.Utils.MessageManager.MsgType;

public class FlyCommand implements CommandExecutor{
	
	 @SuppressWarnings("deprecation")
	@Override
		public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("fly")) {
				 if (args.length >= 2) {
					 p.sendMessage(MsgType.WARNING + "Too many arguments! USe /fly or /fly <player>");
					 return true;
				 }
				 if (args.length == 0) {
					 if (p.hasPermission(Main.flyPerms)) {
						 if (p.getAllowFlight() == true) {
							 p.setAllowFlight(false);
							 p.sendMessage(MsgType.SENDMESSAGE + "Your fly has been " + ChatColor.RED + "disabled!");
							 return true;
						 }else {
							 p.setAllowFlight(true);
							 p.sendMessage(MsgType.SENDMESSAGE + "Your fly has been " + ChatColor.GREEN + "enabled");
							 return true;
						 }	 
					 }
				 }else if (args.length == 1) {
					 Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							p.sendMessage(MsgType.WARNING + "We cant find player " + args[0] + " for you. Im sorry!");
							return true;
						}
						if (target.getAllowFlight()) {
							if (target.getName() == p.getName()) {
								p.sendMessage(MsgType.WARNING + "Did you know you can use /fly for yourself!");
							}
							target.setAllowFlight(false);
							p.sendMessage(MsgType.SENDMESSAGE + "Fly for " + ChatColor.RED + target.getName() + ChatColor.GREEN + " disabled!");
							return true;
						}else {
							if (target.getName() == p.getName()) {
								p.sendMessage(MsgType.WARNING + "Did you know you can use /fly for yourself!");
							}
							target.setAllowFlight(true);
							p.sendMessage(MsgType.SENDMESSAGE + "Fly for " + ChatColor.RED + target.getName() + ChatColor.RED + " enabled!");
							return true;
						}
				 }
			 }
		 }
		 return false;
	 }

}
