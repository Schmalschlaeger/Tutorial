package tuts;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public String motdServer = null;
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new StaffJoin(), this);
	}
	
		public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 if (cmd.getName().equalsIgnoreCase("setmotd")) {
				 if (args.length == 0) {
					 s.sendMessage("Please specify a motd!");
					 return true;
				 }
				 
				 StringBuilder str = new StringBuilder();
				 for (int i = 0; i < args.length; i++) {
					 str.append(args[i] + " ");
				 }
				 String motd = str.toString().replaceAll("&", "§").replaceAll("%player%", s.getName());
				 
				 motdServer = motd;
				 
				 s.sendMessage("MOTD set to: " + motd);
			 }
		 }
		 return false;
	 }
	 
	 @EventHandler
	 public void onServerPingEvent(ServerListPingEvent e) {
		 String motd = motdServer;
		 e.setMotd(motd);
	 }
	
}