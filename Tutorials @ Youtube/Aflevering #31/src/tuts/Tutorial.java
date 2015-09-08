package tuts;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Tutorial extends JavaPlugin {
	
	API h = new API(this);
	FileConfiguration config = null;

	public void onEnable() { 	        
       config = getConfig();
       getConfig().options().copyDefaults(true);
       saveConfig();
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("ride")) {
		if (args.length == 0 || args.length == 1) {
			p.sendMessage(ChatColor.RED + "Unknown command! Try /ride close/open create/remove");
			return true;
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("create")) {
				h.createRide(p, args[1]);
			}
			if (args[0].equalsIgnoreCase("remove")) {
				h.removeRide(p, args[1]);
			}
			if (args[0].equalsIgnoreCase("close")) {
				h.setRideClosed(args[1], p);
			}
			if (args[0].equalsIgnoreCase("open")) {
				h.setRideOpen(args[1], p);
			}
		}
		}
		return false;
	}
	
}