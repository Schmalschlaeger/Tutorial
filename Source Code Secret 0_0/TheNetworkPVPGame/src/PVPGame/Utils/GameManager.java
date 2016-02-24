package PVPGame.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

import PVPGame.Main;
import PVPGame.Listeners.KitSelector;

public class GameManager{
	
	private static GameManager gm = new GameManager();
	//Hiermee pak je de constructors met GameManager.getManager()......
	public static GameManager getManager() {
		return gm;
	}
	
	private static Team red = Main.getRedTeam();
	private static Team blue = Main.getBlueTeam();
	
	/*public Scoreboard board;
	public Scoreboard sb;
	public Team red;
	public Team blue;
	public Objective objective;
	public Score score;
	*/
	@SuppressWarnings("deprecation")
	public void addPlayer(Player p) {
		World lobby = Bukkit.getWorld(Main.getWorldLobby());
		p.teleport(lobby.getSpawnLocation());
		
		p.getInventory().clear();
		p.updateInventory();
		p.setHealth(20.0);
		p.setFireTicks(0);
	}
	
	public void removePlayer(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setFireTicks(0);
		
		setItems(p);
	}
	
	@SuppressWarnings("deprecation")
	public void ifPlayerIsInGameAndChooseTeam(Player p) {
		Location redSpawn = new Location(Bukkit.getWorld(Main.getWorldGame()), -25.5, 67, 230.5);
		Location blueSpawn = new Location(Bukkit.getWorld(Main.getWorldGame()), -16.5, 68, 206.5);
		if (Main.isIngame() && Main.isInLobby() == false) {
			if (red.hasPlayer(p)) {
				p.getInventory().clear();
				p.teleport(redSpawn);
				getKit(p);
			}else if (blue.hasPlayer(p)) {
				p.getInventory().clear();
				p.teleport(blueSpawn);
				getKit(p);
			}
		}
	}
	
	public void setItems(Player p) {
		InventoryMenu.createCustomItem(new ItemStack(Material.DIAMOND), ChatColor.AQUA + "" + ChatColor.BOLD + "Your Kits"
				, 0, p.getInventory(), ChatColor.GOLD + "Right Click to choose your kit!");
		InventoryMenu.createCustomItem(new ItemStack(Material.COMPASS), ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Team Selector"
				, 1, p.getInventory(), ChatColor.GOLD + "Right Click to choose your team!");
	}
	
	public void startGame(Player p) {
		p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		p.getInventory().clear();
		teleportToSpawn(p);
		getKit(p);
		Main.setInLobby(false);
		Main.setIngame(true);
		Main.setScoreBoard(p);
		Main.updateScoreboardTimerGame();
		Main.updateGameScoreboard();
	}
	
	public void endGame(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.teleport(Bukkit.getWorld(Main.getWorldLobby()).getSpawnLocation());
		
		Main.setIngame(false);
		Main.setInLobby(true);
	}
	
