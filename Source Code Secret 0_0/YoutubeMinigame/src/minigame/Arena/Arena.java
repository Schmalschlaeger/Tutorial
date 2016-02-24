package minigame.Arena;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;

public class Arena {
	
	public int id;
    private final Location spawn;
    private final List<UUID> players = new ArrayList<UUID>();
 
    public Arena(Location spawn, int id) {
        this.spawn = spawn;
        this.id = id;
    }
 
    public int getId() {
        return id;
    }
 
    public List<UUID> getPlayers() {
        return players;
    }
    
    public Location getSpawn() {
    	return spawn;
    }

}
