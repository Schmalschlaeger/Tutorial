package me.jusjus112;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener{
	
	//private Main plugin;
	 
	//public Listeners(Main instance) {
	//    this.plugin = instance;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {	
		if (e.getPlayer().hasPermission("network.rank.owner")) {
			Main.admins.add(e.getPlayer());
			for (String mes : Main.messages) {
			e.getPlayer().sendMessage(mes);
			Main.messages.clear();
			}
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		if (e.getPlayer().hasPermission("network.rank.owner")) {
			Main.admins.remove(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onProjectileHitEvent(ProjectileHitEvent e) {
		Projectile proj = e.getEntity();
		if (proj instanceof Fireball) {
			Fireball ball = (Fireball) proj;
			ball.getWorld().createExplosion(ball.getLocation(), 15);
		}
	}

}
