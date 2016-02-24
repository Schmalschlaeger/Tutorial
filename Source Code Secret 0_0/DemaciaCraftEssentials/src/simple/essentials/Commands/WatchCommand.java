package simple.essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import simple.essentials.Main;
import simple.essentials.Utils.MessageManager.MsgType;

public class WatchCommand implements CommandExecutor{
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("watch")) {
				 if (args.length == 0) {
					 p.sendMessage(MsgType.WARNING + "Sorry, i didn't know there was a player with no name! Fill a name in /watch <player>");
					 return true;
				 }
				 if (args.length == 1) {
					 Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							p.sendMessage(MsgType.WARNING + "We cant find player " + args[0] + " for you. Im sorry!");
							return true;
						}
						if (target.getName() == p.getName()) {
							p.sendMessage(MsgType.WARNING + "Sorry, you cant watch yourself.");
							return true;
						}
						if (!Main.watcher.containsKey(p)) {
						Main.watcher.put(p, target);
						p.sendMessage(MsgType.SENDMESSAGE + "You are now watching " + ChatColor.RED + target.getName());
						return true;
						}else {
							Main.watcher.remove(p);
							p.sendMessage(MsgType.SENDMESSAGE + "You are no longer watching " + ChatColor.RED + target.getName());
						}
				 }
				 if (args.length >= 2) {
					 p.sendMessage(MsgType.WARNING + "Too manu arguments. You can use only /watch <player>");
					 return true;
				 }
			 }
		 }
		 return false;
	}

}
