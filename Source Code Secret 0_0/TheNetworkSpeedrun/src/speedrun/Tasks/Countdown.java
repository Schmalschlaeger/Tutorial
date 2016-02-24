package speedrun.Tasks;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import speedrun.Main;

public class Countdown extends BukkitRunnable{
	
	public Main plugin;
	 
    public Countdown(Main plugin){
    this.plugin = plugin;
    }
	
	public static HashMap<String, Integer> timer = new HashMap<String, Integer>();

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (timer.containsKey(all.getName())) {
				timer.put(all.getName(), timer.get(all.getName()) +1);
			}
		}
	}
	BukkitTask timer = new Countdown(this).runTaskTimer(this, 0, 20);
}
