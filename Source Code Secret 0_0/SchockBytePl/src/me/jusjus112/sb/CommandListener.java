package me.jusjus112.sb;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd,	String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("activate")) {
			if (args.length == 1) {
				
			}else {
				
			}
		}
		return false;
		
	}
	
}
