package server.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class BasicEvents implements Listener{
	
	@EventHandler
	public void onWheaterChange(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onThunderChange(ThunderChangeEvent e) {
		e.setCancelled(true);
	}

}
