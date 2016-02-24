package PVPGame.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import PVPGame.Main;
import PVPGame.Commands.StartCommand;
import PVPGame.Utils.GameManager;
import PVPGame.Utils.MessageManager.MsgType;

public class CountDown extends BukkitRunnable{

	private int ticksPerSecond = 20; //(20 ticks = 1 second)
	
	private int globalTicks = 40; //20 Seconden
	
	private int timer = globalTicks;
	public static int lobbyTimer = -1;
	public static int lobbySBTimer = 51;
	public static int gameSBTimerSeconds = 60;
	public static int gameSBTimerMinuts = 19;
	private int GameTimer = 20; //40 seconden
	private int inGameTimer = 100; //40 seconden
	
	@SuppressWarnings({"unused", "deprecation" })
	@Override
	public void run() {
		timer--;		

		if (timer <0) {
			if (Bukkit.getServer().getOnlinePlayers().size() >= 1) {
				if (Main.isIngame() == false && Main.isInLobby() == true) {
				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
					//int allPlayers = Bukkit.getServer().getOnlinePlayers().length;
				all.sendMessage(ChatColor.GRAY + "There are " + ChatColor.RED + Main.getTeamRedSize() + ChatColor.GRAY + " player(s) at the red team! " + ChatColor.BLUE 
						+ Main.getTeamBlueSize() + ChatColor.GRAY + " player(s) at the blue team!");
				}
				}
		}
			timer = globalTicks;
		}
		
		if (Main.isIngame() == true && Main.isInLobby() == false) {
			Main.board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + Integer.toString(CountDown.gameSBTimerMinuts+1) + ":" + Integer.toString(CountDown.gameSBTimerSeconds+1)));
			Main.board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + Integer.toString(CountDown.gameSBTimerMinuts-1) + ":" + Integer.toString(CountDown.gameSBTimerSeconds-1)));
			Main.board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + Integer.toString(CountDown.gameSBTimerMinuts) + ":" + Integer.toString(CountDown.gameSBTimerSeconds)));
			Main.updateScoreboardTimerGame();
			gameSBTimerSeconds--;
			if (gameSBTimerSeconds == 0) {
				gameSBTimerMinuts = gameSBTimerMinuts-1;
				gameSBTimerSeconds = 61;
			}
			if (gameSBTimerMinuts == 0 && gameSBTimerSeconds == 0) {
				for (Player all : Bukkit.getOnlinePlayers()) {
				GameManager.getManager().endGame(all);
				}
			}
		}
		
		if (Main.isIngame() == true && Main.isInLobby() == false) {
			GameTimer--;
			inGameTimer--;
			if (inGameTimer < 1) {
				inGameTimer = ticksPerSecond;
			}
			if (GameTimer < 0) {
				if (inGameTimer == 90 || inGameTimer == 70 || inGameTimer == 50 || inGameTimer == 30 || inGameTimer == 10) {
					for (Player all : Bukkit.getServer().getOnlinePlayers()) {
						Bukkit.broadcastMessage(MsgType.BROADCAST + "Kill the enemy to win! " + inGameTimer);
					}
				}
			}
		}else if (Main.isIngame() == false && Main.isInLobby() == true) {
			if (!(lobbySBTimer == -1)){
				lobbySBTimer--;
				Main.updateScoreboardTimer();
			}
			if (lobbySBTimer == 0) {
				if (Main.getRedTeam().getSize() > 0 && Main.getBlueTeam().getSize() > 0) {//TODO!!!!!!!! Change 0 - 0
					if (lobbyTimer == -1) {
					Bukkit.broadcastMessage(MsgType.BROADCAST + "Game is starting soon....");
					Main.updateScoreboardTimerGame();
					lobbySBTimer = -1;
					lobbyTimer = 31;
					}
				}
			}
			if (lobbySBTimer == 0) {
			if (!(Main.getRedTeam().getSize() >= 1 && Main.getBlueTeam().getSize() >= 1)) {   //TODO!!!!!!!! Change 1 - 1
				Bukkit.broadcastMessage(MsgType.BROADCAST + "You must play with someone else in a team! You cant go just solo or 2 vs 0!");
			lobbySBTimer = 51;
			}
			}
			
			if (StartCommand.isCommandTyped == true) {
				if (lobbyTimer == -1) {
					Main.updateScoreboardTimerGame();
				lobbySBTimer = -1;
				lobbyTimer = 31;
				}
			}
			
			if (!(lobbyTimer == -1)) {
				lobbyTimer--;
				Main.updateScoreboardTimerGame();
			}
			if (lobbyTimer == 50 || lobbyTimer == 40 || lobbyTimer == 30 || lobbyTimer == 20 || lobbyTimer == 10 || lobbyTimer == 5 || lobbyTimer == 4 || lobbyTimer == 3 
					|| lobbyTimer == 2 ||lobbyTimer == 1) {
				Bukkit.broadcastMessage(MsgType.BROADCAST + "Game Starts in " + lobbyTimer + " seconds!");
			}else if (lobbyTimer == 0) {
				StartCommand.isCommandTyped = false;
				lobbyTimer = -1;
				Bukkit.broadcastMessage(MsgType.BROADCAST + "Game Started! Teleporting players....");
				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
					GameManager.getManager().startGame(all);
					
				}
			}
		}		
	}

}
