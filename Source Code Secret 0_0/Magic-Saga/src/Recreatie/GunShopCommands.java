package Recreatie;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import scoreboard.Main;
import scoreboard.MessageManager.MsgType;

public class GunShopCommands implements CommandExecutor {
	
    static Main plugin;
    
    public GunShopCommands(Main m) {
        plugin = m;
    }

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("gunshop")) {
			if (args.length != 3) {
				p.sendMessage(ChatColor.GOLD + "You are an " + MsgType.OWNER + ChatColor.GOLD + " Your commands are:");
    			p.sendMessage(ChatColor.GOLD + "/gunshop: " + ChatColor.GRAY + "Shows this screen");
    			p.sendMessage(ChatColor.GOLD + "/gunshop removegun <player> <gunName>: " + ChatColor.GRAY + "Remove's an gun from the player");
    			p.sendMessage(ChatColor.GOLD + "/gunshop addgun <player> <gunName>: " + ChatColor.GRAY + "Add's an gun to the player");
				return true;
			} else {
				if (args[0].equalsIgnoreCase("removegun")) {
					Player target = Bukkit.getServer().getPlayer(args[1]);
					if (target == null) {
						p.sendMessage(ChatColor.RED + "Player " + args[1] + " is not online! or din't exist");
						return true;
					}
					if (plugin.getBoolean("Guns." + args[2]) == false) {
						p.sendMessage(ChatColor.RED + "Player " + args[1] + " doesn't have the gun: " + args[2]);
						return true;
					}else {
						plugin.updateConfigPerPlayer("Guns." + args[2], false);
						p.sendMessage(ChatColor.GOLD + "Succesfull removed gun: " + ChatColor.RED + args[2] + ChatColor.GOLD + " from player " + ChatColor.RED + args[1]);
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("addgun")) {
					Player target = Bukkit.getServer().getPlayer(args[1]);
					if (target == null) {
						p.sendMessage(ChatColor.RED + "Player " + args[1] + " is not online! or din't exist");
						return true;
					}
					if (plugin.getBoolean("Guns." + args[2]) == true) {
						p.sendMessage(ChatColor.RED + "Player " + args[1] + " has already the gun: " + args[2]);
						return true;
					}else {
						plugin.updateConfigPerPlayer("Guns." + args[2], true);
						p.sendMessage(ChatColor.GOLD + "Succesfull added the gun: " + ChatColor.RED + args[2] + ChatColor.GOLD + " for player " + ChatColor.RED + args[1]);
						return true;
					}
				}
			}
		}
		return false; 
		
	}
	
}
