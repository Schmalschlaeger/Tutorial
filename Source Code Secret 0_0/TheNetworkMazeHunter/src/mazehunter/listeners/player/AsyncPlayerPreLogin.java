package mazehunter.listeners.player;

import mazehunter.MazeHunter;
import mazehunter.handlers.Game;
import mazehunter.listeners.MGListener;

import org.bukkit.event.EventHandler;

public class AsyncPlayerPreLogin extends MGListener{

	public AsyncPlayerPreLogin(MazeHunter pl) {
		super(pl);
	}
	
	@EventHandler
	public void preLogin(AsyncPlayerPreLogin e) {
		if (Game.hasStarted()) {
			
		}
	}

}
