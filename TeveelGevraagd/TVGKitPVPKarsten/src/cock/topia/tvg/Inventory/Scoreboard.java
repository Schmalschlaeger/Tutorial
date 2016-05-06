package cock.topia.tvg.Inventory;

import me.robin.battlelevels.api.BattleLevelsAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import cock.topia.tvg.Main;
import cock.topia.tvg.Utils.MoneyManager;

public class Scoreboard {
	
	Main plugin;
	public Scoreboard(Main plugin) {
		this.plugin = plugin;
	}

	 public void scoreboard(Player p) {
		MoneyManager mm = new MoneyManager(plugin);
	    ScoreboardManager manager = Bukkit.getServer().getScoreboardManager();
	    org.bukkit.scoreboard.Scoreboard scoreboard = manager.getNewScoreboard();

	    Objective obj = scoreboard.registerNewObjective("Board", "dummy");
	    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
	    obj.setDisplayName(ChatColor.GREEN + "§lKitPvp Stats");
	    Score killss = obj.getScore(ChatColor.AQUA + "§lKillstreak: ");
	    killss.setScore(BattleLevelsAPI.getKillstreak(p.getUniqueId()));
	    Score kills = obj.getScore(ChatColor.DARK_RED + "§lKills: ");
	    kills.setScore(BattleLevelsAPI.getKills(p.getUniqueId()));
	    Score death = obj.getScore(ChatColor.DARK_GRAY + "§lDeaths: ");
	    death.setScore(BattleLevelsAPI.getDeaths(p.getUniqueId()));

	    Score Money2 = obj.getScore(ChatColor.GOLD + "§lMoney: ");
	    Money2.setScore(mm.getMoney(p));
	    Score ks = obj.getScore(ChatColor.GREEN + "§lTop killstreak: ");
	    ks.setScore(BattleLevelsAPI.getTopKillstreak(p.getUniqueId()));
	    Score lv = obj.getScore(ChatColor.BLUE + "§lLevel: ");
	    lv.setScore(BattleLevelsAPI.getLevel(p.getUniqueId()));
	    Score tvg1 = obj.getScore(ChatColor.DARK_AQUA + "    ");
	    tvg1.setScore(-1);
	    Score tvg = obj.getScore(ChatColor.RED + "" + ChatColor.ITALIC + "play.TeVeelGevraagd.nl ");
	    tvg.setScore(-2);

	    p.setScoreboard(scoreboard);
	  }
	
}
