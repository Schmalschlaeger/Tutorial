package troller;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Sorry, only players can troll other players!");
		}
		if (cmd.getName().equalsIgnoreCase("jtroller")) {
			if (args.length == 0) {
				
			}
			return true;
		}
		return false;
	}
	
	

}
