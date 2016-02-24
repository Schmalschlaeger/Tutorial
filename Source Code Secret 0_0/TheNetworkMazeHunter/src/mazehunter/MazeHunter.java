package mazehunter;

import java.util.ArrayList;
import java.util.List;

import mazehunter.listeners.entity.EntityDamageByEntity;
import mazehunter.listeners.player.PlayerDeath;
import mazehunter.listeners.player.PlayerJoin;
import mazehunter.listeners.player.PlayerLeave;
import mazehunter.utils.startCountdown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MazeHunter extends JavaPlugin implements Listener{
	
	/*Plannen:
	 * 
	 * Wanner de game begint, update de signs!
	 * Verschillende mappen/worlds
	 * 
	 * Classes:
	 * ChatManager
	 * MazeListener
	 * SignListener
	 * 		
	 */	
	private static int minPlayers = 5;
	private static int maxPlayers = 20;
	private static String getWorld = "GeneratedWorld";
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static int startCountdownID;
	
	public static MazeHunter plugin;
	
	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();
		strings.add("one");
		
		System.out.println(strings.size());
	}
	
	@Override
	public void onEnable() {	
		plugin = this;
		registerListeners();
		GameState.setState(GameState.IN_LOBBY);
		
		startCountdown();
	}	
	
	public static String getWorld() {
		return getWorld;
	}
	
	public static int getMinPlayers() {
		return minPlayers;
	}
	
	public static int getMaxPlayers() {
		return maxPlayers;
	}
	
	public void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new PlayerLeave(this), this);
		pm.registerEvents(new PlayerDeath(this), this);
		pm.registerEvents(new EntityDamageByEntity(this), this);
	}
	
	public void startCountdown() {
		mazehunter.utils.startCountdown.timeUntilStart = 20;
		startCountdownID = Bukkit.getServer()
				.getScheduler()
				.scheduleSyncRepeatingTask(this, new startCountdown(this), 20L, 20L);
	}
	
	public void stopCountdown() {
		getServer().getScheduler().cancelTask(startCountdownID);
	}
	
	public void resetCountdown() {
		stopCountdown();
		startCountdown();
	}

}
