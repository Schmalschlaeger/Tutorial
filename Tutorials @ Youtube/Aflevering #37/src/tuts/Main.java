package tuts;

import net.md_5.bungee.api.ChatColor;

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
	public boolean onCommand(CommandSender s, Command cmd,	String label, String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			if (cmd.getName().equalsIgnoreCase("staff")) {
				if (args.length == 0) {
					//TODO: Verstuur een use /staff message ;)
					return true;
				}
				if (args.length > 0) {
					StringBuilder b = new StringBuilder();
					for (int i = 0; i < args.length; i++) { //Loopt vovaak jij een message hebt getypt. Stel jij hebt 5 woorden getypt, dan maakt hij 5x een loop
						b.append(" ");      //Voeg elke keer achter elk woord een Spatie toe
						b.append(args[i]);  //Voeg elke woord dat je hebt getypt toe
					}
					String message = b.toString(); //Stringbuilder.toString ;)
					for (Player all : Bukkit.getServer().getOnlinePlayers()) {
						if (all.hasPermission("blablabla")) { //TODO: Kies je eigen permissions
							
							all.sendMessage("§7[§cSTAFF-CHAT§7] §6" + p.getDisplayName() + ": §f" + message); //Voorbeeld 1
							
							all.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "STAFF-CHAT" + ChatColor.GRAY + "]" 
							+ ChatColor.GOLD + p.getDisplayName() + ": " + ChatColor.WHITE + message); //Voorbeeld 2
						}
					}
				}
			}
		}
		
		return false;
	}
	
}
