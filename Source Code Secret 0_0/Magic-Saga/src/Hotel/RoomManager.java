package Hotel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import scoreboard.Main;

public class RoomManager {
	
	public static RoomManager rm = new RoomManager();
    static Main plugin;
    
    public RoomManager(Main MagicSaga) {
        plugin = MagicSaga;
    }

    public RoomManager(){
    }

    public static RoomManager getManager(){
        return rm;
    }

	public void buyRoom(int roomInt, Player p) {
		if (plugin.getBoolean("hotel.room" + roomInt) == false) {
			plugin.updateConfigPerPlayer("hotel.room" + roomInt, true);
			p.sendMessage(ChatColor.GOLD + "Succesfull bought room " + roomInt);
			p.sendMessage(ChatColor.GOLD + "The coins from rent this room is **** coins!");
		}
	}
	
	public void sellRoom(Player p, int roomInt) {
		if (plugin.getBoolean("hotel.room" + roomInt) == true) {
			plugin.updateConfigPerPlayer("hotel.room" + roomInt, false);
			p.sendMessage(ChatColor.GOLD + "Succesfull selled room " + roomInt);
			p.sendMessage(ChatColor.GOLD + "You getting *** coins");
		}
	}
	
}
