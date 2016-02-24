package mazehunter.utils;

import static org.bukkit.ChatColor.*;
import mazehunter.MazeHunter;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatManager {

	public static void broadcast(String msg) {
		for (Player player : MazeHunter.players)
			player.sendMessage(starter() + msg);
	}
	
    public static String starter() {
		return DARK_GRAY + "[" + ChatColor.RED + "MazeHunter" + DARK_GRAY + "]"
				+ WHITE;
	}
}
