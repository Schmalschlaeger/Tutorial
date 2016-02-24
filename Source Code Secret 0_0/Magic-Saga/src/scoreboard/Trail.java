package scoreboard;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.slikey.effectlib.effect.ConeLocationEffect;
import de.slikey.effectlib.effect.ShieldEntityEffect;
import de.slikey.effectlib.effect.WarpEntityEffect;

public class Trail implements Listener {
	
	private Main plugin;
	private ArrayList<String> shooter = new ArrayList<String>();
	 
	public Trail(Main instance) {
	    this.plugin = instance;
	}
	
	public int test;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		final Player p  = e.getPlayer();
		Location eyeLoc = p.getEyeLocation();
		//Block blok = e.getClickedBlock();
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (e.getPlayer().getItemInHand().equals(new ItemStack(Material.IRON_HOE))) {
				if (!shooter.contains(p.getName())) {
				ConeLocationEffect  bleedEffect = new ConeLocationEffect  (plugin.effectManager, eyeLoc);
		        // Add a callback to the effect
		        bleedEffect.callback = new Runnable() {
		            @Override
		            public void run() {
		               shooter.remove(p.getName());
		            }
		            
		        };
		        // Blood-particles lays around for 30 ticks (1.5 seconds)
		        // Bleeding takes 15 seconds
		        // period * iterations = time of effect
		        bleedEffect.iterations = 18;
		        
		        bleedEffect.start();
		        shooter.add(p.getName());
		        
		        
		        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
		        	if (all.getLocation().distanceSquared(p.getEyeLocation()) >= 5) {
		        	all.setHealth(1.0);	
		        		}
		        	}		        		
 		        }
		        
			}else if (e.getPlayer().getItemInHand().equals(new ItemStack(Material.STONE_HOE))) {
				WarpEntityEffect   bleedEffect = new WarpEntityEffect  (plugin.effectManager, p);
		        // Add a callback to the effect
		        bleedEffect.callback = new Runnable() {

		            @Override
		            public void run() {
		               
		            }
		            
		        };
		        // Blood-particles lays around for 30 ticks (1.5 seconds)
		        // Bleeding takes 15 seconds
		        // period * iterations = time of effect
		        bleedEffect.iterations = 20;
		        bleedEffect.start();
			}else if (e.getPlayer().getItemInHand().equals(new ItemStack(Material.GOLD_HOE))) {
				ShieldEntityEffect    bleedEffect = new ShieldEntityEffect   (plugin.effectManager, p);
		        // Add a callback to the effect
		        bleedEffect.callback = new Runnable() {

		            @Override
		            public void run() {
		               
		            }
		            
		        };
		        // Blood-particles lays around for 30 ticks (1.5 seconds)
		        // Bleeding takes 15 seconds
		        // period * iterations = time of effect
		        bleedEffect.delay = 5;
		        bleedEffect.iterations = 30;
		        bleedEffect.start();
			}
		}
	}

}
