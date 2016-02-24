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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import scoreboard.MessageManager.MsgType;

public class Shows implements Listener, CommandExecutor{

	private Main plugin;
	 
	public Shows(Main instance) {
	    this.plugin = instance;
	}
	
	API h = new API(plugin);
	
	@EventHandler
	public void onInventoryGreenClick(InventoryClickEvent e) {
		Player pl = (Player) e.getWhoClicked();
		FileConfiguration config = plugin.newConfigz;
		if (config.contains("Shows"))  {
            Set<String> rideKeys = config.getConfigurationSection("Shows").getKeys(false); // Alle ride names (anus, testshow etc. Welke mongool noemt zijn ride nu anus!)
            for (String ridename: rideKeys) {
            	Inventory inv = e.getInventory();
        		ItemStack cl = e.getCurrentItem();
        			if (inv.getName().equals(Main.shows.getName())) {
        			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GRAY + "• " + ChatColor.GREEN + ridename + ChatColor.GRAY + " •")) {
        			    	if (cl.getType() == Material.STAINED_GLASS) {
        					e.setCancelled(true);
        					pl.closeInventory();
        					teleportToShow(ridename, pl);
        			    	}
        			 	}
        			}
            }
        }
	}
	
	@EventHandler
	public void onRedBlockClick(InventoryClickEvent e) {
		FileConfiguration config = plugin.newConfigz;
		if (config.contains("Shows"))  {
            Set<String> rideKeys = config.getConfigurationSection("Shows").getKeys(false); // Alle ride names (anus, testshow etc. Welke mongool noemt zijn ride nu anus!)
            for (String ridename: rideKeys) {
            	Inventory inv = e.getInventory();
        		ItemStack cl = e.getCurrentItem();
        			if (inv.getName().equals(Main.shows.getName())) {
        			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GRAY + "• " + ChatColor.RED
        			 			+ ridename + ChatColor.GRAY + " •")) {
        			    	if (cl.getType() == Material.STAINED_GLASS) {
        					e.setCancelled(true);
        			    	}
        			 	}
        			}
            }
        }
	}
	
	public boolean isClosed(String name, Player p) {
		ItemStack chest11 = new ItemStack(Material.STAINED_GLASS, (short) 14);
		ItemMeta plate11 = chest11.getItemMeta();
		plate11.setDisplayName(ChatColor.GRAY + "• " + ChatColor.RED + name + ChatColor.GRAY + " •");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.GRAY + "It is closed!");
		iron21.add(ChatColor.GRAY + "Status: " + ChatColor.RED + "Closed");
		plate11.setLore(iron21);
		chest11.setItemMeta(plate11);
		
		if (Main.inventory.contains(chest11)) {
			p.sendMessage(MsgType.DENIED + "This Show is closed! Make sure it is open!");
			return true;
		}
		return false;
	}
	
	public void createShowToMenu(String name, Player p) {
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
		
		if (Main.shows.contains(chest1)) {
			p.sendMessage(MsgType.ERROR + name + ", already created! I dont understand the logic, for making 2 items?");
		}else {
 
        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();

        c.set("Shows." + name + ".x", x);
        c.set("Shows." + name + ".y", y);
        c.set("Shows." + name + ".z", z);
        c.set("Shows." + name + ".yaw", yaw);
        c.set("Shows." + name + ".pitch", pitch);
        c.set("Shows." + name + ".world", world);
        plugin.saveNewConfig();
		
		Main.shows.contains(chest1);
		Main.shows.addItem(chest1);
		
		plugin.saveInventory(Main.shows, plugin.rideFile, "Shows");
		plugin.saveRideConfig();
		
		p.sendMessage(MsgType.NORMAL + "Succesfull created Show: " + name);
		}
	}

	public void setShowClosed(String name, Player p) {
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
        
        if (c.contains("Shows." + name)) {
        	if (Main.shows.contains(chest1)) {
        		Main.shows.remove(chest1);
        		Main.shows.addItem(chest11);
        		plugin.saveInventory(Main.shows, plugin.rideFile, "Shows");
        		plugin.saveRideConfig();
        		Bukkit.getServer().broadcastMessage(MsgType.SHOW + "The Show " + name + " has been closed!");
        	}else {
        		p.sendMessage(MsgType.ERROR + "This Show is already closed!");
        	}
        }else {
        	p.sendMessage(MsgType.ERROR + "No Show found by the name: " + ChatColor.YELLOW + name);
        }
	}
	
	public void setShowOpen(String name, Player p) {
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
        
        if (c.contains("Shows." + name)) {
        	if (Main.shows.contains(chest11)) {
        		Main.shows.remove(chest11);
        		Main.shows.addItem(chest1);
        		plugin.saveInventory(Main.shows, plugin.rideFile, "Shows");
        		plugin.saveRideConfig();
        		Bukkit.getServer().broadcastMessage(MsgType.SHOW + "The Show " + name + " has been Opened!");
        		for (final Player all : Bukkit.getServer().getOnlinePlayers()) {
        			all.getWorld().playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 5, 5);
        		}
        	}else {
        		p.sendMessage(MsgType.ERROR + "This Show is already open!");
        	}
        }else {
        	p.sendMessage(MsgType.ERROR + "No Show found by the name: " + ChatColor.YELLOW + name);
        	p.sendMessage(MsgType.ERROR + "Note the uppercase!");
        }
	}
	
	public void teleportToShow(final String rideName, final Player p) {
		FileConfiguration c = plugin.newConfigz;
        final int x;
		final int y;
		final int z;
		final int yaw;
		final int pitch;
 
		if (!isClosed(rideName, p)) {	
        if (c.contains("Shows." + rideName)){
            final World world = Bukkit.getServer().getWorld(c.getString("Shows." + rideName + ".world"));
            x = c.getInt("Shows." + rideName + ".x");
            y = c.getInt("Shows." + rideName + ".y");
            z = c.getInt("Shows." + rideName + ".z");
            yaw = c.getInt("Shows." + rideName + ".yaw");
            pitch = c.getInt("Shows." + rideName + ".pitch");

            p.teleport(new Location(world, x, y, z, yaw, pitch));
    		p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 2, 2);
    		p.playEffect(p.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
            p.sendMessage(MsgType.NORMAL + "Teleported you to the Show: " +
                            ChatColor.RED + rideName);
        }else {
			p.sendMessage(MsgType.ERROR + "Show " + ChatColor.RED + rideName + ChatColor.DARK_GRAY + " not found!");		 
        }
        }
	}
	
	public void removeShowFromMenu(String name, Player p) {
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
        
        if (c.contains("Shows." + name)){
        	if (Main.inventory.contains(chest11)) {
            c.set("Shows." + name, null);
            plugin.saveNewConfig();
            Main.shows.remove(chest11);
    		plugin.saveInventory(Main.shows, plugin.rideFile, "Shows");
    		plugin.saveRideConfig();
    		Main.toInShowventory(plugin.rideFile, "Shows");
            p.sendMessage(MsgType.NORMAL + "Successfully removed the Show: " + ChatColor.RED + name);
        	}else {
        		p.sendMessage(MsgType.ERROR + "The Show " + name + " must be closed to remove it! ");
        	}
        }else{
            p.sendMessage(MsgType.ERROR + "No Show found by the name: " +
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
		
		if (cmd.getName().equalsIgnoreCase("show")) {
			if (args.length == 0) {
				if (p.hasPermission("magic.owner")) {
        			p.sendMessage(ChatColor.GOLD + "You are an " + MsgType.OWNER + ChatColor.GOLD + " Your commands are:");
        			p.sendMessage(ChatColor.GOLD + "/show: " + ChatColor.GRAY + "Shows this screen");
        			p.sendMessage(ChatColor.GOLD + "/show <name>: " + ChatColor.GRAY + "Go to an show");
        			p.sendMessage(ChatColor.GOLD + "/show add <name> : " + ChatColor.GRAY + "Adds an ride to the ShowMenu");
        			p.sendMessage(ChatColor.GOLD + "/show remove <name>: " + ChatColor.GRAY + "Remove an ride from the ShowMenu");
        			p.sendMessage(ChatColor.GOLD + "/show open <name>: " + ChatColor.GRAY + "Open an show");
        			p.sendMessage(ChatColor.GOLD + "/show close <name>: " + ChatColor.GRAY + "Close an show");
				return true;
				}else if (p.hasPermission("magic.staff")) {
        			p.sendMessage(ChatColor.GOLD + "You are an " + MsgType.STAFF + ChatColor.GOLD + " Your commands are:");
        			p.sendMessage(ChatColor.GOLD + "/show: " + ChatColor.GRAY + "Shows this screen");
        			p.sendMessage(ChatColor.GOLD + "/show <name>: " + ChatColor.GRAY + "Go to an show");
        			p.sendMessage(ChatColor.GOLD + "/show open <name>: " + ChatColor.GRAY + "Open an show");
        			p.sendMessage(ChatColor.GOLD + "/show close <name>: " + ChatColor.GRAY + "Close an show");
        			p.sendMessage(ChatColor.RED + "Special thanks to our developers:");
        			p.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "JusJus112, MasterGamer71246");
        			return true;
				}else {
        			p.sendMessage(ChatColor.GOLD + "You are an " + MsgType.VISITOR + ChatColor.GOLD + " Your commands are:");
        			p.sendMessage(ChatColor.GOLD + "/show: " + ChatColor.GRAY + "Shows this screen");
        			p.sendMessage(ChatColor.GOLD + "/show <name>: " + ChatColor.GRAY + "Go to an show");
        			p.sendMessage(ChatColor.RED + "Special thanks to our developers:");
        			p.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "JusJus112, MasterGamer71246");
					return true;
				}
			}
		}				
		if (args.length == 1) {
				teleportToShow(args[0], p);
				}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("add")) {
					if (p.hasPermission("magic.show.add")) {
					createShowToMenu(args[1], p);
					return true;
					}else {
						p.sendMessage(MsgType.DENIED + "You dont have permissions!");
						return true;
					}
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("remove")) {
					if (p.hasPermission("magic.show.remove")) {
					removeShowFromMenu(args[1], p);
					return true;
					}else {
						p.sendMessage(MsgType.DENIED + "You dont have permissions!");
						return true;
					}
				}
				
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("close")) {
					if (p.hasPermission("magic.show.close")) {
					setShowClosed(args[1], p);
					return true;
					}else {
						p.sendMessage(MsgType.DENIED + "You dont have permissions!");
						return true;
					}
				}
				
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("open")) {
					if (p.hasPermission("magic.show.open")) {
					setShowOpen(args[1], p);
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
