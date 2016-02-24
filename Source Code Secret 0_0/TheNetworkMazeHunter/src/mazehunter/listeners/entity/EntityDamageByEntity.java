package mazehunter.listeners.entity;

import mazehunter.MazeHunter;
import mazehunter.handlers.Game;
import mazehunter.handlers.Team;
import mazehunter.listeners.MGListener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity extends MGListener{

	public EntityDamageByEntity(MazeHunter pl) {
		super(pl);
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player && e.getDamager() instanceof Player)) {
			e.setCancelled(true);
			return;
		}
		if (!Game.hasStarted()) {
			e.setCancelled(true);
			return;
		}
		Player player = (Player) e.getEntity();
		Player damager = (Player) e.getDamager();
		
		if (Team.getTeam(player) == Team.getTeam(damager)) {
			e.setCancelled(true);
			return;
		}
		
	}

}
