package mazehunter.utils;

import mazehunter.MazeHunter;
import mazehunter.handlers.Team;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationUtilities {
	
	private static Location spawnlocation = new Location(Bukkit.getWorld(MazeHunter.getWorld()), 200.5, 88.5, -98.5);
	
	public static void teleportToSpawn(Player p) {
		p.teleport(spawnlocation);
	}
	
	public static void teleportAllPlayersToSpawn() {
		for(Player p :Bukkit.getOnlinePlayers()) 
			teleportToSpawn(p);
	}
	
	public static void teleportToGame(Player p, Team team) {
		p.teleport(team.getSpawn());
	}

}
