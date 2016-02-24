package PVPGame.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import PVPGame.Main;
import PVPGame.Utils.MessageManager.MsgType;

public class StartCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private Main plugin;
	   
    public StartCommand(Main plugin){
    	this.plugin = plugin;
    }
    
    public static boolean isCommandTyped = false;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (cmd.getName().equalsIgnoreCase("game")) {
			if (sender.hasPermission("network.rank.owner") || sender.hasPermission("network.rank.moderator") || sender.hasPermission("network.rank.crew")) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("start")) {
						isCommandTyped = true;
				        Bukkit.broadcastMessage(MsgType.SENDMESSAGE + "Game forced to start by an admin! Starting.....");
					}
				}else if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Wrong arguments, try /game [start/stop]");
					return true;
				}else if (args.length > 1) {
					
				}
				
				
				
			}
		}
		return false;
	}

}
