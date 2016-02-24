package mainp.thenetwork.inventorys;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
	
	public void createCustomItemWithLores(Material mat, String displayName, String[] lore, Inventory inv, int slotNumber, boolean set) {
		ItemStack item = new ItemStack(mat);
		ItemMeta custom = item.getItemMeta();
		custom.setDisplayName(displayName);
		ArrayList<String> iron41111 = new ArrayList<String>();
		iron41111.addAll(Arrays.asList(lore));
		item.setItemMeta(custom);
		
		if (set == true) {
		inv.contains(item);
		inv.setItem(slotNumber, item);
		}else {
			inv.contains(item);
			inv.addItem(item);
		}
	}

}
