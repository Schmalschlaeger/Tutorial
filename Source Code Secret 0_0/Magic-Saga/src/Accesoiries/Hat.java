package Accesoiries;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import scoreboard.Main;
import scoreboard.Menu;
import scoreboard.MessageManager.MsgType;

public class Hat implements Listener {
	
    static Main plugin;
    
    public Hat(Main m) {
        plugin = m;
    }
	
    private static Inventory playerHats;
    
	private static int icePrice = 0;
	private static int packedIcePrice = 0;
	private static int chestPrice = 0;
	private static int redClayPrice = 0;
	private static int tntPrice = 0;
	private static int cactusPrice = 0;
	private static int sandPrice = 0;
	private static int redGlassPrice = 0;
	private static int blueGlassPrice = 0;
	private static int greenGlassPrice = 0;
	private static int snowPrice = 0;
	private static int clayPrice = 0;
	private static int pumpkinPrice = 0;
	private static int woodPrice = 0;
	private static int dispenserPrice = 0;
	private static int beaconPrice = 0;
	private static int grassPrice = 0;
	private static int lapizPrice = 0;

	public static void setItemToHatShop(Player p, Inventory inv) {
		int points = Main.playerPoints.getAPI().look(p.getName());
		
		int amount = 0;
        for (ItemStack is : playerHats.getContents()) {
        	if (is != null) {
            amount = amount + is.getAmount();
        	}
        }
		
		ItemStack chest21 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta plate21 = chest21.getItemMeta();
		plate21.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Your Hat's");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.GRAY + "Here can you see your purchased hat's!");
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.BLUE + p.getName() + "'s info:");
		iron21.add(ChatColor.GOLD + "Name: " + ChatColor.RED + p.getName());
		iron21.add(ChatColor.GOLD + "Purchased hats: " + ChatColor.RED + amount);
		iron21.add(ChatColor.GOLD + "Your Rank: " + ChatColor.RED + "Coming Soon");
		iron21.add(ChatColor.GOLD + "Your Coins: " + ChatColor.RED + points);
		plate21.setLore(iron21);
		chest21.setItemMeta(plate21);
		
		inv.contains(chest21);
		inv.setItem(8, chest21);
		
		ItemStack chest2 = new ItemStack(Material.ICE);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ice hat");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "Right click to buy this item!");
		iron2.add(ChatColor.GRAY + "Price: " + ChatColor.RED + icePrice + ChatColor.GRAY + " points");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(0, chest2);
		
		ItemStack chest2111 = new ItemStack(Material.PACKED_ICE);
		ItemMeta plate2111 = chest2111.getItemMeta();
		plate2111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Packed Ice hat");
		ArrayList<String> iron2111 = new ArrayList<String>();
		iron2111.add(ChatColor.GRAY + "");
		iron2111.add(ChatColor.GRAY + "Right click to buy this item!");
		iron2111.add(ChatColor.GRAY + "Price: " + ChatColor.RED + packedIcePrice + ChatColor.GRAY + " points");
		plate2111.setLore(iron2111);
		chest2111.setItemMeta(plate2111);
		
		inv.contains(chest2111);
		inv.setItem(1, chest2111);
		
