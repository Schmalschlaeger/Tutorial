package Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class pets extends JavaPlugin implements Listener{
	
	AbilitySummon pets = new AbilitySummon();
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new AbilitySummon(), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (pets.isBone(p)) {
					int x = e.getClickedBlock().getX();
					int y = e.getClickedBlock().getY();
					int z = e.getClickedBlock().getZ();
					 
					Location loc = new Location(p.getWorld(), x, y+1, z);
					pets.spawnWolf(p, loc);
	    	}
	    }
			}
		}
	
	@EventHandler
	public void onPlayerInteract1(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (pets.isBlazeRod(p)) {
					int x = e.getClickedBlock().getX();
					int y = e.getClickedBlock().getY();
					int z = e.getClickedBlock().getZ();
					 
					Location loc = new Location(p.getWorld(), x, y+1, z);
					pets.spawnCustomBlaze(p, loc);
	    	}
	    }
			}
		}

}
