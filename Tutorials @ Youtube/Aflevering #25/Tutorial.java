package tuts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Tutorial extends JavaPlugin implements Listener {
       
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onFlyHacking(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location from = e.getFrom();
	    Location to = e.getTo();
		double distance = from.distance(to);
		if (Double.compare(distance, .48) > 0) {
			if (to.getY() - from.getY() < 0D) {
				p.sendMessage(ChatColor.RED + "STOP! you are hacking right now! A admin is tiped right now!");
				p.getLocation().setY(p.getLocation().getY() + 1.0);
			}
		}
	}
	
	
	
}