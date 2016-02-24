package Minigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import Main.MPMGMain;
import Utils.GameManager;
import Utils.TeamManager;

public class MiniGameSlender extends MiniGame {

	//Load objects.

	//Variables that Make a MiniGame
	//private String[] kits = {"Kit 1"};
	//private String[] teams = {"Red", "Blue"};

	//Variables to give user info
	private String miniGameName = "Slender";
	private String miniGameDescription = "Try to survive! But watch out, SLENDER COMING!";
	private String worldAuthor = "BioFrost :D";
	//private List<Location> redSpawns = {world, 0.5, 84, 0.5};

	//Game specific variables.
	private static int redTeamKills = 0;
	private static int blueTeamKills = 0;

	//Define constructor instructions
	public MiniGameSlender() {}

	@Override
	public void loadMiniGame() {
		if (isMapLoaded() == false) {
			setMapLoaded(true);
			//Load next world
			World world = Bukkit.getWorld(MPMGMain.getWorld());
			world.setPVP(true);
			world.setStorm(false);
			world.setMonsterSpawnLimit(0);
			world.setAnimalSpawnLimit(0);
			world.setSpawnFlags(false, false);
			//Get all players online in an array, and teleport them all and play a sound
			for(Player players : Bukkit.getServer().getOnlinePlayers()) {
				//chatManager.debugMessage(TeamManager.getTeamHashMap().toString());
				if(TeamManager.getPlayerTeam(players) == "red"){
					//redteam spawn
					Location teleportloc = new Location(world, -1427, 10, -12);
					players.teleport(teleportloc);
					players.setPlayerTime(6000, false); //Set world time
				} else if (TeamManager.getPlayerTeam(players) == "blue") {
					//blue team spawn
					Location teleportloc = new Location(world, -1427, 10, -12);
					players.teleport(teleportloc);
					players.setPlayerTime(6000, false); //Set world time
				} else {
					Location teleportloc = new Location(world, -1427, 10, -12);
					players.teleport(teleportloc);
					players.setPlayerTime(6000, false); //Set world time
				}
				players.playSound(players.getLocation(), Sound.LEVEL_UP, 1, 10); //play a sound
				
			}
		}
		//Show begging info.
		infoManager.setTitleSlot(miniGameName);
		infoManager.setInfoSlot1(miniGameDescription);
		infoManager.setInfoSlot5(ChatColor.BOLD + "Map created by: " + ChatColor.LIGHT_PURPLE + worldAuthor);
		infoManager.showInfo();
		
		startGamelogic();
		GameManager.isTextDisplayed = true;
		}
	

	@Override
	public void startGamelogic() {

		//chatManager.debugMessage("starting game logic");
	}
	public static void updateScore(Player player) {
		if (TeamManager.getPlayerTeam(player.getKiller()) == "red") {
			redTeamKills = redTeamKills + 1;
		} else {
			blueTeamKills = blueTeamKills + 1;
		}
	}

	public static int getRedTeamKills() {
		return redTeamKills;
	}

	public static void setRedTeamKills(int redTeamKills) {
		MiniGameSlender.redTeamKills = redTeamKills;
	}

	public static int getBlueTeamKills() {
		return blueTeamKills;
	}

	public static void setBlueTeamKills(int blueTeamKills) {
		MiniGameSlender.blueTeamKills = blueTeamKills;
	}
}