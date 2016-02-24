package PVPGame.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import PVPGame.Main;
import PVPGame.Utils.GameManager;
import PVPGame.Utils.MessageManager.MsgType;

public class StopCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private Main plugin;
	   
    public StopCommand(Main plugin){
    	this.plugin = plugin;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if (cmd.getName().equalsIgnoreCase("stopgame")) {
			if (sender.hasPermission("network.rank.owner") || sender.hasPermission("network.rank.moderator") || sender.hasPermission("network.rank.crew")) {
				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				GameManager.getManager().endGame(all);
				}
				sender.sendMessage(MsgType.SENDMESSAGE + "Stopped the game!");
			}
		}
		return false;
	}

}
