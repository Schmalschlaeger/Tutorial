package TWS.VipSystem;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu implements Listener {

	public static Inventory vip = Bukkit.createInventory(null, 36, ChatColor.RED + "" + ChatColor.BOLD + "Vip menu");
	public static Inventory trailMenu = Bukkit.createInventory(null, 36, ChatColor.RED + "" + ChatColor.BOLD + "Trail menu");
	
	public static ArrayList<Player> trailFire = new ArrayList<Player>();
	public static ArrayList<Player> trailCloud = new ArrayList<Player>();

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
	
	static {// Vip menu		
		createCustomItem(Material.BLAZE_ROD, ChatColor.AQUA + "Trail Control", ChatColor.GOLD + "Here you can choose wich trail you like!", 12, vip);
		createCustomItem(Material.BEDROCK, ChatColor.RED + "Player Popper", ChatColor.GRAY + "Coming Soon", 13, vip);
		createCustomItem(Material.LEATHER_CHESTPLATE, ChatColor.RED + "Dressing Room", ChatColor.GRAY + "Coming Soon", 14, vip);
	}
	
	static {// Trail menu
		createCustomItem(Material.FIRE, ChatColor.AQUA + "Fire Particles", ChatColor.GRAY + "Creates a fire particle around you!", 11, trailMenu);
		createCustomItem(Material.QUARTZ, ChatColor.AQUA + "Cloud Particles", ChatColor.GRAY + "Coming Soon", 12, trailMenu);
		createCustomItem(Material.RED_ROSE, ChatColor.AQUA + "Happy Villager Particles", ChatColor.GRAY + "Coming Soon", 13, trailMenu);
		createCustomItem(Material.ENCHANTMENT_TABLE, ChatColor.AQUA + "Enchantment Table Particles", ChatColor.GRAY + "Coming Soon", 14, trailMenu);
		createCustomItem(Material.WATER, ChatColor.AQUA + "Bubble Particles", ChatColor.GRAY + "Coming Soon", 15, trailMenu);
	}

	@EventHandler
	public void onVipClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		if (inv.getName().equals(vip.getName())) {
			if (e.getCurrentItem().hasItemMeta()
					&& e.getCurrentItem().getItemMeta().getDisplayName()
							.contains(ChatColor.AQUA + "Trail Control")) {
				if (cl.getType() == Material.BLAZE_ROD) {
					e.setCancelled(true);
					player.closeInventory();
					player.openInventory(trailMenu);
				}
			} else if (e.getCurrentItem().hasItemMeta()
					&& e.getCurrentItem().getItemMeta().getDisplayName()
							.contains(ChatColor.RED + "Player Popper")) {
				if (cl.getType() == Material.BEDROCK) {
					e.setCancelled(true);
					player.closeInventory();
					player.sendMessage(ChatColor.RED
							+ "This item is coming soon! Please try again later!");
				}
			} else if (e.getCurrentItem().hasItemMeta()
					&& e.getCurrentItem().getItemMeta().getDisplayName()
							.contains(ChatColor.RED + "Dressing Room")) {
				if (cl.getType() == Material.BEDROCK) {
					e.setCancelled(true);
					player.closeInventory();
					player.sendMessage(ChatColor.RED
							+ "This item is coming soon! Please try again later!");
							
				}
			}
		}
	}

	@EventHandler
	public void onTrailClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		
		if (inv.getName().equals(trailMenu.getName())) {
			if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Fire Particles")) {
				if (cl.getType() == Material.FIRE) {
					e.setCancelled(true);
					player.closeInventory();
					if (!trailFire.contains(player)) {
						player.sendMessage(ChatColor.GOLD + "Your trail has been Enabled");
						trailFire.add(player);
					}else {
						player.sendMessage(ChatColor.GOLD + "Your trail has been disabled");
						trailFire.remove(player);
					}
				}
			}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Cloud Particles")) {
				e.setCancelled(true);
				player.closeInventory();
				if (!trailCloud.contains(player)) {
					player.sendMessage(ChatColor.GOLD + "Your trail has been Enabled");
					trailCloud.add(player);
				}else {
					player.sendMessage(ChatColor.GOLD + "Your trail has been disabled");
					trailCloud.remove(player);
				}
			}
		}
	}
}
