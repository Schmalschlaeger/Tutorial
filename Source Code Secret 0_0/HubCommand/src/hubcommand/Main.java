package hubcommand;

import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	FileConfiguration config = null;
	
	public void onEnable() {
		config =  getConfig();
    	getConfig().options().copyDefaults(true);
        saveDefaultConfig();
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	
	public void serverConnect(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only player can use this command!");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("hubcommandreload")) {
			if (!p.hasPermission("hub.reload")) {
				p.sendMessage(ChatColor.RED + "You dont have permissions!");
				return true;
			}
			reloadConfig();
			sender.sendMessage(ChatColor.GOLD + "Config reloaded!");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("hub")) {
			if (!p.hasPermission("hub.command")) {
				p.sendMessage(ChatColor.RED + "You dont have permissions!");
				return true;
			}
			serverConnect(getConfig().getString("server"), p);
			return true;
		}
		return false;		
	}

}
