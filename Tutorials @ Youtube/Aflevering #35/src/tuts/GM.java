package tuts;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public enum GM {
	
	CREATIVE,
	SURVIVAL,
	ADVENTURE,
	SPECTATOR,
	FLY;
	
	public static void changeGamemode(GM gamemode, Player p) {
		switch(gamemode) {
		case CREATIVE :
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage(ChatColor.GOLD + "You gamemode has been changed to Creative");
			break;
		case SURVIVAL :
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage(ChatColor.GOLD + "You gamemode has been changed to Survival");
			break;
		case ADVENTURE :
			p.setGameMode(GameMode.ADVENTURE);
			p.sendMessage(ChatColor.GOLD + "You gamemode has been changed to Adventure");
			break;
		case SPECTATOR :
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage(ChatColor.GOLD + "You gamemode has been changed to Spectator");
			break;
		case FLY :
			p.setAllowFlight(true);
			p.setFlying(true);
			p.sendMessage("Fly enabled");
			break;
		}
	}

}
