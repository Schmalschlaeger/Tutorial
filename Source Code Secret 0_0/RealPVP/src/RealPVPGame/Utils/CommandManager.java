package RealPVPGame.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import RealPVPGame.Utils.MessageManager.MsgType;

public class CommandManager {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] arg, String[] args) {
		if (sender instanceof Player) {
		if (cmd.getName().equalsIgnoreCase("realffa")) {
			
			if (args.length == 0) {
				sender.sendMessage(MsgType.COMMAND_NOT_FOUND + "");
				return true;
			}
			
			
			
		}
	}else {
		sender.sendMessage(MsgType.WARNING + "Sorry, you arent a player! Login in minecraft and then try again");
		return true;
	}
	return false;
}

}
