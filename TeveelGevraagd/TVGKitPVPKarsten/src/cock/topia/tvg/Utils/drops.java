package cock.topia.tvg.Utils;

import java.util.Random;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class drops implements Listener
{
  @SuppressWarnings("deprecation")
public static ItemStack r()
  {
    Random r = new Random();
    int rn = r.nextInt(100);
    ItemStack stack = null;
    if ((rn > 0) && (rn != -1)) {
      if (rn < 10) {
        Potion potionSpeed = new Potion(PotionType.SPEED, 1, true);
        ItemStack speed = potionSpeed.toItemStack(1);
        ItemMeta sm = speed.getItemMeta();
        sm.setDisplayName(ChatColor.WHITE + "§lSpeed potion");
        speed.setItemMeta(sm);
        stack = speed;
      } else if (rn < 20) {
        Potion potionHealing = new Potion(PotionType.INSTANT_HEAL, 1, true);
        ItemStack healing = potionHealing.toItemStack(1);
        ItemMeta sm = healing.getItemMeta();
        sm.setDisplayName(ChatColor.WHITE + "§lHealing potion");
        healing.setItemMeta(sm);
        stack = healing;
      } else if (rn < 30) {
        Potion regenp = new Potion(PotionType.REGEN, 1, true, true);
        ItemStack regen = regenp.toItemStack(1);
        ItemMeta sm = regen.getItemMeta();
        sm.setDisplayName(ChatColor.WHITE + "§lRegeneration potion");
        regen.setItemMeta(sm);
        stack = regen;
      } else if (rn < 40) {
        Potion firep = new Potion(PotionType.FIRE_RESISTANCE, 1, true);
        ItemStack fire = firep.toItemStack(1);
        ItemMeta sm = fire.getItemMeta();
        sm.setDisplayName(ChatColor.WHITE + "§lFire resistance potion");
        fire.setItemMeta(sm);
        stack = fire;
      } else if (rn < 50) {
        Potion nightp = new Potion(PotionType.NIGHT_VISION, 1, true);
        ItemStack night = nightp.toItemStack(1);
        ItemMeta sm = night.getItemMeta();
        sm.setDisplayName(ChatColor.WHITE + "§lNight vision potion");
        night.setItemMeta(sm);
        stack = night;
      } else if (rn < 60) {
        Potion jumpp = new Potion(PotionType.JUMP, 1, true);
        ItemStack jump = jumpp.toItemStack(1);
        ItemMeta sm = jump.getItemMeta();
        sm.setDisplayName(ChatColor.WHITE + "§lJump potion");
        jump.setItemMeta(sm);
        stack = jump;
      } else if (rn < 70) {
        ItemStack apple = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta sm = apple.getItemMeta();
        sm.setDisplayName(ChatColor.WHITE + "§lGolden apple");
        apple.setItemMeta(sm);
        stack = apple;
      } else if (rn == 71) {
        Potion jumpp = new Potion(PotionType.INVISIBILITY, 1, true);
        ItemStack jump = jumpp.toItemStack(1);
        ItemMeta sm = jump.getItemMeta();
        sm.setDisplayName(ChatColor.WHITE + "§lInvisibility potion");
        jump.setItemMeta(sm);
        stack = jump;
      } else {
        stack = new ItemStack(Material.AIR);
      }
    }
    return stack;
  }
}