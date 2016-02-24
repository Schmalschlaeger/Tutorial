package Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import Main.MPMGMain;
import Utils.GameManager;
import Utils.PlayerManager;

public class PlayerJoinServerEvent {

	//Load objects
	private PlayerManager playerManager = new PlayerManager();

	//Player spawn even
	public void togglePlayerJoinServerEvent(Player player) {
		//Add player to the PlayerManager
		playerManager.MiniGamePlayer(player);
		org.bukkit.Location location = player.getLocation();
		//GameManager.setupScoreboard(player);


		if (GameManager.isGameActive() == false) {	
			//Get world that is being played on (lobby or game world)
		    World world = Bukkit.getWorld(MPMGMain.getWorld());
		    player.teleport(new Location(world , -1402, 10, -12));
		    player.setPlayerTime(6000, false); //Set world time
		    //play a sound
		    player.playSound(location, Sound.SUCCESSFUL_HIT, 1, 10);
		} else {
			//Player must be in a game.. right?
			//Get world that is being played on (lobby or game world)
		    World world = Bukkit.getWorld(MPMGMain.getWorld());
		    player.teleport(new Location(world , -1402, 10, -12));
		    player.setPlayerTime(6000, false); //Set world time
		    //play a sound
		    player.playSound(location, Sound.LEVEL_UP, 1, 10);
		    player.sendMessage(ChatColor.GOLD + "Sorry, but this game is already ingame! You have been teleported to the spectators spawn!");
		}
	}

}