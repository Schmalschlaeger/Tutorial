package tuts;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Tutorial extends JavaPlugin implements Listener {
       
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (cmd.getName().equalsIgnoreCase("help")) {
			if (args.length > 0) {
				//TODO: Check for if args == null
				if (args[0].equalsIgnoreCase("test")) {
					//TODO: Code.....
					sender.sendMessage("WORKS!");
					return true;
				}
				if (args[1].equalsIgnoreCase("config")) {
					//TODO: Code.....
					sender.sendMessage("WORKS!");
					return true;
				}
				@SuppressWarnings("deprecation")
				Player p = Bukkit.getPlayer(args[0]);
				
				if (p == null) {
					return true;
				}
				//TODO: Code.....
				sender.sendMessage("WORKS!");
				return true;
			}
		}
		return false;
	}
}