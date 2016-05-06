package cock.topia.tvg.Inventory;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import cock.topia.tvg.Main;
import cock.topia.tvg.Utils.MoneyManager;

public class ItemShop
  implements Listener
{
	Main plugin;
	public ItemShop(Main plugin) {
		this.plugin = plugin;
	}
	
  @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public static void openGUI1(Player p)
  {
	  
    Inventory inv = Bukkit.createInventory(null, 9, "§2§lItemShop");
    Potion potionSpeed = new Potion(PotionType.SPEED, 1, true);
    ItemStack speed = potionSpeed.toItemStack(1);
    ItemMeta Cspeed = speed.getItemMeta();
    List l1 = new ArrayList();
    l1.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "50 money");
    List l2 = new ArrayList();
    l2.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "100 money");
    List l3 = new ArrayList();
    l3.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "150 money");
    List l4 = new ArrayList();
    l4.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "200 money");
    List l5 = new ArrayList();
    l5.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "300 money");
    List l6 = new ArrayList();
    l6.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "400 money");
    Potion potionHealing = new Potion(PotionType.INSTANT_HEAL, 1, true);
    ItemStack healing = potionHealing.toItemStack(1);
    ItemMeta Chealing = healing.getItemMeta();
    Potion regenp = new Potion(PotionType.REGEN, 1, true, true);
    ItemStack regen = regenp.toItemStack(1);
    ItemMeta Cregen = regen.getItemMeta();
    Potion firep = new Potion(PotionType.FIRE_RESISTANCE, 1, true);
    ItemStack fire = firep.toItemStack(1);
    ItemMeta Cfire = fire.getItemMeta();
    Potion nightp = new Potion(PotionType.NIGHT_VISION, 1, true);
    ItemStack night = nightp.toItemStack(1);
    ItemMeta Cnight = night.getItemMeta();
    Potion jumpp = new Potion(PotionType.JUMP, 1, true);
    ItemStack jump = jumpp.toItemStack(1);
    ItemMeta Cjump = jump.getItemMeta();
    ItemStack end = new ItemStack(Material.ENDER_PEARL);
    ItemMeta Cend = end.getItemMeta();
    ItemStack apple = new ItemStack(Material.GOLDEN_APPLE);
    ItemMeta Capple = apple.getItemMeta();
    Cfire.setLore(l1);
    Cspeed.setLore(l1);
    Chealing.setLore(l2);
    Cregen.setLore(l2);
    Cend.setLore(l2);
    Cnight.setLore(l3);
    Cjump.setLore(l4);
    Capple.setLore(l5);

    Cspeed.setDisplayName(ChatColor.WHITE + "§lSpeed potion");
    speed.setItemMeta(Cspeed);
    Chealing.setDisplayName(ChatColor.WHITE + "§lHealth potion");
    healing.setItemMeta(Chealing);
    Cregen.setDisplayName(ChatColor.WHITE + "§lRegeneration potion");
    regen.setItemMeta(Cregen);
    Cfire.setDisplayName(ChatColor.WHITE + "§lFire resistance potion");
    fire.setItemMeta(Cfire);
    Cnight.setDisplayName(ChatColor.WHITE + "§lNight vision potion");
    night.setItemMeta(Cnight);
    Cjump.setDisplayName(ChatColor.WHITE + "§lJump potion");
    jump.setItemMeta(Cjump);
    Cend.setDisplayName(ChatColor.WHITE + "§lEnder pearl");
    end.setItemMeta(Cend);
    Capple.setDisplayName(ChatColor.WHITE + "§lGolden apple");
    apple.setItemMeta(Capple);

    ItemStack sword3 = new ItemStack(Material.STICK);
    ItemMeta swordMeta3 = sword3.getItemMeta();
    swordMeta3.setDisplayName(ChatColor.WHITE + "§lKnockback stick");
    swordMeta3.setLore(l6);
    sword3.setItemMeta(swordMeta3);

    sword3.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
    sword3.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
    
    inv.setItem(0, speed);
    inv.setItem(1, healing);
    inv.setItem(2, regen);
    inv.setItem(3, fire);
    inv.setItem(4, night);
    inv.setItem(5, jump);
    inv.setItem(6, end);
    inv.setItem(7, sword3);
    inv.setItem(8, apple);
    p.openInventory(inv);
  }

  @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
