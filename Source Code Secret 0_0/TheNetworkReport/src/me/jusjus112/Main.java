package me.jusjus112;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	private File dir;
	public Config c;
	
    private static File report;
    public FileConfiguration reportConfig;
    
    public static ArrayList<Player> admins = new ArrayList<Player>();
    public static ArrayList<String> messages = new ArrayList<String>();
    public HashMap<Player, String> reported = new HashMap<Player, String>();
    
	public void onEnable() {
		report = new File(getDataFolder(), "reports.yml");
		reportConfig = YamlConfiguration.loadConfiguration(report);
		reportConfig.options().copyDefaults(true);
    	saveCustomConfig(); 
    	
    	Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);
    	Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void setUpPerPlayerConfig(Player p) {
		dir = new File(this.getDataFolder() + File.separator + "players");
		c = new Config(dir, p.getName(), this);

		setConfigPerPlayer("info.uuid", p.getName());
		setConfigPerPlayer("reportedPlayers.Notch", true);
	}

	public boolean getBoolean(String name) {
		if (c.getConfig().get(name) == null) {
			return false;
		} else {
			return c.getConfig().getBoolean(name);
		}
	}

	public void updateConfigPerPlayer(String name, Object obj) {
		c.getConfig().set(name, obj);
		c.save();
	}

	public void setConfigPerPlayer(String name, Object obj) {
		String str = c.getConfig().getString(name);
		if (str == null) {
			c.set(name, obj);
			c.save();
		}
	}
	
    public void saveCustomConfig(){
   	try{
   		reportConfig.save(report);
   	}catch(Exception e){
      	e.printStackTrace();
    	}
   	}
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
    	setUpPerPlayerConfig(e.getPlayer());
    }
    
    public void getPlayerReports(Player player, String catogorie) {
    	switch(catogorie) {
    		case "flyHacks":
    			reportConfig.get(player + "");
    	}
    }
    
    public void checkIfContactAdmin(Player p) {
    	if (reportConfig.contains("reportsNumbers." + p.getName())) {
    		if (reportConfig.getInt("reportsNumbers." + p.getName()) == 5) {
    			if (!(admins.size() == 0)) {
    				for (Player adminsPl : admins) {
    					adminsPl.sendMessage(ChatColor.GOLD + "[Reports] " + p.getName() + " has reached 5 reports! Watch out for him, or ban him!");
    				}
    			}else if (!(admins.size() > 0)) {
    				messages.add(ChatColor.GOLD + "[Reports] " + p.getName() + " has reached 5 reports! Watch out for him, or ban him!");
    			}
    		}
    	}
    }
    
	public static void createCustomItem(Material mat, String displayName, String lore, int itemPlace, Inventory inv) {
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
	
	@Override
    public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		if (!(s instanceof Player)) {
			s.sendMessage("Sorry, but only players can use this command!");
			return true;
		}
		Player p = (Player) s;   
		if (cmd.getName().equalsIgnoreCase("report")) {
			
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Sorry, you must fill in a name /report <name>");
				return true;
			} 
			
			if (args.length == 1) {
				Player target = getServer().getPlayer(args[0]);
				if (target == null) {
					s.sendMessage(ChatColor.RED + "Player " + args[0] + " is not online so you cant report him! You can report him, if he is online!");
					return true;
				}
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Report player " + target.getName() + " for?");
				
				createCustomItem(Material.DIAMOND_SWORD, ChatColor.AQUA + "PVP Hacks", ChatColor.GRAY + target.getName() + " has pvp hacks, like aimbot!", 3, inv);
				createCustomItem(Material.QUARTZ, ChatColor.AQUA + "Fly Hacks", ChatColor.GRAY + target.getName() + " has fly hacks, like he's flying!", 4, inv);
				createCustomItem(Material.COMPASS, ChatColor.AQUA + "Spam", ChatColor.GRAY + target.getName() + " is spamming!", 5, inv);
				
				p.openInventory(inv);
				reported.put(p, target.getName());
			}
			if (args.length > 1) {
			if (args[0].equalsIgnoreCase("config")) {
				if (args[1].equalsIgnoreCase("set")) {
					if (!p.hasPermission("network.rank.owner")) {
						p.sendMessage(ChatColor.RED + "You dont have permissions!");
						return true;
					}
					Player target2 = getServer().getPlayer(args[2]);
					if (target2 == null) {
						s.sendMessage(ChatColor.RED + "Player " + args[2] + " is not online so you cant change the config! /report config set <name> <number>");
						return true;
					}
					if (args[3].equalsIgnoreCase("flyhacks")) {
						if (reportConfig.contains(target2 + ".flyHacks")) {
					reportConfig.set(target2 + ".flyHacks", Integer.parseInt(args[4]));
		    		reportConfig.options().copyDefaults(true);
		    		saveCustomConfig();
					p.sendMessage(ChatColor.GOLD + "Succesfull set the player " + ChatColor.GRAY + target2.getName() + ChatColor.GOLD + 
							" his report number to " + ChatColor.GRAY + Integer.parseInt(args[4]));
						}else {
							p.sendMessage(ChatColor.RED + "This player was not reported for fly hacks!");
				    		return true;
						}
				    }else if (args[3].equalsIgnoreCase("pvphacks")) {
				    	if (reportConfig.contains(target2 + ".pvpHacks")) {
				    	reportConfig.set(target2 + ".pvpHacks", Integer.parseInt(args[4]));
			    		reportConfig.options().copyDefaults(true);
			    		saveCustomConfig();
						p.sendMessage(ChatColor.GOLD + "Succesfull set the player " + ChatColor.GRAY + target2.getName() + ChatColor.GOLD + 
								" his report number to " + ChatColor.GRAY + Integer.parseInt(args[4]));
				    	}else {
				    		p.sendMessage(ChatColor.RED + "This player was not reported for pvp hacks!");
				    		return true;
				    	}
				    }else if (args[3].equalsIgnoreCase("spam")) {
				    	if (reportConfig.contains(target2 + ".spam")) {
				    	reportConfig.set(target2 + ".spam", Integer.parseInt(args[4]));
			    		reportConfig.options().copyDefaults(true);
			    		saveCustomConfig();
						p.sendMessage(ChatColor.GOLD + "Succesfull set the player " + ChatColor.GRAY + target2.getName() + ChatColor.GOLD + 
								" his report number to " + ChatColor.GRAY + Integer.parseInt(args[4]));
				    	}else {
				    		p.sendMessage(ChatColor.RED + "This player was not reported for spamming!");
				    		return true;
				    	}
				    }
		    		}
				}
			}
		}
		return false;
    }
	
	public void setPlayerReportShizzle(Player p, String catogorie, String target) {
		if (catogorie == "fly hacks") {
			updateConfigPerPlayer("reportedPlayers." + target, true);
			if (!reportConfig.contains(target + ".flyHacks")) {
				reportConfig.addDefault(target + ".flyHacks", 1);
				reportConfig.options().copyDefaults(true);
				saveCustomConfig();
			}else {
			reportConfig.set(target + ".flyHacks", reportConfig.getInt(target + ".flyHacks") +1);
			reportConfig.options().copyDefaults(true);
			saveCustomConfig();
			}
		}else if (catogorie == "pvp hacks") {
			updateConfigPerPlayer("reportedPlayers." + target, true);
			if (!reportConfig.contains(target + ".pvpHacks")) {
				reportConfig.addDefault(target + ".pvpHacks", 1);
				reportConfig.options().copyDefaults(true);
				saveCustomConfig();
			}else {
			reportConfig.set(target + ".pvpHacks", reportConfig.getInt(target + ".pvpHacks") +1);
			reportConfig.options().copyDefaults(true);
			saveCustomConfig();
			}
		}else if (catogorie == "spam") {
			updateConfigPerPlayer("reportedPlayers." + target, true);
			if (!reportConfig.contains(target + ".spam")) {
				reportConfig.addDefault(target + ".spam", 1);
				reportConfig.options().copyDefaults(true);
				saveCustomConfig();
			}else {
			reportConfig.set(target + ".spam", reportConfig.getInt(target + ".spam") +1);
			reportConfig.options().copyDefaults(true);
			saveCustomConfig();
			}
		}else {
			return;
		}
	}
		
	@EventHandler
	public void onVipClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		String target = reported.get(player);
		Inventory inventory = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Report player " + target + " for?");
		if (inventory.getName().equals(inv.getName())) {
			if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "PVP Hacks")) {
				if (cl.getType() == Material.DIAMOND_SWORD) {
					e.setCancelled(true);
					player.closeInventory();
					if (c.getConfig().getBoolean("reportedPlayers." + target) == true) {
						player.sendMessage(ChatColor.RED + "You have already reported this player! You can report him back after 1 month!");
						return;
					}
					if (!(c.getConfig().getString("reportedPlayers").equals(target))) {
					player.sendMessage(ChatColor.GOLD + "Succesfull reported the player: " + ChatColor.GRAY + target 
							+ ChatColor.GOLD + "! Make sure you report him for sure, false reports wil result in a ban!");			
					reported.remove(player);
					setPlayerReportShizzle(player, "pvp hacks", target);
					}
				}
			}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Fly Hacks")) {
				if (cl.getType() == Material.QUARTZ) {
					e.setCancelled(true);
					player.closeInventory();
					if (c.getConfig().getBoolean("reportedPlayers." + target) == true) {
						player.sendMessage(ChatColor.RED + "You have already reported this player! You can report him back after 1 month!");
						return;
					}
					if (!(c.getConfig().getString("reportedPlayers").equals(target))) {
					player.sendMessage(ChatColor.GOLD + "Succesfull reported the player: " + ChatColor.GRAY + target 
							+ ChatColor.GOLD + "! Make sure you report him for sure, false reports wil result in a ban!");		
					reported.remove(player);
					setPlayerReportShizzle(player, "fly hacks", target);
					}
				}
			}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Spam")) {
				if (cl.getType() == Material.COMPASS) {
					e.setCancelled(true);
					player.closeInventory();
					if (c.getConfig().getBoolean("reportedPlayers." + target) == true) {
						player.sendMessage(ChatColor.RED + "You have already reported this player! You can report him back after 1 month!");
						return;
					}
					if (!(c.getConfig().getString("reportedPlayers").equals(target))) {
					player.sendMessage(ChatColor.GOLD + "Succesfull reported the player: " + ChatColor.GRAY + target 
							+ ChatColor.GOLD + "! Make sure you report him for sure, false reports wil result in a ban!");	
					reported.remove(player);
					setPlayerReportShizzle(player, "spam", target);
					}
				}
			}
		}
	}	

}
