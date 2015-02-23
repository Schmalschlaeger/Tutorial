package tuts;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Tutorial extends JavaPlugin implements Listener {
       
        public void onEnable() {
                Bukkit.getServer().getPluginManager().registerEvents(this, this);      
               
                getConfig().options().copyDefaults(true);
                saveDefaultConfig();
        }
       
        public void onDisable() {
                getConfig().options().copyDefaults(true);
                saveConfig();
        }
       
        @EventHandler
        public void blockBreaker(BlockBreakEvent e) {
                Player p = e.getPlayer();
               
                if (getConfig().getString("players." + p.getName()) == null) {
                        getConfig().addDefault("players." + p.getName(), 1);
                        saveConfig();
                }else {
                        getConfig().set("players." + p.getName(), getConfig().getInt("players." + p.getName()) +1);
                        saveConfig();
                }
        }
       
}