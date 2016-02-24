package PVPGame;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import PVPGame.Commands.LobbyCommand;
import PVPGame.Commands.StartCommand;
import PVPGame.Listeners.ChatEvent;
import PVPGame.Listeners.DeathEvent;
import PVPGame.Listeners.InteractEvent;
import PVPGame.Listeners.InventoryClickListener;
import PVPGame.Listeners.JoinEvent;
import PVPGame.Listeners.LeaveEvent;
import PVPGame.Listeners.PlayerDeathListener;
import PVPGame.Listeners.SignListener;
import PVPGame.Tasks.CountDown;
import PVPGame.Utils.StatsManager;

public class Main extends JavaPlugin{
	
    private static File kit;
    private static FileConfiguration kitConfig;
	
	private static String worldLobby = "lobby";
	private static String WorldGame = "game";
	private static int minPlayers = 2;
	private static boolean isIngame = false;
	private static boolean isInLobby = true;
	
	public static Scoreboard board;
	private static Team red;
	private static Team blue;
    private static Objective o;

	public void onEnable() {
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
		createScoreBoard(all);
		}
    	startCountDown();
    	
    	getCommand("game").setExecutor(new StartCommand(this));
    	getCommand("lobby").setExecutor(new LobbyCommand(this));
    	
		Bukkit.getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SignListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new InteractEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
		kit = new File(getDataFolder(), "kits.yml");
    	kitConfig = YamlConfiguration.loadConfiguration(kit);
    	saveCustomConfig(); 
		
		//Bukkit.getServer().getPluginManager().registerEvents(this, new);
    	getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    	
    		if (!Bukkit.getWorlds().equals(worldLobby)) {
    			WorldCreator worldCreator = new WorldCreator(worldLobby);
    			worldCreator.createWorld();
    		}
    		if (!Bukkit.getWorlds().equals(WorldGame)) {
    			WorldCreator worldCreator = new WorldCreator(WorldGame);
    			worldCreator.createWorld();
    		}
    		//TODO: Set motd of mysql motd
    	
