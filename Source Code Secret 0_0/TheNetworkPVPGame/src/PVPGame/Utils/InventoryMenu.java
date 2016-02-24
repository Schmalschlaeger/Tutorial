package PVPGame.Utils;

import java.util.Arrays;

import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class InventoryMenu {
	
		public static void createCustomItem(ItemStack mat, String displayName, int itemPlace, Inventory inv, String... lore) {
			ItemStack menu1 = new ItemStack(mat);
			ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			shop1.setLore(Arrays.asList(lore));
			menu1.setItemMeta(shop1);

			inv.contains(menu1);
			inv.setItem(itemPlace, menu1);
		}
		public static void createCustomItemWithEnchantment(ItemStack mat, String displayName, int itemPlace, Inventory inv, Enchantment enchant, int level, String... lore) {
			ItemStack menu1 = new ItemStack(mat);
			ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			shop1.setLore(Arrays.asList(lore));
			menu1.setItemMeta(shop1);
			menu1.addEnchantment(enchant, level);

			inv.contains(menu1);
			inv.setItem(itemPlace, menu1);
		}
		
		public static void createHelmet(ItemStack mat, String displayName, Enchantment enchant, int level, PlayerInventory inv, String... lore) {
			ItemStack menu1 = new ItemStack(mat);
			ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			shop1.setLore(Arrays.asList(lore));
			menu1.setItemMeta(shop1);
			menu1.addEnchantment(enchant, level);

			inv.setHelmet(menu1);
		}
		
		public static void createLeggings(ItemStack mat, String displayName, Enchantment enchant, int level, PlayerInventory inv, String... lore) {
			ItemStack menu1 = new ItemStack(mat);
			ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			shop1.setLore(Arrays.asList(lore));
			menu1.setItemMeta(shop1);
			menu1.addEnchantment(enchant, level);

			inv.setLeggings(menu1);
		}
		
		public static void createChestPlate(ItemStack mat, String displayName, Enchantment enchant, int level, PlayerInventory inv, String... lore) {
			ItemStack menu1 = new ItemStack(mat);
			ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			shop1.setLore(Arrays.asList(lore));
			menu1.setItemMeta(shop1);
			menu1.addEnchantment(enchant, level);

			inv.setChestplate(menu1);
		}
		
		public static void createBoots(ItemStack mat, String displayName, Enchantment enchant, int level, PlayerInventory inv, String... lore) {
			ItemStack menu1 = new ItemStack(mat);
			ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			shop1.setLore(Arrays.asList(lore));
			menu1.setItemMeta(shop1);
			menu1.addEnchantment(enchant, level);

			inv.setBoots(menu1);
		}
		
		public static void createLeatherBoots(ItemStack mat, Color color, String displayName, Enchantment enchant, int level, PlayerInventory inv, String... lore) {
			ItemStack menu1 = new ItemStack(mat);
			LeatherArmorMeta  shop1 = (LeatherArmorMeta) menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			shop1.setLore(Arrays.asList(lore));
			shop1.setColor(color);
			menu1.setItemMeta(shop1);
			menu1.addEnchantment(enchant, level);

			inv.setBoots(menu1);
		}
		
		public static void createLeatherChestPlate(ItemStack mat, Color color, String displayName, Enchantment enchant, int level, PlayerInventory inv, String... lore) {
			ItemStack menu1 = new ItemStack(mat);
			LeatherArmorMeta  shop1 = (LeatherArmorMeta) menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			shop1.setLore(Arrays.asList(lore));
			shop1.setColor(color);
			menu1.setItemMeta(shop1);
			menu1.addEnchantment(enchant, level);

			inv.setChestplate(menu1);
		}
		
		public static void createLeatherLeggings(ItemStack mat, Color color, String displayName, Enchantment enchant, int level, PlayerInventory inv, String... lore) {
			ItemStack menu1 = new ItemStack(mat);
			LeatherArmorMeta  shop1 = (LeatherArmorMeta) menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			shop1.setLore(Arrays.asList(lore));
			shop1.setColor(color);
			menu1.setItemMeta(shop1);
			menu1.addEnchantment(enchant, level);

			inv.setLeggings(menu1);
		}
		
		public static void createLeatherHelmet(ItemStack mat, Color color, String displayName, Enchantment enchant, int level, PlayerInventory inv, String... lore) {
			ItemStack menu1 = new ItemStack(mat);
			LeatherArmorMeta  shop1 = (LeatherArmorMeta) menu1.getItemMeta();
			shop1.setDisplayName(displayName);
			shop1.setLore(Arrays.asList(lore));
			shop1.setColor(color);
			menu1.setItemMeta(shop1);
			menu1.addEnchantment(enchant, level);

			inv.setHelmet(menu1);
		}
}
