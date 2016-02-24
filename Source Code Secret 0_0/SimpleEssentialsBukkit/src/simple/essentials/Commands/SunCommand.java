package simple.essentials.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import simple.essentials.Utils.MessageManager.MsgType;

public class SunCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			if (cmd.getName().equalsIgnoreCase("sun")) {
				p.getWorld().setWeatherDuration(0);
				p.getWorld().setStorm(false);
				p.sendMessage(MsgType.SENDMESSAGE + "Set it to sun in '" + ChatColor.RED + p.getWorld().getName() + ChatColor.GOLD + "'");
				return true;
			}
		}
		return false;
	}
}