package simple.essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import simple.essentials.Main;
import simple.essentials.Utils.MessageManager.MsgType;

public class MsgCommand implements CommandExecutor{
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("msg")) {
				 if (args.length == 0) {
					 p.sendMessage(MsgType.WARNING + "Use /msg <player> <message>");
					 return true;
				 }
				 if (args.length == 1) {
					 p.sendMessage(MsgType.WARNING + "This command reguires a message!");
					 return true;
				 }
				 if (args.length > 1) {
					 Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							p.sendMessage(MsgType.WARNING + "We cant find player " + args[0] + " for you. Im sorry!");
							return true;
						}
						
						StringBuilder b = new StringBuilder();
			            for (int i = 1; i < args.length; i++) {
			                if (i != 1)
			                    b.append(" ");
			                b.append(args[i]);
			            }
			         
			            String message = b.toString();
						
						if (target.getName() == p.getName()) {
							target.sendMessage(ChatColor.BLUE + "[" + ChatColor.BLUE + "From: " + ChatColor.GRAY + "SERVER" + ChatColor.BLUE + "]> " + ChatColor.WHITE + "Just tell me...... What is so important that you gone text yourself??");
							return true;
						}
						
						target.sendMessage(ChatColor.BLUE + "[" + ChatColor.BLUE + "From: " + ChatColor.GRAY + p.getName() + ChatColor.BLUE + "]> " + ChatColor.WHITE + ("" + message.charAt(0)).toUpperCase() + message.substring(1));
						p.sendMessage(MsgType.SENDMESSAGE + "Message send!");
						if (Main.watcher.containsValue(p)) {
							Main.watcher.get(p).sendMessage(ChatColor.GOLD + "[MSg From:" + ChatColor.BLUE + p.getName() + ChatColor.GOLD + " to:" + target.getName() 
									+ ChatColor.GOLD + "]> " + ChatColor.WHITE + ("" + message.charAt(0)).toUpperCase() + message.substring(1));
						}
						//[From: JusJus]> Hello
						return true;
				 }
			 }
		 }
		 return false;
	}

}
