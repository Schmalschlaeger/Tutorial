package main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.event.Listener;

public class PigRace implements Listener{
	
	public void SpawnPigs() {
		Location loc = new Location( Bukkit.getWorld("world"), 0, 0, 0);
		Pig pig = (Pig) Bukkit.getWorld("world").spawnEntity(loc ,EntityType.PIG);
        pig.teleport(loc);
	}
	
	public void removePigs() {
		
	}

}
