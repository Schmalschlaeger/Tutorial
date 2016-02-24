package PVPGame.Listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import PVPGame.Main;
import PVPGame.Utils.InventoryMenu;


public class KitSelector {
	
    public static ArrayList<String> kitDefault = new ArrayList<String>();
    public static ArrayList<String> kitHydra = new ArrayList<String>();
    public static ArrayList<String> kitChampion = new ArrayList<String>();
    public static ArrayList<String> kitVIP = new ArrayList<String>();
    
	public static Inventory teams;
	public static Inventory kits;
	
	public static void createTeamsMenu() {
		teams = Bukkit.createInventory(null, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "Select your team!");
		
		InventoryMenu.createCustomItem(new ItemStack(Material.WOOL, 1, (byte) 14), ChatColor.RED + "Join the red team", 3, teams, ChatColor.GRAY + "Team Size: " 
		+ Main.getTeamRedSize());
		
		InventoryMenu.createCustomItem(new ItemStack(Material.WOOL, 1, (byte) 11), ChatColor.BLUE + "Join the blue team", 5, teams, ChatColor.GRAY + "Team Size: " 
				+ Main.getTeamBlueSize());
	}
    
	public static void createKitMenu() {
		for (Player all : Bukkit.getOnlinePlayers()) {
		kits = Bukkit.createInventory(null, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "Select your kit!");
		
		if (!KitSelector.kitDefault.contains(all.getName())) {//TODO: Default
		InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.STONE_SWORD, 1), ChatColor.GOLD + "" + ChatColor.BOLD + "Default Kit", 1, kits,
				Enchantment.KNOCKBACK, 1, " ", ChatColor.GREEN
				+ "You have unlocked this kit!", ChatColor.GRAY + "Click to select this kit"," ", " ",
				ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
				ChatColor.GRAY + "Stone Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Knockback I",
				ChatColor.GRAY + "Leather Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
				ChatColor.GRAY + "Leather ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
				ChatColor.GRAY + "Leather Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
				ChatColor.GRAY + "Leather Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
				ChatColor.GRAY + "Healing Potion I");
		}else {
			InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.STONE_SWORD, 1), ChatColor.GOLD + "" + ChatColor.BOLD + "Default Kit " + ChatColor.BLUE + "< Selected"
					, 1, kits, Enchantment.KNOCKBACK, 1, " ", ChatColor.GREEN
					+ "You have unlocked this kit!", ChatColor.GRAY + "Click to select this kit"," ", " ",
					ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
					ChatColor.GRAY + "Stone Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Knockback I",
					ChatColor.GRAY + "Leather Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
					ChatColor.GRAY + "Leather ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
					ChatColor.GRAY + "Leather Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
					ChatColor.GRAY + "Leather Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
					ChatColor.GRAY + "Healing Potion I");
		}
		
			if (all.hasPermission("network.rank.vip")) {//TODO: VIP
				if (!KitSelector.kitVIP.contains(all.getName())) {
				InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.GOLD_SWORD, 1), ChatColor.GOLD + "" + ChatColor.BOLD + "VIP Kit", 2, kits,
						Enchantment.DAMAGE_ALL, 1," ", ChatColor.GREEN
						+ "You have unlocked this kit!", ChatColor.GRAY + "Click to select this kit"," ", " ",
						ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
						ChatColor.GRAY + "Gold Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Sharpness I",
						ChatColor.GRAY + "Gold Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Gold ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Gold Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Gold Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Healing Potion II", 
						ChatColor.GRAY + "Golden Apple");
				}else {
					InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.GOLD_SWORD, 1), ChatColor.GOLD + "" + ChatColor.BOLD + "VIP Kit " + ChatColor.BLUE + "< Selected", 2, kits,
							Enchantment.DAMAGE_ALL, 1," ", ChatColor.GREEN
							+ "You have unlocked this kit!", ChatColor.GRAY + "Click to select this kit"," ", " ",
							ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
							ChatColor.GRAY + "Gold Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Sharpness I",
							ChatColor.GRAY + "Gold Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Gold ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Gold Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Gold Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Healing Potion II", 
							ChatColor.GRAY + "Golden Apple");
				}
			}else {
				InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.GOLD_SWORD, 1), ChatColor.RED + "" + ChatColor.BOLD + "VIP Kit", 2, kits,
						Enchantment.DAMAGE_ALL, 1," ", ChatColor.RED
						+ "You dont have unlocked this kit!", ChatColor.RED + "Buy vip at the store", ChatColor.GRAY + "www.shop.thenetwork-mc.net"," ", " ",
						ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
						ChatColor.GRAY + "Gold Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Sharpness I",
						ChatColor.GRAY + "Gold Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Gold ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Gold Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Gold Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Healing Potion II", 
						ChatColor.GRAY + "Golden Apple");
			}
			
			if (all.hasPermission("network.rank.hydra")) {//TODO: Hydra
				if (!KitSelector.kitHydra.contains(all.getName())) {
				InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.IRON_SWORD, 1), ChatColor.GOLD + "" + ChatColor.BOLD + "Hydra Kit", 3, kits,
						Enchantment.FIRE_ASPECT, 1," ", ChatColor.GREEN
						+ "You have unlocked this kit!", ChatColor.GRAY + "Click to select this kit"," ", " ",
						ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
						ChatColor.GRAY + "Iron Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Fire Aspect I",
						ChatColor.GRAY + "Iron Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Iron ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Iron Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Iron Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Healing Potion II x 2", 
						ChatColor.GRAY + "Golden Apple x 2");
				}else {
					InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.IRON_SWORD, 1), ChatColor.GOLD + "" + ChatColor.BOLD + "Hydra Kit " + ChatColor.BLUE + "< Selected", 3, kits,
							Enchantment.FIRE_ASPECT, 1," ", ChatColor.GREEN
							+ "You have unlocked this kit!", ChatColor.GRAY + "Click to select this kit"," ", " ",
							ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
							ChatColor.GRAY + "Iron Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Fire Aspect I",
							ChatColor.GRAY + "Iron Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Iron ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Iron Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Iron Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Healing Potion II x 2", 
							ChatColor.GRAY + "Golden Apple x 2");
				}
			}else {
				InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.IRON_SWORD, 1), ChatColor.RED + "" + ChatColor.BOLD + "Hydra Kit", 3, kits, 
						Enchantment.FIRE_ASPECT, 1," ", ChatColor.RED
						+ "You dont have unlocked this kit!", ChatColor.RED + "Buy hydra at the store", ChatColor.GRAY + "www.shop.thenetwork-mc.net"," ", " ",
						ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
						ChatColor.GRAY + "Iron Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Fire Aspect I",
						ChatColor.GRAY + "Iron Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Iron ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Iron Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Iron Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Healing Potion II x 2", 
						ChatColor.GRAY + "Golden Apple x 2");
			}
			if (all.hasPermission("network.rank.champion")) {//TODO: Champion
				if (!KitSelector.kitChampion.contains(all.getName())) {
				InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.DIAMOND_SWORD, 1), ChatColor.GOLD + "" + ChatColor.BOLD + "Champion Kit", 4, kits,
						Enchantment.DAMAGE_UNDEAD, 1," ", ChatColor.GREEN
						+ "You have unlocked this kit!", ChatColor.GRAY + "Click to select this kit"," ", " ",
						ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
						ChatColor.GRAY + "Diamond Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Smite I",
						ChatColor.GRAY + "Diamond Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Diamond ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Diamond Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Diamond Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Regeneration Potion I x 2", 
						ChatColor.GRAY + "Enchanted Golden Apple x 1");
				}else {
					InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.DIAMOND_SWORD, 1), ChatColor.GOLD + "" + ChatColor.BOLD + "Champion Kit " + ChatColor.BLUE + "< Selected", 4, kits,
							Enchantment.DAMAGE_UNDEAD, 1," ", ChatColor.GREEN
							+ "You have unlocked this kit!", ChatColor.GRAY + "Click to select this kit"," ", " ",
							ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
							ChatColor.GRAY + "Diamond Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Smite I",
							ChatColor.GRAY + "Diamond Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Diamond ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Diamond Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Diamond Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
							ChatColor.GRAY + "Regeneration Potion I x 2", 
							ChatColor.GRAY + "Enchanted Golden Apple x 1");
				}
			}else {
				InventoryMenu.createCustomItemWithEnchantment(new ItemStack(Material.DIAMOND_SWORD, 1), ChatColor.RED + "" + ChatColor.BOLD + "Champion Kit", 4, kits, 
						Enchantment.DAMAGE_UNDEAD, 1," ", ChatColor.RED
						+ "You dont have unlocked this kit!", ChatColor.RED + "Buy champion at the store", ChatColor.GRAY + "www.shop.thenetwork-mc.net"," ", " ",
						ChatColor.GRAY + "" + ChatColor.UNDERLINE + "This kit includes: ",
						ChatColor.GRAY + "Diamond Sword " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Smite I",
						ChatColor.GRAY + "Diamond Helmet " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Diamond ChestPlate " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Diamond Leggings " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Diamond Boots " + ChatColor.GOLD + "|" + ChatColor.GRAY + " Protection I",
						ChatColor.GRAY + "Regeneration Potion I x 2", 
						ChatColor.GRAY + "Enchanted Golden Apple x 1");
			}
		}
	}

}
