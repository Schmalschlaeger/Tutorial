package Hello;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class helloworld extends JavaPlugin {
	
	public void onEnable() {
		getServer().getLogger().info("Plugin enabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel) {
		Player p = (Player) sender;
		
		if (commandLabel.equalsIgnoreCase("hello")) {
			p.sendMessage(ChatColor.GOLD + "hello back");
			return true;
		}
		return false;
		
	}

}
