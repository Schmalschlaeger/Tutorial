package gc;

import java.util.ArrayList;

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
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("deprecation")
public class Menu implements Listener{
	
	private Main plugin;
	 
	public Menu(Main instance) {
	    this.plugin = instance;
	}
	
	public static Inventory fen = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "FeneticMenu");
	 public ArrayList<Player> trail = new ArrayList<Player>();
	 
	 private int id;
	
	static { 		
		ItemStack chest2 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "TeamSpeak Info");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GOLD + "TeamSpeak-Staff");
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "[Founder]:");
		iron2.add(ChatColor.GRAY + "Monster_Killer_4 (Tim)");
		iron2.add(ChatColor.GRAY + "Cloudbreaker01 (Maartje)");
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "[Admin]:");
		iron2.add(ChatColor.GRAY + "-");
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "[Mod]:");
		iron2.add(ChatColor.GRAY + "sweeptail (Stan)");
		iron2.add(ChatColor.GRAY + "ShockElevation (Eloy)");
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "---[Developer]---");
		iron2.add(ChatColor.GRAY + "skillerfox3 (Skip)");
		iron2.add(ChatColor.GRAY + "Droeskiller1 (Dries)");
		iron2.add(ChatColor.GRAY + "");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
		
		fen.contains(chest2);
		fen.setItem(0, chest2);
		
		ItemStack chest21 = new ItemStack(Material.BOW);
		ItemMeta plate21 = chest21.getItemMeta();
		plate21.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "FeneticGames");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.GRAY + "Join our games!");
		plate21.setLore(iron21);
		chest21.setItemMeta(plate21);
		
		fen.contains(chest21);
		fen.setItem(2, chest21);
		
		ItemStack chest214 = new ItemStack(Material.FEATHER);
		ItemMeta plate214 = chest214.getItemMeta();
		plate214.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Sparticle");
		ArrayList<String> iron214 = new ArrayList<String>();
		iron214.add(ChatColor.GRAY + "");
		iron214.add(ChatColor.GRAY + "Get a special particle effect!");
		plate214.setLore(iron214);
		chest214.setItemMeta(plate214);
		
		fen.contains(chest214);
		fen.setItem(4, chest214);
		
		ItemStack chest2111111 = new ItemStack(Material.IRON_SWORD);
		ItemMeta plate2111111 = chest2111111.getItemMeta();
		plate2111111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "AnnihilationGames");
		ArrayList<String> iron2111111 = new ArrayList<String>();
		iron2111111.add(ChatColor.GRAY + "");
		iron2111111.add(ChatColor.GRAY + "Play a Anniliation game!");
		plate2111111.setLore(iron2111111);
		chest2111111.setItemMeta(plate2111111);
		
		fen.contains(chest2111111);
		fen.setItem(6, chest2111111);
		
		ItemStack chest21111111 = new ItemStack(Material.getMaterial(383), 1, (byte) 54);
		ItemMeta plate21111111 = chest21111111.getItemMeta();
		plate21111111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ZombieGames");
		ArrayList<String> iron21111111 = new ArrayList<String>();
		iron21111111.add(ChatColor.GRAY + "");
		iron21111111.add(ChatColor.GRAY + "Join our zombie games!");
		plate21111111.setLore(iron21111111);
		chest21111111.setItemMeta(plate21111111);
		
		fen.contains(chest21111111);
		fen.setItem(8, chest21111111);
	}
	
	@EventHandler
	public void joining(PlayerJoinEvent e) {
		ItemStack clock = new ItemStack(Material.MAGMA_CREAM);
		ItemMeta head1 = clock.getItemMeta();
		head1.setDisplayName(ChatColor.GOLD + "FeneticMenu!");
		clock.setItemMeta(head1);
		clock.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		
		if (!e.getPlayer().getInventory().contains(clock)) {
		e.getPlayer().getInventory().addItem(clock);
		}
	}
	
	@EventHandler
	public void onSlimeClick5(PlayerInteractEvent e) {
		final Player player = (Player) e.getPlayer();
		Inventory inv = player.getInventory();
		ItemStack cl = player.getItemInHand();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (inv.getName().equals(inv.getName())) {
				if (cl.getType() == Material.MAGMA_CREAM) {
					player.openInventory(fen);
					}
				}
			}
	   }  
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Bukkit.getServer().getScheduler().cancelTask(id);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.teleport(p.getWorld().getSpawnLocation());
		ParticleEffect.CLOUD.display(p.getLocation(), 0.3f, 0.5f, 0.2f, 0.1F, 70);
		ParticleEffect.FIREWORKS_SPARK.display(p.getLocation(), 0.3f, 0.5f, 0.2f, 0.1F, 70);
		ParticleEffect.RED_DUST.display(p.getLocation(), 0.3f, 0.5f, 0.2f, 0.1F, 70);
		p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
	}
	
	@EventHandler
	public void onChestClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		final Player p = (Player) e.getWhoClicked();
			if (inv.getName().equals(fen.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "TeamSpeak Info")) {
			    	if (cl.getType() == Material.ENCHANTED_BOOK) {
					e.setCancelled(true);
					Location loc = new Location(p.getWorld(), -268, 6, -289);
		            p.teleport(loc);
			    	}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "FeneticGames")) {
			 		if (cl.getType() == Material.BOW) {
						e.setCancelled(true);
						Location loc = new Location(p.getWorld(), -284, 6, -289);
			            p.teleport(loc);
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Sparticle")) {
			 		if (cl.getType() == Material.FEATHER) {
						e.setCancelled(true);
						p.closeInventory();
						if (!trail.contains(p)) {
							id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
								@Override
								public void run() {
									ParticleEffect.HEART.display(p.getLocation(), 0.2f, 0.5f, 0.2f, 0.1F, 70);
								}
							}, 10, 10);
							
				            p.sendMessage(ChatColor.GOLD + "Your trail has been Enabled");
				            trail.add(p);
						}else {
							Bukkit.getServer().getScheduler().cancelTask(id);
							p.sendMessage(ChatColor.GOLD + "Your trail has been disabled");
							trail.remove(p);
							return;
						}
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "AnnihilationGames")) {
			 		if (cl.getType() == Material.IRON_SWORD) {
						e.setCancelled(true);
						Location loc = new Location(p.getWorld(), -276 ,6 ,-281);
			            p.teleport(loc);
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "ZombieGames")) {
			 		if (cl.getType() == Material.getMaterial(383)) {
						e.setCancelled(true);
						Location loc = new Location(p.getWorld(), -276 ,6 ,-297, 2, -179);
			            p.teleport(loc);
			 		}
			 	}

			}
	   }

}
