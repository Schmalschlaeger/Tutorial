package cock.topia.tvg.Listeners;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import cock.topia.tvg.Main;
import cock.topia.tvg.Inventory.Scoreboard;
import cock.topia.tvg.Kits.kits;

public class JoinListener implements Listener{
	
	Main plugin;
	public JoinListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		Scoreboard b = new Scoreboard(plugin);
		b.scoreboard(e.getPlayer());		
		final Player pl = e.getPlayer();
		sendActionBar(pl, "Play.TeveelGevraagd.nl");
	    pl.setMaxHealth(30);
	    pl.setHealth(pl.getMaxHealth());
	    pl.setGameMode(GameMode.ADVENTURE);
	    
		final Scoreboard sb = new Scoreboard(plugin);
	    
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
	    {
	      @SuppressWarnings("static-access")
		public void run() {
	        sb.scoreboard(e.getPlayer());

	        plugin.data.addDefault(pl.getName() + ".lastkit", "none");
	        plugin.data.addDefault(pl.getName()  + ".killstreaks", Integer.valueOf(0));
	        plugin.saveCustomConfig();
	        plugin.getConfig().set(pl.getName()  + ".killstreak", Integer.valueOf(0));
	        plugin.data.options().copyDefaults(true);
	        plugin.saveConfig();
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "speed walk 4 " + pl.getName());
	        if (!plugin.data.getString(pl.getName() + ".lastkit").equals("none")) {
	          if (plugin.data.getString(pl.getName() + ".lastkit").equals("Wood")) {
	            kits.Wood(pl);
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16385 name:&6&lRegeneration lore:&7SoulBound");
	          } else if (plugin.data.getString(pl.getName() + ".lastkit").equals("Coal")) {
	        	  kits.Coal(pl);
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16454 name:&8&lNight_Vision lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16386 name:&8&lSpeed lore:&7SoulBound");
	          } else if (plugin.data.getString(pl.getName() + ".lastkit").equals("Lapis")) {
	        	  kits.Lapis(pl);
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16427 name:&3&lLeaping lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&3&lSpeed lore:&7SoulBound");
	          } else if (plugin.data.getString(pl.getName() + ".lastkit").equals("Iron")) {
	        	  kits.Iron(pl);
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	          } else if (plugin.data.getString(pl.getName() + ".lastkit").equals("Redstone")) {
	        	  kits.Redstone(pl);
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&4&lSpeed lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&4&lWater_Breathing lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
	          } else if (plugin.data.getString(pl.getName() + ".lastkit").equals("Gold")) {
	        	  kits.Gold(pl);
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&6&lSpeed lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16388 name:&6&lpoison lore:&7SoulBound");
	          } else if (plugin.data.getString(pl.getName() + ".lastkit").equals("Diamond")) {
	        	  kits.Diamond(pl);
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&3&lWater_Breathing lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16389 name:&3&lHealing lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16387 name:&3&lFire_resistance lore:&7SoulBound");
	          } else if (plugin.data.getString(pl.getName() + ".lastkit").equals("Emerald")) {
	        	  kits.Emerald(pl);
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&2&lSpeed lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
	          } else if (plugin.data.getString(pl.getName() + ".lastkit").equals("KillStreaker")) {
	        	  kits.KillStreaker(pl);
	        	  pl.setMaxHealth(24);
	        	  pl.setHealth(pl.getMaxHealth());
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&2&lSpeed lore:&7SoulBound");
	          } else {
	        	  kits.Speler(pl);
	            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	          }

	        }
	        else if ((plugin.playerInGroup("World", e.getPlayer(), "Wood")) || (plugin.playerInGroup("World", e.getPlayer(), "Helper+"))) {
	          kits.Wood(pl);
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16385 name:&6&lRegeneration lore:&7SoulBound");
	        } else if ((plugin.playerInGroup("World", e.getPlayer(), "Coal")) || (plugin.playerInGroup("World", e.getPlayer(), "Helper+"))) {
	          kits.Coal(pl);
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16454 name:&8&lNight_Vision lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16386 name:&8&lSpeed lore:&7SoulBound");
	        } else if ((plugin.playerInGroup("World", e.getPlayer(), "Lapis")) || (plugin.playerInGroup("World", e.getPlayer(), "Helper+"))) {
	        	kits.Lapis(pl);
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16427 name:&3&lLeaping lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&3&lSpeed lore:&7SoulBound");
	        } else if ((plugin.playerInGroup("World", e.getPlayer(), "Iron")) || (plugin.playerInGroup("World", e.getPlayer(), "Helper+"))) {
	        	kits.Iron(pl);
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	        } else if ((plugin.playerInGroup("World", e.getPlayer(), "Redstone")) || (plugin.playerInGroup("World", e.getPlayer(), "Helper+"))) {
	        	kits.Redstone(pl);
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&4&lSpeed lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&4&lWater_Breathing lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
	        } else if ((plugin.playerInGroup("World", e.getPlayer(), "Gold")) || (plugin.playerInGroup("World", e.getPlayer(), "Helper+"))) {
	        	kits.Gold(pl);
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&6&lSpeed lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16388 name:&6&lpoison lore:&7SoulBound");
	        } else if ((plugin.playerInGroup("World", e.getPlayer(), "Diamond")) || (plugin.playerInGroup("World", e.getPlayer(), "Helper+"))) {
	        	kits.Diamond(pl);
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&3&lWater_Breathing lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16389 name:&3&lHealing lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16387 name:&3&lFire_resistance lore:&7SoulBound");
	        } else if ((plugin.playerInGroup("World", e.getPlayer(), "Emerald")) || (plugin.playerInGroup("World", e.getPlayer(), "Helper+"))) {
	        	kits.Emerald(pl);
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16450 name:&2&lSpeed lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
	        } else {
	        	kits.Speler(pl);
	          plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + pl.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	        }
		    Location loc4 = new Location(pl.getServer().getWorld("world"), 149.5D, 165.5D, 457.5D);
		    loc4.setPitch(10.0F);
		    loc4.setYaw(90.0F);
		    pl.teleport(loc4);
	      }
	    }
	    , 15L);
	    
	}
	
	public static void b(Player player, String message){
		CraftPlayer craftPlayer = (CraftPlayer) player;
		PlayerConnection con = craftPlayer.getHandle().playerConnection;
        IChatBaseComponent cbc = ChatSerializer.a(message);
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);
        con.sendPacket(ppoc);
    }
	
	public void sendActionBar(final Player p, final String s) {
		new BukkitRunnable() {
			int i = 1;
			@Override
			public void run() {
				if (!p.isOnline()) {
					cancel();
				}
				if (i == 1) {
					b(p, ChatColor.RED + "" + ChatColor.BOLD + s);
					i = 2;
				}else if (i == 2) {
					b(p, ChatColor.BLUE + "" + ChatColor.BOLD + s);
					i = 1;
				}
			}
		}.runTaskTimer(plugin, 0, 20);
	}

}
