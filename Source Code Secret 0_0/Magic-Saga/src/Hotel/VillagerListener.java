package Hotel;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import scoreboard.Main;

public class VillagerListener implements Listener {
	
    static Main plugin;
    
    public VillagerListener(Main m) {
        plugin = m;
    }
	
	public static void addItemToHatShopInventory(Inventory toInv, int slotNumber, String disPlayName, Material mat, String lore, String lore2, String lore3, String lore4, String lore5) {
		ItemStack chest211 = new ItemStack(mat);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(disPlayName);
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "");
		iron211.add(ChatColor.GRAY + lore);
		iron211.add(ChatColor.GRAY + lore2);
		iron211.add(ChatColor.GRAY + lore3);
		iron211.add(ChatColor.GRAY + lore4);
		iron211.add(ChatColor.GRAY + lore5);
		plate211.setLore(iron211);
		chest211.setItemMeta(plate211);
		
		toInv.contains(chest211);
		toInv.setItem(slotNumber, chest211);
	}
	
	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e) {
		Inventory rooms = Bukkit.createInventory(null, 18, ChatColor.AQUA + "" + ChatColor.BOLD + "Magic-Saga Hotel Roomshop");
		if (plugin.getConfig().getBoolean("", true)) {
			//TODO: Add kamer owned door iemand
		if (plugin.getBoolean("") == true) {
			//TODO: Add kamer owned door deze persoon
		}
		}else {
			//TODO: Add kamer niet owned
		}
		
	    Player p = e.getPlayer();
	    Entity entity = e.getRightClicked();
	       if (entity instanceof Villager && ((LivingEntity) entity).getCustomName().equals(ChatColor.GOLD + "Hotel Medewerker")) {
	           e.setCancelled(true);
	           p.openInventory(rooms);
	           
    	}
	}

}
