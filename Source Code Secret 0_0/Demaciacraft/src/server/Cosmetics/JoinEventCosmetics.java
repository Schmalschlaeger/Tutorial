package server.Cosmetics;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import server.Inventory.createItems;

public class JoinEventCosmetics implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Inventory inv = p.getInventory();
		
		createItems.createCustomItem(p, inv, Material.CHAINMAIL_CHESTPLATE, (byte) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "Cosmetics Menu", 6);
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		Inventory menu = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Cosmetics Menu");
		
		Player p = e.getPlayer();
		ItemStack cl = e.getItem();
		if (p.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()) {
					if (cl.getType() == Material.SIGN) {
						e.setCancelled(true);
						p.openInventory(menu);
					}
    			}
        	}
		}
	}
	
}
