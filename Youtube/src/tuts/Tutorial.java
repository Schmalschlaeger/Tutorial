package tuts;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Tutorial extends JavaPlugin implements Listener{
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		createCustomItem(new ItemStack(Material.COMPASS), ChatColor.GOLD + "Server Selector", 0, e.getPlayer().getInventory(), "Click to open this item!");
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getPlayer().getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
				if (e.getPlayer().getItemInHand().hasItemMeta() && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Server Selector")) {
					if (e.getItem().getType() == Material.COMPASS) {
						e.setCancelled(true);
						//e.getPlayer().openInventory(arg0);
					}
				}
			}
		}
	}
	
	public static void createCustomItem(ItemStack mat, String displayName, int itemPlace, Inventory inv, String... lore) {
		ItemStack menu1 = new ItemStack(mat);
		ItemMeta shop1 = menu1.getItemMeta();
		shop1.setDisplayName(displayName);
		shop1.setLore(Arrays.asList(lore));
		menu1.setItemMeta(shop1);

		if (!inv.contains(menu1)) {
		inv.setItem(itemPlace, menu1);
		}
	}
	
}
