package TN;

import java.util.ArrayList;

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
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu implements Listener {

	private Main plugin;
	 
	public Menu(Main instance) {
	    this.plugin = instance;
	}
	
	API h = new API(plugin);
	
	private Inventory effects;
	
	public static Inventory menu = Bukkit.createInventory(null, 36, ChatColor.AQUA + "" + ChatColor.BOLD + "Servers Menu");
	public static Inventory youtube = Bukkit.createInventory(null, 9, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Test Server's");
	private static String stainedGlass = ChatColor.GRAY + "[*]";
	
	static { //Youtube
		ItemStack chest2 = new ItemStack(Material.DIAMOND);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.DARK_AQUA + "Youtube Server");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.RED + "On Air");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
		
		youtube.contains(chest2);
		youtube.setItem(0, chest2);
	}
	
  /*  static { //StainedGlass

		ItemStack chest2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(stainedGlass);
		chest2.setItemMeta(plate2);
		menu.contains(chest2);
		menu.setItem(0, chest2);
		
		ItemStack chest21 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate21 = chest21.getItemMeta();
		plate21.setDisplayName(stainedGlass);
		chest21.setItemMeta(plate21);
		menu.contains(chest21);
		menu.setItem(1, chest21);
		
		ItemStack chest211 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(stainedGlass);
		chest211.setItemMeta(plate211);
		menu.contains(chest211);
		menu.setItem(2, chest211);
		
		ItemStack chest3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate3 = chest3.getItemMeta();
		plate3.setDisplayName(stainedGlass);
		chest3.setItemMeta(plate3);
		menu.contains(chest3);
		menu.setItem(3, chest3);
		
		ItemStack chest4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate4 = chest4.getItemMeta();
		plate4.setDisplayName(stainedGlass);
		chest4.setItemMeta(plate4);
		menu.contains(chest4);
		menu.setItem(4, chest4);
		
		ItemStack chest5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate5 = chest5.getItemMeta();
		plate5.setDisplayName(stainedGlass);
		chest5.setItemMeta(plate5);
		menu.contains(chest5);
		menu.setItem(5, chest5);
		
		ItemStack chest6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate6 = chest6.getItemMeta();
		plate6.setDisplayName(stainedGlass);
		chest6.setItemMeta(plate6);
		menu.contains(chest6);
		menu.setItem(6, chest6);

		ItemStack chest7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate7 = chest7.getItemMeta();
		plate7.setDisplayName(stainedGlass);
		chest7.setItemMeta(plate7);
		menu.contains(chest7);
		menu.setItem(7, chest7);
		
		ItemStack chest8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate8 = chest8.getItemMeta();
		plate8.setDisplayName(stainedGlass);
		chest8.setItemMeta(plate8);
		menu.contains(chest8);
		menu.setItem(8, chest8);
		
		ItemStack chest9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate9 = chest9.getItemMeta();
		plate9.setDisplayName(stainedGlass);
		chest9.setItemMeta(plate9);
		menu.contains(chest9);
		menu.setItem(9, chest9);
		
		ItemStack chest10 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate10 = chest10.getItemMeta();
		plate10.setDisplayName(stainedGlass);
		chest10.setItemMeta(plate10);
		menu.contains(chest10);
		menu.setItem(17, chest10);
		
		ItemStack chest11 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate11 = chest11.getItemMeta();
		plate11.setDisplayName(stainedGlass);
		chest11.setItemMeta(plate11);
		menu.contains(chest11);
		menu.setItem(18, chest11);
		
		ItemStack chest12 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plater12 = chest12.getItemMeta();
		plater12.setDisplayName(stainedGlass);
		chest12.setItemMeta(plater12);
		menu.contains(chest12);
		menu.setItem(26, chest12);
		
		ItemStack chest14 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate14 = chest14.getItemMeta();
		plate14.setDisplayName(stainedGlass);
		chest14.setItemMeta(plate14);
		menu.contains(chest14);
		menu.setItem(35, chest14);
		
		////////////////////////////////////////////
		
		ItemStack chest15 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate15 = chest15.getItemMeta();
		plate15.setDisplayName(stainedGlass);
		chest15.setItemMeta(plate15);
		menu.contains(chest15);
		menu.setItem(44, chest15);

		ItemStack chest16 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate16 = chest16.getItemMeta();
		plate16.setDisplayName(stainedGlass);
		chest16.setItemMeta(plate16);
		menu.contains(chest16);
		menu.setItem(27, chest16);

		ItemStack chest = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate = chest.getItemMeta();
		plate.setDisplayName(stainedGlass);
		chest.setItemMeta(plate);
		menu.contains(chest);
		menu.setItem(36, chest);
		
		ItemStack chest1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta plate1 = chest1.getItemMeta();
		plate1.setDisplayName(stainedGlass);
		chest1.setItemMeta(plate1);
		menu.contains(chest1);
		menu.setItem(37, chest1);
		
		ItemStack chestf = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta sdf = chestf.getItemMeta();
		sdf.setDisplayName(stainedGlass);
		chestf.setItemMeta(sdf);
		menu.contains(chestf);
		menu.setItem(38, chestf);
		
		ItemStack dfdf = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta ds = dfdf.getItemMeta();
		ds.setDisplayName(stainedGlass);
		dfdf.setItemMeta(ds);
		menu.contains(dfdf);
		menu.setItem(39, dfdf);
		
		ItemStack chest221 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta sdfsdf = chest221.getItemMeta();
		sdfsdf.setDisplayName(stainedGlass);
		chest221.setItemMeta(sdfsdf);
		menu.contains(chest221);
		menu.setItem(40, chest221);
		
		ItemStack ddf = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta sdfdsf = ddf.getItemMeta();
		sdfdsf.setDisplayName(stainedGlass);
		ddf.setItemMeta(sdfdsf);
		menu.contains(ddf);
		menu.setItem(41, ddf);
		
		ItemStack chest2214e = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta chest22144 = chest2214e.getItemMeta();
		chest22144.setDisplayName(stainedGlass);
		chest2214e.setItemMeta(chest22144);
		menu.contains(chest2214e);
		menu.setItem(42, chest2214e);
		
		ItemStack chest221454 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta esdfsdf = chest221454.getItemMeta();
		esdfsdf.setDisplayName(stainedGlass);
		chest221454.setItemMeta(esdfsdf);
		menu.contains(chest221454);
		menu.setItem(43, chest221454);
    }
    */
	
	static { //Menu
		ItemStack chest21 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta plate21 = chest21.getItemMeta();
		plate21.setDisplayName(ChatColor.DARK_AQUA + " Extreme Survival");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.GREEN + "Online");
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.AQUA + "Survive the world! Play with your friends");
		iron21.add(ChatColor.GRAY + "and be the best on the server!");
		iron21.add(ChatColor.GRAY + "Make faction's and sells items with auction's!");
		iron21.add(ChatColor.GRAY + "Grief everyone, and follow the rules!");
		plate21.setLore(iron21);
		chest21.setItemMeta(plate21);
		
		menu.contains(chest21);
		menu.setItem(21, chest21);
		
		ItemStack chest3 = new ItemStack(Material.COMPASS);
		ItemMeta plate3 = chest3.getItemMeta();
		plate3.setDisplayName(ChatColor.DARK_AQUA + "MazeHunter");
		ArrayList<String> iron3 = new ArrayList<String>();
		iron3.add(ChatColor.GRAY + "");
		iron3.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.GRAY + "Coming soon");
		iron3.add(ChatColor.GRAY + "");
		iron3.add(ChatColor.AQUA + "Stay alive on the map! Survive is");
		iron3.add(ChatColor.AQUA + "our motto. Survive The Maze!");
		iron3.add(ChatColor.GRAY + "Use your gold to protect youself!");
		iron3.add(ChatColor.GRAY + "The hunters are searching for you!");
		iron3.add(ChatColor.GRAY + "They are hungry......");
		plate3.setLore(iron3);
		chest3.setItemMeta(plate3);
		
		menu.contains(chest3);
		menu.setItem(8, chest3);
		
		ItemStack chest4 = new ItemStack(Material.BOOKSHELF, 1);
		ItemMeta plate4 = chest4.getItemMeta();
		plate4.setDisplayName(ChatColor.DARK_AQUA + "Back to begin");
		ArrayList<String> iron4 = new ArrayList<String>();
		iron4.add(ChatColor.GRAY + "");
		iron4.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.AQUA + "You are already on it");
		iron4.add(ChatColor.GRAY + "");
		iron4.add(ChatColor.AQUA + "It's just an lonely HUB");
		iron4.add(ChatColor.GRAY + "Nothing special ");
		iron4.add(ChatColor.GRAY + "Chat with other's and have fun");
		iron4.add(ChatColor.GRAY + "to join our servers!");
		plate4.setLore(iron4);
		chest4.setItemMeta(plate4);
		
		menu.contains(chest4);
		menu.setItem(0, chest4);
		
		ItemStack chest43 = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta plate43 = chest43.getItemMeta();
		plate43.setDisplayName(ChatColor.DARK_AQUA + "Staff Secret");
		ArrayList<String> iron43 = new ArrayList<String>();
		iron43.add(ChatColor.GRAY + "");
		iron43.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.RED + "Staff only");
		iron43.add(ChatColor.GRAY + "");
		iron43.add(ChatColor.AQUA + "It's just a secret for all staff members!");
		iron43.add(ChatColor.GRAY + "Nothing special ");
		plate43.setLore(iron43);
		chest43.setItemMeta(plate43);
		
		menu.contains(chest43);
		menu.setItem(9, chest43);
		
		ItemStack chest41 = new ItemStack(Material.FEATHER, 1);
		ItemMeta plate41 = chest41.getItemMeta();
		plate41.setDisplayName(ChatColor.DARK_AQUA + "Speedrun");
		ArrayList<String> iron41 = new ArrayList<String>();
		iron41.add(ChatColor.GRAY + "");
		iron41.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.GREEN + "Online");
		iron41.add(ChatColor.GRAY + "");
		iron41.add(ChatColor.AQUA + "You know the parkour maps? Think about it,");
		iron41.add(ChatColor.AQUA + "we have added more effects to this parkour!");
		iron41.add(ChatColor.GRAY + "When you see parkour, dont ragequit. This is");
		iron41.add(ChatColor.GRAY + "just a parkour server! ITS SPEEDRUN!");
		iron41.add(ChatColor.GRAY + "So, you get special stuff when you are jumping!");
		plate41.setLore(iron41);
		chest41.setItemMeta(plate41);
		
		menu.contains(chest41);
		menu.setItem(22, chest41);
		
		ItemStack chest411 = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		ItemMeta plate411 = chest411.getItemMeta();
		plate411.setDisplayName(ChatColor.DARK_AQUA + "PVPZ");
		ArrayList<String> iron411 = new ArrayList<String>();
		iron411.add(ChatColor.GRAY + "");
		iron411.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.WHITE + "Coming soon 23 february 2015");
		iron411.add(ChatColor.GRAY + "");
		iron411.add(ChatColor.AQUA + "Just a epic PVP-TDM game!");
		iron411.add(ChatColor.AQUA + "With a little more special stuff");
		iron411.add(ChatColor.GRAY + "Play with your friends, and defeat the enemy!");
		iron411.add(ChatColor.GRAY + "And watch our for the mobs/zombie's");
		plate411.setLore(iron411);
		chest411.setItemMeta(plate411);
		
		menu.contains(chest411);
		menu.setItem(23, chest411);
		
		ItemStack chest41111 = new ItemStack(Material.BEDROCK, 1);
		ItemMeta plate41111 = chest41111.getItemMeta();
		plate41111.setDisplayName(ChatColor.DARK_AQUA + "Unknown");
		ArrayList<String> iron41111 = new ArrayList<String>();
		iron41111.add(ChatColor.GRAY + "");
		iron41111.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.RED + "Offline");
		iron41111.add(ChatColor.GRAY + "");
		iron41111.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Coming Soon! So its not here");
		plate41111.setLore(iron41111);
		chest41111.setItemMeta(plate41111);
		
		menu.contains(chest41111);
		menu.setItem(7, chest41111);
		
		ItemStack chest6 = new ItemStack(Material.SKULL_ITEM, 1, (byte) 2);
		ItemMeta plate6 = chest6.getItemMeta();
		plate6.setDisplayName(ChatColor.DARK_AQUA + "ZombiePVP");
		ArrayList<String> iron6 = new ArrayList<String>();
		iron6.add(ChatColor.GRAY + "");
		iron6.add(ChatColor.DARK_GRAY + "Status: " + ChatColor.GRAY + "Coming Soon");
		iron6.add(ChatColor.GRAY + "");
		iron6.add(ChatColor.AQUA + "This is a simple pvp minigame TDM!");
		iron6.add(ChatColor.AQUA + "Kill the enemy and find loot around the map!");
		iron6.add(ChatColor.AQUA + "But carefull, watch out for mobs!");
		iron6.add(ChatColor.GRAY + "Like what it said, its a simple pvp TDM minigame!");
		iron6.add(ChatColor.GRAY + "When the timer is ready, you gone kill the enemy");
		iron6.add(ChatColor.GRAY + "and stay alive to find loot around the map.");
		iron6.add(ChatColor.GRAY + "But there are more enemy's then players!");
		plate6.setLore(iron6);
		chest6.setItemMeta(plate6);
		
		menu.contains(chest6);
		menu.setItem(6, chest6);
	}

	@SuppressWarnings("deprecation")
	public void createEffectMenu() {
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
			if (!all.hasPermission("network.effect.fire")) {
		createCustomItem(Material.FIRE, ChatColor.RED + "Fire Particles", ChatColor.GRAY + "Creates a fire particle around you!"
				, ChatColor.RED + "You do not own this effect!", ChatColor.RED + "Buy this item at the online shop!", ChatColor.GOLD + "http://shop.thenetwork-mc.net", 11, effects);
			}else {
				createCustomItem3(Material.FIRE, ChatColor.AQUA + "Fire Particles", ChatColor.GRAY + "Creates a fire particle around you!"
						, ChatColor.GREEN + "You are owning this effect!", ChatColor.GREEN + "Click to enable this!", 11, effects);
			}
			if (!all.hasPermission("network.effect.cloud")) {
		createCustomItem(Material.QUARTZ, ChatColor.RED + "Cloud Particles", ChatColor.GRAY + "Create a cloud behind you!"
				, ChatColor.RED + "You do not own this effect!", ChatColor.RED + "Buy this item at the online shop!", ChatColor.GOLD + "http://shop.thenetwork-mc.net", 12, effects);
			}else {
				createCustomItem3(Material.QUARTZ, ChatColor.AQUA + "Cloud Particles", ChatColor.GRAY + "Coming Soon"
						, ChatColor.GREEN + "You are owning this effect!", ChatColor.GREEN + "Click to enable this!", 12, effects);
			}
		//createCustomItem(Material.RED_ROSE, ChatColor.AQUA + "Happy Villager Particles", ChatColor.GRAY + "Coming Soon", 13, effects);
		//createCustomItem(Material.ENCHANTMENT_TABLE, ChatColor.AQUA + "Enchantment Table Particles", ChatColor.GRAY + "Coming Soon", 14, effects);
		//createCustomItem(Material.WATER, ChatColor.AQUA + "Bubble Particles", ChatColor.GRAY + "Coming Soon", 15, effects);
		
		createCustomItem1Lore(Material.FEATHER, ChatColor.GOLD + "Disable your effect!", ChatColor.GRAY + "Click to disable your effect!", 35, effects);
		}
	}
		public static void createCustomItem(Material mat, String displayName, String lore, String lore2, String lore3, String lore4, int itemPlace, Inventory inv) {
			ItemStack menu1 = new ItemStack(mat);
			ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			ArrayList<String> iron3 = new ArrayList<String>();
			iron3.add(ChatColor.GRAY + "");
			iron3.add(lore);
			iron3.add("");
			iron3.add(lore2);
			iron3.add(lore3);
			iron3.add(lore4);
			shop1.setLore(iron3);
			menu1.setItemMeta(shop1);

			inv.contains(menu1);
			inv.setItem(itemPlace, menu1);
	}
		
		public static void createCustomItem3(Material mat, String displayName, String lore, String lore2, String lore3, int itemPlace, Inventory inv) {
			ItemStack menu1 = new ItemStack(mat);
			ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			ArrayList<String> iron3 = new ArrayList<String>();
			iron3.add(ChatColor.GRAY + "");
			iron3.add(lore);
			iron3.add("");
			iron3.add(lore2);
			iron3.add(lore3);
			shop1.setLore(iron3);
			menu1.setItemMeta(shop1);

			inv.contains(menu1);
			inv.setItem(itemPlace, menu1);
	}
		
		public static void createCustomItem1Lore(Material mat, String displayName, String lore, int itemPlace, Inventory inv) {
			ItemStack menu1 = new ItemStack(mat);
			ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			ArrayList<String> iron3 = new ArrayList<String>();
			iron3.add(ChatColor.GRAY + " ");
			iron3.add(lore);
			shop1.setLore(iron3);
			menu1.setItemMeta(shop1);

			inv.contains(menu1);
			inv.setItem(itemPlace, menu1);
	}
	
    	public static void createCustomItemWithLores(Material mat, String displayName, ArrayList<String> lore, Inventory inv, int slotNumber, boolean set) {
		ItemStack item = new ItemStack(mat);
		ItemMeta custom = item.getItemMeta();
		custom.setDisplayName(displayName);
		lore = new ArrayList<String>();
		custom.setLore(lore);
		item.setItemMeta(custom);
		
		if (set == true) {
		inv.contains(item);
		inv.setItem(slotNumber, item);
		}else {
			inv.contains(item);
			inv.addItem(item);
		}
		}

	
	@EventHandler
	public void oneffectClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(inv.getName())) {
				if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Disable your effect!")) {
					if (cl.getType() == Material.FEATHER) {
						e.setCancelled(true);
						p.closeInventory();
						Main.removeEffects(p);
					}
				}else
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Fire Particles")) {
			    	if (cl.getType() == Material.FIRE) {
					e.setCancelled(true);
					p.closeInventory();
					Main.trailFire.add(p.getName());
					p.sendMessage(ChatColor.GRAY + "Effect enabled!");
			    	}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.RED + "Fire Particles")) {
			 		if (cl.getType() == Material.FIRE) {
			 			e.setCancelled(true);
			 			p.openInventory(effects);
			 			p.sendMessage(ChatColor.RED + "Sorry, you dont have permissions to enable this!");
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Cloud Particles")) {
			 		if (cl.getType() == Material.QUARTZ) {
			 			e.setCancelled(true);
						p.closeInventory();
						Main.trailCloud.add(p.getName());
						p.sendMessage(ChatColor.GRAY + "Effect enabled!");
			 		}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.RED + "Cloud Particles")) {
			 			if (cl.getType() == Material.QUARTZ) {
			 				e.setCancelled(true);
				 			p.openInventory(effects);
				 			p.sendMessage(ChatColor.RED + "Sorry, you dont have permissions to enable this!");
			 			}
			 		}
			 	}
			}
	   }
	
	@EventHandler
	public void onYoutubeClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(youtube.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "Youtube Server")) {
			    	if (cl.getType() == Material.DIAMOND) {
					e.setCancelled(true);
					plugin.serverConnect("build", p);
			    	}
			 	}
			}
	   }
		
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInvClick1(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(inv.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "" + ChatColor.BOLD + "Server Selector")) {
			    	if (cl.getType() == Material.COMPASS) {
					e.setCancelled(true);
			    	}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "" + ChatColor.BOLD + "Server Information")) {
			 		if (cl.getType() == Material.getMaterial(387)) {
			 			e.setCancelled(true);
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GREEN + "" + ChatColor.BOLD + "Player Visibility -> " + ChatColor.GOLD + "" + ChatColor.BOLD + "ON")) {
			 		if (cl.getType() == Material.getMaterial(351)) {
			 			e.setCancelled(true);
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GREEN + "" + ChatColor.BOLD + "Player Visibility -> " + ChatColor.RED + "" + ChatColor.BOLD + "OFF")) {
			 		if (cl.getType() == Material.getMaterial(351)) {
			 			e.setCancelled(true);
			 		}
			 	}
			}
	   }
	
	@EventHandler
	public void onInvClick2(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(inv.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GREEN + "" + ChatColor.BOLD + "Player Visibility")) {
			    	if (cl.getType() == Material.WATCH) {
					e.setCancelled(true);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onInvClick21(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(inv.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "Staff Secret")) {
			    	if (cl.getType() == Material.NETHER_STAR) {
					e.setCancelled(true);
					if (player.hasPermission("network.rank.owner") || player.hasPermission("network.rank.support")) {
						player.openInventory(youtube);
					}else {
						player.closeInventory();player.sendMessage(ChatColor.RED + "Sorry, you are not one of the staff! No permissions!");
					}
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onMazeClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
				e.setCancelled(true);
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "MazeHunter")) {
			    	if (cl.getType() == Material.COMPASS) {
					e.setCancelled(true);
					player.closeInventory();
					
					player.sendMessage(ChatColor.RED + "This Server is coming soon! Only vips can join this! " + ChatColor.GOLD + "Buy VIP at the store!");
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onGlassClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(stainedGlass)) {
			    	if (cl.getType() == Material.STAINED_GLASS_PANE) {
					e.setCancelled(true);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onLMSlClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "ZombiePVP")) {
			    	if (cl.getType() == Material.SKULL_ITEM){
					e.setCancelled(true);
					player.closeInventory();
					
					player.sendMessage(ChatColor.RED + "This Server is coming soon! Only vips can join this! " + ChatColor.GOLD + "Buy VIP at the store!");
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onSpeedrunClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "Speedrun")) {
			    	if (cl.getType() == Material.FEATHER) {
					e.setCancelled(true);
					player.closeInventory();
					
					plugin.serverConnect("speedrun", player);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onZombieGamesClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "Unknown")) {
			    	if (cl.getType() == Material.BEDROCK) {
					e.setCancelled(true);
					player.closeInventory();
					
					player.sendMessage(ChatColor.RED + "This Server is coming soon! Only vips can join this! " + ChatColor.GOLD + "Buy VIP at the store!");
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onNarutoCraftClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "PVPZ")) {
			    	if (cl.getType() == Material.LEATHER_CHESTPLATE) {
					e.setCancelled(true);
					player.closeInventory();
					player.sendMessage(ChatColor.GRAY + "This gamemode is coming in February 23e!");
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onSurvivalClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + " Extreme Survival")) {
			    	if (cl.getType() == Material.DIAMOND_SWORD) {
					e.setCancelled(true);
					player.closeInventory();
					
					plugin.serverConnect("factions", player);
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
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "Back to begin")) {
			    	if (cl.getType() == Material.BOOKSHELF) {
					e.setCancelled(true);
					player.teleport(player.getWorld().getSpawnLocation());
			    	}
			 	}
			}
	   }
	
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Inventory inv = p.getInventory();
		
		ItemStack chest1 = new ItemStack(Material.COMPASS);
		ItemMeta plate1 = chest1.getItemMeta();
		plate1.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Server Selector");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "Right click to open our server menu!");
		plate1.setLore(iron2);
		chest1.setItemMeta(plate1);
		EnchantGlow.addGlow(chest1);
		
		if (!p.getInventory().contains(chest1)) {
		inv.setItem(0, chest1);
		}
		
		/*@SuppressWarnings("deprecation")
		ItemStack chest11 = new ItemStack(Material.getMaterial(349), 1, (byte) 3);
		ItemMeta plate11 = chest11.getItemMeta();
		plate11.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Easter Egg's ");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "Find all the hidden easter egg's!");
		plate11.setLore(iron21);
		chest11.setItemMeta(plate11);
		
		inv.contains(chest11);
		inv.setItem(2, chest11);*/
		
		ItemStack chest111 = new ItemStack(Material.BLAZE_ROD);
		ItemMeta plate111 = chest111.getItemMeta();
		plate111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Effects " + ChatColor.GRAY + "(Coming Soon)");
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "Right click to choose your particle effects!");
		plate111.setLore(iron211);
		chest111.setItemMeta(plate111);
		
		//inv.contains(chest111);
		//inv.setItem(7, chest111);
		
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setTitle(ChatColor.AQUA + "" + ChatColor.BOLD + "Server Information");
        meta.setAuthor(ChatColor.GRAY + "The-Network");
        
        meta.addPage(ChatColor.DARK_AQUA + "\n    SERVER INFO" + ChatColor.GOLD 
        		+ "\n " + "\nWelcome" + ChatColor.GRAY + " to this great server! You can find information in this book are all on different servers. And of course fun minigames to join!");
        
        meta.addPage(ChatColor.BLACK + "" + ChatColor.BOLD + 
        		"▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬" //18
        		+ ChatColor.DARK_AQUA + "\nLobby Information\n" + ChatColor.BLACK + ChatColor.BOLD 
        		+ "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬" + ChatColor.RESET + ChatColor.GRAY + "\nHey, and welcome to our lobby. "
        				+ "In this book you can see all the information about our servers!\n" 
        		+ "We're going to explain what, on the lobby server! You can choose your compass and enable/disable players! And of course this book!");
        
        book.setItemMeta(meta);
        
 		inv.contains(book);
 		inv.setItem(8, book);
	}
	
	@EventHandler
	public void onBlazeRodClick(PlayerInteractEvent e) {
		effects = Bukkit.createInventory(null, 36, ChatColor.GOLD + "" + ChatColor.BOLD + "Effects " + ChatColor.GRAY + "(Coming Soon)");
		createEffectMenu();
		Player p = e.getPlayer();
		ItemStack cl = e.getItem();
		if (p.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()) {
					if (cl.getType() == Material.BLAZE_ROD) {
						e.setCancelled(true);						
					}
    			}
        	}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerClick1(PlayerInteractEvent e) {
		Player player = (Player) e.getPlayer();
		ItemStack cl = e.getItem();
		if (player.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName().contains((ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Pet Shop" + ChatColor.RESET + ChatColor.GRAY + " (Coming Soon)"))) {
			    	if (cl.getType() == Material.getMaterial(383)) {
					e.setCancelled(true);
					}
    			}
        	}
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
		
}