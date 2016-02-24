package simple.essentials.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand implements CommandExecutor{
	
	 @Override
		public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 //Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("")) {
			
			 }
		 }
		 return false;
	 }

}
