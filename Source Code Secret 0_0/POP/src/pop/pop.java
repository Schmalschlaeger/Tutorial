package pop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import pop.ParticleEffects;

public class pop extends JavaPlugin implements Listener {
		
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
			
			 }
	
	@EventHandler
	public void onEntityByDamagerEntity(EntityDamageByEntityEvent e) {
		Player damaging = (Player)e.getDamager();
		Player hurt = (Player) e.getEntity();
		if(e.getEntity() instanceof Player) {
			if (hurt.getGameMode() == GameMode.CREATIVE) {
				damaging.sendMessage(ChatColor.YELLOW + "Sorry, but this player cant be popped!");
				return;
			}
		    	if(e.getDamager() instanceof Player) {	
				e.setCancelled(true);
				damaging.hidePlayer(hurt);
				damaging.setLevel(damaging.getLevel() + 1);
				damaging.playSound(hurt.getLocation(), Sound.EXPLODE, 1, 1);
				ParticleEffects.HEART.display(hurt.getLocation().add(0.5D, 0.5D, 0.5D), 0.0f, 0.5f, 0.0f, 1.0F, 30);
				damaging.sendMessage(ChatColor.GREEN + "Popped " + hurt.getDisplayName());
			}
		}
	}
		
		@EventHandler
		public void onPlayerQuit(PlayerQuitEvent e) {
			Player p = e.getPlayer();
			p.setLevel(0);
		}

	}


			 


	  
