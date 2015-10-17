package tuts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Tutorial extends JavaPlugin implements Listener{

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.getServer().getWorld("world").setTime(16000);
			}
		}, 1, 1);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.getPlayer().getWorld().spawnCreature(e.getPlayer().getLocation(), EntityType.BAT);
		e.getPlayer().getWorld().spawnCreature(e.getPlayer().getLocation(), EntityType.BAT);
		e.getPlayer().getWorld().spawnCreature(e.getPlayer().getLocation(), EntityType.BAT);
		e.getPlayer().getWorld().spawnCreature(e.getPlayer().getLocation(), EntityType.BAT);
		e.getPlayer().getWorld().spawnCreature(e.getPlayer().getLocation(), EntityType.BAT);
		e.getPlayer().getWorld().spawnCreature(e.getPlayer().getLocation(), EntityType.BAT);
		e.getPlayer().getWorld().spawnCreature(e.getPlayer().getLocation(), EntityType.BAT);
		e.getPlayer().getWorld().spawnCreature(e.getPlayer().getLocation(), EntityType.BAT);
		e.getPlayer().getWorld().spawnCreature(e.getPlayer().getLocation(), EntityType.BAT);
		
		e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.IRONGOLEM_DEATH, 1, 1);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = e.getEntity();
		Zombie z = (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
		
		z.setCustomName(ChatColor.GOLD + "ZOMBIE " + p.getName());
		z.setCustomNameVisible(true);

		z.setHealth(100.0);
		z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 2));
		
		Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "A ZOMBIE " + p.getName() + " HAS SPAWNED AND RULED THE WORLD!");
		
		Bukkit.getServer().getWorld(p.getWorld().getName()).setStorm(true);
		Bukkit.getServer().getWorld(p.getWorld().getName()).setTicksPerMonsterSpawns(5);
		Bukkit.getServer().getWorld(p.getWorld().getName()).strikeLightning(p.getLocation());
		}
				
	}

}

