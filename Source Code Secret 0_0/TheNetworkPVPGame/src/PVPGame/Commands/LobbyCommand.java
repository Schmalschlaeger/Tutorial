package PVPGame.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import PVPGame.Main;
import PVPGame.Utils.MessageManager.MsgType;

public class LobbyCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private Main plugin;
	   
    public LobbyCommand(Main plugin){
    	this.plugin = plugin;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if (cmd.getName().equalsIgnoreCase("lobby")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.teleport(Bukkit.getWorld(Main.getWorldLobby()).getSpawnLocation());
				sender.sendMessage(MsgType.SENDMESSAGE + "Sended you to the lobby!");
			}
		}
		return false;
	}
	
	

}
