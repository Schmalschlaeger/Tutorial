package Pretpark;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener{
	
	private Kits plugin;
	 
	public Events(Kits instance) {
	    this.plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		Inventory inv = p.getInventory();
		
		ItemStack netherstar = new ItemStack(Material.NETHER_STAR);
		ItemMeta star = netherstar.getItemMeta();
		star.setDisplayName(ChatColor.AQUA + "Theme" + ChatColor.DARK_AQUA + "Park Menu");
		netherstar.setItemMeta(star);
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);		
		BookMeta bm = (BookMeta) book.getItemMeta();
		bm.setTitle(ChatColor.BLUE + "" + ChatColor.BOLD + "Park Info");
		bm.setAuthor(ChatColor.AQUA + "Theme" + ChatColor.DARK_AQUA + "Park");
		bm.addPage(
				ChatColor.GRAY + "ThemePark is een Pretpark server met een gezellige sfeer, " +
				ChatColor.GRAY + "awesome plugins en coole spelers. We hebben Leuke, mooigebouwde attracties, met effecten en geluiden. Genoeg om je de hele dag te vermaken. Kom ook een keer langs!"
				);
		bm.addPage(ChatColor.BLUE + "Volg ons op twitter:"
				+ "  twitter.com/InfoThemepark");
		book.setItemMeta(bm);
		
		inv.contains(netherstar);
		inv.setItem(1, netherstar);
		
		inv.contains(book);
		inv.setItem(0, book);
		
		ParticleEffect.ENCHANTMENT_TABLE.display(loc, 0.0f, 1.0f, 0.0f, 1.5F, 60);
		ParticleEffect.EXPLODE.display(loc, 0.0f, 1.0f, 0.0f, 1.7F, 50);
		ParticleEffect.FIREWORKS_SPARK.display(loc, 0.0f, 1.0f, 0.0f, 1.5F, 50);
		ParticleEffect.CLOUD.display(loc, 0.0f, 1.0f, 0.0f, 1.5F, 50);
		p.playSound(loc, Sound.ENDERDRAGON_GROWL, 1, 1);
		
	}
	
	@EventHandler
    public void onPlayerJoinFireWork(final PlayerJoinEvent e) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run() {
                            if (e.getPlayer().hasPlayedBefore()) return;
                           
                            Firework f = (Firework) e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
                           
                            FireworkMeta fm = f.getFireworkMeta();
                            fm.addEffect(FireworkEffect.builder()
                                            .flicker(false)
                                            .trail(true)
                                            .with(Type.CREEPER)
                                            .withColor(Color.GREEN)
                                            .withFade(Color.BLUE)
                                            .build());
                            fm.setPower(3);
                            f.setFireworkMeta(fm);
                    }
            }, 20);
    }
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		e.getDrops().clear();
	}
	
	@EventHandler
	public void onPlayerLoseHunger(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {		
		
	}		
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(null);
		e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
	}	
	
}
