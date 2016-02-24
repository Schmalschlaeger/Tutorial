package TWS.Mobs;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZombieListener implements Listener {

	private static ArrayList<String> enderman = new ArrayList<String>();
	private static ArrayList<String> skeleton = new ArrayList<String>();
	private static ArrayList<String> witch = new ArrayList<String>();
	private static ArrayList<String> zombie = new ArrayList<String>();
	
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
	  
	  @EventHandler(priority=EventPriority.HIGHEST)
	  public void onentiryDeath(EntityDeathEvent e) {
		  LivingEntity eventEntity = e.getEntity();
	      EntityType eventEntityType = eventEntity.getType();
	      
	      if (eventEntityType == EntityType.ZOMBIE) {
	    	  e.setDroppedExp(0);
	    	  e.getDrops().clear();e.getDrops().add(new ItemStack(Material.BONE));
	      }
		
	  }

	@EventHandler
	public void onZombieSpawn(CreatureSpawnEvent e) {
		boolean plg = (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM);
		for (EntityType bt : blocked) {
			if ((e.getEntityType() == bt) && (!plg)) {
				e.setCancelled(true);
			}
		}
		if (e.getEntityType() == EntityType.ZOMBIE) {
		equipZombie((Zombie)e.getEntity());
	
	}
		if (e.getEntity() instanceof Enderman) {
			enderman.add("Void Walker");

			int random = new Random().nextInt(enderman.size());
			String name = enderman.get(random);

			e.getEntity().setCustomName(ChatColor.GOLD + name);
			e.getEntity().setCustomNameVisible(true);
			e.getEntity().setRemoveWhenFarAway(true);
		} else if (e.getEntity() instanceof Witch) {
			witch.add("Dark Sorceress");

			int random = new Random().nextInt(witch.size());
			String name = witch.get(random);

			e.getEntity().setCustomName(ChatColor.GOLD + name);
			e.getEntity().setCustomNameVisible(true);
		} else if (e.getEntity() instanceof Skeleton) {
			skeleton.add("Bone Hunter");
			skeleton.add("Dog Bone With A Flavour");

			int random = new Random().nextInt(skeleton.size());
			String name = skeleton.get(random);

			e.getEntity().setCustomName(ChatColor.GOLD + name);
			e.getEntity().setCustomNameVisible(true);
		} else if (e.getEntity() instanceof Zombie) {
			zombie.add("Walker");

			int random = new Random().nextInt(zombie.size());
			String name = zombie.get(random);

			e.getEntity().setCustomName(ChatColor.GOLD + name);
			e.getEntity().setCustomNameVisible(true);
		}
	}

}
