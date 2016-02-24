package hubpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu implements Listener {

	private Hub plugin;
	 
	public Menu(Hub instance) {
	    this.plugin = instance;
	}
	
	API h = new API(plugin);
	
	public static Inventory menu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Servers menu");
	public static Inventory pretpark = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Pretpark menu");
	public static Set<String> hidingPlayers = new HashSet<String>();
    public ArrayList<Player> clickdelay = new ArrayList<Player>();
	
	static { //Menu
		ItemStack chest2 = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.DARK_AQUA + "The best Survival/Factions");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.GREEN + "Online");
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.AQUA + "Survive the world! Play with your friends");
		iron2.add(ChatColor.AQUA + "and be the best on the server!");
		iron2.add(ChatColor.AQUA + "Make faction's and sells items with auction's!");
		iron2.add(ChatColor.AQUA + "Grief everyone, and follow the rules!");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
		
		menu.contains(chest2);
		menu.setItem(0, chest2);
		
		ItemStack chest3 = new ItemStack(Material.SKULL_ITEM, 1, (byte) 2);
		ItemMeta plate3 = chest3.getItemMeta();
		plate3.setDisplayName(ChatColor.DARK_AQUA + "Hungry ZombieSurvival Game");
		ArrayList<String> iron3 = new ArrayList<String>();
		iron3.add(ChatColor.AQUA + "");
		iron3.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.GRAY + "Coming soon");
		iron3.add(ChatColor.AQUA + "");
		iron3.add(ChatColor.AQUA + "Stay alive on the map! Survive is");
		iron3.add(ChatColor.AQUA + "our motto. Kill the zombies and monsters!");
		iron3.add(ChatColor.AQUA + "Use your gold to protect youself!");
		iron3.add(ChatColor.AQUA + "The monsters are searching for you!");
		iron3.add(ChatColor.AQUA + "They are hungry......");
		plate3.setLore(iron3);
		chest3.setItemMeta(plate3);
		
		menu.contains(chest3);
		menu.setItem(2, chest3);
		
		ItemStack chest4 = new ItemStack(Material.BOOKSHELF, 1);
		ItemMeta plate4 = chest4.getItemMeta();
		plate4.setDisplayName(ChatColor.DARK_AQUA + "The lonely HUB");
		ArrayList<String> iron4 = new ArrayList<String>();
		iron4.add(ChatColor.GRAY + "");
		iron4.add(ChatColor.GRAY + "Status: " + ChatColor.AQUA + "You are already on it");
		iron4.add(ChatColor.GRAY + "");
		iron4.add(ChatColor.AQUA + "It's just an lonely HUB");
		iron4.add(ChatColor.AQUA + "Nothing special ");
		iron4.add(ChatColor.AQUA + "Chat with other's and have fun");
		iron4.add(ChatColor.AQUA + "to join our servers!");
		plate4.setLore(iron4);
		chest4.setItemMeta(plate4);
		
		menu.contains(chest4);
		menu.setItem(4, chest4);
		
		ItemStack chest5 = new ItemStack(Material.MINECART, 1);
		ItemMeta plate5 = chest5.getItemMeta();
		plate5.setDisplayName(ChatColor.DARK_AQUA + "The amazing ThemePark's");
		ArrayList<String> iron5 = new ArrayList<String>();
		iron5.add(ChatColor.GRAY + "");
		iron5.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.GREEN + "");
		iron5.add(ChatColor.YELLOW + "Magic-Saga: " + ChatColor.GREEN + "Online");
		iron5.add(ChatColor.YELLOW + "ThemePark: " + ChatColor.RED + "Offline");
		iron5.add(ChatColor.GRAY + "");
		iron5.add(ChatColor.AQUA + "Here are the best server's on the planet!");
		iron5.add(ChatColor.AQUA + "Did you think on see an ThemePark with");
		iron5.add(ChatColor.AQUA + "rides and more special effects?");
		iron5.add(ChatColor.AQUA + "So this are the server's to join and it is");
		iron5.add(ChatColor.AQUA + "it worth to join the best servers!");
		iron5.add(ChatColor.AQUA + "It contains, awsome rides/shows and more!");
		iron5.add(ChatColor.AQUA + "It's just not an park you know...");
		plate5.setLore(iron5);
		chest5.setItemMeta(plate5);
		
		menu.contains(chest5);
		menu.setItem(6, chest5);
		
		ItemStack chest6 = new ItemStack(Material.DIAMOND, 1);
		ItemMeta plate6 = chest6.getItemMeta();
		plate6.setDisplayName(ChatColor.DARK_AQUA + "First realesed LMS Game");
		ArrayList<String> iron6 = new ArrayList<String>();
		iron6.add(ChatColor.GRAY + "");
		iron6.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.GRAY + "Coming soon");
		iron6.add(ChatColor.GRAY + "");
		iron6.add(ChatColor.AQUA + "It is an LMS minigame! Never heard of it?");
		iron6.add(ChatColor.AQUA + "Join the minigame, and DONT die!");
		iron6.add(ChatColor.AQUA + "Be the first with the highest kills!");
		iron6.add(ChatColor.AQUA + "BUT.........");
		iron6.add(ChatColor.AQUA + "This is not JUST an LMS, we have added something");
		iron6.add(ChatColor.AQUA + "special to make it harder, and more fun!");
		iron6.add(ChatColor.AQUA + "We hope you can make the best score in this server!");
		iron6.add(ChatColor.AQUA + "Good luck, hope you enjoy it!");
		plate6.setLore(iron6);
		chest6.setItemMeta(plate6);
		
		menu.contains(chest6);
		menu.setItem(8, chest6);
	}
	
	static { //Pretpark
		ItemStack chest2 = new ItemStack(Material.MINECART);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.RED + "Magic-Saga Park");
		chest2.setItemMeta(plate2);
		
		pretpark.contains(chest2);
		pretpark.setItem(2, chest2);
		
		ItemStack chest21 = new ItemStack(Material.MINECART);
		ItemMeta plate21 = chest21.getItemMeta();
		plate21.setDisplayName(ChatColor.RED + "ThemePark");
		chest21.setItemMeta(plate21);
		
		pretpark.contains(chest21);
		pretpark.setItem(6, chest21);
	}
	
	public void serverConnect(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
	
	@EventHandler
	public void onChestPlateClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "The best Survival/Factions")) {
			    	if (cl.getType() == Material.DIAMOND_CHESTPLATE) {
					e.setCancelled(true);
					player.closeInventory();
					serverConnect("survival", player);
			    	}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "First realesed LMS Game")) {
			 		if (cl.getType() == Material.DIAMOND) {
						e.setCancelled(true);
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "The amazing ThemePark's")) {
			 		if (cl.getType() == Material.DIAMOND) {
						e.setCancelled(true);
						player.closeInventory();
						player.openInventory(pretpark);
			 		}
			 	}
			}
	   }
	
	@EventHandler
	public void onThemeParkClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(pretpark.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.RED + "ThemePark")) {
			    	if (cl.getType() == Material.MINECART) {
					e.setCancelled(true);
					serverConnect("themepark", player);
			    	}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.RED + "Magic-Saga Park")) {
			    	if (cl.getType() == Material.MINECART) {
					e.setCancelled(true);
					serverConnect("magicsaga", player);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onCompasstClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(inv.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Servers. Right click to Teleport to an server ")) {
			    	if (cl.getType() == Material.COMPASS) {
					e.setCancelled(true);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onClocktClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(inv.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Player Visibility. Right click to hide/see other players! ")) {
			    	if (cl.getType() == Material.WATCH) {
					e.setCancelled(true);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onSkullClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "Hungry ZombieSurvival Game")) {
			    	if (cl.getType() == Material.SKULL_ITEM) {
					e.setCancelled(true);
					player.closeInventory();
					
					serverConnect("zombie", player);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onBookShelfClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "The lonely HUB")) {
			    	if (cl.getType() == Material.BOOKSHELF) {
					e.setCancelled(true);
					player.closeInventory();
					
					serverConnect("lobby", player);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if  (!e.getPlayer().hasPermission("network.join")) {
		Player p = e.getPlayer();
		Inventory inv = p.getInventory();
		
		ItemStack chest2 = new ItemStack(Material.WATCH);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.AQUA + "Player Visibility. Right click to hide/see other players! ");
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(8, chest2);
		
		ItemStack chest1 = new ItemStack(Material.COMPASS);
		ItemMeta plate1 = chest1.getItemMeta();
		plate1.setDisplayName(ChatColor.AQUA + "Servers. Right click to Teleport to an server ");
		chest1.setItemMeta(plate1);
		
		inv.contains(chest1);
		inv.setItem(1, chest1);
		}		
	}		
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack cl = e.getItem();
		if (p.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()) {
					if (cl.getType() == Material.COMPASS) {
						e.setCancelled(true);
						p.openInventory(menu);
						
					}
    			}
        	}
		}
	}
	
	//public void startHideTimer() {
    //	id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
    	//	@Override
    	//	public void run() {
		//        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
    	//		player.hidePlayer(online);
		//        }
    //		}
    	//}, 0L, 20L);
//	}
	
	public void removeClickDelay(final Player p) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run () {
				clickdelay.remove(p);
			}
		}, 60L);
	}
	
	@EventHandler
	public void onWatchClick(PlayerInteractEvent e) {
		Player player = (Player) e.getPlayer();
		ItemStack cl = e.getItem();
		if (player.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName().contains((ChatColor.AQUA + "Player Visibility. Right click to hide/see other players! "))) {
			    	if (cl.getType() == Material.WATCH) {
					e.setCancelled(true);
					
					if (!hidingPlayers.contains(player.getName())) {
						if (!clickdelay.contains(player)) {
				        hidingPlayers.add(player.getName());
				        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
				            player.hidePlayer(online);
					        player.sendMessage(ChatColor.YELLOW + "Players hidden");
				            clickdelay.add(player);				            
				            removeClickDelay(player);
				        }							
						}else {
							 player.sendMessage(ChatColor.RED + "Je moet " + ChatColor.GRAY + "3 seconden" + ChatColor.RED + " wachten voordat je het opnieuw kan proberen!");
						}
				 } else {
						if (clickdelay.contains(player)) {
				        hidingPlayers.remove(player.getName());				        
				        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
				            if (!player.canSee(online)) {
				                player.showPlayer(online);
				                player.sendMessage(ChatColor.YELLOW + "Players enabled");
				                clickdelay.add(player);
				                removeClickDelay(player);
				            }
				            }
				        }else {
							 player.sendMessage(ChatColor.RED + "Je moet " + ChatColor.GRAY + "3 seconden" + ChatColor.RED + " wachten voordat je het opnieuw kan proberen!");
				        }
				 }
				 
				 }
				    }
				}
			}
    	}
	
}
	
