package tuts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Tutorial extends JavaPlugin implements Listener{

	public void onEnable() { 	
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.broadcastMessage(ChatColor.RED + "ITS WORKING!!");
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
		if (cmd.getName().equalsIgnoreCase("restart")) {
			if (sender.hasPermission("reload.acces")) {
				Bukkit.getPluginManager().disablePlugin(this);
				Bukkit.getPluginManager().enablePlugin(this);
				sender.sendMessage(ChatColor.GOLD + "Restarted!");
			}
		}
		if (cmd.getName().equalsIgnoreCase("preload")) {
			if (sender.hasPermission("reload.commands")) {
				reloadConfig();
				sender.sendMessage(ChatColor.GOLD + "Reloaded!");
			}
		}
		return false;
	}

}

