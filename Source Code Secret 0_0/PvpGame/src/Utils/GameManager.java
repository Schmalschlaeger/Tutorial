package Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import Main.MPMGMain;
import Minigame.MiniGame;
import Minigame.MiniGameFFA;
import Minigame.MiniGameLMS;
import Minigame.MiniGameSlender;
import Minigame.MiniGameTDM;

public class GameManager {
	
	//Load objects
	private static MPMGMain plugin = MPMGMain.plugin;
	private ChatManager chatManager = new ChatManager();
	private static Scoreboard scoreboardLobby;
	private static Scoreboard scoreboardGame;
	private static Objective objective;

	//Timing/Task Variables
	private int ticksPerSecond = 20; //(20 ticks = 1 second)
	@SuppressWarnings("unused")
	private int taskID = -1; //ID of the bukkit scheduler

	//Set Defaults
	private int globalTicks = 60;
	private static int lobbyCountDown = 20; //Wait time in lobby after minimal players reached.
	private int gameStartCountDown = 10; 	//Time before the game starts, and player is unfrozen.		
	private int gameStatsDelay = 10;			//Time after game ends to show statistics, before returning to lobby.
	
	//Variable counters for tracking loops.
	private int globalTickCount = globalTicks;
	private static int lobbyCountDownCount = lobbyCountDown;
	private static boolean gameActive = false;
	private static boolean gameIsSetup = false;
	private static boolean countDownOver = false;
	public static boolean isTextDisplayed = false;
	public static boolean canattack = false;
	@SuppressWarnings("unused")
	private boolean varReset = false;
	private int gameStartCountDownCount = gameStartCountDown;
	@SuppressWarnings("unused")
	private int gameStatsDelayCount = gameStatsDelay;
	private int currentGame = 1; 
	private int lastGame = 0;

	//Load the constructor.
	@SuppressWarnings("static-access")
	public GameManager(MPMGMain plugin) {
		this.plugin = plugin;
	}


