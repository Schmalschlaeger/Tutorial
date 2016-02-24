package mainp.thenetwork.lobby;

import java.util.ArrayList;

import mainp.thenetwork.MessageManager.MsgType;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * @author jusjus112. All rights reserved.
 */
public class CommandManager implements Listener{
	
	private ArrayList<String> commands = new ArrayList<String>();
	
	public void setPlayerGamemode(Player p, GameMode mode) {
		p.setGameMode(mode);
	}
	
	/**
     * Check the config for a value
     * @param p Just a single player
     * @param number Wich flyspeed we must take?
     */
	public void setPlayerFlySpeed(Player p, int number) {
		p.setAllowFlight(true);
		p.setFlySpeed(number);
	}
	/**
     * Set the player his fly enabled or disabled?
     * @param p Just a single player
     * @param toggle Enabled or disabled?
     */
	public void setPlayerFly(Player p, boolean toggle) {
		p.setAllowFlight(toggle);
		p.setFlying(toggle);
	}
	
	public void addAdisabledCommand(String command) {
		this.commands.add(command);
	}
	
	public void broadcastMessage(String message) {
		Bukkit.broadcastMessage(MsgType.BROADCAST + message);
	}
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
		for (String blockedcmd : commands) {
		if (e.getMessage().contains(blockedcmd)) {
			e.setCancelled(true);
		}
		}
	}

}
