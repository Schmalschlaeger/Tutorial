package mainp.thenetwork.survival;

import java.util.Random;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomZombies {
	
	  public static final EntityType[] blocked = { 
		    EntityType.SKELETON, EntityType.CREEPER, EntityType.SPIDER, EntityType.ENDERMAN, EntityType.GHAST, 
		    EntityType.SILVERFISH, EntityType.SLIME, EntityType.SQUID, EntityType.PIG_ZOMBIE, EntityType.MAGMA_CUBE, 
		    EntityType.CAVE_SPIDER, EntityType.BLAZE, EntityType.OCELOT, EntityType.BAT, EntityType.WITCH, 
		    EntityType.WOLF, EntityType.MUSHROOM_COW, EntityType.HORSE };
	  
	  public static final EntityType[] animals = { 
		    EntityType.SHEEP, EntityType.PIG, EntityType.COW, EntityType.CHICKEN };
	  
	  public static void equipZombie(Zombie zombie) {
		  zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, new Random().nextInt(5) - 1));
		  zombie.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2147483647, new Random().nextInt(2) - 1));
		  zombie.setMaxHealth(100.0);
		  zombie.setHealth(20.0);
		  if ((new Random().nextInt(7) < 1)) {
			  zombie.setBaby(true);
			  zombie.setMaxHealth(50.0);
			  zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, new Random().nextInt(7) - 1));
		  }
	  }
	  
	  public static void addDrops(EntityDeathEvent e, ItemStack mat) {
		  if (!e.getDrops().contains(mat)) {
		       e.getDrops().add(mat);
		  }
	  }
	  
	  public void removeDrops(EntityDeathEvent e, ItemStack mat) {
		  if (e.getDrops().contains(mat)) {
			  e.getDrops().remove(mat);
		  }
	  }

}