		ItemStack chest211 = new ItemStack(Material.CHEST);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Chest hat");
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "");
		iron211.add(ChatColor.GRAY + "Right click to buy this item!");
		iron211.add(ChatColor.GRAY + "Price: " + ChatColor.RED + chestPrice + ChatColor.GRAY + " points");
		plate211.setLore(iron211);
		chest211.setItemMeta(plate211);
		
		inv.contains(chest211);
		inv.setItem(2, chest211);
		
		ItemStack chest21111 = new ItemStack(Material.STAINED_CLAY ,1 , (short) 14);
		ItemMeta plate21111 = chest21111.getItemMeta();
		plate21111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Red Clay hat");
		ArrayList<String> iron21111 = new ArrayList<String>();
		iron21111.add(ChatColor.GRAY + "");
		iron21111.add(ChatColor.GRAY + "Right click to buy this item!");
		iron21111.add(ChatColor.GRAY + "Price: " + ChatColor.RED + redClayPrice + ChatColor.GRAY + " points");
		plate21111.setLore(iron21111);
		chest21111.setItemMeta(plate21111);
		
		inv.contains(chest21111);
		inv.setItem(3, chest21111);
		
		ItemStack chest211111 = new ItemStack(Material.STAINED_GLASS ,1 , (short) 14);
		ItemMeta plate211111 = chest211111.getItemMeta();
		plate211111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Red Glass hat");
		ArrayList<String> iron211111 = new ArrayList<String>();
		iron211111.add(ChatColor.GRAY + "");
		iron211111.add(ChatColor.GRAY + "Right click to buy this item!");
		iron211111.add(ChatColor.GRAY + "Price: " + ChatColor.RED + redGlassPrice + ChatColor.GRAY + " points");
		plate211111.setLore(iron211111);
		chest211111.setItemMeta(plate211111);
		
		inv.contains(chest211111);
		inv.setItem(9, chest211111);
		
		ItemStack chest2111111 = new ItemStack(Material.STAINED_GLASS ,1 , (short) 11);
		ItemMeta plate2111111 = chest2111111.getItemMeta();
		plate2111111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Blue Glass hat");
		ArrayList<String> iron2111111 = new ArrayList<String>();
		iron2111111.add(ChatColor.GRAY + "");
		iron2111111.add(ChatColor.GRAY + "Right click to buy this item!");
		iron2111111.add(ChatColor.GRAY + "Price: " + ChatColor.RED + blueGlassPrice + ChatColor.GRAY + " points");
		plate2111111.setLore(iron2111111);
		chest2111111.setItemMeta(plate2111111);
		
		inv.contains(chest2111111);
		inv.setItem(10, chest2111111);
		
		ItemStack chest21111111 = new ItemStack(Material.STAINED_GLASS ,1 , (short) 13);
		ItemMeta plate21111111 = chest21111111.getItemMeta();
		plate21111111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Green Glass hat");
		ArrayList<String> iron21111111 = new ArrayList<String>();
		iron21111111.add(ChatColor.GRAY + "");
		iron21111111.add(ChatColor.GRAY + "Right click to buy this item!");
		iron21111111.add(ChatColor.GRAY + "Price: " + ChatColor.RED + greenGlassPrice + ChatColor.GRAY + " points");
		plate21111111.setLore(iron21111111);
		chest21111111.setItemMeta(plate21111111);
		
		inv.contains(chest21111111);
		inv.setItem(11, chest21111111);
		
		addItemToHatShopInventory(inv, 13, ChatColor.GOLD + "" + ChatColor.BOLD + "TNT hat", Material.TNT, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + tntPrice + ChatColor.GRAY + " points");
		addItemToHatShopInventory(inv, 5, ChatColor.GOLD + "" + ChatColor.BOLD + "Cactus hat", Material.CACTUS, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + cactusPrice + ChatColor.GRAY + " points");
		addItemToHatShopInventory(inv, 6, ChatColor.GOLD + "" + ChatColor.BOLD + "Sand hat", Material.SAND, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + sandPrice + ChatColor.GRAY + " points");
		addItemToHatShopInventory(inv, 12, ChatColor.GOLD + "" + ChatColor.BOLD + "Snow hat", Material.SNOW_BLOCK, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + snowPrice + ChatColor.GRAY + " points");
		addItemToHatShopInventory(inv, 4, ChatColor.GOLD + "" + ChatColor.BOLD + "Clay hat", Material.CLAY, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + clayPrice + ChatColor.GRAY + " points");
		addItemToHatShopInventory(inv, 14, ChatColor.GOLD + "" + ChatColor.BOLD + "Pumpkin hat", Material.PUMPKIN, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + pumpkinPrice + ChatColor.GRAY + " points");
		addItemToHatShopInventory(inv, 15, ChatColor.GOLD + "" + ChatColor.BOLD + "Wood hat", Material.WOOD, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + woodPrice + ChatColor.GRAY + " points");
		addItemToHatShopInventory(inv, 18, ChatColor.GOLD + "" + ChatColor.BOLD + "Dispenser hat", Material.DISPENSER, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + dispenserPrice + ChatColor.GRAY + " points");
		addItemToHatShopInventory(inv, 19, ChatColor.GOLD + "" + ChatColor.BOLD + "Beacon hat", Material.BEACON, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + beaconPrice + ChatColor.GRAY + " points");
		addItemToHatShopInventory(inv, 20, ChatColor.GOLD + "" + ChatColor.BOLD + "Grass Block hat", Material.GRASS, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + grassPrice + ChatColor.GRAY + " points");
		addItemToHatShopInventory(inv, 21, ChatColor.GOLD + "" + ChatColor.BOLD + "Lapiz hat", Material.LAPIS_BLOCK, 
				ChatColor.GRAY + "Right click to buy this item!", 
				ChatColor.GRAY + "Price: " + ChatColor.RED + lapizPrice + ChatColor.GRAY + " points");
	}
	
	public static void addItemToHatsInventory(Inventory toInv, String disPlayName, Material mat, String lore) {
		ItemStack chest211 = new ItemStack(mat);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(disPlayName);
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "");
		iron211.add(ChatColor.GRAY + lore);
		plate211.setLore(iron211);
		chest211.setItemMeta(plate211);
		
		toInv.contains(chest211);
		toInv.addItem(chest211);
	}
	
	public static void addItemToHatShopInventory(Inventory toInv, int slotNumber, String disPlayName, Material mat, String lore, String lore2) {
		ItemStack chest211 = new ItemStack(mat);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(disPlayName);
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "");
		iron211.add(ChatColor.GRAY + lore);
		iron211.add(ChatColor.GRAY + lore2);
		plate211.setLore(iron211);
		chest211.setItemMeta(plate211);
		
		toInv.contains(chest211);
		toInv.setItem(slotNumber, chest211);
	}
	
	public void setredClayToInventory(Inventory inv, String displayName, String lore) {
		ItemStack chest211 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(displayName);
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "");
		iron211.add(ChatColor.GRAY + lore);
		plate211.setLore(iron211);
		chest211.setItemMeta(plate211);
		
		inv.contains(chest211);
		inv.addItem(chest211);
	}
	
	public static void setRedClassToInventory(Inventory inv, String displayName, String lore) {
		ItemStack chest211 = new ItemStack(Material.STAINED_GLASS, 1, (short) 14);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(displayName);
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "");
		iron211.add(ChatColor.GRAY + lore);
		plate211.setLore(iron211);
		chest211.setItemMeta(plate211);
		
		inv.contains(chest211);
		inv.addItem(chest211);
	}
	
	public static void setDataMaterialToInv(Inventory inv, String displayName, String lore, int data, Material mat) {
		ItemStack chest211 = new ItemStack(mat, 1, (short) data);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(displayName);
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "");
		iron211.add(ChatColor.GRAY + lore);
		plate211.setLore(iron211);
		chest211.setItemMeta(plate211);
		
		inv.contains(chest211);
		inv.addItem(chest211);
	}

	@SuppressWarnings("deprecation")
	public void setHelmet(Player p, String disPlayName, Material mat) {
		ItemStack chest211 = new ItemStack(mat, 1);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(disPlayName);
		chest211.setItemMeta(plate211);		
		
		p.getInventory().setHelmet(chest211);
		p.updateInventory();
		p.closeInventory();
	}
	
	@SuppressWarnings("deprecation")
	public void setClayHelmet(Player p, String disPlayName, Material mat, int data) {
		ItemStack chest211 = new ItemStack(mat, 1, (short) data);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(disPlayName);
		chest211.setItemMeta(plate211);		
		
		p.getInventory().setHelmet(chest211);
		p.updateInventory();
		p.closeInventory();
	}
	
	@EventHandler
	public void onItemClick(InventoryClickEvent e) {		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equals(playerHats.getName())) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.ICE) {//
				setHelmet(p, ChatColor.GOLD + "Your ice hat", Material.ICE);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.PACKED_ICE) {//
				setHelmet(p, ChatColor.GOLD + "Your Packed Ice Hat", Material.PACKED_ICE);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.CHEST) {//
				setHelmet(p, ChatColor.GOLD + "Your Chest Hat", Material.CHEST);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Red Clay hat")) {
				setClayHelmet(p, ChatColor.GOLD + "Your Red Clay Hat", Material.STAINED_CLAY, 14);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.CLAY) {//
				setHelmet(p, ChatColor.GOLD + "Your Clay Hat", Material.CLAY);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.CACTUS) {//
				setHelmet(p, ChatColor.GOLD + "Your Cactus Hat", Material.CACTUS);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.SAND) {//
				setHelmet(p, ChatColor.GOLD + "Your Sand Hat", Material.SAND);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Red Glass hat")) {
				setClayHelmet(p, ChatColor.GOLD + "Your Red Glass Hat", Material.STAINED_GLASS, 14);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Blue Glass hat")) {
				setClayHelmet(p, ChatColor.GOLD + "Your Blue Glass Hat", Material.STAINED_GLASS, 11);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Green Glass hat")) {
				setClayHelmet(p, ChatColor.GOLD + "Your Green Glass Hat", Material.STAINED_GLASS, 13);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.SNOW_BLOCK) {//
				setHelmet(p, ChatColor.GOLD + "Your Snow Hat", Material.SNOW_BLOCK);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.TNT) {//
				setHelmet(p, ChatColor.GOLD + "Your TNT Hat", Material.TNT);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.PUMPKIN) {//
				setHelmet(p, ChatColor.GOLD + "Your Pumpkin Hat", Material.PUMPKIN);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.WOOD) {//
				setHelmet(p, ChatColor.GOLD + "Your Wood Hat", Material.WOOD);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.DISPENSER) {//
				setHelmet(p, ChatColor.GOLD + "Your Dispenser Hat", Material.DISPENSER);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.BEACON) {//
				setHelmet(p, ChatColor.GOLD + "Your Beacon Hat", Material.BEACON);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.GRASS) {//
				setHelmet(p, ChatColor.GOLD + "Your Grass Block Hat", Material.GRASS);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}else if (e.getCurrentItem().getType() == Material.LAPIS_BLOCK) {//
				setHelmet(p, ChatColor.GOLD + "Your Lapiz Hat", Material.LAPIS_BLOCK);
				p.sendMessage(ChatColor.GOLD + "You now wearing this awsome hat!");
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemClick1(InventoryClickEvent e) {		
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equals(p.getInventory().getName())) {
			if (e.getCurrentItem().hasItemMeta() &&
					e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Remove your curent hat")) {
				if (p.getInventory().getHelmet() != null) {			
					e.setCancelled(true);
				p.getInventory().setHelmet(null);
				p.updateInventory();
				p.closeInventory();
				p.sendMessage(ChatColor.GOLD + "Hat removed!");
				}else {
					p.sendMessage(ChatColor.GOLD + "You do not wear any hat!");
					e.setCancelled(true);
					p.closeInventory();
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		ItemStack chest211 = new ItemStack(Material.TNT, 1);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Remove your curent hat");
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "");
		iron211.add(ChatColor.GRAY + "Click to remove your hat that");
		iron211.add(ChatColor.GRAY + "you wearing right now!");
		plate211.setLore(iron211);
		chest211.setItemMeta(plate211);
		
		Player p = e.getPlayer();
		p.getInventory().contains(chest211);
		p.getInventory().setItem(17, chest211);
	}
	
    @EventHandler
    public void hatItemClickEvent1(InventoryClickEvent event) {    	
    	playerHats = Bukkit.createInventory(null, 18, ChatColor.AQUA + "" + ChatColor.BOLD + event.getWhoClicked().getName() + "'s hat's");
    	if (plugin.getBoolean("Hats.iceBlock") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "IceBlock Hat", Material.ICE, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.packetIce") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "PackedIce Hat", Material.PACKED_ICE, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.chest") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "Chest Hat", Material.CHEST, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.redClay") == true) {
    		setredClayToInventory(playerHats, ChatColor.GOLD + "Red Stained Clay Hat", "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.tnt") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "TNT Hat", Material.TNT, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.cactus") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "Cactus Hat", Material.CACTUS, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.sand") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "Sand Hat", Material.SAND, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.redGlass") == true) {
    		setDataMaterialToInv(playerHats, ChatColor.GOLD + "Red Glass Hat", "Click to use this hat", 14, Material.STAINED_GLASS);
    	}
    	if (plugin.getBoolean("Hats.blueGlass") == true) {
    		setDataMaterialToInv(playerHats, ChatColor.GOLD + "Blue Glass Hat", "Click to use this hat", 11, Material.STAINED_GLASS);
    	}
    	if (plugin.getBoolean("Hats.greenGlass") == true) {
    		setDataMaterialToInv(playerHats, ChatColor.GOLD + "Green Glass Hat", "Click to use this hat", 13, Material.STAINED_GLASS);
    	}
    	if (plugin.getBoolean("Hats.clay") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "Clay Hat", Material.CLAY, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.snowBlock") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "SnowBlock Hat", Material.SNOW_BLOCK, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.pumpkin") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "Pumpkin Hat", Material.PUMPKIN, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.wood") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "Wood Hat", Material.WOOD, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.dispenser") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "Dispenser Hat", Material.DISPENSER, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.beacon") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "Beacon Hat", Material.BEACON, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.grass") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "Grass Block Hat", Material.GRASS, "Click to use this hat");
    	}
    	if (plugin.getBoolean("Hats.lapiz") == true) {
    		addItemToHatsInventory(playerHats, ChatColor.GOLD + "Lapiz Hat", Material.LAPIS_BLOCK, "Click to use this hat");
    	}
            Player player = (Player) event.getWhoClicked();
            ItemStack clicked = event.getCurrentItem();
            Inventory inventory = event.getInventory();
            if (inventory.getName().equals(Menu.hatshop.getName())) {
                      if (clicked.getType() == Material.SKULL_ITEM) {//
                    	  player.openInventory(playerHats);
                      }//
                  }
              }
	
    @EventHandler
    public void hatItemClickEvent(InventoryClickEvent event) {
    	String hatNull = MsgType.ACCESOIRIES + "You have already buyt an hat, you can only buy hats with no hat on your head!";    	
    	
		ItemStack red = new ItemStack(Material.STAINED_GLASS ,1 , (short) 14);
		ItemMeta red1 = red.getItemMeta();
		red1.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Red Glass hat");
		ArrayList<String> red2 = new ArrayList<String>();
		red2.add(ChatColor.GRAY + "");
		red2.add(ChatColor.GRAY + "Right click to buy this item!");
		red2.add(ChatColor.GRAY + "Price: " + ChatColor.RED + redGlassPrice + ChatColor.GRAY + " points");
		red1.setLore(red2);
		red.setItemMeta(red1);
		
		ItemStack blue = new ItemStack(Material.STAINED_GLASS ,1 , (short) 11);
		ItemMeta blue1 = blue.getItemMeta();
		blue1.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Blue Glass hat");
		ArrayList<String> blue3 = new ArrayList<String>();
		blue3.add(ChatColor.GRAY + "");
		blue3.add(ChatColor.GRAY + "Right click to buy this item!");
		blue3.add(ChatColor.GRAY + "Price: " + ChatColor.RED + blueGlassPrice + ChatColor.GRAY + " points");
		blue1.setLore(blue3);
		blue.setItemMeta(blue1);
		
		ItemStack green = new ItemStack(Material.STAINED_GLASS ,1 , (short) 13);
		ItemMeta green1 = green.getItemMeta();
		green1.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Green Glass hat");
		ArrayList<String> green2 = new ArrayList<String>();
		green2.add(ChatColor.GRAY + "");
		green2.add(ChatColor.GRAY + "Right click to buy this item!");
		green2.add(ChatColor.GRAY + "Price: " + ChatColor.RED + greenGlassPrice + ChatColor.GRAY + " points");
		green1.setLore(green2);
		green.setItemMeta(green1);
		
            Player player = (Player) event.getWhoClicked();
            ItemStack clicked = event.getCurrentItem();
            Inventory inventory = event.getInventory();
            if (inventory.getName().equals(Menu.hatshop.getName())) {
                event.setCancelled(true);
                      if (clicked.getType() == Material.ICE) {//
                          player.closeInventory();
                      if (player.getInventory().getHelmet() == null) {
                    	  if (plugin.getBoolean("Hats.iceBlock") == false) {
                      if(Main.playerPoints.getAPI().take(player.getName(), icePrice)) {
                           	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                           	plugin.updateConfigPerPlayer("Hats.iceBlock", true);
                    	} else {
                    		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                    	}
                      } else {
                    	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                      }
                         }else {
                        	 player.sendMessage(hatNull);
                         }
                     }else if (clicked.getType() == Material.PACKED_ICE) {//
                         player.closeInventory();
                         if (player.getInventory().getHelmet() == null) {
                        	 if (plugin.getBoolean("Hats.packetIce") == false) {
                         if(Main.playerPoints.getAPI().take(player.getName(), packedIcePrice)) {
                            	 player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                              	plugin.updateConfigPerPlayer("Hats.packetIce", true);
                       	} else {
                       		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                       	}
                         } else {
                         	player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                         }
                            }else {
                           	 player.sendMessage(hatNull);
                            }
                     }else if (clicked.getType() == Material.CHEST) {//
                         player.closeInventory();
                         if (player.getInventory().getHelmet() == null) {
                       	  if (plugin.getBoolean("Hats.chest") == false) {
                         if(Main.playerPoints.getAPI().take(player.getName(), chestPrice)) {
                              	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                              	plugin.updateConfigPerPlayer("Hats.chest", true);
                       	} else {
                       		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                       	}
                         } else {
                       	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                         }
                            }else {
                           	 player.sendMessage(hatNull);
                            }
                     }else if (clicked.getType() == Material.STAINED_CLAY) {//
                         player.closeInventory();
                     if (player.getInventory().getHelmet() == null) {
                   	  if (plugin.getBoolean("Hats.redClay") == false) {
                     if(Main.playerPoints.getAPI().take(player.getName(), redClayPrice)) {
                          	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                          	plugin.updateConfigPerPlayer("Hats.redClay", true);
                   	} else {
                   		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                   	}
                     } else {
                   	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                     }
                        }else {
                       	 player.sendMessage(hatNull);
                        }
                     }else if (clicked.getType() == Material.TNT) {//
                         player.closeInventory();
                     if (player.getInventory().getHelmet() == null) {
                   	  if (plugin.getBoolean("Hats.tnt") == false) {
                     if(Main.playerPoints.getAPI().take(player.getName(), tntPrice)) {
                          	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                          	plugin.updateConfigPerPlayer("Hats.tnt", true);
                   	} else {
                   		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                   	}
                     } else {
                   	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                     }
                        }else {
                       	 player.sendMessage(hatNull);
                        }
                     }else if (clicked.getType() == Material.CACTUS) {//
                         player.closeInventory();
                         if (player.getInventory().getHelmet() == null) {
                       	  if (plugin.getBoolean("Hats.cactus") == false) {
                         if(Main.playerPoints.getAPI().take(player.getName(), cactusPrice)) {
                              	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                              	plugin.updateConfigPerPlayer("Hats.cactus", true);
                       	} else {
                       		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                       	}
                         } else {
                       	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                         }
                            }else {
                           	 player.sendMessage(hatNull);
                            }
                     }else if (clicked.getType() == Material.SAND) {//
                         player.closeInventory();
                         if (player.getInventory().getHelmet() == null) {
                       	  if (plugin.getBoolean("Hats.sand") == false) {
                         if(Main.playerPoints.getAPI().take(player.getName(), sandPrice)) {
                              	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                              	plugin.updateConfigPerPlayer("Hats.sand", true);
                       	} else {
                       		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                       	}
                         } else {
                       	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                         }
                            }else {
                           	 player.sendMessage(hatNull);
                            }
                     }else 
                    	 if (event.getCurrentItem().equals(red)) {
                         player.closeInventory();
                     if (player.getInventory().getHelmet() == null) {//
                   	  if (plugin.getBoolean("Hats.redGlass") == false) {//*
                     if(Main.playerPoints.getAPI().take(player.getName(), redGlassPrice)) {//=
                          	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                          	plugin.updateConfigPerPlayer("Hats.redGlass", true);
                   	} else {//=
                   		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                   	}
                     } else {//*
                   	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                     }
                        }else {//
                       	 player.sendMessage(hatNull);
                        }
                    	 
                     }else 
                    	 if (event.getCurrentItem().equals(blue)) {
                         player.closeInventory();
                     if (player.getInventory().getHelmet() == null) {//-
                   	  if (plugin.getBoolean("Hats.blueGlass") == false) {//=
                     if(Main.playerPoints.getAPI().take(player.getName(), blueGlassPrice)) {//*
                          	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                          	plugin.updateConfigPerPlayer("Hats.blueGlass", true);
                   	} else {//*
                   		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                   	}
                     } else {//=
                   	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                     }
                        }else {//-
                       	 player.sendMessage(hatNull);
                        }
                    	 
                     }else 
                    	 if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Green Glass hat")) {
                         player.closeInventory();
                     if (player.getInventory().getHelmet() == null) {
                   	  if (plugin.getBoolean("Hats.greenGlass") == false) {
                     if(Main.playerPoints.getAPI().take(player.getName(), greenGlassPrice)) {
                          	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                          	plugin.updateConfigPerPlayer("Hats.greenGlass", true);
                   	} else {
                   		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                   	}
                     } else {
                   	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                     }
                        }else {
                       	 player.sendMessage(hatNull);
                        }
                    	 }else if (clicked.getType() == Material.SNOW_BLOCK) {//
                             player.closeInventory();
                             if (player.getInventory().getHelmet() == null) {
                           	  if (plugin.getBoolean("Hats.snowBlock") == false) {
                             if(Main.playerPoints.getAPI().take(player.getName(), snowPrice)) {
                                  	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                                  	plugin.updateConfigPerPlayer("Hats.snowBlock", true);
                           	} else {
                           		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                           	}
                             } else {
                           	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                             }
                                }else {
                               	 player.sendMessage(hatNull);
                                }
                    	 }else if (clicked.getType() == Material.CLAY) {//
                             player.closeInventory();
                             if (player.getInventory().getHelmet() == null) {
                           	  if (plugin.getBoolean("Hats.clay") == false) {
                             if(Main.playerPoints.getAPI().take(player.getName(), clayPrice)) {
                                  	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                                  	plugin.updateConfigPerPlayer("Hats.clay", true);
                           	} else {
                           		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                           	}
                             } else {
                           	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                             }
                                }else {
                               	 player.sendMessage(hatNull);
                                }
                    	 }else if (clicked.getType() == Material.PUMPKIN) {//
                             player.closeInventory();
                             if (player.getInventory().getHelmet() == null) {
                           	  if (plugin.getBoolean("Hats.pumpkin") == false) {
                             if(Main.playerPoints.getAPI().take(player.getName(), pumpkinPrice)) {
                                  	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                                  	plugin.updateConfigPerPlayer("Hats.pumpkin", true);
                           	} else {
                           		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                           	}
                             } else {
                           	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                             }
                                }else {
                               	 player.sendMessage(hatNull);
                                }
                    	 }else if (clicked.getType() == Material.WOOD) {//
                             player.closeInventory();
                             if (player.getInventory().getHelmet() == null) {
                           	  if (plugin.getBoolean("Hats.wood") == false) {
                             if(Main.playerPoints.getAPI().take(player.getName(), woodPrice)) {
                                  	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                                  	plugin.updateConfigPerPlayer("Hats.wood", true);
                           	} else {
                           		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                           	}
                             } else {
                           	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                             }
                                }else {
                               	 player.sendMessage(hatNull);
                                }
                    	 }else if (clicked.getType() == Material.DISPENSER) {//
                             player.closeInventory();
                             if (player.getInventory().getHelmet() == null) {
                           	  if (plugin.getBoolean("Hats.dispenser") == false) {
                             if(Main.playerPoints.getAPI().take(player.getName(), dispenserPrice)) {
                                  	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                                  	plugin.updateConfigPerPlayer("Hats.dispenser", true);
                           	} else {
                           		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                           	}
                             } else {
                           	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                             }
                                }else {
                               	 player.sendMessage(hatNull);
                                }
                    	 }else if (clicked.getType() == Material.BEACON) {//
                             player.closeInventory();
                             if (player.getInventory().getHelmet() == null) {
                           	  if (plugin.getBoolean("Hats.beacon") == false) {
                             if(Main.playerPoints.getAPI().take(player.getName(), beaconPrice)) {
                                  	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                                  	plugin.updateConfigPerPlayer("Hats.beacon", true);
                           	} else {
                           		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                           	}
                             } else {
                           	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                             }
                                }else {
                               	 player.sendMessage(hatNull);
                                }
                    	 }else if (clicked.getType() == Material.GRASS) {//
                             player.closeInventory();
                             if (player.getInventory().getHelmet() == null) {
                           	  if (plugin.getBoolean("Hats.grass") == false) {
                             if(Main.playerPoints.getAPI().take(player.getName(), grassPrice)) {
                                  	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                                  	plugin.updateConfigPerPlayer("Hats.grass", true);
                           	} else {
                           		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                           	}
                             } else {
                           	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                             }
                                }else {
                               	 player.sendMessage(hatNull);
                                }
                    	 }else if (clicked.getType() == Material.LAPIS_BLOCK) {//
                             player.closeInventory();
                             if (player.getInventory().getHelmet() == null) {
                           	  if (plugin.getBoolean("Hats.lapiz") == false) {
                             if(Main.playerPoints.getAPI().take(player.getName(), lapizPrice)) {
                                  	player.sendMessage(MsgType.ACCESOIRIES + "Thanks, your hat is added to your hats");
                                  	plugin.updateConfigPerPlayer("Hats.lapiz", true);
                           	} else {
                           		player.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                           	}
                             } else {
                           	  player.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... Check your hats");
                             }
                                }else {
                               	 player.sendMessage(hatNull);
                                }
                    	 }
             }
    }
}

	
