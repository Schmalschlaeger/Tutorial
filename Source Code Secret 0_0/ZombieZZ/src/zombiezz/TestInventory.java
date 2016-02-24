package zombiezz;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class TestInventory extends JavaPlugin implements Listener {

	public HashMap<String, Integer> effects = new HashMap<String, Integer>();
	private ArrayList<String> jump = new ArrayList<String>();
	public static Set<String> hidingPlayers = new HashSet<String>();
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		
	}
	

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {	
		
		Player p = e.getPlayer();
		PlayerInventory inv = p.getInventory();
		
		ItemStack head = new ItemStack(Material.NETHER_STAR);
		ItemMeta head1 = head.getItemMeta();
		head1.setDisplayName(ChatColor.AQUA + "[" + ChatColor.GRAY + " Info from the server " + ChatColor.AQUA + "]");
		ArrayList<String> head11 = new ArrayList<String>();
		head11.add(ChatColor.GRAY + "                             ");
		head11.add(ChatColor.GRAY + "Thanks for the builders for the server");
		head11.add(ChatColor.GREEN + "" + ChatColor.BOLD + "_laurens_ | " + ChatColor.GOLD + ChatColor.BOLD + " jusjus112");
		head11.add(ChatColor.GRAY + "Server is made by YOU");
		head11.add(ChatColor.GRAY + "Good luck and heve fun");
		head1.setLore(head11);
		head.setItemMeta(head1);
		
		inv.contains(head);
		inv.setItem(8, head);
		
		ItemStack hider = new ItemStack(Material.WATCH);
		ItemMeta hider1 = hider.getItemMeta();
		hider1.setDisplayName(ChatColor.AQUA + "[" + ChatColor.GRAY + " Player Hider " + ChatColor.AQUA + "]");
		ArrayList<String> hider11 = new ArrayList<String>();
		hider11.add(ChatColor.GRAY + "                             ");
		hider11.add(ChatColor.GRAY + "Right Klick to hide Players!");
		hider11.add(ChatColor.GRAY + "Right Klick again to show the players!");
		hider1.setLore(hider11);
		hider.setItemMeta(hider1);
		
		inv.contains(hider);
		inv.setItem(6, hider);
		
		ItemStack book = new ItemStack(Material.COMPASS);
		ItemMeta item = book.getItemMeta();
		item.setDisplayName(ChatColor.AQUA + "Warp system");
		item.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10, true);
		ArrayList<String> bal = new ArrayList<String>();
		bal.add(ChatColor.GRAY + "                             ");
		bal.add(ChatColor.GRAY + "Right Klick to opens the Warp menu,");
		bal.add(ChatColor.GRAY + "you can see all of the server Warps!");
		item.setLore(bal);
		book.setItemMeta(item);
		
		inv.contains(book);
		inv.setItem(0, book);
		
		ItemStack bone = new ItemStack(Material.BONE);
		ItemMeta bone1 = bone.getItemMeta();
		bone1.setDisplayName(ChatColor.GRAY + "Your Rank");
		ArrayList<String> abone = new ArrayList<String>();
		abone.add(ChatColor.GRAY + "                             ");
		abone.add(ChatColor.GREEN + " bone " + ChatColor.GOLD + " rank 3 "); 
		bone1.setLore(abone);
		bone.setItemMeta(bone1);
			
		ItemStack pis0 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im0 = pis0.getItemMeta();
		im0.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis0.setItemMeta(im0);
		
		ItemStack pis = new ItemStack(Material.IRON_FENCE);
		ItemMeta im = pis.getItemMeta();
		im.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis.setItemMeta(im);
		
		ItemStack pis2 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im2 = pis2.getItemMeta();
		im2.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis2.setItemMeta(im2);
		
		ItemStack pis3 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im3 = pis3.getItemMeta();
		im3.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis3.setItemMeta(im3);
		
		ItemStack pis4 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im4 = pis4.getItemMeta();
		im4.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis4.setItemMeta(im4);
		
		ItemStack pis5 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im5 = pis5.getItemMeta();
		im5.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis5.setItemMeta(im5);
		
		ItemStack pis6 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im6 = pis6.getItemMeta();
		im6.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis6.setItemMeta(im6);
		
		ItemStack pis7 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im7 = pis7.getItemMeta();
		im7.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis7.setItemMeta(im7);
		
		ItemStack pis8 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im8 = pis8.getItemMeta();
		im8.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis8.setItemMeta(im8);
		
		ItemStack pis9 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im9 = pis9.getItemMeta();
		im9.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis9.setItemMeta(im9);
		
		ItemStack pis10 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im10 = pis10.getItemMeta();
		im10.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis10.setItemMeta(im10);
		
		ItemStack pis11 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im11 = pis11.getItemMeta();
		im11.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis11.setItemMeta(im11);
		
		ItemStack secret = new ItemStack(Material.SKULL_ITEM, 1, (byte) 2);
		ItemMeta im12 = secret.getItemMeta();
		im12.setDisplayName(ChatColor.GRAY + "Secret Item [VIP]");
		ArrayList<String> secret12 = new ArrayList<String>();
		secret12.add(ChatColor.GRAY + "                             ");
		secret12.add(ChatColor.GREEN + " This item is very usefull"); 
		secret12.add(ChatColor.GREEN + "                          "); 
		secret12.add(ChatColor.YELLOW+ " Coming Soon"); 
		im12.setLore(secret12);
		secret.setItemMeta(im12);
		
		ItemStack secret2 = new ItemStack(Material.SLIME_BALL, 1);
		ItemMeta im122 = secret2.getItemMeta();
		im122.setDisplayName(ChatColor.GRAY + "Secret Item");
		ArrayList<String> secret122 = new ArrayList<String>();
		secret122.add(ChatColor.GRAY + "                             ");
		secret122.add(ChatColor.GREEN + " This item is very usefull"); 
		secret122.add(ChatColor.GREEN + "                          "); 
		secret122.add(ChatColor.YELLOW+ " Coming Soon"); 
		im122.setLore(secret122);
		secret2.setItemMeta(im122);
		
		ItemStack pis13 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im13 = pis13.getItemMeta();
		im13.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis13.setItemMeta(im13);
		
		ItemStack pis14 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im14 = pis14.getItemMeta();
		im14.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis14.setItemMeta(im14);
		
		ItemStack pis15 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im15 = pis15.getItemMeta();
		im15.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis15.setItemMeta(im15);
		
		ItemStack pis16 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im16 = pis16.getItemMeta();
		im16.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis16.setItemMeta(im16);
		
		ItemStack pis17 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im17 = pis17.getItemMeta();
		im17.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis17.setItemMeta(im17);
		
		ItemStack pis18 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im18 = pis18.getItemMeta();
		im18.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis18.setItemMeta(im18);
		
		ItemStack pis19 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im19 = pis19.getItemMeta();
		im19.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis19.setItemMeta(im19);
		
		ItemStack pis20 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im20 = pis20.getItemMeta();
		im20.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis20.setItemMeta(im20);
		
		ItemStack pis21 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im21 = pis21.getItemMeta();
		im21.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis21.setItemMeta(im21);

		ItemStack pis22 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im22 = pis22.getItemMeta();
		im22.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis22.setItemMeta(im22);
		
		ItemStack pis23 = new ItemStack(Material.IRON_FENCE);
		ItemMeta im23 = pis23.getItemMeta();
		im23.setDisplayName(ChatColor.AQUA + "[" + " " + ChatColor.AQUA + "]");
		pis23.setItemMeta(im23);

		inv.contains(pis0);
		inv.setItem(9, pis0);
		
		inv.contains(pis);
		inv.setItem(10, pis);	
		
		inv.contains(pis2);
		inv.setItem(11, pis2);
		
		inv.contains(pis3);
		inv.setItem(12, pis3);
		
		inv.contains(pis4);
		inv.setItem(13, pis4);
		
		inv.contains(pis5);
		inv.setItem(14, pis5);
		
		inv.contains(pis6);
		inv.setItem(15, pis6);
		
		inv.contains(pis7);
		inv.setItem(16, pis7);
		
		inv.contains(pis8);
		inv.setItem(17, pis8);
		
		inv.contains(pis9);
		inv.setItem(18, pis9);
		
		inv.contains(bone);
		inv.setItem(19, bone);
		
		inv.contains(pis10);
		inv.setItem(23, pis10);
		
		inv.contains(secret2);
		inv.setItem(24, secret2);
		
		//pis11
		
		inv.contains(secret);
		inv.setItem(25, secret);
		
		inv.contains(pis13);
		inv.setItem(26, pis13);
		
		inv.contains(pis14);
		inv.setItem(27, pis14);
		
		inv.contains(pis15);
		inv.setItem(28, pis15);
		
		inv.contains(pis16);
		inv.setItem(29, pis16);
		
		inv.contains(pis17);
		inv.setItem(30, pis17);
		
		inv.contains(pis18);
		inv.setItem(31, pis18);

		inv.contains(pis19);
		inv.setItem(32, pis19);
		
		inv.contains(pis20);
		inv.setItem(33, pis20);
		
		inv.contains(pis21);
		inv.setItem(34, pis21);
		
		inv.contains(pis22);
		inv.setItem(35, pis22);
		
		inv.contains(pis23);
		inv.setItem(36, pis23);
		
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, 2));
		p.getInventory().setBoots(new ItemStack(Material.AIR));
		p.getInventory().setLeggings(new ItemStack(Material.AIR));
		p.getInventory().setChestplate(new ItemStack(Material.AIR));
		p.getInventory().setHelmet(new ItemStack(Material.AIR));
	
			
		}

	
	@EventHandler
	public void onInventoryClick1(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		if (inv.getName().equals(inv.getName())) {
			if (cl.getType() == Material.BONE) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onInventoryClick2(InventoryClickEvent e) {
		ItemStack cl = e.getCurrentItem();
		Inventory inv = e.getInventory();
		if (inv.getName().equals(inv.getName())) {
			if (cl.getType() == Material.IRON_FENCE) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onSlimeClick5(final InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		Location loc = e.getWhoClicked().getLocation();
		ItemStack cl = e.getCurrentItem();
		if (inv.getName().equals(inv.getName())) {
			if (inv.getName().equals(inv.getName())) {
				if (cl.getType() == Material.SLIME_BALL) {
					e.setCancelled(true);
				    player.closeInventory();
				    ParticleEffects.SLIME.display(loc, 0.0f, 1.0f, 0.0f, 0.0F, 50);
				    player.playSound(loc, Sound.SLIME_WALK2, 1, 1);
				    if (!hidingPlayers.contains(player.getName())) {
				        hidingPlayers.add(player.getName());
				        for (Player online : getServer().getOnlinePlayers()) {
				            player.hidePlayer(online);
				        }
				        player.sendMessage(ChatColor.YELLOW + "Players hidden");
				 } else {
				        hidingPlayers.remove(player.getName());
				        for (Player online : getServer().getOnlinePlayers()) {
				            if (!player.canSee(online)) {
				                player.showPlayer(online);
				            }
				        }
				        player.sendMessage(ChatColor.YELLOW + "Players enabled");
				 }
	}
			}
		}
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack cl = e.getItem();
		if (p.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()) {
					if (cl.getType() == Material.WATCH) {
						
					}
    			}
        	}
		}
	}

	
	
	
	@EventHandler
	public void onSkullClick(final InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		if (inv.getName().equals(inv.getName())) {
			if (inv.getName().equals(inv.getName())) {
				if (cl.getType() == Material.SKULL_ITEM) {
					e.setCancelled(true);
				    p.closeInventory();
				if(!jump.contains(e.getWhoClicked().getName())) {
				ParticleEffects.ENCHANTMENT_TABLE.display(e.getWhoClicked().getLocation(), 0.5f, 1.0f, 0.5f, 0.1F, 50);
				p.playSound(e.getWhoClicked().getLocation(), Sound.CLICK, 1, 1);
				Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				
					@Override
					public void run() {
					if (jump.contains(e.getWhoClicked().getName()));
					ParticleEffects.FLAME.display(e.getWhoClicked().getLocation(), 0.5f, 1.0f, 0.5f, 0.1F, 40);
					//ParticleEffects.WITCH_MAGIC.display(loc, offsetX, offsetY, offsetZ, speed, amount);
				
				}				
			}, 20, 3);
				jump.add(e.getWhoClicked().getName());
				}else {
					jump.remove(e.getWhoClicked().getName());
					p.getServer().getScheduler().cancelTasks(this);
				}
	    }
	}
		}
	}
	@EventHandler
	public void onDeath(final PlayerRespawnEvent e){
	new BukkitRunnable() {
	 
	@Override
	public void run() {
	Player p = e.getPlayer();
	if(p != null){
	p.setHealth(20.0);
	p.kickPlayer("§3>> You have died!");
	}
	 
	}
	}.runTaskLater(this, 60L); //Change 30L to anything you want, (This is the time to wait before executing the code, And not in seconds :p)
	}	
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		ItemStack cl = e.getCurrentItem();
		Inventory inv = e.getInventory();
		if (inv.getName().equals(inv.getName())) {
			if (cl.getType() == Material.DIAMOND) {
				e.setCancelled(true);
			}
		}
	}
}
	    
	   

	
		
	