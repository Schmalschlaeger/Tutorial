package coins.jusjus;

import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener{

	private Main plugin;
	 
	public Events(Main instance) {
	    this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String name = p.getName();
		
		try {
			Statement statement = plugin.c.createStatement();
			statement.executeUpdate("INSERT INTO Coins (`PlayerName`, `Coins`) VALUES ('" + name + "', '0') ON DUPLICATE KEY UPDATE Coins=Coins+1;");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
