package tuts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
 
public class Tutorial extends JavaPlugin implements Listener {
	
	public void onEnable() {
         Bukkit.getServer().getPluginManager().registerEvents(this, this);                      
 }
	 
	 @SuppressWarnings("deprecation")
	public void createScoreboard(Player all) {	
			ScoreboardManager manager = Bukkit.getScoreboardManager();
		    Scoreboard board = manager.getNewScoreboard();
		    
		 Objective obj = board.registerNewObjective("dummy", "Kills");
         obj.setDisplaySlot(DisplaySlot.SIDEBAR);
         obj.setDisplayName(ChatColor.RED + "Kills:" + ChatColor.RESET);
         
         Score score1 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Kills:" + ChatColor.RESET));
         Score score2 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Deaths:" + ChatColor.RESET));
         
         score1.setScore(20);
         score2.setScore(10);
         
             all.setScoreboard(board);
	 }
	
		@EventHandler
		public void onPlayerJoin(PlayerJoinEvent e) {
			Player p = e.getPlayer();
			if (p.getWorld().getName().equals("world")) {
		        	createScoreboard(e.getPlayer());
			}else {
				e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			}
		}
		
		@EventHandler
		public void onPlayerSwitch(PlayerChangedWorldEvent e) {
			if (e.getPlayer().getWorld().getName().equals("world")) {
				if (!e.getPlayer().getScoreboard().equals(Bukkit.getScoreboardManager())) {
			    	createScoreboard(e.getPlayer());
				}
				}else {
					e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
				}
		}
	
}