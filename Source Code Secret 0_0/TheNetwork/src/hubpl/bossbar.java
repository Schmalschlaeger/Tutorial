package hubpl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import hubpl.Hub;

public class bossbar implements Listener {
	
	String boss = ChatColor.GOLD + "" + ChatColor.BOLD + "Welcome to " 	+ ChatColor.RESET + "" + ChatColor.BOLD + ChatColor.AQUA + "The" + ChatColor.DARK_AQUA + "Network " 
	+ ChatColor.GOLD + "";
	
	
	private Hub plugin;
	 
	public bossbar(Hub instance) {
	    this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					try {
						MobBarAPI.getInstance().setStatus(p, ((String) plugin.getConfig().get("bossbar.text")).replace("{player}", p.getName()), 100, true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, 4);
		}
	
	@EventHandler
	public void onPlayerDeath(PlayerRespawnEvent e) {
		final Player p = e.getPlayer();

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					try {
						MobBarAPI.getInstance().setStatus(p, ((String) plugin.getConfig().get("bossbar.text")).replace("{player}", p.getName()), 100, true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, 4);
		}
	
	

}
