package mazehunter.listeners.player;

import mazehunter.MazeHunter;
import mazehunter.handlers.Team;
import mazehunter.listeners.MGListener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath extends MGListener {

	public PlayerDeath(MazeHunter pl) {
		super(pl);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Team.getTeam(p).remove(p);
		
		p.kickPlayer("You died");
	}

}
