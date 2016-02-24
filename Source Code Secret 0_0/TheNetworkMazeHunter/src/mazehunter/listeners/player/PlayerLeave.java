package mazehunter.listeners.player;

import mazehunter.GameState;
import mazehunter.MazeHunter;
import mazehunter.handlers.Game;
import mazehunter.handlers.Team;
import mazehunter.listeners.MGListener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave extends MGListener{

	public PlayerLeave(MazeHunter pl) {
		super(pl);
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		if (GameState.isState(GameState.IN_LOBBY))
			Game.setCanStart(Bukkit.getOnlinePlayers().length - 1 >= 8);
		
		Player player = e.getPlayer();
		
		if (Game.hasStarted())
			Team.getTeam(player).remove(player);
	}
	
}
