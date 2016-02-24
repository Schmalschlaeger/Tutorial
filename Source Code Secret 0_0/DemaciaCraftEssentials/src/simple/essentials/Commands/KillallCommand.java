package simple.essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import simple.essentials.Utils.MessageManager.MsgType;

public class KillallCommand implements CommandExecutor{
	
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("killall")) {
				 for (Player all : Bukkit.getServer().getOnlinePlayers()) {
					 p.sendMessage(MsgType.SENDMESSAGE + "Killed " + ChatColor.RED + all.getWorld().getEntities().size() + ChatColor.GOLD + " entities!");
					 all.getWorld().getEntities().remove(all.getWorld().getEntities());
				 }
				 return true;
			 }
		 }
		 return false;
	}
}
