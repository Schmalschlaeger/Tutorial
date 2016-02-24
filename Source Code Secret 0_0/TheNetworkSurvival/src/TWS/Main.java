package TWS;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import TWS.MessageManager.MsgType;
import TWS.Mobs.ZombieListener;
import TWS.Tasks.Ranks;
import TWS.VipSystem.Menu;
import TWS.VipSystem.VipCommands;

public class Main extends JavaPlugin implements Listener{

	private static final Logger log = Logger.getLogger("Minecraft");

	public static String OwnerPerms = "network.owner";
	public static String BuilderPerms = "network.builder";
	public static String noPerms = ChatColor.RED + "Sorry, but you dont have permissions!";

	public static Economy econ = null;
	
	ServerEvents h = new ServerEvents(this);

	@SuppressWarnings("unused")
	public void onEnable() {
		StartTimer();
		BukkitTask ranks = new Ranks(this).runTaskTimer(this, 20, 100);
		startCountdown();
		if (!setupEconomy()) {
			log.severe(String.format(
					"[%s] - Disabled due to no Vault dependency found!",
					getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		Bukkit.getServer().getPluginManager()
				.registerEvents(new ZombieListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new Menu(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ServerEvents(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Ranks(this), this);

		getServer().getMessenger().registerOutgoingPluginChannel(this,
				"BungeeCord");

		getCommand("zombie").setExecutor(new Boss());
		getCommand("user").setExecutor(new Commands(this));
		getCommand("hub").setExecutor(new Commands(this));
		getCommand("event").setExecutor(new Events(this));
		getCommand("shop").setExecutor(new Commands(this));
		getCommand("vip").setExecutor(new VipCommands());
		getCommand("ragequit").setExecutor(new Commands(this));
		getCommand("mainon").setExecutor(new Maintenance(this));
		getCommand("mainoff").setExecutor(new Maintenance(this));
		
		//h.spawnVillagers(202.5, 88, -100.5);
		//h.spawnVillagers(202.5, 88, -96.5);
		//h.spawnVillagers(198.5, 88, -100.5);
		//h.spawnVillagers(198.5, 88, -96.5); 
	} 
	
	public void onDisable() {
		 for(World world : Bukkit.getWorlds()) {
		        for(Entity e : world.getEntities()) {
		            // You can change this to see if it's a CraftLivingEntity and if so, if it's CraftLivingEntity#getHandle() is an instance of your custom mob.
		            // Alternatively you can change this to check if the entity has any fixed metadata set (that you would set on spawn) to see if it's a custom mob.
		            if(e.getType() == EntityType.ZOMBIE) {
		                e.remove();
		            }
		        }
		    }
		
	}
	
	public void startCountdown() {
		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(this, new Runnable() {
					@SuppressWarnings("deprecation")
					@Override
					public void run() {
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (Menu.trailFire.contains(all)) {
								ParticleEffect.FLAME.display(all.getLocation(),
										0.2f, 0.5f, 0.2f, 0.1F, 80);
							
							}else if (Menu.trailCloud.contains(all)) {
								ParticleEffect.CLOUD.display(all.getLocation(),
										0.2f, 0.5f, 0.2f, 0.1F, 80);
								
							}
						}
					}
				}, 0, 2L);
	}

	public void StartTimer() {
		Bukkit.getServer().getScheduler()
				.scheduleSyncDelayedTask(this, new Runnable() {
					@Override
					public void run() {
						Bukkit.getServer()
								.broadcastMessage(
										MsgType.GRAY
												+ " Server is in BETA! Please report bugs and glitches!");
						Timer(); // Call the other timer
					}
				}, 1000); // After 50 seconds
	}

	public void Timer() {
		Bukkit.getServer().getScheduler()
				.scheduleSyncDelayedTask(this, new Runnable() {
					@Override
					public void run() {
						Bukkit.getServer()
								.broadcastMessage(
										MsgType.GRAY
												+ " We are working on a network server, hope you enjoyed :D");
						AnotherTimer();
					}
				}, 1000);
	}

	public void AnotherTimer() {
		Bukkit.getServer().getScheduler()
				.scheduleSyncDelayedTask(this, new Runnable() {
					@Override
					public void run() {
						Bukkit.getServer()
								.broadcastMessage(
										MsgType.GRAY
												+ " type /ragequit anywhere to ragequit the survival!");
						StartTimer();
					}
				}, 1000);
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

}
