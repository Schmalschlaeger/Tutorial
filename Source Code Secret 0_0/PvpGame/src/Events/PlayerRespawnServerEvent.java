package Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import Main.MPMGMain;
import Utils.GameManager;

public class PlayerRespawnServerEvent {

	public void togglePlayerRespawnServerEvent(Player player) {		
		if (GameManager.isGameActive() == false) {
			//Get world that is being played on (lobby or game world)
			WorldCreator c = new WorldCreator(MPMGMain.getWorld());
			c.createWorld();
		    World world = Bukkit.getWorld(MPMGMain.getWorld());
		    player.teleport(new Location(world , 0.5, 71, 0.5));

		    //play a sound
		    org.bukkit.Location location = player.getLocation();
		    player.playSound(location, Sound.LEVEL_UP, 1, 10);
		} else {
			//Player must be in a game.. right?
			//Get world that is being played on (lobby or game world)
			WorldCreator c = new WorldCreator(MPMGMain.getWorld());
			c.createWorld();
		    World world = Bukkit.getWorld(MPMGMain.getWorld());
		    player.teleport(new Location(world , 0.5, 71, 0.5));

		    //play a sound
		    org.bukkit.Location location = player.getLocation();
		    player.playSound(location, Sound.LEVEL_UP, 1, 10);
		}
	}

}