@EventHandler
  public void onInventoryClick(InventoryClickEvent event)
  {
	  MoneyManager mm = new MoneyManager(plugin);
    if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("ItemShop")) {
      return;
    }
    Player p = (Player)event.getWhoClicked();
    event.setCancelled(true);
    if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType() == Material.AIR) || (!event.getCurrentItem().hasItemMeta()))
    {
      return;
    }
    Potion potionSpeed = new Potion(PotionType.SPEED, 1, true);
    ItemStack speed = potionSpeed.toItemStack(1);
    ItemMeta Cspeed = speed.getItemMeta();
    List l1 = new ArrayList();
    l1.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "50 money");
    List l2 = new ArrayList();
    l2.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "100 money");
    List l3 = new ArrayList();
    l3.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "150 money");
    List l4 = new ArrayList();
    l4.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "200 money");
    List l5 = new ArrayList();
    l5.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "300 money");
    List l6 = new ArrayList();
    l6.add(ChatColor.WHITE + "Kost: " + ChatColor.GOLD + "400 money");
    Potion potionHealing = new Potion(PotionType.INSTANT_HEAL, 1, true);
    ItemStack healing = potionHealing.toItemStack(1);
    ItemMeta Chealing = healing.getItemMeta();
    Potion regenp = new Potion(PotionType.REGEN, 1, true, true);
    ItemStack regen = regenp.toItemStack(1);
    ItemMeta Cregen = regen.getItemMeta();
    Potion firep = new Potion(PotionType.FIRE_RESISTANCE, 1, true);
    ItemStack fire = firep.toItemStack(1);
    ItemMeta Cfire = fire.getItemMeta();
    Potion nightp = new Potion(PotionType.NIGHT_VISION, 1, true);
    ItemStack night = nightp.toItemStack(1);
    ItemMeta Cnight = night.getItemMeta();
    Potion jumpp = new Potion(PotionType.JUMP, 1, true);
    ItemStack jump = jumpp.toItemStack(1);
    ItemMeta Cjump = jump.getItemMeta();
    ItemStack end = new ItemStack(Material.ENDER_PEARL);
    ItemMeta Cend = end.getItemMeta();
    ItemStack apple = new ItemStack(Material.GOLDEN_APPLE);
    ItemMeta Capple = apple.getItemMeta();

    Cspeed.setDisplayName(ChatColor.WHITE + "§lSpeed potion");
    speed.setItemMeta(Cspeed);
    Chealing.setDisplayName(ChatColor.WHITE + "§lHealth potion");
    healing.setItemMeta(Chealing);
    Cregen.setDisplayName(ChatColor.WHITE + "§lRegeneration potion");
    regen.setItemMeta(Cregen);
    Cfire.setDisplayName(ChatColor.WHITE + "§lFire resistance potion");
    fire.setItemMeta(Cfire);
    Cnight.setDisplayName(ChatColor.WHITE + "§lNight vision potion");
    night.setItemMeta(Cnight);
    Cjump.setDisplayName(ChatColor.WHITE + "§lJump potion");
    jump.setItemMeta(Cjump);
    Cend.setDisplayName(ChatColor.WHITE + "§lEnder pearl");
    end.setItemMeta(Cend);
    Capple.setDisplayName(ChatColor.WHITE + "§lGolden apple");
    apple.setItemMeta(Capple);

    ItemStack sword3 = new ItemStack(Material.STICK);
    ItemMeta swordMeta3 = sword3.getItemMeta();
    swordMeta3.setDisplayName(ChatColor.WHITE + "§lKnockback stick");
    sword3.setItemMeta(swordMeta3);
    sword3.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
    sword3.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
    switch (event.getSlot())
    {
    case 0:
      if (mm.getMoney(p) >= 50) {
        p.getInventory().addItem(new ItemStack[] { speed });
        mm.removeMoney(p, 50);
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Je hebt 1 speed potion gekocht voor 50 money!");
      } else {
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED + " Je hebt niet genoeg money hiervoor!");
      }
      break;
    case 1:
      if (mm.getMoney(p) >= 100) {
        p.getInventory().addItem(new ItemStack[] { healing });
        mm.removeMoney(p, 100);
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Je hebt 1 instant health potion gekocht voor 100 money!");
      } else {
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED + " Je hebt niet genoeg money hiervoor!");
      }
      break;
    case 2:
      if (mm.getMoney(p) >= 100) {
        p.getInventory().addItem(new ItemStack[] { regen });
        mm.removeMoney(p, 100);
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Je hebt 1 regen potion gekocht voor 100 money!");
      } else {
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED + " Je hebt niet genoeg money hiervoor!");
      }
      break;
    case 3:
      if (mm.getMoney(p) >= 50) {
        p.getInventory().addItem(new ItemStack[] { fire });
        mm.removeMoney(p, 50);
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Je hebt 1 fire resistance potion gekocht voor 50 money!");
      } else {
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED + " Je hebt niet genoeg money hiervoor!");
      }
      break;
    case 4:
      if (mm.getMoney(p) >= 150) {
        p.getInventory().addItem(new ItemStack[] { night });
        mm.removeMoney(p, 150);
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Je hebt 1 night vision potion gekocht voor 150 money!");
      } else {
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED + " Je hebt niet genoeg money hiervoor!");
      }
      break;
    case 5:
      if (mm.getMoney(p) >= 200) {
        p.getInventory().addItem(new ItemStack[] { jump });
        mm.removeMoney(p, 200);
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Je hebt 1 jump boost potion gekocht voor 200 money!");
      } else {
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED + " Je hebt niet genoeg money hiervoor!");
      }
      break;
    case 6:
      if (mm.getMoney(p) >= 100) {
        p.getInventory().addItem(new ItemStack[] { end });
        mm.removeMoney(p, 100);
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Je hebt 1 ender pearl gekocht voor 100 money!");
      } else {
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED + " Je hebt niet genoeg money hiervoor!");
      }
      break;
    case 7:
      if (mm.getMoney(p) >= 400) {
        p.getInventory().addItem(new ItemStack[] { sword3 });
        mm.removeMoney(p, 400);
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Je hebt 1 knockback stick gekocht voor 400 money!");
      } else {
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED + " Je hebt niet genoeg money hiervoor!");
      }
      break;
    case 8:
      if (mm.getMoney(p) >= 300) {
        p.getInventory().addItem(new ItemStack[] { apple });
        mm.removeMoney(p, 300);
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Je hebt 1 golden apple gekocht voor 300 money!");
      } else {
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED + " Je hebt niet genoeg money hiervoor!");
      }
      break;
    }
  }
}