	@SuppressWarnings("deprecation")
	public void getKit(Player p) {
		if (KitSelector.kitDefault.contains(p.getName())) {
			InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.STONE_SWORD), ChatColor.AQUA + "Default Sword"
					, 0, p.getInventory(), Enchantment.KNOCKBACK, 1, ChatColor.GRAY + "Kill the enemy with this powerfull sword!");
			if (blue.hasPlayer(p)) {
			InventoryMenu.createLeatherHelmet(new ItemStack(Material.LEATHER_CHESTPLATE), Color.BLUE, ChatColor.AQUA + "Default Helmet", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createLeatherChestPlate(new ItemStack(Material.LEATHER_CHESTPLATE), Color.BLUE, ChatColor.AQUA + "Default ChestPlate", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createLeatherLeggings(new ItemStack(Material.LEATHER_LEGGINGS), Color.BLUE, ChatColor.AQUA + "Default Leggings", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createLeatherBoots(new ItemStack(Material.LEATHER_BOOTS), Color.BLUE, ChatColor.AQUA + "Default Boots", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");		
			}else if (red.hasPlayer(p)) {
				InventoryMenu.createLeatherHelmet(new ItemStack(Material.LEATHER_CHESTPLATE), Color.RED, ChatColor.AQUA + "Default Helmet", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
				InventoryMenu.createLeatherChestPlate(new ItemStack(Material.LEATHER_CHESTPLATE), Color.RED, ChatColor.AQUA + "Default ChestPlate", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
				InventoryMenu.createLeatherLeggings(new ItemStack(Material.LEATHER_LEGGINGS), Color.RED, ChatColor.AQUA + "Default Leggings", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
				InventoryMenu.createLeatherBoots(new ItemStack(Material.LEATHER_BOOTS), Color.RED, ChatColor.AQUA + "Default Boots", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");		
			}
		}else if (KitSelector.kitVIP.contains(p.getName())) {
			InventoryMenu.createHelmet(new ItemStack(Material.GOLD_HELMET), ChatColor.AQUA + "VIP Helmet", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createChestPlate(new ItemStack(Material.GOLD_CHESTPLATE), ChatColor.AQUA + "VIP ChestPlate", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createLeggings(new ItemStack(Material.GOLD_LEGGINGS), ChatColor.AQUA + "VIP Leggings", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createBoots(new ItemStack(Material.GOLD_BOOTS), ChatColor.AQUA + "VIP Boots", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			
			InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.GOLD_SWORD), ChatColor.AQUA + "VIP Sword"
					, 0, p.getInventory(), Enchantment.DAMAGE_ALL, 1, ChatColor.GRAY + "Kill the enemy with this powerfull sword!");
			InventoryMenu.createCustomItem(new ItemStack(Material.GOLDEN_APPLE), ChatColor.AQUA + "Golden apple", 1, p.getInventory(), ChatColor.GOLD + "SouldBound");
		}else if (KitSelector.kitHydra.contains(p.getName())) {
			InventoryMenu.createHelmet(new ItemStack(Material.IRON_HELMET), ChatColor.AQUA + "Hydra Helmet", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createChestPlate(new ItemStack(Material.IRON_CHESTPLATE), ChatColor.AQUA + "Hydra ChestPlate", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createLeggings(new ItemStack(Material.IRON_LEGGINGS), ChatColor.AQUA + "Hydra Leggings", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createBoots(new ItemStack(Material.IRON_BOOTS), ChatColor.AQUA + "Hydra Boots", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			
			InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.IRON_SWORD), ChatColor.AQUA + "Hydra Sword"
					, 0, p.getInventory(), Enchantment.FIRE_ASPECT, 1, ChatColor.GRAY + "Kill the enemy with this powerfull sword!");
			InventoryMenu.createCustomItem(new ItemStack(Material.GOLDEN_APPLE, 2), ChatColor.AQUA + "Golden apple", 1, p.getInventory(), ChatColor.GOLD + "SouldBound");
		}else if (KitSelector.kitChampion.contains(p.getName())) {
			InventoryMenu.createHelmet(new ItemStack(Material.DIAMOND_HELMET), ChatColor.AQUA + "Champion Helmet", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createChestPlate(new ItemStack(Material.DIAMOND_CHESTPLATE), ChatColor.AQUA + "Champion ChestPlate", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createLeggings(new ItemStack(Material.DIAMOND_LEGGINGS), ChatColor.AQUA + "Champion Leggings", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createBoots(new ItemStack(Material.DIAMOND_BOOTS), ChatColor.AQUA + "Champion Boots", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			
			InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.IRON_SWORD), ChatColor.AQUA + "Champion Sword"
					, 0, p.getInventory(), Enchantment.DAMAGE_UNDEAD, 1, ChatColor.GRAY + "Kill the enemy with this powerfull sword!");
			InventoryMenu.createCustomItem(new ItemStack(Material.GOLDEN_APPLE, 2), ChatColor.AQUA + "Enchanted golden apple", 1, p.getInventory(), ChatColor.GOLD + "SouldBound");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void teleportToSpawn(Player p) {
		Location redSpawn = new Location(Bukkit.getWorld(Main.getWorldGame()), 7.5, 71, 51.5);
		Location blueSpawn = new Location(Bukkit.getWorld(Main.getWorldGame()), 27.5, 71, 71.5);
		if (red.hasPlayer(p)) {
			p.teleport(redSpawn);
		}else
		if (blue.hasPlayer(p)) {
			p.teleport(blueSpawn);
		}else {
			TeamManager.randomTeam(p);
			teleportToSpawn(p);
		}
	}
	
	/*public void setScoreboard(Player player) {
		sb = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = sb.registerNewObjective("test", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Your Stats");
	}
	
	@SuppressWarnings("deprecation")
	public void updateScoreBoard() { 
		for(Player players : Bukkit.getServer().getOnlinePlayers()) {
		if (players.getScoreboard().getObjective("test") == null) {
			setScoreboard(players);
			players.setScoreboard(sb);
		}else
		if (players.getScoreboard().getObjective("test") != null){
			players.setScoreboard(sb);
			
			sb.resetScores(Bukkit.getOfflinePlayer(ChatColor.GOLD + "" 
					+ ChatColor.BOLD + TeamManager.getPlayerTeam(players)));
			
			sb.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
					"   ")).setScore(-1);
			sb.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" +
					ChatColor.BOLD + "Kills:")).setScore(-2);
			sb.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "" 
					+ ChatColor.BOLD + StatsManager.getPlayerKills(players.getName()))).setScore(-3);
			
			sb.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" +
					ChatColor.BOLD + "Team:")).setScore(-4);
			sb.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "" 
					+ ChatColor.BOLD + TeamManager.getPlayerTeam(players))).setScore(-5);
		}
	}
	}
	
	public void setTeams(Player all) {		
			all.setScoreboard(board);
	}
	
	public void onEnableTeams() {
		board = Bukkit.getScoreboardManager().getNewScoreboard();
        blue = board.registerNewTeam("blue");
        red = board.registerNewTeam("red");
        
		red.setAllowFriendlyFire(false);
		red.setCanSeeFriendlyInvisibles(true);
		red.setPrefix(ChatColor.RED + "[R]");
		red.setDisplayName(ChatColor.RED + "");
		
		blue.setAllowFriendlyFire(false);
		blue.setCanSeeFriendlyInvisibles(false);
		blue.setPrefix(ChatColor.BLUE + "[B]");
		blue.setDisplayName(ChatColor.BLUE + "");
	}*/

	
	/*public String getTeamRedSize() {
		return Integer.toString(red.getSize());
	}
	
	public String getTeamBlueSize() {
		return Integer.toString(blue.getSize());
	}*/

}
