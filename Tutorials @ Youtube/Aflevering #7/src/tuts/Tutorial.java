package tuts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Tutorial extends JavaPlugin implements Listener {
    
    public void onEnable() {
            Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
   
    @EventHandler
    public void onPlayerDmagge(EntityDamageByEntityEvent e) {
            Player damager = (Player) e.getDamager();
            Player hurt = (Player) e.getEntity();
            if (hurt instanceof Player) {
                    if (damager instanceof Player) {
                            e.setCancelled(true);
                            damager.hidePlayer(hurt);
                            damager.playSound(damager.getLocation(), Sound.LEVEL_UP, 1, 1);
                            damager.playEffect(damager.getLocation(), Effect.STEP_SOUND, Material.ANVIL);
                            damager.sendMessage(ChatColor.GOLD + "You popped " + hurt.getName());
                    }
            }
    }
   
}