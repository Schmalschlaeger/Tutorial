package Pretpark;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class bossbar implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		try {
			MobBarAPI.getInstance().setStatus(p, ChatColor.GOLD + "" + ChatColor.BOLD + "Welcome to " + ChatColor.RESET + "" + ChatColor.BOLD + ChatColor.AQUA + "Theme" + ChatColor.DARK_AQUA + "Park " + ChatColor.GOLD + "" + ChatColor.BOLD + p.getName(), 100, false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
		
		try {
			MobBarAPI.getInstance().setStatus(p, ChatColor.GOLD + "" + ChatColor.BOLD + "Welcome to " + ChatColor.RESET + "" + ChatColor.BOLD + ChatColor.AQUA + "Theme" + ChatColor.DARK_AQUA + "Park " + ChatColor.GOLD + "" + ChatColor.BOLD + p.getName(), 100, false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	

}
