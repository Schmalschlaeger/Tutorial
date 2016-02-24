package scoreboard;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
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

import Accesoiries.Hat;
import scoreboard.MessageManager.MsgType;

@SuppressWarnings("deprecation")
public class Menu implements Listener, CommandExecutor {
	
	private Main plugin;
	 
	public Menu(Main instance) {
	    this.plugin = instance;
	}
	
	Checklist checklist = new Checklist(plugin);
	
	API h = new API(plugin);
	
	public static Inventory menu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Magic-Saga Menu");
	public static Inventory status = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Magic-Saga StatusMenu");
	public static Inventory accesoiries = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Magic-Saga Accesoiries");
	public static Inventory ingameShop = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Magic-Saga IngameShop");
	public static Inventory checkMenu;
	public static Inventory hatshop;
	
	String arguments = MsgType.NORMAL + "Wrong argument section! Try /ride add|remove <name>";
	
	static { 		
		ItemStack chest2 = new ItemStack(Material.COMPASS);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "STATUS");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "Here you can see what is open!");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
		
		menu.contains(chest2);
		menu.setItem(0, chest2);
		
		ItemStack chest21 = new ItemStack(Material.MINECART);
		ItemMeta plate21 = chest21.getItemMeta();
		plate21.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "RIDES");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.GRAY + "See the rides on the server!");
		plate21.setLore(iron21);
		chest21.setItemMeta(plate21);
		
		status.contains(chest21);
		status.setItem(0, chest21);
		
		ItemStack chest214 = new ItemStack(Material.NETHER_STAR);
		ItemMeta plate214 = chest214.getItemMeta();
		plate214.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "SHOWS");
		ArrayList<String> iron214 = new ArrayList<String>();
		iron214.add(ChatColor.GRAY + "");
		iron214.add(ChatColor.GRAY + "See the Shows on the server!");
		plate214.setLore(iron214);
		chest214.setItemMeta(plate214);
		
		status.contains(chest214);
		status.setItem(8, chest214);		
		
		ItemStack chest21111 = new ItemStack(Material.BEACON);
		ItemMeta plate21111 = chest21111.getItemMeta();
		plate21111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "CheckList");
		ArrayList<String> iron21111 = new ArrayList<String>();
		iron21111.add(ChatColor.GRAY + "");
		iron21111.add(ChatColor.GRAY + "Here you can see what you have done!");
		iron21111.add(ChatColor.RED + "" + ChatColor.ITALIC+ "Coming Soon");
		plate21111.setLore(iron21111);
		chest21111.setItemMeta(plate21111);
		
		menu.contains(chest21111);
		menu.setItem(8, chest21111);
		
		ItemStack chest211111 = new ItemStack(Material.getMaterial(175));
		ItemMeta plate211111 = chest211111.getItemMeta();
		plate211111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Accesoiries");
		ArrayList<String> iron211111 = new ArrayList<String>();
		iron211111.add(ChatColor.GRAY + "");
		iron211111.add(ChatColor.GRAY + "See Accesoiries on the server");
		plate211111.setLore(iron211111);
		chest211111.setItemMeta(plate211111);
		
		menu.contains(chest211111);
		menu.setItem(4, chest211111);
		
		ItemStack chest2111111 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta plate2111111 = chest2111111.getItemMeta();
		plate2111111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Hats");
		ArrayList<String> iron2111111 = new ArrayList<String>();
		iron2111111.add(ChatColor.GRAY + "");
		iron2111111.add(ChatColor.GRAY + "See the hats you can buy");
		iron2111111.add(ChatColor.RED + "" + ChatColor.ITALIC+ "Coming Soon");
		plate2111111.setLore(iron2111111);
		chest2111111.setItemMeta(plate2111111);
		
		accesoiries.contains(chest2111111);
		accesoiries.setItem(0, chest2111111);
		
		ItemStack chest21111111 = new ItemStack(Material.CHEST);
		ItemMeta plate21111111 = chest21111111.getItemMeta();
		plate21111111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ingame Shop");
		ArrayList<String> iron21111111 = new ArrayList<String>();
		iron21111111.add(ChatColor.GRAY + "");
		iron21111111.add(ChatColor.GRAY + "Buy ranks with your");
		iron21111111.add(ChatColor.GRAY + "ingame points!");
		iron21111111.add(ChatColor.RED + "" + ChatColor.ITALIC+ "Coming Soon");
		plate21111111.setLore(iron21111111);
		chest21111111.setItemMeta(plate21111111);
		
		accesoiries.contains(chest21111111);
		accesoiries.setItem(8, chest21111111);
	}

	static { //ingame shop
		ItemStack chest2 = new ItemStack(Material.IRON_INGOT);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "FastPass");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "Buy an fastpass!");
		iron2.add(ChatColor.GRAY + "Cost: 500 points");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
		
		ingameShop.contains(chest2);
		ingameShop.setItem(0, chest2);
	}
	
	@EventHandler
	public void onChecklistClick1(InventoryClickEvent e) {	
		//Player p = (Player) e.getWhoClicked();
		checkMenu = Bukkit.createInventory(null, 45, ChatColor.RED + e.getWhoClicked().getName() + "'s " + ChatColor.GOLD + "checklist");
		//toCheckListInventory(Main.pd.getData(), "Achievements", checkMenu);
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "CheckList")) {
			    	if (cl.getType() == Material.BEACON) {
					e.setCancelled(true);
					//p.playSound(e.getWhoClicked().getLocation(), Sound.CLICK, 2, 2);
					//p.openInventory(checkMenu);
			    	}
			 	}
			}
	   }
	
    public static Inventory toCheckListInventory(FileConfiguration config, String path, Inventory inv) {
        for (int i = 0; i<27;i++) {
            if (config.isItemStack(path + "." + i)) {
            	inv.setItem(i, config.getItemStack(path + "." + i));
            }
        }
        return inv;
    }
	
	@EventHandler
 	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Inventory inv = p.getInventory();
		
		ItemStack chest1 = new ItemStack(Material.getMaterial(403));
		ItemMeta plate1 = chest1.getItemMeta();
		plate1.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Magic-Navigator" + ChatColor.RESET + "" + ChatColor.GRAY + " (Click to open)");
		chest1.setItemMeta(plate1);
		
		inv.contains(chest1);
		inv.setItem(0, chest1);
		
	}
	
	@EventHandler
	public void onChestPlateClick12(InventoryClickEvent e) {
		hatshop = Bukkit.createInventory(null, 27, ChatColor.AQUA + "" + ChatColor.BOLD + "Magic-Saga HatShop");
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(accesoiries.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Hats")) {
			    	if (cl.getType() == Material.SKULL_ITEM) {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
					Hat.setItemToHatShop(p, hatshop);
				    p.openInventory(hatshop);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onChestClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		//Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(accesoiries.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Ingame Shop")) {
			    	if (cl.getType() == Material.CHEST) {
					e.setCancelled(true);
					//p.playSound(p.getLocation(), Sound.CHEST_OPEN, 2, 2);
					//p.openInventory(ingameShop);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onIronIngotClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(ingameShop.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "" + ChatColor.BOLD + "FastPass")) {
			    	if (cl.getType() == Material.IRON_INGOT) {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
                    if (p.getDisplayName() == ChatColor.RED + "Fastpass") {
                    if(Main.playerPoints.getAPI().take(p.getName(), 500)) {
    					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "/manuadd PLAYER Fastpass");               
                  	} else {
                  		p.sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this item!");
                  	}
                     
                    }else {
                  	  p.sendMessage(MsgType.ACCESOIRIES + "You have already buyt this item.... -,-");
                    }
			    	}
			 	}
			}
    	}
	   
	
	@EventHandler
	public void onChestPlateClick1(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(status.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "RIDES")) {
			    	if (cl.getType() == Material.MINECART) {
					e.setCancelled(true);
					p.playSound(e.getWhoClicked().getLocation(), Sound.CLICK, 2, 2);
					p.openInventory(Main.inventory);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onArrowBackPlateClick1(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(Main.inventory.getName()) || inv.getName().equals(Main.shows.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Go Back!")) {
			    	if (cl.getType() == Material.ARROW) {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
					p.openInventory(status);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onArrowBackPlate2Click1(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(status.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Go Back!")) {
			    	if (cl.getType() == Material.ARROW) {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
					p.openInventory(menu);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onChestPlateClick11(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(status.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "SHOWS")) {
			    	if (cl.getType() == Material.NETHER_STAR) {
					e.setCancelled(true);
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
					p.openInventory(Main.shows);					
			    	}
			 	}
			}
	   }
	@EventHandler
	public void onChestPlateClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(inv.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.RED + "" + ChatColor.BOLD + "Magic-Navigator" + ChatColor.RESET + "" + ChatColor.GRAY + " (Click to open)")) {
			    	if (cl.getType() == Material.getMaterial(403)) {
					    e.setCancelled(true);
			    	}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Item reserved for minigames!")) {
			 		if (cl.getType() == Material.STICK) {
					    e.setCancelled(true);
			 		}
			 	}
			}
	   }
	
	@EventHandler
	public void onBoatClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "STATUS")) {
			    	if (cl.getType() == Material.COMPASS) {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
			        p.openInventory(status);
			        }
			 
			    	}
			 	}
			}
	
	@EventHandler
	public void onBoatClick1(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "CheckList")) {
			    	if (cl.getType() == Material.BEACON) {
			    		p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
					e.setCancelled(true);
			        }
			 
			    	}
			 	}
			}

	@EventHandler
	public void onBoatClick11(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Accesoiries")) {
			    	if (cl.getType() == Material.getMaterial(175)) {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.CLICK, 2, 2);
					p.openInventory(accesoiries);
			        }
			 
			    	}
			 	}
			}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		
		if (!p.isOp()) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack cl = e.getItem();
		if (p.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()) {
					if (cl.getType() == Material.getMaterial(403)) {
						e.setCancelled(true);	
						
						p.openInventory(menu);
					}
    			}
        	}
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player pl = (Player) e.getWhoClicked();
		FileConfiguration config = plugin.newConfigz;
		if (config.contains("Rides"))  {
            Set<String> rideKeys = config.getConfigurationSection("Rides").getKeys(false); // Alle ride names (anus, testshow etc. Welke mongool noemt zijn ride nu anus!)
            for (String ridename: rideKeys) {
            	Inventory inv = e.getInventory();
        		ItemStack cl = e.getCurrentItem();
        			if (inv.getName().equals(Main.inventory.getName())) {
        			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GRAY + "• " + ChatColor.GREEN + ridename + ChatColor.GRAY + " •")) {
        			    	if (cl.getType() == Material.STAINED_GLASS) {
        					e.setCancelled(true);
        					pl.closeInventory();
        					pl.playSound(pl.getLocation(), Sound.CLICK, 2, 2);
        					teleportToRide(ridename, pl);
        			    	}
        			 	}
        			}
            }
        }
	}
	
	@EventHandler
	public void onRedstoneBlockClick1(InventoryClickEvent e) {
            	Inventory inv = e.getInventory();
        			if (inv.getName().equals(Main.inventory.getName())) {
        					e.setCancelled(true);
        			    	}else if (inv.getName().equals(menu.getName())) {
        			    		e.setCancelled(true);
        			    	}else if (inv.getName().equals(status.getName())) {
        			    		e.setCancelled(true);
        			    	}else if (inv.getName().equals(accesoiries.getName())) {
        			    		e.setCancelled(true);
        			    	}else if (inv.getName().equals(hatshop.getName())) {
        			    		e.setCancelled(true);
        			    	}
        			 	}
        		
	@EventHandler
	public void onRedstoneBlockClick(InventoryClickEvent e) {
		FileConfiguration config = plugin.newConfigz;
		Player pl = (Player) e.getWhoClicked();
		if (config.contains("Rides"))  {
            Set<String> rideKeys = config.getConfigurationSection("Rides").getKeys(false); // Alle ride names (anus, testshow etc. Welke mongool noemt zijn ride nu anus!)
            for (String ridename: rideKeys) {
            	Inventory inv = e.getInventory();
        		ItemStack cl = e.getCurrentItem();
        			if (inv.getName().equals(Main.inventory.getName())) {
        			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GRAY + "• " + ChatColor.RED + ridename + ChatColor.GRAY + " •")) {
        			    	if (cl.getType() == Material.STAINED_GLASS) {
        					e.setCancelled(true);
        					pl.playSound(pl.getLocation(), Sound.CLICK, 2, 2);
        			    	}
        			 	}
        			}
            }
        }
	}
	 
	public boolean isClosed(String name, Player p) {
		ItemStack chest11 = new ItemStack(Material.STAINED_GLASS, 1, (short) 14);
		ItemMeta plate11 = chest11.getItemMeta();
		plate11.setDisplayName(ChatColor.GRAY + "• " + ChatColor.RED + name + ChatColor.GRAY + " •");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.GRAY + "It is closed!");
		iron21.add(ChatColor.GRAY + "Status: " + ChatColor.RED + "Closed");
		plate11.setLore(iron21);
		chest11.setItemMeta(plate11);
		
		if (Main.inventory.contains(chest11)) {
			p.sendMessage(MsgType.DENIED + "This ride is closed! Make sure it is open!");
			return true;
		}
		return false;
	}
	
	public void createRideToMenu(String name, Player p) {
        FileConfiguration c = plugin.newConfigz;
        Location loc = p.getLocation();
        String world = p.getWorld().getName();
        int x, y, z;
        float yaw, pitch;
        
		ItemStack chest1 = new ItemStack(Material.STAINED_GLASS, 1, (short) 5);		
		ItemMeta plate1 = chest1.getItemMeta();		
		plate1.setDisplayName(ChatColor.GRAY + "• " + ChatColor.GREEN + name + ChatColor.GRAY + " •");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "Status: " + ChatColor.GREEN + "Open");
		plate1.setLore(iron2);
		chest1.setItemMeta(plate1);
		
		if (Main.inventory.contains(chest1)) {
			p.sendMessage(MsgType.ERROR + name + ", already created! I dont understand the logic, for making 2 items?");
		}else {
 
        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();

        c.set("Rides." + name + ".x", x);
        c.set("Rides." + name + ".y", y);
        c.set("Rides." + name + ".z", z);
        c.set("Rides." + name + ".yaw", yaw);
        c.set("Rides." + name + ".pitch", pitch);
        c.set("Rides." + name + ".world", world);
        plugin.saveNewConfig();
		
		Main.inventory.contains(chest1);
		Main.inventory.addItem(chest1);
		
		plugin.saveInventory(Main.inventory, plugin.rideFile, "Rides");
		plugin.saveRideConfig();
		
		p.sendMessage(MsgType.NORMAL + "Succesfull created ride: " + name);
		}
	}

	public void setRideClosed(String name, CommandSender p) {
        FileConfiguration c = plugin.newConfigz;

		ItemStack chest1 = new ItemStack(Material.STAINED_GLASS, 1, (short) 5);		
		ItemMeta plate1 = chest1.getItemMeta();		
		plate1.setDisplayName(ChatColor.GRAY + "• " + ChatColor.GREEN + name + ChatColor.GRAY + " •");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "Status: " + ChatColor.GREEN + "Open");
		plate1.setLore(iron2);
		chest1.setItemMeta(plate1);
		
		ItemStack chest11 = new ItemStack(Material.STAINED_GLASS, 1, (short) 14);
		ItemMeta plate11 = chest11.getItemMeta();
		plate11.setDisplayName(ChatColor.GRAY + "• " + ChatColor.RED + name + ChatColor.GRAY + " •");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.GRAY + "It is closed!");
		iron21.add(ChatColor.GRAY + "Status: " + ChatColor.RED + "Closed");
		plate11.setLore(iron21);
		chest11.setItemMeta(plate11);
        
		if (p instanceof BlockCommandSender) {
        if (c.contains("Rides." + name)) {
        	if (Main.inventory.contains(chest1)) {
        		Main.inventory.remove(chest1);
        		Main.inventory.addItem(chest11);
        		plugin.saveInventory(Main.inventory, plugin.rideFile, "Rides");
        		plugin.saveRideConfig();
        		Bukkit.getServer().broadcastMessage(MsgType.SHOW + "The ride " + name + " has been closed!");
        	}else {
        		p.sendMessage(MsgType.ERROR + "This ride is already closed!");
        	}
        }else {
        	p.sendMessage(MsgType.ERROR + "No ride found by the name: " + ChatColor.YELLOW + name);
        }
        }else {
            if (c.contains("Rides." + name)) {
            	if (Main.inventory.contains(chest1)) {
            		Main.inventory.remove(chest1);
            		Main.inventory.addItem(chest11);
            		plugin.saveInventory(Main.inventory, plugin.rideFile, "Rides");
            		plugin.saveRideConfig();
            		Bukkit.getServer().broadcastMessage(MsgType.SHOW + "The ride " + name + " has been closed!");
            	}else {
            		p.sendMessage(MsgType.ERROR + "This ride is already closed!");
            	}
            }else {
            	p.sendMessage(MsgType.ERROR + "No ride found by the name: " + ChatColor.YELLOW + name);
            }
        }
	}
	
	public void setRideOpen(String name, CommandSender p) {
        FileConfiguration c = plugin.newConfigz;

		ItemStack chest1 = new ItemStack(Material.STAINED_GLASS, 1, (short) 5);		
		ItemMeta plate1 = chest1.getItemMeta();		
		plate1.setDisplayName(ChatColor.GRAY + "• " + ChatColor.GREEN + name + ChatColor.GRAY + " •");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "Status: " + ChatColor.GREEN + "Open");
		plate1.setLore(iron2);
		chest1.setItemMeta(plate1);
		
		ItemStack chest11 = new ItemStack(Material.STAINED_GLASS, 1, (short) 14);
		ItemMeta plate11 = chest11.getItemMeta();
		plate11.setDisplayName(ChatColor.GRAY + "• " + ChatColor.RED + name + ChatColor.GRAY + " •");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.GRAY + "It is closed!");
		iron21.add(ChatColor.GRAY + "Status: " + ChatColor.RED + "Closed");
		plate11.setLore(iron21);
		chest11.setItemMeta(plate11);
        
        if (c.contains("Rides." + name)) {
        	if (Main.inventory.contains(chest11)) {
        		Main.inventory.remove(chest11);
        		Main.inventory.addItem(chest1);
        		plugin.saveInventory(Main.inventory, plugin.rideFile, "Rides");
        		plugin.saveRideConfig();
        		Bukkit.getServer().broadcastMessage(MsgType.SHOW + "The ride " + name + " has been Opened!");
        		for (final Player all : Bukkit.getServer().getOnlinePlayers()) {
        			all.getWorld().playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 5, 5);
        		}
        	}else {
        		p.sendMessage(MsgType.ERROR + "This ride is already open!");
        	}
        }else {
        	p.sendMessage(MsgType.ERROR + "No ride found by the name: " + ChatColor.YELLOW + name);
        	p.sendMessage(MsgType.ERROR + "Note the uppercase!");
        }
	}
	
	public void createChecklistItem(String name, Player p) {        
		ItemStack chest1 = new ItemStack(Material.WOOL, (short) 14);		
		ItemMeta plate1 = chest1.getItemMeta();		
		plate1.setDisplayName(ChatColor.GRAY + "• " + ChatColor.RED + name + ChatColor.GRAY + " •");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.DARK_GRAY + "EMPTY!");
		plate1.setLore(iron2);
		chest1.setItemMeta(plate1);
		
		Main.inventory.contains(chest1);
		Main.inventory.addItem(chest1);
		
		plugin.saveInventory(Main.inventory, plugin.rideFile, "Rides");
		plugin.saveRideConfig();
	}
	
	public void removeChecklistItem(String name, Player p) {
        FileConfiguration c = plugin.newConfigz;
        Location loc = p.getLocation();
        String world = p.getWorld().getName();
        int x, y, z;
        float yaw, pitch;
 
        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();

        c.set("Rides." + name + ".x", x);
        c.set("Rides." + name + ".y", y);
        c.set("Rides." + name + ".z", z);
        c.set("Rides." + name + ".yaw", yaw);
        c.set("Rides." + name + ".pitch", pitch);
        c.set("Rides." + name + ".world", world);
        plugin.saveNewConfig();
        
		ItemStack chest1 = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta plate1 = chest1.getItemMeta();
		plate1.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "====[ " + name + " ]====");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.DARK_GRAY + "Click to go to: " + ChatColor.GOLD + name);
		plate1.setLore(iron2);
		chest1.setItemMeta(plate1);
		
		Main.inventory.contains(chest1);
		Main.inventory.addItem(chest1);
		
		plugin.saveInventory(Main.inventory, plugin.rideFile, "Rides");
		plugin.saveRideConfig();
	}
	
	public void teleportToRide(final String rideName, final Player p) {
		FileConfiguration c = plugin.newConfigz;
        final int x;
		final int y;
		final int z;
		final int yaw;
		final int pitch;
 
		if (!isClosed(rideName, p)) {		
        if (c.contains("Rides." + rideName)){
            final World world = Bukkit.getServer().getWorld(c.getString("Rides." + rideName + ".world"));
            x = c.getInt("Rides." + rideName + ".x");
            y = c.getInt("Rides." + rideName + ".y");
            z = c.getInt("Rides." + rideName + ".z");
            yaw = c.getInt("Rides." + rideName + ".yaw");
            pitch = c.getInt("Rides." + rideName + ".pitch");
            
            p.teleport(new Location(world, x, y, z, yaw, pitch));
    		p.sendMessage(MsgType.NORMAL + "Teleported you to the Ride: " + ChatColor.RED + rideName);
    		p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 2, 2);
    		p.playEffect(p.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
    		
        }else {
 			p.sendMessage(MsgType.ERROR + "Ride " + ChatColor.RED + rideName + ChatColor.DARK_GRAY + " not found!");
        }
        }
	}
	
	public void removeRideFromMenu(String name, Player p) {
		ItemStack chest11 = new ItemStack(Material.STAINED_GLASS, 1, (short) 14);
		ItemMeta plate11 = chest11.getItemMeta();
		plate11.setDisplayName(ChatColor.GRAY + "• " + ChatColor.RED + name + ChatColor.GRAY + " •");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.GRAY + "It is closed!");
		iron21.add(ChatColor.GRAY + "Status: " + ChatColor.RED + "Closed");
		plate11.setLore(iron21);
		chest11.setItemMeta(plate11);
		
        FileConfiguration c = plugin.newConfigz;
        
        if (c.contains("Rides." + name)){
        	if (Main.inventory.contains(chest11)) {
            c.set("Rides." + name, null);
            plugin.saveNewConfig();
            Main.inventory.remove(chest11);
    		plugin.saveInventory(Main.inventory, plugin.rideFile, "Rides");
    		plugin.saveRideConfig();
    		Main.toInventory(plugin.rideFile, "Rides");
            p.sendMessage(MsgType.NORMAL + "Successfully removed the Ride: " + ChatColor.RED + name);
        	}else {
        		p.sendMessage(MsgType.ERROR + "The ride " + name + " must be closed to remove it! ");
        	}
        }else{
            p.sendMessage(MsgType.ERROR + "No Ride found by the name: " +
                    ChatColor.YELLOW + name);
        }
	}

	public void startInventoryUpdate(Inventory inv, final Player p) {
		p.sendMessage(MsgType.NORMAL + "Starting initializing inventory!");
		Main.inventory.clear();
		Main.toInShowventory(plugin.rideFile, "Rides");
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				p.sendMessage(MsgType.NORMAL + "Inventory Updated!");
			}
		}, 5 * 20L);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("status")) {
			if (p.hasPermission("magic.status")) {
				p.openInventory(status);
			}
		}
		if (cmd.getName().equalsIgnoreCase("ride")) {
			if (args.length == 0) {
				if (p.hasPermission("magic.owner")) {
        			p.sendMessage(ChatColor.GOLD + "You are an " + MsgType.OWNER + ChatColor.GOLD + " Your commands are:");
        			p.sendMessage(ChatColor.GOLD + "/ride: " + ChatColor.GRAY + "Shows this screen");
        			p.sendMessage(ChatColor.GOLD + "/ride <name>: " + ChatColor.GRAY + "Go to an ride");
        			p.sendMessage(ChatColor.GOLD + "/ride add <name> : " + ChatColor.GRAY + "Adds an ride to the RideMenu");
        			p.sendMessage(ChatColor.GOLD + "/ride remove <name>: " + ChatColor.GRAY + "Remove an ride from the RideMenu");
        			p.sendMessage(ChatColor.GOLD + "/ride open <name>: " + ChatColor.GRAY + "Open an ride");
        			p.sendMessage(ChatColor.GOLD + "/ride close <name>: " + ChatColor.GRAY + "Close an ride");
				return true;				
				}else if (p.hasPermission("Mmagic.staff")) {
        			p.sendMessage(ChatColor.GOLD + "You are an " + MsgType.STAFF + ChatColor.GOLD + " Your commands are:");
        			p.sendMessage(ChatColor.GOLD + "/ride: " + ChatColor.GRAY + "Shows this screen");
        			p.sendMessage(ChatColor.GOLD + "/ride <name>: " + ChatColor.GRAY + "Go to an show");
        			p.sendMessage(ChatColor.GOLD + "/ride open <name>: " + ChatColor.GRAY + "Open an show");
        			p.sendMessage(ChatColor.GOLD + "/ride close <name>: " + ChatColor.GRAY + "Close an show");
				}else {
        			p.sendMessage(ChatColor.GOLD + "You are an " + MsgType.VISITOR + ChatColor.GOLD + " Your commands are:");
        			p.sendMessage(ChatColor.GOLD + "/ride: " + ChatColor.GRAY + "Shows this screen");
        			p.sendMessage(ChatColor.GOLD + "/ride <name>: " + ChatColor.GRAY + "Go to an ride");
					return true;
				}
			}
		}				
		if (args.length == 1) {
				teleportToRide(args[0], p);	         			
			}
		
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("add")) {
					if (p.hasPermission("magic.ride.add")) {
					createRideToMenu(args[1], p);
					return true;
					}else {
						p.sendMessage(MsgType.DENIED + "You dont have permissions!");
						return true;
					}
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("remove")) {
					if (p.hasPermission("magic.ride.remove")) {
					removeRideFromMenu(args[1], p);
					return true;
					}else {
						p.sendMessage(MsgType.DENIED + "You dont have permissions!");
						return true;
					}
				}
				
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("close")) {
					if (p.hasPermission("magic.ride.close")) {
					setRideClosed(args[1], sender);
					return true;
					}else {
						p.sendMessage(MsgType.DENIED + "You dont have permissions!");
						return true;
					}
				}
				
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("open")) {
					if (p.hasPermission("magic.ride.open")) {
					setRideOpen(args[1], sender);
					return true;
					}else {
						p.sendMessage(MsgType.DENIED + "You dont have permissions!");
						return true;
					}
				}
				
			}	
		return false;
		
	}
}
