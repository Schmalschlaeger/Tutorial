package Main;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class AbilitySummon implements Listener {	
 
    public void spawnWolf(Player p, Location loc) {
        Wolf wolf = (Wolf) p.getWorld().spawnEntity(loc, EntityType.WOLF);       
        
        // Just to make sure it's a normal wolf.
        wolf.setAdult();
        wolf.setTamed(true);
        wolf.setOwner(p);
 
        // We don't want extra wolves.
        wolf.setBreed(false);
 
        // Clarify the owner.
        wolf.setCustomName(ChatColor.YELLOW + p.getName() + "'s Wolf");
        wolf.setCustomNameVisible(true);
 
        // Let's have a little bit of fun!
        wolf.setCollarColor(randomizeColor());
 
        // Misc. settings
        wolf.setHealth(10.0);
        wolf.setCanPickupItems(false);
        
        p.getInventory().removeItem(new ItemStack(Material.BONE));
    }
    
    public void spawnCustomBlaze(Player p, Location loc) {
    	Blaze blaze = (Blaze) p.getWorld().spawnEntity(loc, EntityType.BLAZE);       
        
    	blaze.setLeashHolder(p);
    	blaze.setCustomName(ChatColor.YELLOW + p.getName() + "'s Blaze");
    	blaze.setCustomNameVisible(true);
    	
    	blaze.setTarget(null);
    	blaze.setMaxHealth(20.0);
    	
    	p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD));
    }
 
    // Check if the player's item is a bone.
    public boolean isBone(Player p) {
        return (p.getItemInHand().getType() == Material.BONE);
    }
    
    public boolean isBlazeRod(Player p) {
        return (p.getItemInHand().getType() == Material.BLAZE_ROD);
    }
 
    // Getting the data is deprecated.
    @SuppressWarnings("deprecation")
    /*
    * Each Dyecolor (Black, Blue, Red, etc..) has it's own byte attached to it.
    */
    public static DyeColor randomizeColor() {
        Random random = new Random();
        int color = random.nextInt(16);
 
        for (int i = 0; i < 16; i++) {
            if (i == color)
                return DyeColor.getByData((byte) i);
            continue;
        }
        return null;
    }
}
