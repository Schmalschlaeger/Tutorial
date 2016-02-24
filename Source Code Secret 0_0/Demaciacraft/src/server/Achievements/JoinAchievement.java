package server.Achievements;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinAchievement implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if (!p.hasPlayedBefore()) {
			Achievements.setAchievementDone("serverjoiner", p, "Server Joiner");
		}
	}

}
