package Pretpark;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Pretpark.MessageManager.MsgType;

public class NetherStar implements Listener {
	
	private Kits plugin;
	 
	public NetherStar(Kits instance) {
	    this.plugin = instance;
	}

	
	public static Inventory hoofd = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "ThemePark Info");
	public static Inventory onderhoud = Bukkit.createInventory(null, 18, ChatColor.AQUA + "" + ChatColor.BOLD + "ThemePark Onderhoud");
	public static Inventory donatie = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "ThemePark Donatie");
	public static Inventory ridemenu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "ThemePark RideMenu");
	
	static { //Nether Menu
		
		ItemStack netherstar = new ItemStack(Material.COMPASS);
		ItemMeta star = netherstar.getItemMeta();
		star.setDisplayName(ChatColor.AQUA + "Click to teleport to your start point!");
		netherstar.setItemMeta(star);
		
		hoofd.setItem(0, netherstar);
		
		ItemStack netherstar1 = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemMeta star1 = netherstar1.getItemMeta();
		star1.setDisplayName(ChatColor.AQUA + "Click to see if the attraction is open/closed");
		netherstar1.setItemMeta(star1);
		
		hoofd.setItem(2, netherstar1);
		
		ItemStack netherstar2 = new ItemStack(Material.DIAMOND);
		ItemMeta star2 = netherstar2.getItemMeta();
		star2.setDisplayName(ChatColor.AQUA + "Click to see the Donation info!");
		netherstar2.setItemMeta(star2);
		
		hoofd.setItem(4, netherstar2);
		
	}
	
	static { //Donatie Menu
		
		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemMeta arroww = arrow.getItemMeta();
		arroww.setDisplayName(ChatColor.AQUA + "Go Back!");
		arrow.setItemMeta(arroww);
		
		donatie.setItem(8, arrow);
		
		ItemStack arrow2 = new ItemStack(Material.RAILS);
		ItemMeta arroww2 = arrow2.getItemMeta();
		arroww2.setDisplayName(ChatColor.AQUA + "VIP Package");
		arrow2.setItemMeta(arroww2);
		
		donatie.setItem(0, arrow2);
		
		ItemStack arrow3 = new ItemStack(Material.ACTIVATOR_RAIL);
		ItemMeta arroww3 = arrow3.getItemMeta();
		arroww3.setDisplayName(ChatColor.AQUA + "VIP" + ChatColor.GOLD + "+" + ChatColor.AQUA + " Package");
		arrow3.setItemMeta(arroww3);
		
		donatie.setItem(1, arrow3);
		
		ItemStack arrow4 = new ItemStack(Material.POWERED_RAIL);
		ItemMeta arroww4 = arrow4.getItemMeta();
		arroww4.setDisplayName(ChatColor.AQUA + "VIP" + ChatColor.GOLD + "++" + ChatColor.AQUA + " Package");
		arrow4.setItemMeta(arroww4);
		
		donatie.setItem(2, arrow4);
	}
	
	static { // Onderhoud menu
		
		ItemStack arrow1 = new ItemStack(Material.STAINED_CLAY);
		ItemMeta arroww1 = arrow1.getItemMeta();
		arroww1.setDisplayName(ChatColor.AQUA + "Go Back!");
		arrow1.setItemMeta(arroww1);
		
		onderhoud.setItem(17, arrow1);
		
		ItemStack arrow2 = new ItemStack(Material.ARROW);
		ItemMeta arroww2 = arrow2.getItemMeta();
		arroww2.setDisplayName(ChatColor.AQUA + "Go Back!");
		arrow2.setItemMeta(arroww2);
		
		onderhoud.setItem(17, arrow2);
		
	}	
	
	@EventHandler
    public void onCompassClick(final InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack cl = e.getCurrentItem();
            if (inv.getName().equals(hoofd.getName())) {
            	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Click to teleport to your start point!")) {
                    if (cl.getType() == Material.COMPASS) {
                    e.setCancelled(true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 160, 20000));
                    player.sendMessage(MsgType.SPAWN + "Teleport you to spawn....");
                    player.sendMessage(MsgType.SPAWN + "Wait an little seconds....");
                    player.closeInventory();
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    	@Override
                    	public void run() {
                    		player.performCommand("spawn");
                            Location loc = e.getWhoClicked().getLocation();
                            player.playSound(loc, Sound.LEVEL_UP, 1, 1);
                            ParticleEffect.EXPLODE.display(loc, 0.0f, 1.0f, 0.0f, 0.3F, 50);
                            ParticleEffect.CLOUD.display(loc, 0.0f, 1.0f, 0.0f, 0.3F, 50);
                            ParticleEffect.PORTAL.display(loc, 0.0f, 1.0f, 0.0f, 0.5F, 50);
                            player.sendMessage(MsgType.SPAWN + "Teleported!");
                    	}
                    }, 4 * 20L);
                }
            }
        }
	}
	
	@EventHandler
	public void onrailsClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		Location loc = e.getWhoClicked().getLocation();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(donatie.getName())) {
				if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "VIP Package") 
						|| e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "VIP" + ChatColor.GOLD + "+" + ChatColor.AQUA + " Package") 
						|| e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "VIP" + ChatColor.GOLD + "++" + ChatColor.AQUA + " Package")) {
	     			if (cl.getType() == Material.RAILS || cl.getType() == Material.POWERED_RAIL || cl.getType() == Material.ACTIVATOR_RAIL) {
					e.setCancelled(true);
				    player.closeInventory();
				    ParticleEffect.ENCHANTMENT_TABLE.display(loc, 0.0f, 1.0f, 0.0f, 1.0F, 50);
				    player.playSound(loc, Sound.ITEM_PICKUP, 1, 1);
				    player.sendMessage(ChatColor.GOLD + "--------- " + MsgType.NORMAL + "" + ChatColor.GOLD + "---------------");
				    player.sendMessage(ChatColor.DARK_AQUA + "Click the link to Donate!");
				    player.sendMessage(ChatColor.DARK_AQUA + "http://themeparkmc.buycraft.net");
				    player.sendMessage(ChatColor.GOLD + "------------------------------------");
				}
			}
		}
	}
	
	@EventHandler
	public void onarrowClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		Location loc = e.getWhoClicked().getLocation();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(hoofd.getName()) || inv.getName().equals(donatie.getName()) || inv.getName().equals(ridemenu.getName()) || inv.getName().equals(onderhoud.getName())) {
				if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Go Back!")) {
			    	if (cl.getType() == Material.ARROW) {
					e.setCancelled(true);
				    player.closeInventory();
				    player.playSound(loc, Sound.WOOD_CLICK, 1, 1);
				    player.openInventory(hoofd);
				}
			}
		}
	}
	
	@EventHandler
	public void onTorchClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		Location loc = e.getWhoClicked().getLocation();
		ItemStack cl = e.getCurrentItem();
		if (inv.getName().equals(hoofd.getName())) {
			if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Click to see if the attraction is open/closed")) {
				if (cl.getType() == Material.REDSTONE_TORCH_ON) {
					e.setCancelled(true);
				    player.closeInventory();
				    player.playSound(loc, Sound.WOOD_CLICK, 1, 1);
				    player.openInventory(onderhoud);
				}
			}
		}
	}
	
	
	
	@EventHandler
	public void ondiaClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		Location loc = e.getWhoClicked().getLocation();
		ItemStack cl = e.getCurrentItem();
		if (inv.getName().equals(hoofd.getName())) {
			if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Click to see the Donation info!")) {
				if (cl.getType() == Material.DIAMOND) {
					e.setCancelled(true);
				    player.closeInventory();
				    player.playSound(loc, Sound.WOOD_CLICK, 1, 1);
				    player.openInventory(donatie);
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
					if (cl.getType() == Material.NETHER_STAR) {
						p.openInventory(hoofd);
					}
    			}
        	}
		}
	}

}
