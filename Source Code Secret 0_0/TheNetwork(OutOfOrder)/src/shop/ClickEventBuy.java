package shop;

import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickEventBuy implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBuyClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(Shop.buy.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Go Back!")) {
			    	if (cl.getType() == Material.ARROW) {
					e.setCancelled(true);
					player.closeInventory();	
					player.openInventory(Shop.menu);
			    	}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Diamond")) {
			    	if (cl.getType() == Material.DIAMOND) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.diamondPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.DIAMOND));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.coalPrice 
									+ ChatColor.GREEN + " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN + "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Iron")) {
			    	if (cl.getType() == Material.IRON_INGOT) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.ironPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.IRON_INGOT));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.ironPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Coal")) {
			    	if (cl.getType() == Material.COAL) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.coalPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.COAL));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.coalPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Gold")) {
			    	if (cl.getType() == Material.GOLD_INGOT) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.goldPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.goldPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Carrot on an stick!")) {
			    	if (cl.getType() == Material.CARROT_STICK) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.carrotOnAnStickPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.CARROT_STICK));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.carrotOnAnStickPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Gold Nugget")) {
			    	if (cl.getType() == Material.GOLD_NUGGET) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.goldNuggetPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.goldNuggetPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Blaze rod")) {
			    	if (cl.getType() == Material.BLAZE_ROD) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.blazeRodePrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.blazeRodePrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Quartz")) {
			    	if (cl.getType() == Material.QUARTZ) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.quartzPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.QUARTZ));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.quartzPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Quartz Ore")) {
			    	if (cl.getType() == Material.QUARTZ_ORE) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.quartzBlockPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.QUARTZ_ORE));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.quartzBlockPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Chest")) {
			    	if (cl.getType() == Material.CHEST) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.chestPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.CHEST));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.chestPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Enchantment Table")) {
			    	if (cl.getType() == Material.ENCHANTMENT_TABLE) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.enchantmentTablePrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.enchantmentTablePrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Sun Flower")) {
			    	if (cl.getType() == Material.getMaterial(175)) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.sunFlowerPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.getMaterial(175)));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.sunFlowerPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Redstone Block")) {
			    	if (cl.getType() == Material.REDSTONE_BLOCK) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.redstoneBlockPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.REDSTONE_BLOCK));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.redstoneBlockPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Book")) {
			    	if (cl.getType() == Material.BOOK) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.bookPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.BOOK));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.bookPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Painting")) {
			    	if (cl.getType() == Material.PAINTING) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.paintingPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.PAINTING));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.paintingPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "CobbleStone Wall")) {
			    	if (cl.getType() == Material.COBBLE_WALL) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.coblleWallPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.COBBLE_WALL));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.coblleWallPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Flint and Steel")) {
			    	if (cl.getType() == Material.FLINT_AND_STEEL) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.flintAndSteelPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.flintAndSteelPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Egg")) {
			    	if (cl.getType() == Material.EGG) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.eggPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.EGG));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.eggPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "EnderPearl")) {
			    	if (cl.getType() == Material.ENDER_PEARL) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.enderPearlPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.enderPearlPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Enchantment Bottle")) {
			    	if (cl.getType() == Material.getMaterial(384)) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.enchantmentBottlePrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.getMaterial(384)));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.enchantmentBottlePrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Cake")) {
			    	if (cl.getType() == Material.CAKE) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.cakePrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.CAKE));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.cakePrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Boat")) {
			    	if (cl.getType() == Material.BOAT) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.boatPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.BOAT));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.boatPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Carrot")) {
			    	if (cl.getType() == Material.CARROT_ITEM) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.carrotPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.CARROT_ITEM));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.carrotPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Potato")) {
			    	if (cl.getType() == Material.POTATO_ITEM) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.patatoPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.POTATO_ITEM));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.patatoPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Pumpkin Pie")) {
			    	if (cl.getType() == Material.PUMPKIN_PIE) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.pumpkinPiePrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.BOOK));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.bookPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Fishing Rod")) {
			    	if (cl.getType() == Material.FISHING_ROD) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.fishingRodPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.fishingRodPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Raw Beef")) {
			    	if (cl.getType() == Material.RAW_BEEF) {//
			    		e.setCancelled(true);	
						EconomyResponse r = Shop.econ.withdrawPlayer(player.getName(), Shop.rawBeefPrice);
						if (r.transactionSuccess()) {
							player.getInventory().addItem(new ItemStack(Material.RAW_BEEF));
							player.sendMessage(ChatColor.GREEN + "You have buyt an item! " + ChatColor.GOLD + Shop.rawBeefPrice + ChatColor.GREEN 
									+ " dollar has been taken!");
				    	} else {
	     					player.sendMessage(ChatColor.GREEN+ "You dont have enough money to buy this item!");
	     					player.closeInventory();
	     					return;
	     				}
			    	}//
			 	}
			}
	   }

}
