package PVPGame.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractEvent implements Listener{
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		KitSelector.createTeamsMenu();
		KitSelector.createKitMenu();
		
		Player p = e.getPlayer();
		ItemStack cl = e.getItem();
		if (p.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()) {
					if (cl.getType() == Material.COMPASS) {
						e.setCancelled(true);
						p.openInventory(KitSelector.teams);
					}else if (cl.getType() == Material.DIAMOND) {
    					p.openInventory(KitSelector.kits);
    					e.setCancelled(true);
    				}
    			}
        	}
		}
	}

}
