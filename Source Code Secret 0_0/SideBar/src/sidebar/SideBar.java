package sidebar;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.bionicrm.side.Line;
import com.gmail.bionicrm.side.ScoreText;
import com.gmail.bionicrm.side.Side;
import com.gmail.bionicrm.side.SideAPI;

public class SideBar extends JavaPlugin implements Listener {
	
	private Side side = SideAPI.getSide();
	
	public void onEnable() {
		
	}
	
	public void createFunMessage(Player player, String scoreLine) {
	    ScoreText scoreText = side.getPlayerScoreText(player);
	    Line line = new Line("funMsg", scoreLine);

	    scoreText.addLine(line);
	    side.update(player);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		createFunMessage(e.getPlayer(), "&cYOLO");
	}

}