    	//TODO: Start de task van de game
	}
	
	public void onDisable() {
		saveCustomConfig();
		//TODO: Set motd van mysgl of server
	}
	
    public void saveCustomConfig(){
   	try{
   		kitConfig.save(kit);
   	}catch(Exception e){
      	e.printStackTrace();
    	}
   	}
    
	public void createScoreBoard(Player all) {
    	board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
    	
    	red = board.registerNewTeam("red");
    	blue = board.registerNewTeam("blue");
    	
    	red.setPrefix(ChatColor.RED + "[R]");
    	blue.setPrefix(ChatColor.BLUE + "[B]");
    	
    	red.setAllowFriendlyFire(false);
    	blue.setAllowFriendlyFire(false);
    	
    	red.setCanSeeFriendlyInvisibles(true);
    	blue.setCanSeeFriendlyInvisibles(true);
    	
    	o = board.registerNewObjective("test", "dummy");
    	
    	o.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Your Stats");
    	o.setDisplaySlot(DisplaySlot.SIDEBAR);
    	
    	updateScoreboard(all);
    }
	
	public void createGameSB() {
		
	}
    
	@SuppressWarnings("deprecation")
	public static void updateScoreboard(Player all) {
    		createScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Team:", 6);
    		if (red.hasPlayer(all)) {
    			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Blue"));
    			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "No Team"));
    			
    			Score score4 = o.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Red"));
        		score4.setScore(5);
    		}else if (blue.hasPlayer(all)) {
    			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.RED + "Red"));
    			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "No Team"));
    			
    			Score score4 = o.getScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Blue"));
        		score4.setScore(5);
    		}else {
    			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Blue"));
    			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.RED + "Red"));
    			
    			Score score4 = o.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "No Team"));
        		score4.setScore(5);
    		}
    }
	
	@SuppressWarnings("deprecation")
	public static void updateGameScoreboard() {
    	for (Player all : Bukkit.getOnlinePlayers()) {	
		createScore(ChatColor.GOLD + "Deaths", 3);
		createScore(ChatColor.GOLD + "Kills", 1);
		if (!(StatsManager.getPlayerDeaths(all.getName()) < 1)) {
    		board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "No Deaths"));
    		board.resetScores(Bukkit.getOfflinePlayer(Integer.toString(StatsManager.getPlayerDeaths(all.getName())-1)));
			createScore(ChatColor.AQUA + "   "+ Integer.toString(StatsManager.getPlayerDeaths(all.getName())), 3);
		}else{
			createScore(ChatColor.GRAY + "No Deaths", 2);
		}
		
		if (!(StatsManager.getPlayerKills(all.getName()) < 1)) {    		
    		if (StatsManager.getPlayerKills(all.getName()) > 0) {
        		board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "No Kills"));
        		board.resetScores(Bukkit.getOfflinePlayer(Integer.toString(StatsManager.getPlayerKills(all.getName()))));
			createScore(Integer.toString(StatsManager.getPlayerKills(all.getName())), 0);
    		}else {
    			createScore(Integer.toString(0), 0);
    		}
		}else{
    		board.resetScores(Bukkit.getOfflinePlayer(Integer.toString(StatsManager.getPlayerKills(all.getName()))));
			createScore(ChatColor.GRAY + "No Kills", 0);
		}
    	}
	}
	
	@SuppressWarnings("deprecation")
	public static void updateScoreboardTimer() {
		createScore(ChatColor.GOLD + "LobbyTimer", 8);
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "00:" + Integer.toString(CountDown.lobbySBTimer+1)));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "00:0"));
			createScore(ChatColor.GRAY + "00:" + Integer.toString(CountDown.lobbySBTimer), 7);
	}
	
	@SuppressWarnings("deprecation")
	public static void updateScoreboardTimerGame() {
		if (Main.isIngame == true) {
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "00:-1"));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "00:0"));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.LIGHT_PURPLE + "Starts in:"));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.AQUA + "Ends over:"));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + Integer.toString(CountDown.gameSBTimerMinuts+1) + ":" + Integer.toString(CountDown.gameSBTimerSeconds+1)));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + Integer.toString(CountDown.gameSBTimerMinuts-1) + ":" + Integer.toString(CountDown.gameSBTimerSeconds-1)));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + Integer.toString(CountDown.gameSBTimerMinuts) + ":" + Integer.toString(CountDown.gameSBTimerSeconds)));
			createScore(ChatColor.AQUA + "Ends over:", 8);
			createScore(ChatColor.GRAY + Integer.toString(CountDown.gameSBTimerMinuts) + ":" + Integer.toString(CountDown.gameSBTimerSeconds), 7);
		}else if (Main.isInLobby == true) {
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GOLD + "LobbyTimer"));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "00:" + Integer.toString(CountDown.lobbyTimer+1)));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "00:" + Integer.toString(CountDown.lobbySBTimer-1)));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "00:" + Integer.toString(CountDown.lobbySBTimer+1)));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "00:" + Integer.toString(CountDown.lobbySBTimer)));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "00:0"));
			board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GRAY + "00:-1"));
			createScore(ChatColor.LIGHT_PURPLE + "Starts in:", 8);
			createScore(ChatColor.GRAY + "00:" + Integer.toString(CountDown.lobbyTimer), 7);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void createScore(String name, int place) {
		Score score1 = o.getScore(Bukkit.getOfflinePlayer(name));
		score1.setScore(place);
	}
    
    public static void setScoreBoard(Player p) {
    	p.setScoreboard(board);
    }
    
    public static Scoreboard getScoreboard() {
    	return board;
    }
    
    public static Team getRedTeam() {
    	return red;
    }
    
    public static Team getBlueTeam() {
    	return blue;
    }
    
    public static String getTeamRedSize() {
		return Integer.toString(red.getSize());
	}
    
    public static String getTeamBlueSize() {
		return Integer.toString(blue.getSize());
	}
    
    public static FileConfiguration getCustomConfig() {
		return kitConfig;
    }

	public static String getWorldLobby() {
		return worldLobby;
	}
	
	public static String getWorldGame() {
		return WorldGame;
	}
	
	public static int getMinPlayers() {
		return minPlayers;
	}
	
	public static boolean isIngame() {
		return isIngame;
	}
	
	public static boolean isInLobby() {
		return isInLobby;
	}
	
	public static void setIngame(boolean b) {
		isIngame = b;
	}
	
	public static void setInLobby(boolean b) {
		isInLobby = b;
	}
	
	@SuppressWarnings("deprecation")
	public void startCountDown() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new CountDown(), 0, 20L);
		//Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new ScoreBoard(this), 0, 20L);
	}

}
