package scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

public class Checklist implements Listener, CommandExecutor {

	public HashMap<String, Inventory> checkhash = new HashMap<String, Inventory>();
	
		private Main plugin;
		 
		public Checklist(Main instance) {
		    this.plugin = instance;
		}
		
		API h = new API(plugin);
		
		@EventHandler
		public void onInventoryClick(InventoryClickEvent e) {
			FileConfiguration config = plugin.checkList;
			if (config.contains("Achievements"))  {
	            Set<String> rideKeys = config.getConfigurationSection("Achievements").getKeys(false); // Alle ride names (anus, testshow etc. Welke mongool noemt zijn ride nu anus!)
	            for (String ridename: rideKeys) {
	            	Inventory inv = e.getInventory();
	        		ItemStack cl = e.getCurrentItem();
	        			if (inv.getName().equals(Menu.checkMenu.getName())){
	        			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GRAY + "• " + ChatColor.GREEN + ridename + ChatColor.GRAY + " •")) {
	        			    	if (cl.getType() == Material.WOOL) {
	        					e.setCancelled(true);
	        			    	}
	        			 	}
	        			}
	            }
	        }
		}
		
		public void createItemToMenu(String name, Player p) {	        
			ItemStack chest1 = new ItemStack(Material.WOOL, 1, (short) 14);		
			ItemMeta plate1 = chest1.getItemMeta();		
			plate1.setDisplayName(ChatColor.GRAY + "• " + ChatColor.RED + name + ChatColor.GRAY + " •");
			ArrayList<String> iron2 = new ArrayList<String>();
			iron2.add(ChatColor.GRAY + "");
			iron2.add(ChatColor.ITALIC + "Edit the lore in the config!");
			iron2.add(ChatColor.ITALIC + "magic-saga/checklist.yml");
			plate1.setLore(iron2);
			chest1.setItemMeta(plate1);
			
			if (Main.checkmenu.contains(chest1)) {
				p.sendMessage(MsgType.ERROR + name + ", already created! I dont understand the logic, for making 2 items?");
			}else {
			
			Main.checkmenu.contains(chest1);
			Main.checkmenu.addItem(chest1);
			
		//	for (Player all : Bukkit.getServer().getOnlinePlayers()) {
			plugin.saveInventory(Main.checkmenu, plugin.checkList, "Achievements");
			plugin.saveCheckListConfig();
			
			p.sendMessage(MsgType.NORMAL + "Succesfull created CheckItem: " + name);
		//	}
			}
		}
		
		public void removeShowFromMenu(String name, Player p) {
			ItemStack chest1 = new ItemStack(Material.WOOL, 1, (short) 14);		
			ItemMeta plate1 = chest1.getItemMeta();		
			plate1.setDisplayName(ChatColor.GRAY + "• " + ChatColor.GREEN + name + ChatColor.GRAY + " •");
			ArrayList<String> iron2 = new ArrayList<String>();
			iron2.add(ChatColor.GRAY + "");
			iron2.add(ChatColor.ITALIC + "Edit the lore in the config!");
			iron2.add(ChatColor.ITALIC + "magic-saga/checklist.yml");
			plate1.setLore(iron2);
			chest1.setItemMeta(plate1);
			
	        FileConfiguration c = plugin.newConfigz;
	        
	        if (c.contains("Shows." + name)){
	        	if (Main.inventory.contains(chest1)) {
	            c.set("Shows." + name, null);
	            plugin.saveNewConfig();
	            Main.shows.remove(chest1);
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
			
			if (cmd.getName().equalsIgnoreCase("checklist")) {
				if (args.length == 0) {
					if (p.hasPermission("magic.owner")) {
	        			p.sendMessage(ChatColor.GOLD + "You are an " + MsgType.OWNER + ChatColor.GOLD + " Your commands are:");
	        			p.sendMessage(ChatColor.GOLD + "/checklist: " + ChatColor.GRAY + "Shows this screen");
	        			p.sendMessage(ChatColor.GOLD + "/checklist make <name>: " + ChatColor.GRAY + "Makes an item for all of the checklists");
	        			p.sendMessage(ChatColor.GOLD + "/checklist delete <name>: " + ChatColor.GRAY + "Delete an item from the checklist menu");
	        			p.sendMessage(ChatColor.GOLD + "/checklist add <player> <name> : " + ChatColor.GRAY + "Adds an checklistItem to the player");
	        			p.sendMessage(ChatColor.GOLD + "/checklist remove <player> <name>: " + ChatColor.GRAY + "Remove an ride from the ShowMenu");
					return true;					
					}else if (p.hasPermission("Mmagic.staff")) {
	        			p.sendMessage(ChatColor.GOLD + "You are an " + MsgType.STAFF + ChatColor.GOLD + " Your commands are:");
	        			p.sendMessage(ChatColor.GOLD + "/checklist: " + ChatColor.GRAY + "Shows this screen");
	        			p.sendMessage(ChatColor.GOLD + "More commands Coming soon!");
	        			p.sendMessage(ChatColor.RED + "Special thanks to our developers:");
	        			p.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "JusJus112");
					}else {
	        			p.sendMessage(ChatColor.GOLD + "You are an " + MsgType.VISITOR + ChatColor.GOLD + " Your commands are:");
	        			p.sendMessage(ChatColor.GOLD + "/checklist: " + ChatColor.GRAY + "Shows this screen");
	        			p.sendMessage(ChatColor.RED + "Special thanks to our developers:");
	        			p.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "JusJus112");
					}
				}
			}				
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("make")) {
						if (p.hasPermission("magic.checklist.add")) {
						createItemToMenu(args[1], p);
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
			return false;
		}
	}

