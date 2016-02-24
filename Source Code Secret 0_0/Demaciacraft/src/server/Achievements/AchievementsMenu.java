package server.Achievements;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import server.Inventory.createItems;
import server.Systems.InfoBot;

public class AchievementsMenu implements Listener{
	
	
	@SuppressWarnings("deprecation")
	public void createAchievmentMenu(Player p, Inventory inv) {
		createItems.createCustomItem(p, inv, Material.getMaterial(262), (byte) 0, ChatColor.GOLD + "◄◄◄ Back", 18);
		
		if (Achievements.isAchievementNotReached(p, "serverjoiner")) {
			createItems.createCustomItemLores3(p, inv, Material.getMaterial(160), (byte) 14, ChatColor.RED + "Server Joiner", 0, 
					ChatColor.GRAY + "Join this server for the first time!", ChatColor.GRAY + "Status: " + ChatColor.RED + "Not Done yet", ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "10 XP");
		}else if (Achievements.isAchievementisDone(p, "serverjoiner")) {
			createItems.createCustomItemLores3(p, inv, Material.getMaterial(160), (byte) 5, ChatColor.GREEN + "Server Joiner", 0, 
				ChatColor.GRAY + "Join this server for the first time!", ChatColor.GRAY + "Status: " + ChatColor.GREEN + "DONE",
				ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "10 XP");
		}
		if (Achievements.isAchievementNotReached(p, "infobot")) {
			createItems.createCustomItemLores3(p, inv, Material.getMaterial(160), (byte) 14, ChatColor.RED + "Meet " + ChatColor.DARK_AQUA + "InfoBot", 1, 
					ChatColor.GRAY + "Be friends with infobot. He is always with you!", ChatColor.GRAY + "Status: " + ChatColor.RED + "Not Done yet",
					ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "5 Coins");
		}else if (Achievements.isAchievementisDone(p, "infobot")) {
			createItems.createCustomItemLores3(p, inv, Material.getMaterial(160), (byte) 5, ChatColor.GREEN + "Meet " + ChatColor.DARK_AQUA + "InfoBot", 1, 
					ChatColor.GRAY + "Be friends with infobot. He is always with you!", ChatColor.GRAY + "Status: " + ChatColor.GREEN + "DONE",
					ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "5 Coins");
		}
		if (Achievements.isAchievementNotReached(p, "populair5")) {
			createItems.createCustomItemLores3(p, inv, Material.getMaterial(160), (byte) 14, ChatColor.RED + "Your are populair", 2, 
				ChatColor.GRAY + "Get 5 friends ", ChatColor.GRAY + "Status: " + ChatColor.RED + "Not Done yet",
				ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "20 Coins");
		}else if (Achievements.isAchievementisDone(p, "populair5")) {
			createItems.createCustomItemLores3(p, inv, Material.getMaterial(160), (byte) 5, ChatColor.GREEN + "Your are populair", 2, 
					ChatColor.GRAY + "Get 5 friends ", ChatColor.GRAY + "Status: " + ChatColor.GREEN + "DONE",
					ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "20 Coins");
		}else if (Achievements.isAchievementisBusy(p, "populair5")) {
			createItems.createCustomItemLores3(p, inv, Material.getMaterial(160), (byte) 1, ChatColor.GOLD + "Your are populair", 2, 
					ChatColor.GRAY + "Get 5 friends ", ChatColor.GRAY + "Status: " + ChatColor.GOLD + "Busy",
					ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "20 Coins");
		}
		if (Achievements.isAchievementNotReached(p, "achievements10")) {
			createItems.createCustomItemLores3(p, inv, Material.getMaterial(160), (byte) 14, ChatColor.RED + "Complete 10 Achievements", 3, 
				ChatColor.GRAY + "Complete a total of 10 Achievements", ChatColor.GRAY + "Status: " + ChatColor.RED + "Not Done yet",
				ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "50 Coins");
		}else if (Achievements.isAchievementisDone(p, "achievements10")) {
			createItems.createCustomItemLores3(p, inv, Material.getMaterial(160), (byte) 5, ChatColor.GREEN + "Complete 10 Achievements", 3, 
					ChatColor.GRAY + "Complete a total of 10 Achievements", ChatColor.GRAY + "Status: " + ChatColor.GREEN + "DONE",
					ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "50 Coins");
		}else if (Achievements.isAchievementisBusy(p, "achievements10")) {
			createItems.createCustomItemLores3(p, inv, Material.getMaterial(160), (byte) 1, ChatColor.GOLD + "Complete 10 Achievements", 3, 
					ChatColor.GRAY + "Complete a total of 10 Achievements", ChatColor.GRAY + "Status: " + ChatColor.GOLD + "Busy",
					ChatColor.GRAY + "Reward: " + ChatColor.GOLD + "50 Coins");
		}
	}
		
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(inv.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
			    	if (cl.getType() == Material.getMaterial(322)) {
					e.setCancelled(true);
					
					Inventory menu = Bukkit.createInventory(null, 27, ChatColor.DARK_AQUA + "Achievements " 
					+ ChatColor.RED + Integer.toString(Achievements.getAchievementFromUser(p)) + ChatColor.GRAY + "/" + ChatColor.RED + Achievements.totalAchievements);
					
					createAchievmentMenu(p, menu);
					
					p.openInventory(menu);
			    	}else if (cl.getType() == Material.ARROW) {
			    		Inventory menu = Bukkit.createInventory(null, 27, ChatColor.DARK_AQUA + "InfoBot " + ChatColor.GOLD + "Menu");
			    		InfoBot.createInvMenuHoofdMenu(p, menu);
			    		
			    		e.setCancelled(true);
			    		p.openInventory(menu);
			    	}else if (cl.getType() == Material.getMaterial(160)) {
						e.setCancelled(true);
					}
			 	}
			}
	   }

}
