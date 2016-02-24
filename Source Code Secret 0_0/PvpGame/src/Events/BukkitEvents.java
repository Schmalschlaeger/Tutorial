package Events;	

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
//import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import Main.MPMGMain;
import Minigame.MiniGameSlender;
import Utils.ChatManager;
import Utils.ConnectionManager;
import Utils.GameManager;
import Utils.TeamManager;

public class BukkitEvents implements Listener {

	//Load objects
	MPMGMain plugin = MPMGMain.plugin;
	private ChatManager chatManager = new ChatManager();
	private PlayerJoinServerEvent playerJoinServerEvent = new PlayerJoinServerEvent();
	private PlayerRespawnServerEvent playerRespawnServerEvent = new PlayerRespawnServerEvent();
	private PlayerQuitServerEvent playerQuitServerEvent = new PlayerQuitServerEvent();
	private ConnectionManager connectionManager = new ConnectionManager();
	GameManager gameManager;

	public BukkitEvents(MPMGMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	//PlayerJoinEvent fired when someone logs into server
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		String playerName = player.getName();
		int playerCount = connectionManager.getPlayerCount();

		//Show Personal welcome message
		player.sendMessage(chatManager.welcomeMessage(playerName));
		//Show server version
		player.sendMessage(chatManager.serverVersion());
		//Show message to all player.
		event.setJoinMessage(chatManager.playerJoinMessage(playerName, playerCount));

		//Toggle player join minigame event!
		playerJoinServerEvent.togglePlayerJoinServerEvent(player);
	}
	
	//@EventHandler
	//public void onPlayerDamageByPlayer(EntityDamageByEntityEvent e) {
	//	if (e.getDamager() instanceof Player) {
	//		if (e.getEntity() instanceof Player) {
	//			Player killer = (Player) e.getEntity();
	//			Player damager = (Player) e.getDamager();
	//			if (TeamManager.getPlayerTeam(killer) == "red" && TeamManager.getPlayerTeam(damager) == "red") {
	//				e.setCancelled(true);
	//			}else if (TeamManager.getPlayerTeam(killer) == "blue" && TeamManager.getPlayerTeam(damager) == "red") {
	//				e.setCancelled(true);
	//			}else {
	//				e.setCancelled(false);
	//			}
	///		}
	//	}
	//}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();		
		if (p instanceof Player) {
			if (e.getCause().equals(DamageCause.FALL)) {
				e.setCancelled(true);
			}
			if (GameManager.canattack == true) {
				e.setCancelled(false);
			}else {
				e.setCancelled(true);
			}
		}
	}
			

	//PlayerJoinEvent fired when someone leaves/quits the server
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		int playerCount = connectionManager.getPlayerCount();
		//Show message to all player.
		event.setQuitMessage(chatManager.playerQuitMessage(playerName, playerCount));
		playerQuitServerEvent.togglePlayerQuitServerEvent();
	}

	//PlayerRespawnEvent fired when someone respawns after death
	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent event) {
		new BukkitRunnable() {
	          @Override
	          public void run() {
	        	  Player player = event.getPlayer();
	        	  //Toggle player join minigame event!
	        	  playerRespawnServerEvent.togglePlayerRespawnServerEvent(player);
	          }
		}.runTaskLater(this.plugin, 1); //run after 1 tick

	}

	//Temporary Anti-Grief
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!(player.isOp())) { //Cancel event if not Operator
			if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
				event.setCancelled(true);
			}
	    }
	}

	//If the arena count down is active, dont let player move
	@EventHandler
	public void freezePlayer(PlayerMoveEvent event) {
		if(GameManager.isCountDownOver() == false && GameManager.isGameActive() == true) {
			if (!event.getFrom().toVector().equals(event.getTo().toVector())) {
			    event.getPlayer().teleport(event.getFrom());
			}
		}
	}

	@EventHandler
	public void onDeath(EntityDeathEvent e){
	  if(e.getEntity() instanceof Player){
	     Player player = (Player) e.getEntity();
	     player.setHealth(20.0);
	     player.setFoodLevel(20);

	     //update score
	     if (player.getKiller() instanceof Player) {
	    	 MiniGameSlender.updateScore(player);
	     }

	     //we will do basic teleporting for now.
	     if (GameManager.isGameActive() == true) {
				World world = Bukkit.getWorld(MPMGMain.getWorld());
				world.setPVP(true);
				world.setStorm(false);
				world.setMonsterSpawnLimit(0);
				world.setAnimalSpawnLimit(0);
				world.setSpawnFlags(false, false);
				//Get all player online in an array, and teleport them all and play a sound

					//chatManager.debugMessage(TeamManager.getTeamHashMap().toString());
					if(TeamManager.getPlayerTeam(player) == "red"){
						//redteam spawn
						Location teleportloc = new Location(world, -1426, 22, -15);
						player.teleport(teleportloc); //Teleport player
						player.setPlayerTime(6000, false); //Set world time
					} else if (TeamManager.getPlayerTeam(player) == "blue") {
						//blue team spawn
						Location teleportloc = new Location(world, -1426, 22, -15);
						player.teleport(teleportloc); //Teleport player
						player.setPlayerTime(6000, false); //Set world time
					} else {
						Location teleportloc = new Location(world, -1426, 22, -15);
						player.teleport(teleportloc); //Teleport player
						player.setPlayerTime(6000, false); //Set world time
					}
					player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 10); //play a sound

	     }
	  }
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if(TeamManager.getPlayerTeam(player) == "red"){
			event.setFormat(ChatColor.RED + "%s" + ": " + ChatColor.WHITE + "%s");
		} else if (TeamManager.getPlayerTeam(player) == "blue") {
			event.setFormat(ChatColor.BLUE + "%s" + ": " + ChatColor.WHITE + "%s");
		} else {
			event.setFormat(ChatColor.YELLOW + "%s" + ": " + ChatColor.WHITE + "%s");
		}
	}


}
