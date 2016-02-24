package mazehunter.utils;

import mazehunter.MazeHunter;
import mazehunter.handlers.Game;

import org.bukkit.scheduler.BukkitRunnable;

public class startCountdown extends BukkitRunnable {
	
  	public static int timeUntilStart;
  	
  	MazeHunter plugin;
  	
  	public startCountdown(MazeHunter pl) {
  		plugin = pl;
  	}
	
	public void run() {
				if (timeUntilStart == 0) {
					if (!Game.canStart())	 {
					plugin.resetCountdown();
					ChatManager.broadcast("Cannot start countdown! Resetting!");
					return;
					}
					Game.start();
				 }
				
				if (timeUntilStart % 10 == 0 || timeUntilStart <10){
					ChatManager.broadcast(timeUntilStart + " seconds, until the game starts!");
				}
				timeUntilStart -= 1;
			}

}