	//Run Game Manager Tasks
	public void gameManagerTask() {
		//Lets start a repeating task
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {				

					globalTickCount--;
					if (globalTickCount <= 0) {
						if (Bukkit.getServer().getOnlinePlayers().length == MPMGMain.getMinPlayers()) {
						globalTickCount = globalTicks;
						}else {
							Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "There are not enough players online! MinPlayers are: " + 
						ChatColor.GOLD + "" + ChatColor.BOLD + MPMGMain.getMinPlayers());
							globalTickCount = lobbyCountDown;
						}
					}

				/*
				 *  Lobby Loop
				 *    
				 *	If gameActive == false then lets go to the lobby.  
				*/
				if (gameActive == false) {
					LobbyManager lobbyManager = new LobbyManager();
					lobbyManager.loadLobby();
					updateLobbyScoreboard();
					if (globalTickCount == 56 || globalTickCount == 41 || globalTickCount == 26 
							|| globalTickCount == 11 && lobbyCountDownCount > 10) {
						chatManager.randomTip("Kill the enemy to win! " + globalTickCount);
					}
				}


				/*
				 *  Game Loop
				 *    
				 *	If gameActive == true then lets go to the game.  
				*/
				if (gameActive == true) {
					//updateGameScoreboard(); //causes errors
					//Load Game 0
					updateGameScoreboard();
					if(currentGame == 0 && currentGame != lastGame && gameIsSetup == false) {
						gameIsSetup = true;
						updateTeams();
						MiniGame minigame = new MiniGameFFA();
						minigame.loadMiniGame();
					}
					//Load Game 1
					if(currentGame == 1 && currentGame != lastGame && gameIsSetup == false) {
						gameIsSetup = true;
						updateTeams();
						scoreboardLobby.clearSlot(DisplaySlot.SIDEBAR);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								MiniGame minigame = new MiniGameSlender();						
								minigame.loadMiniGame();
							}
						}, 40L);
					}
					//Load Game 2
					if(currentGame == 2 && currentGame != lastGame && gameIsSetup == false) {
						gameIsSetup = true;
						updateTeams();
						MiniGame minigame = new MiniGameLMS();
						minigame.loadMiniGame();
						
					}
					//Load Game 3
					if(currentGame == 3 && currentGame != lastGame && gameIsSetup == false) {
						gameIsSetup = true;
						updateTeams();
								MiniGame minigame = new MiniGameTDM();
								minigame.loadMiniGame();
									scoreboardLobby.clearSlot(DisplaySlot.SIDEBAR);
									updateGameScoreboard();
					}
					//Start countdown.
					if (countDownOver == false && isTextDisplayed == true) {	
						chatManager.colorCountDown(gameStartCountDownCount);
						gameStartCountDownCount--;
						if (gameStartCountDownCount <= 0) {
							for (Player player : Bukkit.getServer().getOnlinePlayers()) {
							if (TeamManager.getPlayerTeam(player) == "blue") {
								player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Good luck, find the papers! But watch out");
								player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Slender COMING!!!!");
								}else if (TeamManager.getPlayerTeam(player) == "red"){
									player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Good luck slender! Try to kill the survivors");
									player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "BEFORE they found the 5 papers!");
								}
							}
							countDownOver = true;
							isTextDisplayed = false;
							canattack = true;
							gameStartCountDownCount = gameStartCountDown;

						}
					}
					//Manage Score
					if (countDownOver == true && gameIsSetup == true) {
						for (final Player all : Bukkit.getServer().getOnlinePlayers()) {
						if (MiniGameSlender.getBlueTeamKills() == 4) {
							MiniGameSlender.setBlueTeamKills(MiniGameSlender.getBlueTeamKills() +1);
							chatManager.announceWinner();
							canattack = false;
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									all.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + " And the WINNER  is.........");
								}
							}, 60L);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
										all.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "The SURVIVORS!!");
									
								}
							}, 100L);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
										all.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Thank you for playing!");
										all.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Made by: The-Network dev!");
								}
							}, 120L);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
										all.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You will be teleported to the hub server");
									
								}
							}, 140L);
							
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
										//Teleport to hub server										
								}
							}, 160L);
							
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									
										gameActive = false;
						            	varReset();										
								}
							}, 320L);
							}
						    if (MiniGameSlender.getRedTeamKills() == 4) {
						    	MiniGameSlender.setRedTeamKills(MiniGameSlender.getRedTeamKills() +1);
								chatManager.announceWinner();
								canattack = false;
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
										all.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + " And the WINNER  is.........");
									}
								}, 60L);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
											all.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "SLENDER!!");
										
									}
								}, 100L);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
											all.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Thank you for playing!");
											all.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Made by: The-Network dev!");
									}
								}, 120L);
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
											all.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You will be teleported to the hub server");
										
									}
								}, 140L);
								
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
											//Teleport to hub server										
									}
								}, 160L);
								
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
										
											gameActive = false;
							            	varReset();										
									}
								}, 320L);
								
								}
							}
						}
					}


				/*
				 *  Reset Variables
				 *    
				 *	If varReset == true then reset tracking variables.  
				*/
			} //END Run method.
		}, 0, ticksPerSecond); //(20 ticks = 1 second)
	} //END gameManagerTask method.

	//Sets players team
	private void updateTeams() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			int redTeam = TeamManager.getRedTeamCount();
			if (TeamManager.getPlayerTeam(player) == null) {
				if (redTeam != 1) {
					TeamManager.addToTeam(player, "red");
				} else {
					TeamManager.addToTeam(player, "blue");
				}
			}
		}
	}
	//Setup Lobby scoreboardLobby
	public static void setupScoreboardLobby(Player player) {
		scoreboardLobby = Bukkit.getScoreboardManager().getNewScoreboard();

		objective = scoreboardLobby.registerNewObjective("lobby", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Slender Games");
	}
	/*
	 *  scoreboardLobby Setup and updates!
	 *    
	 *	This will update the scoreboardLobby for all players.  
	*/
	private void updateLobbyScoreboard() {
		for(Player players : Bukkit.getServer().getOnlinePlayers()) {
			if (players.getScoreboard().getObjective("lobby") == null) {
				//chatManager.debugMessage("scoreboardLobby null for " + players.getDisplayName());
				setupScoreboardLobby(players);
				players.setScoreboard(scoreboardLobby);
			}
			if (players.getScoreboard().getObjective("lobby") != null){
				int onlinepl = Bukkit.getServer().getOnlinePlayers().length;
				scoreboardLobby.resetScores(Bukkit.getOfflinePlayer(ChatColor.GREEN + Integer.toString(lobbyCountDownCount + 2)));
				scoreboardLobby.resetScores(Bukkit.getOfflinePlayer(ChatColor.GREEN + Integer.toString(lobbyCountDownCount)));
				//chatManager.debugMessage("scoreboardLobby not(?) null for " + players.getDisplayName());
				players.setScoreboard(scoreboardLobby);//Get scoreboardLobby
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						" ")).setScore(15);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + 
						ChatColor.BOLD + "Time Left:")).setScore(14);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + 
						Integer.toString(lobbyCountDownCount + 1))).setScore(13);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						"  ")).setScore(12);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + 
						ChatColor.BOLD + "Min Players:")).setScore(11);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + 
						Integer.toString(MPMGMain.getMinPlayers()))).setScore(10);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						"   ")).setScore(9);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" +
						ChatColor.BOLD + "Max Players:")).setScore(8);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + 
						Integer.toString(MPMGMain.getMaxPlayers()))).setScore(7);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						"    ")).setScore(6);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" +
						ChatColor.BOLD + "Now Online:")).setScore(5);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD +
						onlinepl)).setScore(4);
			}
		}
	}

	public static Scoreboard getScoreboardLobby() {
		return scoreboardLobby;
	}

	//Setup Lobby scoreboardGame
	public static void setupGameScoreboard(Player players) {
		scoreboardGame = Bukkit.getScoreboardManager().getNewScoreboard();

		objective = scoreboardGame.registerNewObjective("game", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Slender");
		players.setScoreboard(scoreboardGame);
	}
	/*
	 *  scoreboardGame Setup and updates!
	 *    
	 *	This will update the scoreboardGame for all players.  
	*/
	
	private void updateGameScoreboard() {
		for(Player players : Bukkit.getServer().getOnlinePlayers()) {
			if (players.getScoreboard().getObjective("game") == null) {
				//chatManager.debugMessage("scoreboardGame null for " + players.getDisplayName());
				setupGameScoreboard(players);
			}else {
				scoreboardGame.resetScores(players);
				scoreboardGame.resetScores(Bukkit.getOfflinePlayer(ChatColor.RED + "" + ChatColor.BOLD + "Red Team"));
				scoreboardGame.resetScores(Bukkit.getOfflinePlayer(ChatColor.RED + "" + ChatColor.BOLD + "Blue Team"));
				//chatManager.debugMessage("scoreboardGame not(?) null for " + players.getDisplayName());
				players.setScoreboard(scoreboardGame);//Get scoreboardGame
				scoreboardGame.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "" 
				+ ChatColor.BOLD + "Slender")).setScore(MiniGameSlender.getRedTeamKills());
				scoreboardGame.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + "" 
						+ ChatColor.BOLD + "Survivors")).setScore(MiniGameSlender.getBlueTeamKills());
			}
		}
	}

	@SuppressWarnings("unused")
	private void setupScoreboardGame(Player players) {
		// TODO Auto-generated method stub
	}


	//Lobby CountDown count getters and setters
	public static int getLobbyCountDownCount() {
		return lobbyCountDownCount;
	}
	public static void setLobbyCountDownCount(int count) {
		lobbyCountDownCount = count; 
	}
	//Get if in game or not (for late player join spawns or death respawns.
	public static boolean isGameActive() {
		return gameActive;
	}
	public static void setGameActive(Boolean setGameActive) {
		gameActive = setGameActive;
	}
	public static boolean isCountDownOver() {
		return countDownOver;
	}

	//Reset loop tracking variables.
	@SuppressWarnings("deprecation")
	public void varReset() {
		gameIsSetup = false;
		varReset = false;
		countDownOver = false;
		gameStartCountDownCount = gameStartCountDown;
		gameStatsDelayCount = gameStatsDelay;
		TeamManager.clearTeams(); //clears teams
		globalTickCount = globalTicks;
		lobbyCountDownCount = lobbyCountDown;

		MiniGameSlender.setBlueTeamKills(0);
		MiniGameSlender.setRedTeamKills(0);

		//Load next world
		World world = Bukkit.getWorld(MPMGMain.getWorld());
		world.setPVP(false);
		world.setStorm(false);
		world.setMonsterSpawnLimit(0);
		world.setAnimalSpawnLimit(0);
		world.setSpawnFlags(false, false);//spawn animals, spawn monsters
		//Next world spawn coordinates
		Location teleportloc = new Location(world, -1427, 10, -12);
		//Get all players online in an array, and teleport them all and play a sound
		for(Player players : Bukkit.getServer().getOnlinePlayers()) {
			players.setHealth(20);
			players.setFoodLevel(20);
			players.teleport(teleportloc); //Teleport player
			players.setPlayerTime(6000, false); //Set world time
			players.playSound(teleportloc, Sound.LEVEL_UP, 1, 7); //play a sound
		}
	}
}