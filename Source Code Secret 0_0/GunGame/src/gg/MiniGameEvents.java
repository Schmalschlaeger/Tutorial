package gg;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MiniGameEvents implements Listener{
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (GunMinigameListener.getManager().isInGame(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	//@EventHandler
	//public void onPlayerLeave(PlayerCommandPreprocessEvent  e) {
	//	if (GunMinigameListener.getManager().isInGame(e.getPlayer())) {
	//		if (!e.getMessage().equals("leave")) {
	//	e.setCancelled(true);
	//		}
	//	}
	//}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (GunMinigameListener.getManager().isInGame(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		if (GunMinigameListener.getManager().isInGame(e.getPlayer())) {
		GunMinigameListener.getManager().removePlayer(e.getPlayer());
		}
	}

}
