package tuts;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class StatsManager {
	
	private static HashMap<String, Integer> kills = new HashMap<String, Integer>();
	private static HashMap<String, Integer> blocks = new HashMap<String, Integer>();
	
	Tutorial plugin;
	
	public StatsManager (Tutorial instance) {
		this.plugin = instance;
	}
	
	public static int getPlayerKills(String playerName) {
		if (!kills.containsKey(playerName)) {
			kills.put(playerName, 0);
		}
		return kills.get(playerName);
	}
	
	public static void addPlayerKills(String playerName, int i) {
		if (kills.containsKey(playerName)) {
			kills.put(playerName, kills.get(playerName) +i);
		}
	}
	
    public static int getBlocksWalked(String playerName) {
    	if (!blocks.containsKey(playerName)) {
    		blocks.put(playerName, 0);
		}
    	return blocks.get(playerName);
	}
	
	public static void addWalkedBlocks(String playerName, int i) {
		if (blocks.containsKey(playerName)) {
			blocks.put(playerName, blocks.get(playerName) +i);
		}
	}

	public static void resetStats() {
		for (OfflinePlayer all : Bukkit.getServer().getOfflinePlayers()) {
			kills.put(all.getName(), 0);
			blocks.put(all.getName(), 0);
		}
	}
}
