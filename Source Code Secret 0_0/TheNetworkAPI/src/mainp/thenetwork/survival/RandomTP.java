package mainp.thenetwork.survival;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class RandomTP implements Listener{
	
	public void randomTeleportAPlayer(Player p) {
		Random random = new Random();
		Location teleportLocation = null;
		
		int x = random.nextInt(6000) + 1;
	    int y = 150;
	    int z = random.nextInt(6000) + 1;
	    
	    boolean isOnLand = false;
	    
	    while (!isOnLand) {
	    	teleportLocation = new Location(p.getServer().getWorld("world"), x, y, z);
	    	
	    	if (teleportLocation.getBlock().getType() != Material.AIR) {
	    		isOnLand = true;
	    		if (teleportLocation.getChunk().getWorld().isChunkInUse(x, z)) {
	    			p.teleport(new Location(p.getServer().getWorld("world"), teleportLocation.getX(), teleportLocation.getY() + 1.0D, teleportLocation.getZ()));
	    			return;
	    		}
	    	}else { y--; }
	    }
	    p.teleport(new Location(p.getServer().getWorld("world"), teleportLocation.getX(), teleportLocation.getY() + 1.0D, teleportLocation.getZ()));
	}

}
