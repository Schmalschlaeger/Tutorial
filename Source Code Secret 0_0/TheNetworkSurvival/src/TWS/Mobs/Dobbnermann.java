package TWS.Mobs;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import TWS.Main;

public class Dobbnermann implements Listener {

	static Main plugin;

	public Dobbnermann(Main m) {
		plugin = m;
	}

	private static ArrayList<Player> op = new ArrayList<Player>();

	public static void startCountDown() {
		Bukkit.broadcastMessage(ChatColor.GREEN + "BOSS Is nu aan :)");
		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(plugin, new Runnable() {
					@SuppressWarnings("deprecation")
					public void run() {
						for (Player p : Bukkit.getOnlinePlayers()) {
							op.clear();
							op.add(p);
						}

						Bukkit.getServer().broadcastMessage(
								ChatColor.RED + "A Boss has been spawned");
						if (!Bukkit.getOnlinePlayers().equals(
								Integer.valueOf(0))) {
							int random = new Random().nextInt(Bukkit
									.getOnlinePlayers().length);
							Bukkit.getServer().broadcastMessage(
									ChatColor.RED + "RN");
							Player player = Bukkit.getOnlinePlayers()[random];
							Random rx = new Random();
							Bukkit.getServer().broadcastMessage(
									ChatColor.RED + "RX");
							int x = rx.nextInt(20) + 1;
							Random rz = new Random();
							int z = rz.nextInt(20) + 1;
							Bukkit.getServer().broadcastMessage(
									ChatColor.RED + "RZ");
							Location nl = player.getLocation().add(x, 0.0D, z);
							Giant giant = (Giant) nl.getWorld().spawn(nl,
									Giant.class);
							giant.setCustomName(ChatColor.GOLD + "Zombie King");
							giant.setCustomNameVisible(true);
							giant.addPotionEffect(new PotionEffect(
									PotionEffectType.INCREASE_DAMAGE, 999999, 1));
							giant.addPotionEffect(new PotionEffect(
									PotionEffectType.HEALTH_BOOST, 999999, 1));
							giant.addPotionEffect(new PotionEffect(
									PotionEffectType.FIRE_RESISTANCE, 999999, 1));
							giant.addPotionEffect(new PotionEffect(
									PotionEffectType.REGENERATION, 999999, 1));
							giant.addPotionEffect(new PotionEffect(
									PotionEffectType.SPEED, 999999, 2));
							Zombie z1 = (Zombie) nl.getWorld().spawn(nl,
									Zombie.class);
							Zombie z2 = (Zombie) nl.getWorld().spawn(nl,
									Zombie.class);
							Zombie z3 = (Zombie) nl.getWorld().spawn(nl,
									Zombie.class);
							z1.setCustomName("Guard");
							z2.setCustomName("Guard");
							z3.setCustomName("Guard");
							z1.setCustomNameVisible(true);
							z2.setCustomNameVisible(true);
							z3.setCustomNameVisible(true);
							z1.addPotionEffect(new PotionEffect(
									PotionEffectType.SPEED, 999999, 2));
							z2.addPotionEffect(new PotionEffect(
									PotionEffectType.SPEED, 999999, 2));
							z3.addPotionEffect(new PotionEffect(
									PotionEffectType.SPEED, 999999, 2));
						}
					}
				}, 120, 120);
	}

	@EventHandler
	public void onMobDamage(EntityDamageEvent e) {
		if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
			if ((e.getEntity().getType() == EntityType.ZOMBIE)
					|| (e.getEntity().getType() == EntityType.GIANT)) {
				e.setCancelled(true);
			}

			return;
		}
	}

}
