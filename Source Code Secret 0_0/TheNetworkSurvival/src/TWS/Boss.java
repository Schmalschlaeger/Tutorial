package TWS;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Boss implements Listener, CommandExecutor {

	public void spawnZombie(Player p) {
		Location spawn = p.getLocation();
		Zombie z = (Zombie) p.getWorld().spawnEntity(spawn, EntityType.ZOMBIE);
		EntityEquipment ee = z.getEquipment();
		ee.setHelmet(new ItemStack(Material.LEATHER_HELMET, 1));
		z.setTarget(p);
		z.setCustomName(ChatColor.RED + "Zombie Helper");
		z.setCustomNameVisible(true);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		final Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("zombie")) {
			Location loc = new Location(p.getWorld(), 220, 105, -66);
			Zombie zombie = (Zombie) loc.getWorld().spawn(loc, Zombie.class);

			zombie.setCustomName(ChatColor.RED + "" + ChatColor.BOLD
					+ "BOSS ZOMBIE");
			zombie.setCustomNameVisible(true);
			zombie.setRemoveWhenFarAway(true);
			zombie.setMaxHealth(500.0);
			zombie.setHealth(500.0);
			zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
					99999, 3));
			zombie.addPotionEffect(new PotionEffect(
					PotionEffectType.REGENERATION, 99999, 1));

			Entity target = null;

			for (Entity nearbyPlayer : zombie.getNearbyEntities(500, 500, 500)) {
				if (nearbyPlayer instanceof Player) {
					if (target == null) {
						target = nearbyPlayer;
						zombie.setTarget((LivingEntity) nearbyPlayer);
						((Player) nearbyPlayer)
								.chat(ChatColor.GOLD
										+ "A BOSS ZOMBIE has spawned close to you! Watch out and be carefull!");
						break;
					}
				}
			}
		}
		return false;
	}

}
