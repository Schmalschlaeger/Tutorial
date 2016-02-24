package Accesoiries;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Balloon implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        final Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("balloon")) {
                    ItemStack i;
                    final Location loc1 = p.getLocation();
            loc1.setY(loc1.getY() + 3);
                   
            final Bat bat = (Bat)p.getWorld().spawnEntity(loc1, EntityType.CHICKEN);
            
            i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
            SkullMeta meta = (SkullMeta)i.getItemMeta();
            meta.setOwner(p.getName());
            i.setItemMeta(meta);

           // bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 0));
            bat.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2147483647, 0));
            bat.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2147483647, 1));
            bat.setMaxHealth(999999.0);
            bat.setHealth(999999.0);
            return true;
      }
		return false;
	}

}
