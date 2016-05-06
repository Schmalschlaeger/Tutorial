package cock.topia.tvg.Kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import cock.topia.tvg.Main;

public class kits {
	
	Main plugin;
	public kits(Main plugin) {
		this.plugin = plugin;
	}
	
	  public static void Speler(Player p)
	  {
	    p.getInventory().clear();
	    p.getInventory().setArmorContents(null);
	    p.setMaxHealth(30);
	    p.setHealth(p.getMaxHealth());
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY + "SoulBound");
	    ItemStack sword = new ItemStack(Material.STONE_SWORD);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.GRAY + "§lSpeler Kit");
	    swordMeta.setLore(lore);
	    sword.setItemMeta(swordMeta);
	    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    p.getInventory().setItem(0, sword);

	    ItemStack sword2 = new ItemStack(Material.BOW);
	    ItemMeta swordMeta2 = sword2.getItemMeta();
	    swordMeta2.setDisplayName(ChatColor.GRAY + "§lSpeler Kit");
	    swordMeta2.setLore(lore);
	    sword2.setItemMeta(swordMeta2);
	    sword2.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword2.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
	    p.getInventory().setItem(1, sword2);

	    ItemStack arrow = new ItemStack(Material.ARROW, 64);
	    ItemMeta arrowmeta = arrow.getItemMeta();
	    arrowmeta.setDisplayName(ChatColor.GRAY + "§lSpeler Kit");
	    arrowmeta.setLore(lore);
	    arrow.setItemMeta(arrowmeta);
	    p.getInventory().setItem(4, arrow);

	    ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta = (LeatherArmorMeta)Helmet.getItemMeta();
	    meta.setDisplayName(ChatColor.GRAY + "§lSpeler Kit");
	    meta.setLore(lore);
	    meta.setColor(Color.TEAL);
	    Helmet.setItemMeta(meta);
	    Helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    p.getInventory().setHelmet(Helmet);

	    ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta Cmeta = (LeatherArmorMeta)Chest.getItemMeta();
	    Cmeta.setDisplayName(ChatColor.GRAY + "§lSpeler Kit");
	    Cmeta.setLore(lore);
	    Cmeta.setColor(Color.TEAL);
	    Chest.setItemMeta(Cmeta);
	    Chest.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    p.getInventory().setChestplate(Chest);

	    ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta Lmeta = (LeatherArmorMeta)Pants.getItemMeta();
	    Lmeta.setDisplayName(ChatColor.GRAY + "§lSpeler Kit");
	    Lmeta.setLore(lore);
	    Lmeta.setColor(Color.TEAL);
	    Pants.setItemMeta(Lmeta);
	    Pants.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    p.getInventory().setLeggings(Pants);

	    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta Bmeta = (LeatherArmorMeta)Boots.getItemMeta();
	    Bmeta.setDisplayName(ChatColor.GRAY + "§lSpeler Kit");
	    Bmeta.setLore(lore);
	    Bmeta.setColor(Color.TEAL);
	    Boots.setItemMeta(Bmeta);
	    Boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    p.getInventory().setBoots(Boots);
	    ItemStack apple = new ItemStack(Material.GOLDEN_APPLE);
	    ItemMeta Gapple = apple.getItemMeta();
	    Gapple.setDisplayName(ChatColor.GOLD + "Golden apple");
	    Gapple.setLore(lore);
	    apple.setItemMeta(Gapple);
	    p.getInventory().setItem(2, apple);
	    p.updateInventory();
	  }

	  public static void Wood(Player p) {
	    p.getInventory().clear();
	    p.getInventory().setArmorContents(null);
	    p.setMaxHealth(30);
	    p.setHealth(p.getMaxHealth());
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY + "SoulBound");
	    ItemStack sword = new ItemStack(Material.WOOD_SWORD);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.GOLD + "§lWood Kit");
	    swordMeta.setLore(lore);
	    sword.setItemMeta(swordMeta);
	    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	    p.getInventory().setItem(0, sword);

	    ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta = (LeatherArmorMeta)Helmet.getItemMeta();
	    meta.setDisplayName(ChatColor.GOLD + "§lWood Kit");
	    meta.setLore(lore);

	    Helmet.setItemMeta(meta);
	    Helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    p.getInventory().setHelmet(Helmet);

	    ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta Cmeta = (LeatherArmorMeta)Chest.getItemMeta();
	    Cmeta.setDisplayName(ChatColor.GOLD + "§lWood Kit");
	    Cmeta.setLore(lore);

	    Chest.setItemMeta(Cmeta);
	    Chest.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
	    p.getInventory().setChestplate(Chest);

	    ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta Lmeta = (LeatherArmorMeta)Pants.getItemMeta();
	    Lmeta.setDisplayName(ChatColor.GOLD + "§lWood Kit");
	    Lmeta.setLore(lore);

	    Pants.setItemMeta(Lmeta);
	    Pants.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
	    p.getInventory().setLeggings(Pants);

	    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta Bmeta = (LeatherArmorMeta)Boots.getItemMeta();
	    Bmeta.setDisplayName(ChatColor.GOLD + "§lWood Kit");
	    Bmeta.setLore(lore);

	    Boots.setItemMeta(Bmeta);
	    Boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    p.getInventory().setBoots(Boots);
	    p.getInventory().setItem(2, new ItemStack(Material.GOLDEN_APPLE));

	    p.updateInventory();
	  }

	  public static void Coal(Player p) {
	    p.getInventory().clear();
	    p.getInventory().setArmorContents(null);
	    p.setMaxHealth(30);
	    p.setHealth(p.getMaxHealth());
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY + "SoulBound");
	    ItemStack sword = new ItemStack(Material.GOLD_SWORD);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.DARK_GRAY + "§lCoal Kit");
	    swordMeta.setLore(lore);
	    sword.setItemMeta(swordMeta);
	    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	    sword.addEnchantment(Enchantment.KNOCKBACK, 1);
	    sword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
	    p.getInventory().setItem(0, sword);
	    ItemStack sword2 = new ItemStack(Material.BOW);
	    ItemMeta swordMeta2 = sword2.getItemMeta();
	    swordMeta2.setDisplayName(ChatColor.DARK_GRAY + "§lCoal Kit");
	    swordMeta2.setLore(lore);
	    sword2.setItemMeta(swordMeta2);
	    sword2.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword2.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
	    p.getInventory().setItem(1, sword2);

	    ItemStack arrow = new ItemStack(Material.ARROW, 32);
	    ItemMeta arrowmeta = arrow.getItemMeta();
	    arrowmeta.setDisplayName(ChatColor.DARK_GRAY + "§lCoal Kit");
	    arrowmeta.setLore(lore);
	    arrow.setItemMeta(arrowmeta);
	    p.getInventory().setItem(5, arrow);

	    ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta = (LeatherArmorMeta)Helmet.getItemMeta();
	    meta.setDisplayName(ChatColor.DARK_GRAY + "§lCoal Kit");
	    meta.setLore(lore);
	    meta.setColor(Color.BLACK);
	    Helmet.setItemMeta(meta);
	    Helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    p.getInventory().setHelmet(Helmet);

	    ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta Cmeta = (LeatherArmorMeta)Chest.getItemMeta();
	    Cmeta.setDisplayName(ChatColor.DARK_GRAY + "§lCoal Kit");
	    Cmeta.setLore(lore);
	    Cmeta.setColor(Color.BLACK);
	    Chest.setItemMeta(Cmeta);
	    Chest.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    p.getInventory().setChestplate(Chest);

	    ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta Lmeta = (LeatherArmorMeta)Pants.getItemMeta();
	    Lmeta.setDisplayName(ChatColor.DARK_GRAY + "§lCoal Kit");
	    Lmeta.setLore(lore);
	    Lmeta.setColor(Color.BLACK);
	    Pants.setItemMeta(Lmeta);
	    Pants.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    p.getInventory().setLeggings(Pants);

	    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta Bmeta = (LeatherArmorMeta)Boots.getItemMeta();
	    Bmeta.setDisplayName(ChatColor.DARK_GRAY + "§lCoal Kit");
	    Bmeta.setLore(lore);
	    Bmeta.setColor(Color.BLACK);
	    Boots.setItemMeta(Bmeta);
	    Boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    p.getInventory().setBoots(Boots);
	    p.getInventory().setItem(2, new ItemStack(Material.ENDER_PEARL));
	    p.updateInventory();
	  }
	  public static void Lapis(Player p) {
	    p.getInventory().clear();
	    p.getInventory().setArmorContents(null);
	    p.setMaxHealth(30);
	    p.setHealth(p.getMaxHealth());
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY + "SoulBound");
	    ItemStack sword = new ItemStack(Material.STONE_SWORD);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.BLUE + "§lLapis Kit");
	    swordMeta.setLore(lore);
	    sword.setItemMeta(swordMeta);
	    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword.addEnchantment(Enchantment.KNOCKBACK, 1);
	    sword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
	    p.getInventory().setItem(0, sword);
	    ItemStack sword2 = new ItemStack(Material.BOW);
	    ItemMeta swordMeta2 = sword2.getItemMeta();
	    swordMeta2.setDisplayName(ChatColor.BLUE + "§lLapis Kit");
	    swordMeta2.setLore(lore);
	    sword2.setItemMeta(swordMeta2);
	    sword2.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword2.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
	    sword2.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
	    p.getInventory().setItem(1, sword2);

	    ItemStack arrow = new ItemStack(Material.ARROW, 64);
	    ItemMeta arrowmeta = arrow.getItemMeta();
	    arrowmeta.setDisplayName(ChatColor.BLUE + "§lLapis Kit");
	    arrowmeta.setLore(lore);
	    arrow.setItemMeta(arrowmeta);
	    p.getInventory().setItem(6, arrow);

	    ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta = (LeatherArmorMeta)Helmet.getItemMeta();
	    meta.setDisplayName(ChatColor.BLUE + "§lLapis Kit");
	    meta.setLore(lore);
	    meta.setColor(Color.BLUE);
	    Helmet.setItemMeta(meta);
	    Helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    p.getInventory().setHelmet(Helmet);

	    ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta Cmeta = (LeatherArmorMeta)Chest.getItemMeta();
	    Cmeta.setDisplayName(ChatColor.BLUE + "§lLapis Kit");
	    Cmeta.setLore(lore);
	    Cmeta.setColor(Color.BLUE);
	    Chest.setItemMeta(Cmeta);
	    Chest.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    p.getInventory().setChestplate(Chest);

	    ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta Lmeta = (LeatherArmorMeta)Pants.getItemMeta();
	    Lmeta.setDisplayName(ChatColor.BLUE + "§lLapis Kit");
	    Lmeta.setLore(lore);
	    Lmeta.setColor(Color.BLUE);
	    Pants.setItemMeta(Lmeta);
	    Pants.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    p.getInventory().setLeggings(Pants);

	    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta Bmeta = (LeatherArmorMeta)Boots.getItemMeta();
	    Bmeta.setDisplayName(ChatColor.BLUE + "§lLapis Kit");
	    Bmeta.setLore(lore);
	    Bmeta.setColor(Color.BLUE);
	    Boots.setItemMeta(Bmeta);
	    Boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    p.getInventory().setBoots(Boots);
	    p.getInventory().setItem(2, new ItemStack(Material.ENDER_PEARL));
	    p.updateInventory();
	  }
	  public static void Iron(Player p) {
	    p.getInventory().clear();
	    p.getInventory().setArmorContents(null);
	    p.setMaxHealth(30);
	    p.setHealth(p.getMaxHealth());
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY + "SoulBound");
	    ItemStack sword = new ItemStack(Material.STONE_SWORD);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.WHITE + "§lIron Kit");
	    swordMeta.setLore(lore);
	    sword.setItemMeta(swordMeta);
	    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	    p.getInventory().setItem(0, sword);

	    ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta = (LeatherArmorMeta)Helmet.getItemMeta();
	    meta.setDisplayName(ChatColor.WHITE + "§lIron Kit");
	    meta.setLore(lore);
	    meta.setColor(Color.WHITE);
	    Helmet.setItemMeta(meta);
	    Helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    p.getInventory().setHelmet(Helmet);
	    ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta Cmeta = (LeatherArmorMeta)Chest.getItemMeta();
	    Cmeta.setDisplayName(ChatColor.WHITE + "§lIron Kit");
	    Cmeta.setLore(lore);
	    Cmeta.setColor(Color.WHITE);
	    Chest.setItemMeta(Cmeta);
	    Chest.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    p.getInventory().setChestplate(Chest);

	    ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta Lmeta = (LeatherArmorMeta)Pants.getItemMeta();
	    Lmeta.setDisplayName(ChatColor.WHITE + "§lIron Kit");
	    Lmeta.setLore(lore);
	    Lmeta.setColor(Color.WHITE);
	    Pants.setItemMeta(Lmeta);
	    Pants.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    p.getInventory().setLeggings(Pants);

	    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta Bmeta = (LeatherArmorMeta)Boots.getItemMeta();
	    Bmeta.setDisplayName(ChatColor.WHITE + "§lIron Kit");
	    Bmeta.setLore(lore);
	    Bmeta.setColor(Color.WHITE);
	    Boots.setItemMeta(Bmeta);
	    Boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    p.getInventory().setBoots(Boots);
	    p.getInventory().setItem(1, new ItemStack(Material.ENDER_PEARL, 5));
	    p.updateInventory();
	  }

	  public static void Redstone(Player p) {
	    p.getInventory().clear();
	    p.getInventory().setArmorContents(null);
	    p.setMaxHealth(30);
	    p.setHealth(p.getMaxHealth());
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY + "SoulBound");
	    ItemStack sword = new ItemStack(Material.STONE_AXE);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setLore(lore);
	    sword.setItemMeta(swordMeta);
	    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
	    sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
	    p.getInventory().setItem(0, sword);

	    ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta = (LeatherArmorMeta)Helmet.getItemMeta();
	    meta.setDisplayName(ChatColor.RED + "§lRedstone Kit");
	    meta.setLore(lore);
	    meta.setColor(Color.RED);
	    Helmet.setItemMeta(meta);
	    Helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    p.getInventory().setHelmet(Helmet);
	    ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta Cmeta = (LeatherArmorMeta)Chest.getItemMeta();
	    Cmeta.setDisplayName(ChatColor.RED + "§lRedstone Kit");
	    Cmeta.setLore(lore);
	    Cmeta.setColor(Color.RED);
	    Chest.setItemMeta(Cmeta);
	    Chest.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    p.getInventory().setChestplate(Chest);

	    ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta Lmeta = (LeatherArmorMeta)Pants.getItemMeta();
	    Lmeta.setDisplayName(ChatColor.RED + "§lRedstone Kit");
	    Lmeta.setLore(lore);
	    Lmeta.setColor(Color.RED);
	    Pants.setItemMeta(Lmeta);
	    Pants.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    p.getInventory().setLeggings(Pants);

	    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta Bmeta = (LeatherArmorMeta)Boots.getItemMeta();
	    Bmeta.setDisplayName(ChatColor.RED + "§lRedstone Kit");
	    Bmeta.setLore(lore);
	    Bmeta.setColor(Color.RED);
	    Boots.setItemMeta(Bmeta);
	    Boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    p.getInventory().setBoots(Boots);
	    p.updateInventory();
	  }

	  public static void Gold(Player p)
	  {
	    p.getInventory().clear();
	    p.getInventory().setArmorContents(null);
	    p.setMaxHealth(30);
	    p.setHealth(p.getMaxHealth());
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY + "SoulBound");
	    ItemStack sword = new ItemStack(Material.STONE_SWORD);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.GOLD + "§LGold Kit");
	    swordMeta.setLore(lore);
	    sword.setItemMeta(swordMeta);
	    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
	    sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
	    p.getInventory().setItem(0, sword);

	    ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta = (LeatherArmorMeta)Helmet.getItemMeta();
	    meta.setDisplayName(ChatColor.GOLD + "§LGold Kit");
	    meta.setLore(lore);
	    meta.setColor(Color.YELLOW);
	    Helmet.setItemMeta(meta);
	    Helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    p.getInventory().setHelmet(Helmet);

	    ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta Cmeta = (LeatherArmorMeta)Chest.getItemMeta();
	    Cmeta.setDisplayName(ChatColor.GOLD + "§LGold Kit");
	    Cmeta.setLore(lore);
	    Cmeta.setColor(Color.YELLOW);
	    Chest.setItemMeta(Cmeta);
	    Chest.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    p.getInventory().setChestplate(Chest);

	    ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta Lmeta = (LeatherArmorMeta)Pants.getItemMeta();
	    Lmeta.setDisplayName(ChatColor.GOLD + "§LGold Kit");
	    Lmeta.setLore(lore);
	    Lmeta.setColor(Color.YELLOW);
	    Pants.setItemMeta(Lmeta);
	    Pants.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    p.getInventory().setLeggings(Pants);

	    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta Bmeta = (LeatherArmorMeta)Boots.getItemMeta();
	    Bmeta.setDisplayName(ChatColor.GOLD + "§LGold Kit");
	    Bmeta.setLore(lore);
	    Bmeta.setColor(Color.YELLOW);
	    Boots.setItemMeta(Bmeta);
	    Boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
	    p.getInventory().setBoots(Boots);
	    p.getInventory().setItem(1, new ItemStack(Material.ENDER_PEARL));
	    p.updateInventory();
	  }
	  public static void Diamond(Player p) {
	    p.getInventory().clear();
	    p.getInventory().setArmorContents(null);
	    p.setMaxHealth(30);
	    p.setHealth(p.getMaxHealth());
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY + "SoulBound");
	    ItemStack sword = new ItemStack(Material.STONE_SWORD);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.AQUA + "§lDiamond Kit");
	    swordMeta.setLore(lore);
	    sword.setItemMeta(swordMeta);
	    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
	    sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
	    p.getInventory().setItem(0, sword);

	    ItemStack sword3 = new ItemStack(Material.STICK);
	    ItemMeta swordMeta3 = sword3.getItemMeta();
	    swordMeta3.setDisplayName(ChatColor.AQUA + "§lDiamond Kit");
	    swordMeta3.setLore(lore);
	    sword3.setItemMeta(swordMeta3);
	    sword3.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
	    sword3.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
	    p.getInventory().setItem(2, sword3);

	    ItemStack arrow = new ItemStack(Material.ARROW, 32);
	    ItemMeta arrowmeta = arrow.getItemMeta();
	    arrowmeta.setDisplayName(ChatColor.AQUA + "§lDiamond Kit");
	    arrowmeta.setLore(lore);
	    arrow.setItemMeta(arrowmeta);
	    p.getInventory().setItem(7, arrow);

	    ItemStack sword2 = new ItemStack(Material.BOW);
	    ItemMeta swordMeta2 = sword2.getItemMeta();
	    swordMeta2.setDisplayName(ChatColor.AQUA + "§lDiamond Kit");
	    swordMeta2.setLore(lore);
	    sword2.setItemMeta(swordMeta2);
	    sword2.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword2.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
	    sword2.addEnchantment(Enchantment.ARROW_FIRE, 1);
	    p.getInventory().setItem(1, sword2);

	    ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta = (LeatherArmorMeta)Helmet.getItemMeta();
	    meta.setDisplayName(ChatColor.AQUA + "§lDiamond Kit");
	    meta.setLore(lore);
	    meta.setColor(Color.AQUA);
	    Helmet.setItemMeta(meta);
	    Helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 2);
	    p.getInventory().setHelmet(Helmet);

	    ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta Cmeta = (LeatherArmorMeta)Chest.getItemMeta();
	    Cmeta.setDisplayName(ChatColor.AQUA + "§lDiamond Kit");
	    Cmeta.setLore(lore);
	    Cmeta.setColor(Color.AQUA);
	    Chest.setItemMeta(Cmeta);
	    Chest.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 2);
	    p.getInventory().setChestplate(Chest);

	    ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta Lmeta = (LeatherArmorMeta)Pants.getItemMeta();
	    Lmeta.setDisplayName(ChatColor.AQUA + "§lDiamond Kit");
	    Lmeta.setLore(lore);
	    Lmeta.setColor(Color.AQUA);
	    Pants.setItemMeta(Lmeta);
	    Pants.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 2);
	    p.getInventory().setLeggings(Pants);

	    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta Bmeta = (LeatherArmorMeta)Boots.getItemMeta();
	    Bmeta.setDisplayName(ChatColor.AQUA + "§lDiamond Kit");
	    Bmeta.setLore(lore);
	    Bmeta.setColor(Color.AQUA);
	    Boots.setItemMeta(Bmeta);
	    Boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 2);
	    p.getInventory().setBoots(Boots);
	    p.updateInventory();
	  }
	  public static void Emerald(Player p) {
	    p.getInventory().clear();
	    p.getInventory().setArmorContents(null);
	    p.setMaxHealth(30);
	    p.setHealth(p.getMaxHealth());
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY + "SoulBound");
	    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.GREEN + "§lEmerald Kit");
	    swordMeta.setLore(lore);
	    sword.setItemMeta(swordMeta);
	    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    p.getInventory().setItem(0, sword);

	    ItemStack arrow = new ItemStack(Material.ARROW, 32);
	    ItemMeta arrowmeta = arrow.getItemMeta();
	    arrowmeta.setDisplayName(ChatColor.GREEN + "§lEmerald Kit");
	    arrowmeta.setLore(lore);
	    arrow.setItemMeta(arrowmeta);
	    p.getInventory().setItem(5, arrow);

	    ItemStack sword2 = new ItemStack(Material.BOW);
	    ItemMeta swordMeta2 = sword2.getItemMeta();
	    swordMeta2.setDisplayName(ChatColor.GREEN + "§lEmerald Kit");
	    swordMeta2.setLore(lore);
	    sword2.setItemMeta(swordMeta2);
	    sword2.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    sword2.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
	    sword2.addEnchantment(Enchantment.ARROW_FIRE, 1);
	    p.getInventory().setItem(1, sword2);

	    ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta = (LeatherArmorMeta)Helmet.getItemMeta();
	    meta.setDisplayName(ChatColor.GREEN + "§lEmerald Kit");
	    meta.setLore(lore);
	    meta.setColor(Color.LIME);
	    Helmet.setItemMeta(meta);
	    Helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    p.getInventory().setHelmet(Helmet);

	    ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta Cmeta = (LeatherArmorMeta)Chest.getItemMeta();
	    Cmeta.setDisplayName(ChatColor.GREEN + "§lEmerald Kit");
	    Cmeta.setLore(lore);
	    Cmeta.setColor(Color.LIME);
	    Chest.setItemMeta(Cmeta);
	    Chest.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    p.getInventory().setChestplate(Chest);

	    ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta Lmeta = (LeatherArmorMeta)Pants.getItemMeta();
	    Lmeta.setDisplayName(ChatColor.GREEN + "§lEmerald Kit");
	    Lmeta.setLore(lore);
	    Lmeta.setColor(Color.LIME);
	    Pants.setItemMeta(Lmeta);
	    Pants.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    p.getInventory().setLeggings(Pants);

	    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta Bmeta = (LeatherArmorMeta)Boots.getItemMeta();
	    Bmeta.setDisplayName(ChatColor.GREEN + "§lEmerald Kit");
	    Bmeta.setLore(lore);
	    Bmeta.setColor(Color.LIME);
	    Boots.setItemMeta(Bmeta);
	    Boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    p.getInventory().setBoots(Boots);
	    p.updateInventory();
	  }

	  public static void KillStreaker(Player p) {
		    p.getInventory().clear();
		    p.getInventory().setArmorContents(null);
		    List<String> lore = new ArrayList<String>();
		    lore.add(ChatColor.GRAY + "SoulBound");
		    ItemStack sword = new ItemStack(Material.STONE_SWORD);
		    ItemMeta swordMeta = sword.getItemMeta();
		    swordMeta.setDisplayName(ChatColor.WHITE + "§lKillStreaker");
		    lore.add(ChatColor.RED + "Krijg na elke kill één hartje erbij!");
		    swordMeta.setLore(lore);
		    sword.setItemMeta(swordMeta);
		    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		    p.getInventory().setItem(0, sword);

		    ItemStack arrow = new ItemStack(Material.ARROW, 32);
		    ItemMeta arrowmeta = arrow.getItemMeta();
		    arrowmeta.setDisplayName(ChatColor.WHITE + "§lKillStreaker");
		    arrow.setItemMeta(arrowmeta);
		    p.getInventory().setItem(2, arrow);

		    ItemStack sword2 = new ItemStack(Material.BOW);
		    ItemMeta swordMeta2 = sword2.getItemMeta();
		    List<String> lore2 = new ArrayList<String>();
		    swordMeta2.setDisplayName(ChatColor.WHITE + "§lKillStreaker");
		    lore2.add(ChatColor.RED + "Krijg na elke kill één hartje erbij!");
		    swordMeta2.setLore(lore2);
		    sword2.setItemMeta(swordMeta2);
		    sword2.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		    sword2.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		    p.getInventory().setItem(1, sword2);

		    ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
		    LeatherArmorMeta meta = (LeatherArmorMeta)Helmet.getItemMeta();
		    meta.setDisplayName(ChatColor.WHITE + "§lKillStreaker");
		    meta.setColor(Color.RED);
		    Helmet.setItemMeta(meta);
		    Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		    p.getInventory().setHelmet(Helmet);

		    ItemStack Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		    LeatherArmorMeta Cmeta = (LeatherArmorMeta)Chest.getItemMeta();
		    Cmeta.setDisplayName(ChatColor.WHITE + "§lKillStreaker");
		    Cmeta.setColor(Color.WHITE);
		    Chest.setItemMeta(Cmeta);
		    Chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		    p.getInventory().setChestplate(Chest);

		    ItemStack Pants = new ItemStack(Material.LEATHER_LEGGINGS);
		    LeatherArmorMeta Lmeta = (LeatherArmorMeta)Pants.getItemMeta();
		    Lmeta.setDisplayName(ChatColor.WHITE + "§lKillStreaker");
		    Lmeta.setColor(Color.BLUE);
		    Pants.setItemMeta(Lmeta);
		    Pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		    p.getInventory().setLeggings(Pants);

		    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
		    LeatherArmorMeta Bmeta = (LeatherArmorMeta)Boots.getItemMeta();
		    Bmeta.setDisplayName(ChatColor.WHITE + "§lKillStreaker");
		    Bmeta.setColor(Color.ORANGE);
		    Boots.setItemMeta(Bmeta);
		    Boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		    p.getInventory().setBoots(Boots);
		    p.updateInventory();
	  }
	  
	  public void giveKit(final Player p) {
		  Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
		    {
		      @SuppressWarnings("static-access")
			public void run() {
		        plugin.data.addDefault(p.getName() + ".lastkit", "none");
		        plugin.data.addDefault(p.getName() + ".killstreaks", Integer.valueOf(0));
		        plugin.saveCustomConfig();
		        plugin.getConfig().set(p.getName() + ".killstreak", Integer.valueOf(0));
		        plugin.data.options().copyDefaults(true);
		        plugin.saveConfig();
		        plugin.getServer().dispatchCommand(p.getServer().getConsoleSender(), "speed walk 5 " + p.getName());
		        
		        if (!plugin.data.getString(p.getName() + ".lastkit").equals("none")) {
		          if (plugin.data.getString(p.getName() + ".lastkit").equals("Wood")) {
		            Wood(p);
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&6&lRegeneration lore:&7SoulBound");
		          } else if (plugin.data.getString(p.getName() + ".lastkit").equals("Coal")) {
		            Coal(p);
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16454 name:&8&lNight_Vision lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16386 name:&8&lSpeed lore:&7SoulBound");
		          } else if (plugin.data.getString(p.getName() + ".lastkit").equals("Lapis")) {
		            Lapis(p);
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16427 name:&3&lLeaping lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&3&lSpeed lore:&7SoulBound");
		          } else if (plugin.data.getString(p.getName() + ".lastkit").equals("Iron")) {
		            Iron(p);
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
		          } else if (plugin.data.getString(p.getName() + ".lastkit").equals("Redstone")) {
		            Redstone(p);
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&4&lSpeed lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&4&lWater_Breathing lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
		          } else if (plugin.data.getString(p.getName() + ".lastkit").equals("Gold")) {
		            Gold(p);
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&6&lSpeed lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16388 name:&6&lPoison lore:&7SoulBound");
		          } else if (plugin.data.getString(p.getName() + ".lastkit").equals("Diamond")) {
		            Diamond(p);
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&3&lWater_Breathing lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&3&lHealing lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16387 name:&3&lFire_resistance lore:&7SoulBound");
		          } else if (plugin.data.getString(p.getName() + ".lastkit").equals("Emerald")) {
		            Emerald(p);
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&2&lSpeed lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
		          } else {
		            Speler(p);
		            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
		          }

		        }
		        else if ((plugin.playerInGroup("World", p, "Wood")) || (plugin.playerInGroup("World", p, "Helper+"))) {
		          Wood(p);
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&6&lRegeneration lore:&7SoulBound");
		        } else if ((plugin.playerInGroup("World", p, "Coal")) || (plugin.playerInGroup("World", p, "Helper+"))) {
		          Coal(p);
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16454 name:&8&lNight_Vision lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16386 name:&8&lSpeed lore:&7SoulBound");
		        } else if ((plugin.playerInGroup("World", p, "Lapis")) || (plugin.playerInGroup("World", p, "Helper+"))) {
		          Lapis(p);
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16427 name:&3&lLeaping lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&3&lSpeed lore:&7SoulBound");
		        } else if ((plugin.playerInGroup("World", p, "Iron")) || (plugin.playerInGroup("World", p, "Helper+"))) {
		          Iron(p);
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
		        } else if ((plugin.playerInGroup("World", p, "Redstone")) || (plugin.playerInGroup("World", p, "Helper+"))) {
		          Redstone(p);
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&4&lSpeed lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&4&lWater_Breathing lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
		        } else if ((plugin.playerInGroup("World", p, "Gold")) || (plugin.playerInGroup("World", p, "Helper+"))) {
		          Gold(p);
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&6&lSpeed lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16388 name:&6&lpoison lore:&7SoulBound");
		        } else if ((plugin.playerInGroup("World", p, "Diamond")) || (plugin.playerInGroup("World", p, "Helper+"))) {
		          Diamond(p);
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&3&lWater_Breathing lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&3&lHealing lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16387 name:&3&lFire_resistance lore:&7SoulBound");
		        } else if ((plugin.playerInGroup("World", p, "Emerald")) || (plugin.playerInGroup("World", p, "Helper+"))) {
		          Emerald(p);
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&2&lSpeed lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
		        } else {
		          Speler(p);
		          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
		        }
		      }
		    }
		    , 10L);
	  }
	  
}
