package shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class extra implements Listener {
	
	public static Inventory menu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Shop");
	public static Inventory buy = Bukkit.createInventory(null, 36, ChatColor.AQUA + "" + ChatColor.BOLD + "Buy menu");
	public static Inventory sell = Bukkit.createInventory(null, 27, ChatColor.AQUA + "" + ChatColor.BOLD + "Sell menu");
	
	@EventHandler
	public void onArrowBuyClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(buy.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Go Back!")) {
			    	if (cl.getType() == Material.ARROW) {
					e.setCancelled(true);
					player.closeInventory();	
					
					player.openInventory(menu);
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onCoalBuyClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(buy.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Coal")) {
			    	if (cl.getType() == Material.COAL) {
					e.setCancelled(true);
					player.closeInventory();	
			    	}
			 	}
			}
	   }

	@EventHandler
	public void onIronBuyClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(buy.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Iron")) {
			    	if (cl.getType() == Material.IRON_INGOT) {
					e.setCancelled(true);
					player.closeInventory();	
			    	}
			 	}
			}
	   }

	@EventHandler
	public void onGoldBuyClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(buy.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Gold")) {
			    	if (cl.getType() == Material.GOLD_INGOT) {
					e.setCancelled(true);
					player.closeInventory();	
			    	}
			 	}
			}
	   }

	@EventHandler
	public void onCarrotOnAnStickBuyClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(buy.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Carrot on an stick!")) {
			    	if (cl.getType() == Material.CARROT_STICK) {
					e.setCancelled(true);
					player.closeInventory();	
			    	}
			 	}
			}
	   }

	@EventHandler
	public void onDiaBuyClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(buy.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Diamond")) {
			    	if (cl.getType() == Material.DIAMOND) {
					e.setCancelled(true);
					player.closeInventory();	
			    	}
			 	}
			}
	   }

	@EventHandler
	public void onGoldNugetBuyClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(buy.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Gold Nugget")) {
			    	if (cl.getType() == Material.GOLD_NUGGET) {
					e.setCancelled(true);
					player.closeInventory();	
			    	}
			 	}
			}
	   }
	
	@EventHandler
	public void onBlazeRodBuyClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(buy.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Blaze rod")) {
			    	if (cl.getType() == Material.BLAZE_ROD) {
					e.setCancelled(true);
					player.closeInventory();	
			    	}
			 	}
			}
	   }
}
