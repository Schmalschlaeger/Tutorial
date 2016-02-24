package speedrun.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import speedrun.Main;

public class Ranks extends BukkitRunnable implements Listener{
	
	public Main plugin;
	 
    public Ranks(Main plugin){
    this.plugin = plugin;
    }
	
	ScoreboardManager sbManager = Bukkit.getScoreboardManager();
	final Scoreboard sBoard = sbManager.getNewScoreboard();
	final Team owner = sBoard.registerNewTeam("owner");
	final Team dev = sBoard.registerNewTeam("developer");
	final Team builder = sBoard.registerNewTeam("builder");
	final Team players = sBoard.registerNewTeam("players");
	final Team vip = sBoard.registerNewTeam("vip");
	final Team hydra = sBoard.registerNewTeam("hydra");
	final Team support = sBoard.registerNewTeam("Support");
	final Team moderator = sBoard.registerNewTeam("Moderator");
	final Team champion = sBoard.registerNewTeam("Champion");

	@SuppressWarnings("deprecation")
	public void createRanks(Player all) {
	    	if (all.hasPermission("network.rank.owner")) {
	    	owner.addPlayer(Bukkit.getOfflinePlayer(all.getName()));
	        owner.setPrefix(ChatColor.RED + ""); 
	    	}else if (all.hasPermission("network.rank.builder")) {
	    	builder.addPlayer(Bukkit.getOfflinePlayer(all.getName()));
	    	builder.setPrefix(ChatColor.GREEN + "");   
	    	}else if (all.hasPermission("network.rank.support")) {
    	      support.addPlayer(Bukkit.getOfflinePlayer(all.getName()));
    	      support.setPrefix(ChatColor.BLUE + "");
    	    }else if (all.hasPermission("network.rank.moderator")) {
      	    moderator.addPlayer(Bukkit.getOfflinePlayer(all.getName()));
      	    moderator.setPrefix(ChatColor.GOLD + "");
      	    }else if (all.hasPermission("network.rank.developer")) {
	    	dev.addPlayer(Bukkit.getOfflinePlayer(all.getName()));
    	    dev.setPrefix(ChatColor.DARK_GREEN + "");  
	    	}else if (all.hasPermission("network.rank.vip")) {
	    	vip.addPlayer(Bukkit.getOfflinePlayer(all.getName()));
	    	vip.setPrefix(ChatColor.YELLOW + "");   
	    	}else if (all.hasPermission("network.rank.hydra")) {
	    	hydra.addPlayer(Bukkit.getOfflinePlayer(all.getName()));
    	    hydra.setPrefix(ChatColor.LIGHT_PURPLE + "");
	    	}else if (all.hasPermission("network.rank.champion")) {
	    		champion.addPlayer(Bukkit.getOfflinePlayer(all.getName()));
	    		champion.setPrefix(ChatColor.AQUA + "" + ChatColor.BOLD + "");  
        	}else {
	    		players.addPlayer(Bukkit.getOfflinePlayer(all.getName()));
    	    	players.setPrefix(ChatColor.GRAY + "");
	    	}
	    		all.setScoreboard(sBoard);
	}
	
		@SuppressWarnings("deprecation")
		public void run() {
	    	for (final Player all : Bukkit.getServer().getOnlinePlayers()) {
	    		createRanks(all);
	    	}	    		
		}
		
		@SuppressWarnings("deprecation")
		@EventHandler
		public void onPlayerJoin(PlayerJoinEvent e) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				createRanks(all);
			}
		}

}
