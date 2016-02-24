package server.Achievements;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import server.Main;
import server.MessageManager;

public class Achievements {
	
	public static int totalAchievements = 4;
	
	/*'
	 * Total Achievements Lobby
	 * 
	 * 1. Server Joiner - Join for the first time  - serverjoiner
	 * 2. You are populair - Get 5 friends         - populair5
	 * 3. Meet infobot - Be friends with infobot   - infobot
	 * 4. Complete 10 achievements                 - achievements10
	 */
	
	public static Integer getAchievementFromUser(Player p) {		
		return Main.AchievementConfig.getInt("players." + p.getUniqueId() + ".achievements.total");
	}
	
	public static boolean isAchievementNotReached(Player p, String achievement) {
		FileConfiguration config = Main.AchievementConfig;
		return config.getString("players." + p.getUniqueId() + ".achievements." + achievement + ".status", "not reached").equalsIgnoreCase("not reached");
	}
	
	public static boolean isAchievementisDone(Player p, String achievement) {
		FileConfiguration config = Main.AchievementConfig;
		return config.getString("players." + p.getUniqueId() + ".achievements." + achievement + ".status", "done").equalsIgnoreCase("done");
	}
	
	public static boolean isAchievementisBusy(Player p, String achievement) {
		FileConfiguration config = Main.AchievementConfig;
		return config.getString("players." + p.getUniqueId() + ".achievements." + achievement + ".status", "busy").equalsIgnoreCase("busy");
	}
	
	public static void addTotalAchievements(Player p) {
		Main.AchievementConfig.set("players." + p.getUniqueId() + ".achievements.total", getAchievementFromUser(p)+1);
		Main.saveCustomAchievementConfig();
		
		if (getAchievementFromUser(p) == 1) {
			Achievements.setAchievementOnBusy("achievements10", p);
		}else if (getAchievementFromUser(p) == 10) {
			Achievements.setAchievementDone("achievements10", p, "Complete 10 Achievements");
		}
	}
	
	public static void removeTotalAchievements(Player p) {
		Main.AchievementConfig.set("players." + p.getUniqueId() + ".achievements.total", getAchievementFromUser(p)-1);
		Main.saveCustomAchievementConfig();
		
		if (getAchievementFromUser(p) == 1) {
			Achievements.setAchievementOnBusy("achievements10", p);
		}else if (getAchievementFromUser(p) == 10) {
			Achievements.setAchievementDone("achievements10", p, "Complete 10 Achievements");
		}
	}

	public static void createAchievementConfig(Player p) {
		FileConfiguration config = Main.AchievementConfig;
		
			if (config.getString("players." + p.getUniqueId() + ".achievements.serverJoiner.status") == null) {
				config.set("players." + p.getUniqueId() + ".achievements.serverJoiner.status", "not reached");
				Main.saveCustomAchievementConfig();
			}
			if (config.getString("players." + p.getUniqueId() + ".achievements.populair5.status") == null) {
				config.set("players." + p.getUniqueId() + ".achievements.populair5.status", "not reached");
				Main.saveCustomAchievementConfig();
			}
			if (config.getString("players." + p.getUniqueId() + ".achievements.infobot.status") == null) {
				config.set("players." + p.getUniqueId() + ".achievements.infobot.status", "not reached");
				Main.saveCustomAchievementConfig();
			}
			if (config.getString("players." + p.getUniqueId() + ".achievements.achievements10.status") == null) {
				config.set("players." + p.getUniqueId() + ".achievements.achievements10.status", "not reached");
				Main.saveCustomAchievementConfig();
			}
		}
	
	public static void setAchievementOnBusy(String achievement, Player p) {
		FileConfiguration config = Main.AchievementConfig;
		if (config.getString("players." + p.getUniqueId() + ".achievements." + achievement + ".status") == "not reached" 
				|| !(config.getString("players." + p.getUniqueId() + ".achievements." + achievement + ".status") == "done")) {
		config.set("players." + p.getUniqueId() + ".achievements." + achievement + ".status", "busy");
		Main.saveCustomAchievementConfig();
		}
	}
	
	public static void setAchievementDone(String achievement, Player p, String achievementMessage) {
		FileConfiguration config = Main.AchievementConfig;
		if (config.getString("players." + p.getUniqueId() + ".achievements." + achievement + ".status", "not reached").equalsIgnoreCase("not reached")
				|| (config.getString("players." + p.getUniqueId() + ".achievements." + achievement + ".status", "not reached").equalsIgnoreCase("busy"))) {
		config.set("players." + p.getUniqueId() + ".achievements." + achievement + ".status", "done");
		Main.saveCustomAchievementConfig();
		addTotalAchievements(p);
		MessageManager.giveAchievementMessage(p, achievementMessage);
		}
	}
	
	public static void setAchievementNotReached(String achievement, Player p) {
		FileConfiguration config = Main.AchievementConfig;
		if (config.getString("players." + p.getUniqueId() + ".achievements." + achievement + ".status", "done").equalsIgnoreCase("done")
				|| (config.getString("players." + p.getUniqueId() + ".achievements." + achievement + ".status", "busy").equalsIgnoreCase("busy"))) {
		config.set("players." + p.getUniqueId() + ".achievements." + achievement + ".status", "not reached");
		removeTotalAchievements(p);
		Main.saveCustomAchievementConfig();
		}
	}

}
