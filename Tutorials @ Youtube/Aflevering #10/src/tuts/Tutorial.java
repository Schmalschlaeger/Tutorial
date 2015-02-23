package tuts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
 
public class Tutorial extends JavaPlugin implements Listener {
       
        @SuppressWarnings("deprecation")
		public void onEnable() {
                Bukkit.getServer().getPluginManager().registerEvents(this, this);              
               
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard board = manager.getNewScoreboard();
               
                Objective obj = board.registerNewObjective("dummy", "Kills");
                obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                obj.setDisplayName(ChatColor.RED + "Kills:" + ChatColor.RESET);
               
                Score score1 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Kills:" + ChatColor.RESET));
                Score score2 = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Deaths:" + ChatColor.RESET));
               
                score1.setScore(20);
                score2.setScore(10);           
               
                for (Player all : Bukkit.getOnlinePlayers()) {
                        all.setScoreboard(board);
                }
        }
       
}