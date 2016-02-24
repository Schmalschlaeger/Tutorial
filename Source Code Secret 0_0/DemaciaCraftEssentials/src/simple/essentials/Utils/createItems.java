package simple.essentials.Utils;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class createItems {
	
	public static void createSlot9Item(Player player) {
		Inventory inv = player.getInventory();
		
		ItemStack chest2 = new ItemStack(Material.SIGN);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.DARK_AQUA +  "InfoBot " + ChatColor.GOLD + "Help Item");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "Don't know what this is?");
		iron21.add(ChatColor.GRAY + "Use " + ChatColor.RED + "/infobot");
		plate2.setLore(iron21);
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(8, chest2);
	}
	
	public static void createCustomItemLores(Player p, Inventory inv, Material material, byte byt, String displayName, int slot, String lore, String lore2) {		
		ItemStack chest2 = new ItemStack(material, 1, (byte) byt);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(displayName);
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(lore);
		iron21.add(lore2);
		plate2.setLore(iron21);
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(slot, chest2);
	}
	
	public static void createCustomItemLores3(Player p, Inventory inv, Material material, byte byt, String displayName, int slot, String lore, String lore2, String lore3) {		
		ItemStack chest2 = new ItemStack(material, 1, (byte) byt);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(displayName);
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(lore);
		iron21.add(lore2);
		iron21.add(lore3);
		plate2.setLore(iron21);
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(slot, chest2);
	}
	
	public static void createCustomItem(Player p, Inventory inv, Material material, byte byt, String displayName, int slot) {		
		ItemStack chest2 = new ItemStack(material, 1, (byte) byt);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(displayName);
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(slot, chest2);
	}

}
