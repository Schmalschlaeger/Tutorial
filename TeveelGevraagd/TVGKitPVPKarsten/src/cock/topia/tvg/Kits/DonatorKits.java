package cock.topia.tvg.Kits;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cock.topia.tvg.Main;

public class DonatorKits implements Listener
{
  private static Main pl;

  public DonatorKits(Main plugin)
  {
    pl = plugin;
  }
  @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public static void openGUI1(Player p) {
    Inventory inv = Bukkit.createInventory(null, 18, "§2§lDonateur Kits");
    ItemStack speler = new ItemStack(Material.STONE, 1);
    ItemMeta spelerMeta = speler.getItemMeta();
    ItemStack Wood = new ItemStack(Material.WOOD, 1);
    ItemMeta WoodMeta = Wood.getItemMeta();
    ItemStack Coal = new ItemStack(Material.COAL_BLOCK, 1);
    ItemMeta CoalMeta = Coal.getItemMeta();
    ItemStack Lapis = new ItemStack(Material.LAPIS_BLOCK, 1);
    ItemMeta LapisMeta = Lapis.getItemMeta();
    ItemStack Iron = new ItemStack(Material.IRON_BLOCK, 1);
    ItemMeta IronMeta = Iron.getItemMeta();
    ItemStack Redstone = new ItemStack(Material.REDSTONE_BLOCK, 1);
    ItemMeta RedstoneMeta = Redstone.getItemMeta();
    ItemStack Gold = new ItemStack(Material.GOLD_BLOCK, 1);
    ItemMeta GoldMeta = Gold.getItemMeta();
    ItemStack Diamond = new ItemStack(Material.DIAMOND_BLOCK, 1);
    ItemMeta DiamondMeta = Diamond.getItemMeta();
    ItemStack Emerald = new ItemStack(Material.EMERALD_BLOCK, 1);
    ItemMeta EmeraldMeta = Emerald.getItemMeta();
    
    //TODO: Kits die je kunt kopen
    ItemStack KillStreaker = new ItemStack(Material.getMaterial(351), 1, (byte) 1);
    List<String> lore = new ArrayList<String>();
    lore.add(ChatColor.GOLD + "Speciale kit voor speciale spelers 0_0");
    lore.addAll(getLore(p, "KillStreaker"));
    ItemMeta KillStreakerMeta = KillStreaker.getItemMeta();
    KillStreakerMeta.setLore(lore);
    
    ItemStack Suicide = new ItemStack(Material.TNT, 1);
    ItemMeta SuicideMeta = Suicide.getItemMeta();
    ItemStack Witch = new ItemStack(Material.GLASS_BOTTLE, 1);
    ItemMeta WitchMeta = Witch.getItemMeta();
    ItemStack Vampire = new ItemStack(Material.REDSTONE, 1);
    ItemMeta VampireMeta = Vampire.getItemMeta();
    ItemStack Pikachu = new ItemStack(Material.MONSTER_EGG, 1, (short)61);
    ItemMeta PikachuMeta = Pikachu.getItemMeta();
    ItemStack Frozen = new ItemStack(Material.ICE, 1);
    ItemMeta FrozenMeta = Frozen.getItemMeta();
    ItemStack Striker = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    ItemMeta StrikerMeta = Striker.getItemMeta();
    ItemStack Knive = new ItemStack(Material.STONE_SWORD, 1);
    ItemMeta KniveMeta = Knive.getItemMeta();

    WoodMeta.setLore(getLore(p, "Wood"));
    List array = new ArrayList();
    array.add(ChatColor.GREEN + "§lBeschikbaar!");
    spelerMeta.setLore(array);
    WoodMeta.setDisplayName(ChatColor.WHITE + "§lWood");
    Wood.setItemMeta(WoodMeta);

    CoalMeta.setLore(getLore(p, "Coal"));
    CoalMeta.setDisplayName(ChatColor.DARK_GRAY + "§lCoal");
    Coal.setItemMeta(CoalMeta);

    LapisMeta.setLore(getLore(p, "Lapis"));
    LapisMeta.setDisplayName(ChatColor.BLUE + "§lLapis");
    Lapis.setItemMeta(LapisMeta);

    IronMeta.setLore(getLore(p, "Iron"));
    IronMeta.setDisplayName(ChatColor.GRAY + "§lIron");
    Iron.setItemMeta(IronMeta);

    RedstoneMeta.setLore(getLore(p, "Redstone"));
    RedstoneMeta.setDisplayName(ChatColor.DARK_RED + "§lRedstone");
    Redstone.setItemMeta(RedstoneMeta);
    GoldMeta.setLore(getLore(p, "Gold"));
    GoldMeta.setDisplayName(ChatColor.GOLD + "§lGold");
    Gold.setItemMeta(GoldMeta);
    DiamondMeta.setLore(getLore(p, "Diamond"));
    DiamondMeta.setDisplayName(ChatColor.AQUA + "§lDiamond");
    Diamond.setItemMeta(DiamondMeta);
    EmeraldMeta.setLore(getLore(p, "Emerald"));
    EmeraldMeta.setDisplayName(ChatColor.GREEN + "§lEmerald");
    Emerald.setItemMeta(EmeraldMeta);

    spelerMeta.setDisplayName(ChatColor.DARK_GRAY + "§lSpeler");
    speler.setItemMeta(spelerMeta);
    KillStreakerMeta.setDisplayName(ChatColor.WHITE + "§lKillStreaker");
    KillStreaker.setItemMeta(KillStreakerMeta);
    SuicideMeta.setDisplayName(ChatColor.BLACK + "§lSuicider");
    Suicide.setItemMeta(SuicideMeta);
    WitchMeta.setDisplayName(ChatColor.BLUE + "§lWitch");
    Witch.setItemMeta(WitchMeta);
    VampireMeta.setDisplayName(ChatColor.GRAY + "§lVampire");
    Vampire.setItemMeta(VampireMeta);
    FrozenMeta.setDisplayName(ChatColor.DARK_RED + "§lFrozen");
    Frozen.setItemMeta(FrozenMeta);
    PikachuMeta.setDisplayName(ChatColor.GOLD + "§lPikachu");
    Pikachu.setItemMeta(PikachuMeta);
    StrikerMeta.setDisplayName(ChatColor.AQUA + "§lStriker");
    Striker.setItemMeta(StrikerMeta);
    KniveMeta.setDisplayName(ChatColor.GREEN + "§lKnive runner");
    Knive.setItemMeta(KniveMeta);

    inv.setItem(0, speler);
    inv.setItem(1, Wood);
    inv.setItem(2, Coal);
    inv.setItem(3, Lapis);
    inv.setItem(4, Iron);
    inv.setItem(5, Redstone);
    inv.setItem(6, Gold);
    inv.setItem(7, Diamond);
    inv.setItem(8, Emerald);
    inv.setItem(9, KillStreaker);
    /*inv.setItem(10, Suicide);
    inv.setItem(11, Witch);
    inv.setItem(12, Vampire);
    inv.setItem(13, Frozen);
    inv.setItem(14, Pikachu);
    inv.setItem(15, Striker);
    inv.setItem(16, Knive);
    inv.setItem(17, whiteGlass);
    */
    p.openInventory(inv);
  }
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static List<String> getLore(Player p, String kit) {
    List array = new ArrayList();
    if (hasKit(p, kit))
      array.add(ChatColor.GREEN + "§lBeschikbaar!");
    else {
      array.add(ChatColor.DARK_RED + "§lNiet beschikbaar!!");
    }
    return array;
  }
  public static boolean hasKit(Player p, String kit) {
      if (kit.equalsIgnoreCase("KillStreaker")) {
    	  if (p.hasPermission("KitPvP.KillStreaker")) {
          return true;
    	  }else {
    		  return false;
    	  }
        }
    if ((pl.playerInGroup("world", p, "Emerald")) || (pl.playerInGroup("World", p, "Developer")) || (pl.playerInGroup("World", p, "cock")) || (pl.playerInGroup("World", p, "AdminTeamLeider")) || (pl.playerInGroup("World", p, "Youtube")))
      return true;
    if ((pl.playerInGroup("world", p, "Diamond")) || (pl.playerInGroup("World", p, "Adminplus"))) {
      if (!kit.equalsIgnoreCase("Emerald")) {
        return true;
      }
      return false;
    }
    if ((pl.playerInGroup("world", p, "Gold")) || (pl.playerInGroup("World", p, "Admin")) || (pl.playerInGroup("World", p, "Adminplus"))) {
      if ((!kit.equalsIgnoreCase("Emerald")) && (!kit.equalsIgnoreCase("Diamond"))) {
        return true;
      }
      return false;
    }
    if ((pl.playerInGroup("world", p, "Redstone")) || (pl.playerInGroup("World", p, "ModeratorTeamLeider")) || (pl.playerInGroup("World", p, "Admin")) || (pl.playerInGroup("World", p, "Adminplus"))) {
      if ((!kit.equalsIgnoreCase("Emerald")) && (!kit.equalsIgnoreCase("Diamond")) && (!kit.equalsIgnoreCase("Gold"))) {
        return true;
      }
      return false;
    }
    if ((pl.playerInGroup("world", p, "Iron")) || (pl.playerInGroup("World", p, "Moderatorplus")) || (pl.playerInGroup("World", p, "ModeratorTeamLeider")) || (pl.playerInGroup("World", p, "Admin")) || (pl.playerInGroup("World", p, "Adminplus"))) {
      if ((!kit.equalsIgnoreCase("Emerald")) && (!kit.equalsIgnoreCase("Diamond")) && (!kit.equalsIgnoreCase("Gold")) && (!kit.equalsIgnoreCase("Redstone"))) {
        return true;
      }
      return false;
    }
    if ((pl.playerInGroup("world", p, "Lapis")) || (pl.playerInGroup("World", p, "Moderator")) || (pl.playerInGroup("World", p, "moderatorplus")) || (pl.playerInGroup("World", p, "ModeratorTeamLeider")) || (pl.playerInGroup("World", p, "Admin")) || (pl.playerInGroup("World", p, "Adminplus") || (pl.playerInGroup("World", p, "bouwteam")))) {
      if ((kit.equalsIgnoreCase("Wood")) || (kit.equalsIgnoreCase("Coal")) || (kit.equalsIgnoreCase("Lapis"))) {
        return true;
      }
      return false;
    }
    if ((pl.playerInGroup("world", p, "Coal")) || (pl.playerInGroup("World", p, "HelperTeamLeider")) || (pl.playerInGroup("World", p, "Moderator")) || (pl.playerInGroup("World", p, "Moderatorplus")) || (pl.playerInGroup("World", p, "ModeratorTeamLeider")) || (pl.playerInGroup("World", p, "Admin")) || (pl.playerInGroup("World", p, "Adminplus") || (pl.playerInGroup("World", p, "bouwteam")))) {
      if ((kit.equalsIgnoreCase("Wood")) || (kit.equalsIgnoreCase("Coal"))) {
        return true;
      }
      return false;
    }
    if ((pl.playerInGroup("world", p, "Wood")) || (pl.playerInGroup("World", p, "Helperplus")) || (pl.playerInGroup("World", p, "HelperTeamLeider")) || (pl.playerInGroup("World", p, "Moderator")) || (pl.playerInGroup("World", p, "Moderatorplus")) || (pl.playerInGroup("World", p, "ModeratorTeamLeider")) || (pl.playerInGroup("World", p, "Admin")) || (pl.playerInGroup("World", p, "Adminplus") || (pl.playerInGroup("World", p, "bouwteam")))) {
      if (kit.equalsIgnoreCase("Wood")) {
        return true;
      }
      return false;
    }
    return false;
  }
}