package jeroen.server;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardSB {
	

	static ScoreboardManager manager;
	static Scoreboard board;
	static Objective objective;
	static Player player;
	
	public static void setScoreboard() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		objective = board.registerNewObjective("test", "dummy");
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.BOLD + "" + ChatColor.RED + "Infection");
	}
	
	
	public static void updateScoreboard() {
		setScoreboard();
		
		createScore(ChatColor.GOLD + "Money:", 0);
		createScore("€000", -1);
		createScore("    ", -2);
		createScore(ChatColor.GOLD + "Conditie:",-3);
		createScore("50/100", -4);
		createScore("    ", -5);
		createScore(ChatColor.GREEN + "Virus:", -6);
		createScore("0%", -7);
		createScore("    ", -8);
		createScore(ChatColor.GOLD + "Tijd:", -9);
		createScore("00:00:00", -10);
		
		player.setScoreboard(board);
	}
	
	@SuppressWarnings("deprecation")
	public static void createScore(String name, int place) {
		Score score1 = objective.getScore(Bukkit.getOfflinePlayer(name));
		score1.setScore(place);
	}

}
