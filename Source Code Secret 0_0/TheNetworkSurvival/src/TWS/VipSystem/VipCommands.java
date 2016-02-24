package TWS.VipSystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import TWS.Main;

public class VipCommands implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		final Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("vip")) {
			if (p.hasPermission("network.rank.vip")) {
			p.openInventory(Menu.vip);
			p.sendMessage(ChatColor.GOLD + "Opening vip inventory.......");
			return true;
			}else {
				p.sendMessage(Main.noPerms);
				return true;
			}
		}
		return false;
	}

}
