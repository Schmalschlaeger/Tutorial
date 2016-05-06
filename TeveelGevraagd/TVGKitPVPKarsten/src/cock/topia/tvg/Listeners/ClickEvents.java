package cock.topia.tvg.Listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import cock.topia.tvg.Main;
import cock.topia.tvg.Inventory.ItemShop;
import cock.topia.tvg.Inventory.Scoreboard;
import cock.topia.tvg.Kits.DonatorKits;
import cock.topia.tvg.Kits.kits;
import cock.topia.tvg.Utils.MoneyManager;
import cock.topia.tvg.Utils.drops;

public class ClickEvents implements Listener {
	
	List<String> Pl = new ArrayList<String>();
	
	Main pl;
	public ClickEvents(Main plugin) {
		this.pl = plugin;
	}
	
	  @SuppressWarnings({ "static-access", "deprecation" })
	@EventHandler
	  public void onInventoryClick(InventoryClickEvent event) {
	    if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Donateur Kits")){
	      return;
	  }
	    Player p = (Player)event.getWhoClicked();
	    event.setCancelled(true);
	    if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType() == Material.AIR) || (!event.getCurrentItem().hasItemMeta())) {
	      return;
	    }
	    p.closeInventory();
	    switch (event.getCurrentItem().getType().getId()) {
	    case 1:
	    	p.getInventory().clear();
	        p.getInventory().setArmorContents(null);
	    	p.setMaxHealth(30);
	        p.setHealth(p.getMaxHealth());
	        pl.data.set(p.getName() + ".lastkit", "Speler");
	      kits.Speler(p);
	      pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	      break;
	    case 5:
	      if (DonatorKits.hasKit(p, "Wood")) {
	        pl.data.set(p.getName() + ".lastkit", "Wood");
	        p.getInventory().clear();
	        p.getInventory().setArmorContents(null);
	        p.setMaxHealth(30);
	        p.setHealth(p.getMaxHealth());
	        event.setCancelled(true);
	        kits.Wood(p);
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&6&lRegeneration lore:&7SoulBound");
	      } else {
	        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Je hebt deze kit niet!");
	      }
	      break;
	    case 173:
	      event.setCancelled(true);
	      if (DonatorKits.hasKit(p, "Coal")) {
	        pl.data.set(p.getName() + ".lastkit", "Coal");
	        p.getInventory().clear();
	        p.getInventory().setArmorContents(null);
	        p.setMaxHealth(30);
	        p.setHealth(p.getMaxHealth());
	        event.setCancelled(true);
	        kits.Coal(p);
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16454 name:&8&lNight_Vision lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16386 name:&8&lSpeed lore:&7SoulBound");
	      }
	      else {
	        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Je hebt deze kit niet!");
	      }
	      break;
	    case 22:
	      event.setCancelled(true);
	      if (DonatorKits.hasKit(p, "Lapis")) {
	        pl.data.set(p.getName() + ".lastkit", "Lapis");
	        p.getInventory().clear();
	        p.getInventory().setArmorContents(null);
	        p.setMaxHealth(30);
	        p.setHealth(p.getMaxHealth());
	        event.setCancelled(true);
	        kits.Lapis(p);
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16427 name:&3&lLeaping lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&3&lSpeed lore:&7SoulBound");
	      }
	      else {
	        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Je hebt deze kit niet!");
	      }
	      break;
	    case 42:
	      event.setCancelled(true);
	      if (DonatorKits.hasKit(p, "Iron")) {
	        pl.data.set(p.getName() + ".lastkit", "Iron");
	        p.getInventory().clear();
	        p.getInventory().setArmorContents(null);
	        p.setMaxHealth(30);
	        p.setHealth(p.getMaxHealth());
	        event.setCancelled(true);
	        kits.Iron(p);
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	      }
	      else {
	        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Je hebt deze kit niet!");
	      }
	      break;
	    case 152:
	      event.setCancelled(true);
	      if (DonatorKits.hasKit(p, "Redstone"))
	      {
	        p.getInventory().clear();
	        p.getInventory().setArmorContents(null);
	        p.setMaxHealth(30);
	        p.setHealth(p.getMaxHealth());
	        event.setCancelled(true);
	        kits.Redstone(p);
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&4&lSpeed lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&4&lWater_Breathing lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
	        pl.data.set(p.getName() + ".lastkit", "Redstone");
	      } else {
	        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Je hebt deze kit niet!");
	      }
	      break;
	    case 41:
	      event.setCancelled(true);
	      if (DonatorKits.hasKit(p, "Gold")) {
	        p.getInventory().clear();
	        p.getInventory().setArmorContents(null);
	        p.setMaxHealth(30);
	        p.setHealth(p.getMaxHealth());
	        event.setCancelled(true);
	        kits.Gold(p);
	        pl.data.set(p.getName() + ".lastkit", "Gold");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&6&lSpeed lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16388 name:&6&lpoison lore:&7SoulBound");
	      } else {
	        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Je hebt deze kit niet!");
	      }
	      break;
	    case 57:
	      event.setCancelled(true);
	      if (DonatorKits.hasKit(p, "Diamond")) {
	        p.getInventory().clear();
	        p.getInventory().setArmorContents(null);
	        p.setMaxHealth(30);
	        p.setHealth(p.getMaxHealth());
	        event.setCancelled(true);
	        kits.Diamond(p);
	        pl.data.set(p.getName() + ".lastkit", "Diamond");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&3&lWater_Breathing lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&3&lHealing lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16387 name:&3&lFire_resistance lore:&7SoulBound");
	      } else {
	        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Je hebt deze kit niet!");
	      }
	      break;
	    case 133:
	      event.setCancelled(true);
	      if (DonatorKits.hasKit(p, "Emerald")) {
	        p.getInventory().clear();
	        p.getInventory().setArmorContents(null);
	        event.setCancelled(true);
	        p.setMaxHealth(30);
	        p.setHealth(p.getMaxHealth());
	        kits.Emerald(p);
	        pl.data.set(p.getName() + ".lastkit", "Emerald");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&2&lSpeed lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
	      } else {
	        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Je hebt deze kit niet!");
	      }
	      break;
	    case 351:
	    	 event.setCancelled(true);
	    	if (DonatorKits.hasKit(p, "KillStreaker")) {
	    		p.getInventory().clear();
	    		p.getInventory().setArmorContents(null);
	    		p.setMaxHealth(24);
	    		p.setHealth(p.getMaxHealth());
	    		 pl.data.set(p.getName() + ".lastkit", "KillStreaker");
	    		 
	    		 kits.KillStreaker(p);
	    		 pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&2&lSpeed lore:&7SoulBound");
	    	}else {
	    		p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Je hebt deze kit niet!");
	    	}
	    	break;
	    }

	    pl.saveCustomConfig();
	    for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
	  }
	  
	  @EventHandler
	    public void onPlayerRegainHealth(EntityRegainHealthEvent event) {
	        if(event.getRegainReason() == RegainReason.SATIATED || event.getRegainReason() == RegainReason.REGEN)
	            event.setAmount(3.0D);
	    }
	  
	  @SuppressWarnings("deprecation")
	    @EventHandler
	    public void onConsume(PlayerItemConsumeEvent e) {
	        final Player player = e.getPlayer();
	 
	        if (e.getItem().getTypeId() == 373) {
	            Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(pl, new Runnable() {
	                public void run() {
	                    player.getInventory().remove(Material.getMaterial(374));
	                }
	            }, 5L);
	        }
	    }
	
	  @EventHandler
	  public void Villager(PlayerInteractEntityEvent e)
	  {
	    Entity v = e.getRightClicked();
	    Location loc = new Location(pl.getServer().getWorld("world"), 137.0D, 165.0D, 469.0D);
	    Location loc2 = new Location(pl.getServer().getWorld("world"), 137.0D, 165.0D, 445.0D);

	    if ((v instanceof Villager))
	      if ((v.getLocation().getBlock().getLocation().equals(loc)) || (v.getLocation().getBlock().getLocation().equals(loc2))) {
	        DonatorKits.openGUI1(e.getPlayer());
	      } else if (v.getCustomName().equals("§8")) {
	        ItemShop.openGUI1(e.getPlayer());
	      }
	  }
	  
	  @SuppressWarnings("static-access")
	@EventHandler
	  public void onDeath(PlayerDeathEvent e)
	  {
		  MoneyManager mm = new MoneyManager(pl);
		  Scoreboard sb = new Scoreboard(pl);
	    Player killer = e.getEntity().getKiller();
	    e.getDrops().clear();
	    killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 2),  true);
	    Player p = e.getEntity();
	    if (pl.data.getString(killer.getName() + ".lastkit").equals("KillStreaker")) {
	    	if (killer.getMaxHealth() != 40) {
	    	killer.setMaxHealth(killer.getMaxHealth()+2);
	    	}
	    }
	    
	    if (!Pl.contains(p)) {
	    	e.getDrops().clear();
	    }else {
	    	e.getDrops().add(drops.r());
	    }
	    if ((killer instanceof Player))
	    {
	      if (!killer.getName().equals(p.getName())) {
	        pl.getConfig().set(killer.getName() + ".killstreak", Integer.valueOf(pl.getConfig().getInt(killer.getName() + ".killstreak") + 1));
	        pl.getConfig().set(p.getName() + ".killstreak", Integer.valueOf(0));
	        pl.saveConfig();
	        if ((pl.getConfig().getInt(killer.getName() + ".killstreak") == 5) || (pl.getConfig().getInt(killer.getName() + ".killstreak") == 35) || (pl.getConfig().getInt(killer.getName() + ".killstreak") == 45)) {
	          killer.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLDEN_APPLE, 1) });
	          pl.data.set(killer.getName() + ".killstreaks", Integer.valueOf(pl.data.getInt(killer.getName() + ".killstreaks") + 1));
	          mm.addMoney(killer, 100);
	          p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + " Je hebt 1 golden apple en 100 money gekregen!");
	          p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1));
	        } else if (pl.getConfig().getInt(killer.getName() + ".killstreak") == 10) {
	          killer.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLDEN_APPLE, 2) });
	          pl.data.set(killer.getName() + ".killstreaks", Integer.valueOf(pl.data.getInt(killer.getName() + ".killstreaks") + 1));
	          mm.addMoney(killer, 200);
	          p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + " Je hebt 2 golden apples en 200 money gekregen!");
	          p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1));
	        } else if (pl.getConfig().getInt(killer.getName() + ".killstreak") == 20) {
	          killer.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLDEN_APPLE, 2) });
	          pl.data.set(killer.getName() + ".killstreaks", Integer.valueOf(pl.data.getInt(killer.getName() + ".killstreaks") + 1));
	          mm.addMoney(killer, 300);
	          p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + " Je hebt 2 golden apples en 300 money gekregen!");
	          p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1));
	        } else if (pl.getConfig().getInt(killer.getName() + ".killstreak") == 50) {
	          killer.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLDEN_APPLE, 2) });
	          pl.data.set(killer.getName() + ".killstreaks", Integer.valueOf(pl.data.getInt(killer.getName() + ".killstreaks") + 1));
	          mm.addMoney(killer, 500);
	          p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + " Je hebt 2 golden apples en 500 money gekregen!");
	          p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1));
	        } else if (pl.getConfig().getInt(killer.getName() + ".killstreak") == 75) {
	          killer.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLDEN_APPLE, 3) });
	          pl.data.set(killer.getName() + ".killstreaks", Integer.valueOf(pl.data.getInt(killer.getName() + ".killstreaks") + 1));
	          mm.addMoney(killer, 1000);
	          p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + " Je hebt 3 golden apples en 1000 money gekregen!");
	          p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1));
	        } else if (pl.getConfig().getInt(killer.getName() + ".killstreak") == 100) {
	          killer.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLDEN_APPLE, 5) });
	          pl.data.set(killer.getName() + ".killstreaks", Integer.valueOf(pl.data.getInt(killer.getName() + ".killstreaks") + 1));
	          mm.addMoney(killer, 2000);
	          p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + " Je hebt 5 golden apples en 2000 money gekregen!");
	          p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1));
	        }
	        if (pl.playerInGroup("world", p, "Emerald")) {
	          mm.addMoney(killer, 150);
	          sb.scoreboard(killer);
	          killer.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + "Je hebt " + ChatColor.DARK_GREEN + p.getName() + ChatColor.GREEN + " vermoord! Je kreeg 150 money");
	        } else if (pl.playerInGroup("world", p, "Diamond")) {
	          mm.addMoney(killer, 100);
	          sb.scoreboard(killer);
	          killer.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + "Je hebt " + ChatColor.DARK_GREEN + p.getName() + ChatColor.GREEN + " vermoord! Je kreeg 100 money");
	        } else if (pl.playerInGroup("world", p, "Gold")) {
	          mm.addMoney(killer, 75);
	          sb.scoreboard(killer);
	          killer.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + "Je hebt " + ChatColor.DARK_GREEN + p.getName() + ChatColor.GREEN + " vermoord! Je kreeg 75 money");
	        } else {
	          mm.addMoney(killer, 50);
	          sb.scoreboard(killer);
	          killer.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + "Je hebt " + ChatColor.DARK_GREEN + p.getName() + ChatColor.GREEN + " vermoord! Je kreeg 50 money");
	        }

	        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.RED + "Je bent vermoord door " + ChatColor.DARK_RED + killer.getName());
	      }
	    }
	    else p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "§lTVG" + ChatColor.DARK_GRAY + "] " + ChatColor.RED + "Je bent dood gegaan.");

	    pl.saveCustomConfig();
	    Pl.remove(p.getName());
	    sb.scoreboard(p);
	  }
	  
	  @EventHandler
	  public void entityDamage(EntityDamageEvent e) {
	    if ((e.getEntity() instanceof Player)) {
	      Player p = (Player)e.getEntity();
	      Scoreboard sb = new Scoreboard(pl);
	      sb.scoreboard(p);
	      if (!this.Pl.contains(p.getName()))
	        e.setCancelled(true);
	    }
	  }
	  
	  @EventHandler
	  public void playerMove(final PlayerMoveEvent e) {
	    Location loc = new Location(pl.getServer().getWorld("world"), 131.0D, 166.0D, 456.0D);
	    Location loc2 = new Location(pl.getServer().getWorld("world"), 131.0D, 166.0D, 457.0D);
	    Location loc3 = new Location(pl.getServer().getWorld("world"), 131.0D, 166.0D, 458.0D);
	    
	    Location loctp1 = new Location(pl.getServer().getWorld("world"), 577.0D, 87.0D, 354.0D);
	    Location loctp2 = new Location(pl.getServer().getWorld("world"), 540.0D, 83.0D, 356.0D, -90, 0);
	    Location loctp3 = new Location(pl.getServer().getWorld("world"), 529.0D, 82.0D, 400.0D, -180, -0);
	    
	    Location loc5 = new Location(pl.getServer().getWorld("world"), 148.0D, 166.0D, 439.0D);
	    Location loc6 = new Location(pl.getServer().getWorld("world"), 149.0D, 166.0D, 439.0D);
	    Location loc7 = new Location(pl.getServer().getWorld("world"), 150.0D, 166.0D, 439.0D);
	    Location loc8 = new Location(pl.getServer().getWorld("world"), 148.0D, 166.0D, 475.0D);
	    Location loc9 = new Location(pl.getServer().getWorld("world"), 149.0D, 166.0D, 475.0D);
	    Location loc0 = new Location(pl.getServer().getWorld("world"), 150.0D, 166.0D, 475.0D);
	    
	    Player p = e.getPlayer();
	    if ((e.getPlayer().getLocation().getBlock().getLocation().equals(loc)) || (e.getPlayer().getLocation().getBlock().getLocation().equals(loc2)) || (e.getPlayer().getLocation().getBlock().getLocation().equals(loc3)) || (e.getPlayer().getLocation().getBlock().getLocation().equals(loc5)) || (e.getPlayer().getLocation().getBlock().getLocation().equals(loc6)) || (e.getPlayer().getLocation().getBlock().getLocation().equals(loc7)) || (e.getPlayer().getLocation().getBlock().getLocation().equals(loc8)) || (e.getPlayer().getLocation().getBlock().getLocation().equals(loc9)) || (e.getPlayer().getLocation().getBlock().getLocation().equals(loc0))) {
	      Random r;
	      r = new Random();
	      int randomi = r.nextInt(3) + 1;
	      if (randomi == 1) {
	    	  p.teleport(loctp1);
	      }else if (randomi == 2) {
	    	  p.teleport(loctp2);
	      }else if (randomi == 3) {
	    	  p.teleport(loctp3);
	      }else {
	    	  p.teleport(loctp1);
	      }
	      pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "speed walk 1 " + p.getName());
	      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
	        public void run() {
	          Pl.add(e.getPlayer().getName());
	        }
	      }
	      , 40L);
	    }
	    if ((p.getLocation().getY() < 5.0D) && 
	      (p.getHealth() > 0.5D))
	      p.setHealth(0.0D);
	  }
	  
	  @EventHandler
	  public void onRespawn(final PlayerRespawnEvent e) {
		  final Scoreboard sb = new Scoreboard(pl);
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable()
	    {
	      @SuppressWarnings("static-access")
		public void run() {
	        Player p = e.getPlayer();
	        sb.scoreboard(p);
	        pl.getConfig().set(p.getName() + ".killstreak", Integer.valueOf(0));
	        pl.saveConfig();
	        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "speed walk 4 " + p.getName());
	        if (!pl.data.getString(p.getName() + ".lastkit").equals("none")) {
	          if (pl.data.getString(p.getName() + ".lastkit").equals("Wood")) {
	            kits.Wood(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&6&lRegeneration lore:&7SoulBound");
	          } else if (pl.data.getString(p.getName() + ".lastkit").equals("Coal")) {
	            kits.Coal(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16454 name:&8&lNight_Vision lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16386 name:&8&lSpeed lore:&7SoulBound");
	          } else if (pl.data.getString(p.getName() + ".lastkit").equals("Lapis")) {
	            kits.Lapis(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16427 name:&3&lLeaping lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&3&lSpeed lore:&7SoulBound");
	          } else if (pl.data.getString(p.getName() + ".lastkit").equals("Iron")) {
	            kits.Iron(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	          } else if (pl.data.getString(p.getName() + ".lastkit").equals("Redstone")) {
	            kits.Redstone(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&4&lSpeed lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&4&lWater_Breathing lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
	          } else if (pl.data.getString(p.getName() + ".lastkit").equals("Gold")) {
	            kits.Gold(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&6&lSpeed lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16388 name:&6&lpoison lore:&7SoulBound");
	          } else if (pl.data.getString(p.getName() + ".lastkit").equals("Diamond")) {
	            kits.Diamond(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&3&lWater_Breathing lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&3&lHealing lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16387 name:&3&lFire_resistance lore:&7SoulBound");
	          } else if (pl.data.getString(p.getName() + ".lastkit").equals("Emerald")) {
	            kits.Emerald(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&2&lSpeed lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
	          } else if (pl.data.getString(p.getName() + ".lastkit").equals("KillStreaker")) {
		            kits.KillStreaker(p);
		            p.setMaxHealth(24);
		            p.setHealth(p.getMaxHealth());
		            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&2&lSpeed lore:&7SoulBound");
	          } else if (pl.data.getString(p.getName() + ".lastkit").equals("Speler")) {
	        	  kits.Speler(p);
		            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	          } else {
	            kits.Speler(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	          }
	        }
	        else {
	          if ((pl.playerInGroup("World", e.getPlayer(), "Wood")) || (pl.playerInGroup("World", e.getPlayer(), "cock")) || (pl.playerInGroup("World", e.getPlayer(), "HelperTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "AdminTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Moderator")) || (pl.playerInGroup("World", e.getPlayer(), "Moderator+")) || (pl.playerInGroup("World", e.getPlayer(), "ModeratorTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Admin")) || (pl.playerInGroup("World", e.getPlayer(), "Admin+")) || (pl.playerInGroup("World", e.getPlayer(), "Developer")) || (pl.playerInGroup("World", e.getPlayer(), "Owner"))) {
	            kits.Wood(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&6&lRegeneration lore:&7SoulBound");
	          } else if ((pl.playerInGroup("World", e.getPlayer(), "Coal")) || (pl.playerInGroup("World", e.getPlayer(), "HelperTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "AdminTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Moderator")) || (pl.playerInGroup("World", e.getPlayer(), "Moderator+")) || (pl.playerInGroup("World", e.getPlayer(), "ModeratorTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Admin")) || (pl.playerInGroup("World", e.getPlayer(), "Admin+")) || (pl.playerInGroup("World", e.getPlayer(), "Developer")) || (pl.playerInGroup("World", e.getPlayer(), "Owner"))) {
	            kits.Coal(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16454 name:&8&lNight_Vision lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16386 name:&8&lSpeed lore:&7SoulBound");
	          } else if ((pl.playerInGroup("World", e.getPlayer(), "Lapis")) || (pl.playerInGroup("World", e.getPlayer(), "Moderator")) || (pl.playerInGroup("World", e.getPlayer(), "AdminTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Moderator+")) || (pl.playerInGroup("World", e.getPlayer(), "ModeratorTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Admin")) || (pl.playerInGroup("World", e.getPlayer(), "Admin+")) || (pl.playerInGroup("World", e.getPlayer(), "Developer")) || (pl.playerInGroup("World", e.getPlayer(), "Owner"))) {
	            kits.Lapis(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16427 name:&3&lLeaping lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&3&lSpeed lore:&7SoulBound");
	          } else if ((pl.playerInGroup("World", e.getPlayer(), "Iron")) || (pl.playerInGroup("World", e.getPlayer(), "Moderator+")) || (pl.playerInGroup("World", e.getPlayer(), "AdminTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "ModeratorTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Admin")) || (pl.playerInGroup("World", e.getPlayer(), "Admin+")) || (pl.playerInGroup("World", e.getPlayer(), "Developer")) || (pl.playerInGroup("World", e.getPlayer(), "Owner"))) {
	            kits.Iron(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	          } else if ((pl.playerInGroup("World", e.getPlayer(), "Redstone")) || (pl.playerInGroup("World", e.getPlayer(), "ModeratorTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "AdminTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Admin")) || (pl.playerInGroup("World", e.getPlayer(), "Admin+")) || (pl.playerInGroup("World", e.getPlayer(), "Developer")) || (pl.playerInGroup("World", e.getPlayer(), "Owner"))) {
	            kits.Redstone(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&4&lSpeed lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&4&lWater_Breathing lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16392 name:&4&lWeakness lore:&7SoulBound");
	          }
	          else if ((pl.playerInGroup("World", e.getPlayer(), "Gold")) || (pl.playerInGroup("World", e.getPlayer(), "Admin")) || (pl.playerInGroup("World", e.getPlayer(), "AdminTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Admin+")) || (pl.playerInGroup("World", e.getPlayer(), "Developer")) || (pl.playerInGroup("World", e.getPlayer(), "Owner"))) {
	            kits.Gold(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&6&lSpeed lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16388 name:&6&lpoison lore:&7SoulBound");
	          } else if ((pl.playerInGroup("World", e.getPlayer(), "Diamond")) || (pl.playerInGroup("World", e.getPlayer(), "Admin+")) || (pl.playerInGroup("World", e.getPlayer(), "AdminTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Developer")) || (pl.playerInGroup("World", e.getPlayer(), "Owner"))) {
	            kits.Diamond(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16385 name:&3&lRegeneration lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&3&lWater_Breathing lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&3&lHealing lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16387 name:&3&lFire_resistance lore:&7SoulBound");
	          } else if ((pl.playerInGroup("World", e.getPlayer(), "Emerald")) || (pl.playerInGroup("World", e.getPlayer(), "AdminTeamLeider")) || (pl.playerInGroup("World", e.getPlayer(), "Developer")) || (pl.playerInGroup("World", e.getPlayer(), "Owner"))) {
	            kits.Emerald(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16450 name:&2&lSpeed lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 8270 name:&2&lInvisibilty lore:&7SoulBound");
	          } else {
	            kits.Speler(p);
	            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "give " + p.getName() + " potion 1 16389 name:&4&lHealing lore:&7SoulBound");
	          }

	          p.updateInventory();
	        }
	      }
	    }
	    , 10L);
	    sb.scoreboard(e.getPlayer());
	    e.getPlayer().setHealth(e.getPlayer().getMaxHealth());
	  }
	  
	  @EventHandler
      public void itemSpawn(ItemSpawnEvent e) {
	    ItemStack i = e.getEntity().getItemStack();
	    ItemMeta i2 = i.getItemMeta();
	    if (i2.getLore().contains(ChatColor.GRAY + "SoulBound"))
	      e.setCancelled(true);
	  }
	  
	  @EventHandler
	  public void onleave(PlayerQuitEvent e)
	  {
	    this.Pl.remove(e.getPlayer().getName());
	    Location loc4 = new Location(pl.getServer().getWorld("world"), 149.5D, 165.5D, 457.5D);
	    loc4.setPitch(10.0F);
	    loc4.setYaw(90.0F);
	    e.getPlayer().teleport(loc4);
	    e.getPlayer().setHealth(0);
	    
	  }
	  
	  @EventHandler
	  public void onbreak(BlockBreakEvent e) {
	    if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
	    	e.setCancelled(true);
	    }
	  }
	  
	  @EventHandler
	  public void onPlace(BlockPlaceEvent e) {
		  if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
		    	e.setCancelled(true);
		    }
	  }
	  
	  @EventHandler
	  public void onPlayerDeath(final EntityDamageByEntityEvent event) { //TODO: Cast to an villager
		  if(event.getDamager() instanceof Arrow){
			  new BukkitRunnable() {
				  public void run() {
					  Player p = (Player) event.getEntity();
              ((CraftPlayer)p).getHandle().getDataWatcher().watch(9, (byte) 0);
				  }
			  }.runTaskLater(pl, 50);
          }
	  }

	  @EventHandler
	  public void food(FoodLevelChangeEvent e) {
	    e.setCancelled(true);
	  }
	
}
