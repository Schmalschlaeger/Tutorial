package simple.essentials.Utils;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import simple.essentials.Main;

public class Listeners implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		UUID pUUID = p.getUniqueId();
			Main.playerConfig.set("Players." + pUUID + ".name", p.getName());
			saveUtil.saveLocationToConfig("Players." + pUUID, p.getLocation(), p);
			Main.saveCustomPlayerConfig();
			
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBan(PlayerLoginEvent e) {
    	if (e.getPlayer().isBanned()) {
    		e.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.RED + "You are banned from this server! Reason: "
        	+ ChatColor.WHITE + Main.playerConfig.getString("Players." + e.getPlayer().getUniqueId() + ".banReason"));
    	}
    }
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		saveUtil.saveLocationToConfig("Players." + e.getPlayer().getUniqueId(), e.getPlayer().getLocation(), e.getPlayer());
	}
	
	@EventHandler
	public void onLeavesDespawn(LeavesDecayEvent e) {
		e.setCancelled(true);
	}

}
