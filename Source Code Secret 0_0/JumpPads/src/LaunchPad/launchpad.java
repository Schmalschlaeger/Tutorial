package LaunchPad;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class launchpad extends JavaPlugin implements Listener {
	 
    private ArrayList<Player> jumpers = new ArrayList<Player>();
    
	FileConfiguration config = null;

    public void onEnable() {
            Bukkit.getServer().getPluginManager().registerEvents(this, this);
            
    		config =  getConfig();
        	getConfig().options().copyDefaults(true);
            saveDefaultConfig();
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
    	boolean enablePad1 = getConfig().getBoolean("Jumppad1.Enable");
    	boolean enablePad2 = getConfig().getBoolean("Jumppad2.Enable");
    	Material matPad1 = Material.getMaterial(getConfig().getInt("Jumppad1.Material"));
    	Material matPad2 = Material.getMaterial(getConfig().getInt("Jumppad2.Material"));
    	int height1 = getConfig().getInt("Jumppad1.Height");
    	int height2 = getConfig().getInt("Jumppad2.Height");
    	int velocity1 = getConfig().getInt("Jumppad1.Velocity");
    	int velocity2 = getConfig().getInt("Jumppad2.Velocity");    	
    	
    	if (enablePad1 == true) {
    	if (e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == matPad1) {
                    e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(height1));
                    e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), velocity1, e.getPlayer().getVelocity().getZ()));
                    jumpers.add(e.getPlayer());
        	}
    	}else if (enablePad2 == true) {
                	 if (e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == matPad2) {
                        e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(height2));
                        e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), velocity2, e.getPlayer().getVelocity().getZ()));
                        jumpers.add(e.getPlayer());
             }
    	}
    }
    
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
            if (e.getEntity() instanceof Player) {
                    Player p = (Player) e.getEntity();
                    if (e.getCause() == DamageCause.FALL && jumpers.contains(p)) {
                            e.setDamage(0.0);
                            jumpers.remove(p);
                    }
            }
      }
}

