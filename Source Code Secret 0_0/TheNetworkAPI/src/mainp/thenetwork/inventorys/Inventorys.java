package mainp.thenetwork.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;


public class Inventorys {
	
	private Inventory inventory;
	
	public void createInventory(int slots, String displayname) {
		Inventory inv = Bukkit.createInventory(null, slots, ChatColor.RED + "" + ChatColor.BOLD + "Easter Egg's");
		this.inventory = inv;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

}
