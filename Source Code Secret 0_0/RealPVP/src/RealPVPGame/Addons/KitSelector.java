package RealPVPGame.Addons;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import RealPVPGame.Utils.InventoryMenu;


public class KitSelector {
	
    public static ArrayList<String> kitDefault = new ArrayList<String>();
    public static ArrayList<String> kitPremium = new ArrayList<String>();
    
    public void getKit(Player p) {
		if (KitSelector.kitDefault.contains(p.getName())) {
			InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.IRON_SWORD), ChatColor.AQUA + "Member Sword"
					, 0, p.getInventory(), Enchantment.KNOCKBACK, 1, ChatColor.GRAY + "Kill the enemy with this powerfull sword!");
			
			InventoryMenu.createHelmet(new ItemStack(Material.IRON_HELMET), ChatColor.AQUA + "Member Helmet", Enchantment.PROTECTION_FALL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createChestPlate(new ItemStack(Material.IRON_CHESTPLATE), ChatColor.AQUA + "Member ChestPlate", Enchantment.PROTECTION_FALL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createLeggings(new ItemStack(Material.IRON_LEGGINGS), ChatColor.AQUA + "Member Leggings", Enchantment.PROTECTION_FALL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createBoots(new ItemStack(Material.IRON_BOOTS), ChatColor.AQUA + "Member Boots", Enchantment.PROTECTION_FALL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");

		}else if (KitSelector.kitPremium.contains(p.getName())) {
			InventoryMenu.createHelmet(new ItemStack(Material.IRON_HELMET), ChatColor.AQUA + "Premium Helmet", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createChestPlate(new ItemStack(Material.DIAMOND_CHESTPLATE), ChatColor.AQUA + "Premium ChestPlate", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createLeggings(new ItemStack(Material.IRON_LEGGINGS), ChatColor.AQUA + "Premium Leggings", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			InventoryMenu.createBoots(new ItemStack(Material.IRON_BOOTS), ChatColor.AQUA + "Premium Boots", Enchantment.PROTECTION_ENVIRONMENTAL, 1, p.getInventory(), ChatColor.GOLD + "SoulBound");
			
			InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.IRON_SWORD), ChatColor.AQUA + "Premium Sword"
					, 0, p.getInventory(), Enchantment.DAMAGE_ALL, 1, ChatColor.GRAY + "Kill the enemy with this powerfull sword!");
			
			InventoryMenu.createCustomItem(new ItemStack(Material.GOLDEN_APPLE), ChatColor.AQUA + "Golden apple", 1, p.getInventory(), ChatColor.GOLD + "SouldBound");
		}
	}
}
