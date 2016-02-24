package PVPGame.Utils;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import PVPGame.Main;
import PVPGame.Utils.MessageManager.MsgType;

public class TeamManager {
	
	private static Team red = Main.getRedTeam();
	private static Team blue = Main.getBlueTeam();
	
	@SuppressWarnings("deprecation")
	public static void addToTeam(Player player, String team) {
			if (team == "red") {
				if (blue.hasPlayer(player)) {
					blue.removePlayer(player);
					red.addPlayer(player);
					Main.updateScoreboard(player);
					GameManager.getManager().ifPlayerIsInGameAndChooseTeam(player);
					player.sendMessage(MsgType.SENDMESSAGE + "Switched from " + ChatColor.BLUE + "BLUE " + ChatColor.GOLD + "to " + ChatColor.RED + "RED");
				}else if (red.hasPlayer(player)) {
					player.sendMessage(MsgType.WARNING + "You are already on this team!");
					return;
				}else {
					red.addPlayer(player);
					Main.updateScoreboard(player);
					GameManager.getManager().ifPlayerIsInGameAndChooseTeam(player);
					player.sendMessage(MsgType.SENDMESSAGE + "Added to team " + ChatColor.RED + "RED!");
				}
			} else if (team == "blue") {
				if (red.hasPlayer(player)) {
					red.removePlayer(player);
					blue.addPlayer(player);
					Main.updateScoreboard(player);
					GameManager.getManager().ifPlayerIsInGameAndChooseTeam(player);
					player.sendMessage(MsgType.SENDMESSAGE + "Switched from " + ChatColor.RED + "RED " + ChatColor.GOLD + "to " + ChatColor.BLUE + "BLUE");
				}else if (blue.hasPlayer(player)) {
					player.sendMessage(MsgType.WARNING + "You are already on this team!");
					return;
				}else {
					blue.addPlayer(player);
					Main.updateScoreboard(player);
					GameManager.getManager().ifPlayerIsInGameAndChooseTeam(player);
					player.sendMessage(MsgType.SENDMESSAGE + "Added to team " + ChatColor.BLUE + "BLUE!");
				}
			} else {
				player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "¿I dont know wich team¿ probaly dead!");
				
			}
	}
	
	@SuppressWarnings("deprecation")
	public static String getTeamName(Player p) {
		if (red.hasPlayer(p)) {
			return ChatColor.RED + "[RED]" + p.getName();
		}else if (blue.hasPlayer(p)) {
			return ChatColor.BLUE + "[Blue]" + p.getName();
		}
		return "Error: 121";
	}
	
	@SuppressWarnings("deprecation")
	public static String getPlayerTeam(Player p) {
		if (red.hasPlayer(p)) {
			return "red";
		}else if (blue.hasPlayer(p)) {
			return "blue";
		}else {
			return "no team";
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void removeFromAllTeams(Player p) {
		if (red.hasPlayer(p)) {
			red.removePlayer(p);
		}else if (blue.hasPlayer(p)) {
			blue.removePlayer(p);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void randomTeam(Player p) {
		if (!red.hasPlayer(p) || blue.hasPlayer(p)) {
				if (red.getSize() > blue.getSize()) {
				blue.addPlayer(p);
				p.sendMessage(MsgType.SENDMESSAGE + "You where putted in team" + ChatColor.BLUE + " blue " + ChatColor.GOLD + "because you dont have selected any team!");
				Main.updateScoreboard(p);
				}else if (blue.getSize() > red.getSize()) {
				red.addPlayer(p);
				p.sendMessage(MsgType.SENDMESSAGE + "You where putted in team" + ChatColor.RED + " red " + ChatColor.GOLD + "because you dont have selected any team!");
				Main.updateScoreboard(p);
				}else if (red.getSize() == blue.getSize()) {
					Random r = new Random();
					int random = r.nextInt(2);
					if (random == 1) {
						red.addPlayer(p);
						p.sendMessage(MsgType.SENDMESSAGE + "You where putted in team" + ChatColor.RED + " red " + ChatColor.GOLD + "because you dont have selected any team!");
						Main.updateScoreboard(p);
					}else if (random == 2) {
						blue.addPlayer(p);
						p.sendMessage(MsgType.SENDMESSAGE + "You where putted in team" + ChatColor.BLUE + " blue " + ChatColor.GOLD + "because you dont have selected any team!");
						Main.updateScoreboard(p);
					}
			}
		}
	}

}
