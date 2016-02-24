package mazehunter.handlers;

import mazehunter.MazeHunter;
import mazehunter.utils.LocationUtilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Game {
	
	private static boolean canStart = false;
	private static boolean hasStarted = false;

	public static boolean canStart() {
		return canStart;
	}
	
	public static void setCanStart(boolean b) {
		canStart = b;
	}
	
	public static void start() {
		hasStarted = true;
		new Team("Red", new Location(Bukkit.getWorld(MazeHunter.getWorld()), 223.5, 75.5, -106.5));
		new Team("Blue", new Location(Bukkit.getWorld(MazeHunter.getWorld()), 217.5, 75.5, -106.5));
		
		int i = 0;
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (i >= Team.getAllTeams().size())
				i=0;
			Team.getAllTeams().get(i++).add(player);
			LocationUtilities.teleportToGame(player, Team.getAllTeams().get(i));
			i++;
		}
	}
	
    public static void stop(Team team) {
    	hasStarted = false;
	}
    
    public static boolean hasStarted() {
    	return hasStarted;
    }
}
