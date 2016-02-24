package tuts;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	 @Override
		public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("fly")) {
				 GM.changeGamemode(GM.FLY, p);
				 return true;
			 }
			 if (cmd.getName().equalsIgnoreCase("gamemode")) {
				 if (args.length == 1) {
					 if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
						 GM.changeGamemode(GM.CREATIVE, p);
					 }else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
						 GM.changeGamemode(GM.ADVENTURE, p);
					 }else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spec")) {
						 GM.changeGamemode(GM.SPECTATOR, p);
					 }else if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
						 GM.changeGamemode(GM.SURVIVAL, p);
					 }
				 }
			 }
		 }
		 return false;
	 }
}