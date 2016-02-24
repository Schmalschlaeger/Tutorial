package PVPGame.Utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class StatsManager {
	
	private static HashMap<String, Integer> kills = new HashMap<String, Integer>(); //Stats of the player his 
	private static HashMap<String, Integer> deaths = new HashMap<String, Integer>(); //Stats of the player his deaths

	public static int getPlayerKills(String Playername) {
		if (!kills.containsKey(Playername)) {
			kills.put(Playername, 0);
		}
		return kills.get(Playername);
	}
	
	public static int getPlayerDeaths(String Playername) {
		if (!deaths.containsKey(Playername)) {
			deaths.put(Playername, 0);
		}
		return deaths.get(Playername);
	}
	
	public static void setPlayerDeaths(String Playername, int i) {
		deaths.put(Playername, i);
	}
	
	public static void addPlayerDeaths(String Playername, int i) {
		if (deaths.containsKey(Playername)) {
			deaths.put(Playername, deaths.get(Playername) + i);
		}else {
			setPlayerDeaths(Playername, 0 + i);
		}
	}
	
	public static void setPlayerKills(String Playername, int i) {
			kills.put(Playername, i);		
	}
	
	public static void addPlayerKills(String Playername, int i) {
		if (kills.containsKey(Playername)) {
			kills.put(Playername, kills.get(Playername) + i);
		}else {
			setPlayerKills(Playername, i);
		}
	}
	
	public static void resetStats() {
		for (OfflinePlayer all : Bukkit.getServer().getOfflinePlayers()) {
		kills.put(all.getName(), 0);
		deaths.put(all.getName(), 0);
		}
	}
}
