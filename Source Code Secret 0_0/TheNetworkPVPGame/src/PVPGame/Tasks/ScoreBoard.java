package PVPGame.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import PVPGame.Main;

public class ScoreBoard extends BukkitRunnable{
	
	public Main plugin;
	 
    public ScoreBoard(Main plugin){
    this.plugin = plugin;
    }

	@Override
	public void run() {
			if (Bukkit.getOnlinePlayers().size() > 0) {
				//GameManager.getManager().updateScoreBoard();
		}
	}

}
