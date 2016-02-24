package jeroen.server;

import org.bukkit.entity.Player;

public class EventsSB {
	
	public Main plugin;
	
	public EventsSB(Main plugin) {
		this.plugin = plugin;
	}
	
	static Player player;
	
	public void createScoreboard() {
						SimpleScoreboard scoreboard = new SimpleScoreboard("§c§lInfection:");
						// if you dont specify a score it will display them in the order you add them in
						scoreboard.add("§6§lMoney:");
						scoreboard.add("€0000");
						scoreboard.blankLine();
						scoreboard.add("§6§lConditie");
						scoreboard.add("50/100");
						scoreboard.blankLine();
						scoreboard.add("§a§lVirus:");
						scoreboard.add("20%");
						scoreboard.blankLine();
						scoreboard.add("§6§lTijd:");
						scoreboard.add("00:00:00");
						// the text can be up to 48 characters long (including color codes)
						// call this to create the scoreboard, nothing will happen if you forget to call this
						scoreboard.build();
						// send the scoreboard to the player(s), takes an array
						scoreboard.send(player);
	}

}
