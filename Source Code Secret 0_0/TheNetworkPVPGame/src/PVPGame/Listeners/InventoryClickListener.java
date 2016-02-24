package PVPGame.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import PVPGame.Addons.Kits;
import PVPGame.Utils.TeamManager;

public class InventoryClickListener implements Listener{
	
	@EventHandler
	public void onTeamSelectorClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(KitSelector.teams.getName()) || inv.getName().equals(KitSelector.kits.getName())) {
				e.setCancelled(true);
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.RED + "Join the red team")) {
			    	if (cl.getType() == Material.WOOL) {
			    		TeamManager.addToTeam(p, "red");
			    		p.closeInventory();
			    		}
			    	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.BLUE + "Join the blue team")) {
				    	if (cl.getType() == Material.WOOL) {
							TeamManager.addToTeam(p, "blue");
							p.closeInventory();
			    	}
			////////////////////////////////////////
			////////////////////////////////////////
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Default Kit") 
			 			|| e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Default Kit " + ChatColor.BLUE + "< Selected")) {
			 		if (cl.getType() == Material.STONE_SWORD) {
			 			Kits.setKit(p, Kits.DEFAULT);
			 			p.closeInventory();
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.BOLD + "VIP Kit") 
			 			|| e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.BOLD + "VIP Kit " + ChatColor.BLUE + "< Selected")) {
			 		if (cl.getType() == Material.GOLD_SWORD) {
			 			Kits.setKit(p, Kits.VIP);
			 			p.closeInventory(); 
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Hydra Kit") 
			 			|| e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Hydra Kit " + ChatColor.BLUE + "< Selected")) {
			 		if (cl.getType() == Material.IRON_SWORD) {
			 			Kits.setKit(p, Kits.HYDRA);
			 			p.closeInventory(); 
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Champion Kit") 
			 			|| e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Champion Kit " + ChatColor.BLUE + "< Selected")) {
			 		if (cl.getType() == Material.DIAMOND_SWORD) {
			 			Kits.setKit(p, Kits.CHAMPION);
			 			p.closeInventory(); 
			 		}
			 	}
			}
	   }

}
