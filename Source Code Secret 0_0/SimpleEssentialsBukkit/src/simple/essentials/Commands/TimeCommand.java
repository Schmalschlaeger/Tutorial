package simple.essentials.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import simple.essentials.Utils.MessageManager.MsgType;

public class TimeCommand implements CommandExecutor{
	
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("day")) {
				 p.getWorld().setTime(1000);
				 p.sendMessage(MsgType.SENDMESSAGE + "Set time to " + ChatColor.RED + p.getWorld().getTime() + ChatColor.GOLD  + 
						 " in world " + ChatColor.RED + p.getWorld().getName());
				 return false;
			 }
			 if (cmd.getName().equalsIgnoreCase("night")) {
				 p.getWorld().setTime(13000);
				 p.sendMessage(MsgType.SENDMESSAGE + "Set time to " + ChatColor.RED + p.getWorld().getTime() + ChatColor.GOLD  + 
						 " in world " + ChatColor.RED + p.getWorld().getName());
				 return false;
			 }
		 }
		 return false;
	}

}
