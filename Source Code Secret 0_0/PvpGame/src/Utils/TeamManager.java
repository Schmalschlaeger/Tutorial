package Utils;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeamManager {

	private static ArrayList<String> redTeam = new ArrayList<String>();
	private static ArrayList<String> blueTeam = new ArrayList<String>();

	
	public static void addToTeam(Player player, String team) {
		if (redTeam.contains(player.getName()) == false || blueTeam.contains(player.getName())) {
			if (team == "red") {
				redTeam.add(player.getName());
			} else if (team == "blue") {
				blueTeam.add(player.getName());
			} else if (team == "player") {
				player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "¿I dont know wich team¿");
			} else {
				player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "¿I dont know wich team¿ probaly dead!");
				
			}
		}
	}

	public static String getPlayerTeam(Player player) {
		if (redTeam.contains(player.getName()) == true) {
			return "red";
		} else if (blueTeam.contains(player.getName()) == true) {
			return "blue";
		} else {
			return null;
		}
	}

	public static boolean isOnTeam(Player player) {
		if(redTeam.contains(player.getName()) == true) {
			return true;
		} else if(redTeam.contains(player.getName()) == true) {
			return true;
		} else {
			return false;
		}
	}


	public static int getRedTeamCount() {
		return redTeam.size();
	}

	public static int getBlueTeamCount() {
		return blueTeam.size();
	}

	public static void clearTeams() {
		redTeam.clear();
		blueTeam.clear();
	}

}